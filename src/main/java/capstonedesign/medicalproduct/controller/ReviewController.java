package capstonedesign.medicalproduct.controller;

import capstonedesign.medicalproduct.Login.SessionConst;
import capstonedesign.medicalproduct.domain.FileStore;
import capstonedesign.medicalproduct.domain.Uploadfile;
import capstonedesign.medicalproduct.domain.entity.Item;
import capstonedesign.medicalproduct.domain.entity.Member;
import capstonedesign.medicalproduct.domain.entity.Review;
import capstonedesign.medicalproduct.dto.ReviewDto;
import capstonedesign.medicalproduct.dto.ReviewRegisterForm;
import capstonedesign.medicalproduct.repository.ReviewRepository;
import capstonedesign.medicalproduct.service.ItemService;
import capstonedesign.medicalproduct.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ReviewController {

    private final ItemService itemService;
    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;
    private final FileStore fileStore;

    //주문 상품 리스트에서 후기 작성 버튼 누르면
    @GetMapping("review/item/{id}")
    public String itemReviewWrite(@PathVariable("id") long itemId, Model model){

        //경로에 있는 상품 아이디로 상품을 찾고
        Item item = itemService.findOne(itemId);

        ReviewRegisterForm reviewRegisterForm = new ReviewRegisterForm();

        //리뷰Dto에 상품의 정보 세팅
        reviewRegisterForm.setItemId(item.getId()); reviewRegisterForm.setItemName(item.getName());
        reviewRegisterForm.setItemImageSrc(item.getImageSrc());

        model.addAttribute("reviewRegisterForm", reviewRegisterForm);

        return "reviews/reviewRegister";
    }

    //후기 작성 페이지에서 등록하기 버튼 누르면
    @PostMapping("review/register")
    public String itemReviewRegister(@Valid @ModelAttribute("reviewRegisterForm") ReviewRegisterForm reviewRegisterForm,
                                     BindingResult bindingResult, HttpServletRequest request) throws IOException {

        //후기 작성 폼 값에 에러가 있다면 다시 후기 작성 페이지로 리다이렉트
        if(bindingResult.hasErrors()) {

            return "reviews/reviewRegister";
        }
        if(reviewRegisterForm.getImageFile() == null) {

            //글로벌오류
            bindingResult.reject("imageFileIsNull", "후기 사진을 첨부해주세요!");

            return "reviews/reviewRegister";
        }

        //후기 작성 폼 값에 에러가 없다면
        //세션에 있는 회원 객체, 상품 아이디, 후기 제목, 후기 내용을 후기 등록 메서드 파라미터에 넣어줌
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        Uploadfile uploadfile = fileStore.storeFile(reviewRegisterForm.getImageFile());

        reviewService.reviewRegister(member.getId(), reviewRegisterForm.getItemId(),
                                    reviewRegisterForm.getTitle(), reviewRegisterForm.getContent(), uploadfile);

        return "redirect:/";
    }

    //로그인한 회원이 작성한 후기 리스트 갖고오기
    @GetMapping("review/reviewList")
    public String reviewList(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        List<ReviewDto> reviewList = reviewService.reviewList(member.getId());

        System.out.println(reviewList);

        model.addAttribute("reviewList", reviewList);

        return "reviews/reviewList";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {

        //서버에 저장될 파일이름인 uuid를 넣어 파일의 전체경로를 주면 UrlResource가 그 경로 찾아옴
        //이 경로에 있는 파일에 접근해서 스트림으로 반환하게 됨
        //UrlResource 는 경로에 file 이 붙으면 내부 파일에 접근함, 직접 접근해 자원 갖고와
        //url 요청으로 업로드 파일명이 오면 전체 경로 만들어 파일에 접근해서 바이너리 데이터를 웹브라우저로 전송
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }

    //items/{id} 로 업로드한 파일 조회, 화면에서 첨부파일 링크 클릭하면 파일 다운로드 되도록
    //@ResponseBody 안쓰고 ResponseEntity<Resource> 이거 씀
    @GetMapping("/attach/{reviewId}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable Long reviewId) throws MalformedURLException {
        //아이템을 접근할 수 있는 사용자만 파일을 다운받게 하는
        Review review = reviewRepository.findById(reviewId).get();

        //컴퓨터에 저장된 파일명
        String storeFileName = review.getStoreFileName();

        //사용자가 파일을 다운받을때 다운받은 파일명이 사용자가 업로드한 파일명으로 하게
        String uploadFileName = review.getUploadFileName();

        //업로드 된거 즉 컴퓨터에 저장된거를 가져오는거니 업로드 된 파일명을 파라미터로 주어 파일 있는 전체 경로를
        //file 뒤에 붙이고, UrlResource 는 경로에 file 이 붙으면 내부 파일에 접근
        //UrlResource 객체에 파일 들어있다
        UrlResource resource = new UrlResource("file:" + fileStore.getFullPath(storeFileName));

        log.info("uploadFileName={}", uploadFileName);

        //한글, 특수문자 깨질 수 있기 때문에 인코딩
        String encodedUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);

        //다운받는 파일명 지정
        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

        //다운을 받기 위해 추가적인 헤더를 넣어줘야함
        //ResponseEntity.ok().body(resource)만 하면 홈페이지 바디에 파일 내용만 출력됨
        //브라우저가 HttpHeaders.CONTENT_DISPOSITION 이거 보고 첨부파일 인식
        return ResponseEntity.ok()
                //파일 다운로드 받는 헤더와 다운로드 파일명 넣기
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }

    //후기 리스트에서 후기 중 삭제 버튼을 눌렀을때
    @PostMapping("/review/{id}/cancel")
    public String reviewCancel(@PathVariable("id") long reviewId) {

        reviewService.reviewCancel(reviewId);

        return "redirect:/review/reviewList";
    }
}