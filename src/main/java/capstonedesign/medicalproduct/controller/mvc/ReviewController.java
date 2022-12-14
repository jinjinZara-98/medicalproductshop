package capstonedesign.medicalproduct.controller.mvc;

import capstonedesign.medicalproduct.Login.SessionConst;
import capstonedesign.medicalproduct.domain.Uploadfile;
import capstonedesign.medicalproduct.domain.entity.Item;
import capstonedesign.medicalproduct.domain.entity.Member;
import capstonedesign.medicalproduct.domain.entity.Review;
import capstonedesign.medicalproduct.dto.mvc.ReviewDto;
import capstonedesign.medicalproduct.dto.mvc.ReviewRegisterForm;
import capstonedesign.medicalproduct.repository.ReviewRepository;
import capstonedesign.medicalproduct.service.AwsS3Service;
import capstonedesign.medicalproduct.service.ItemService;
import capstonedesign.medicalproduct.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ReviewController {

    private final ItemService itemService;
    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;
    private final AwsS3Service awsS3Service;

    //주문 상품 리스트에서 후기 작성 버튼 누르면
    @GetMapping("review/item/{id}")
    public String itemReviewWrite(@PathVariable("id") long itemId, Model model){

        //경로에 있는 상품 아이디로 상품을 찾고
        Item item = itemService.findById(itemId);

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

        Uploadfile uploadfile = awsS3Service.uploadFile(reviewRegisterForm.getImageFile());

        reviewService.save(member.getId(), reviewRegisterForm.getItemId(),
                                    reviewRegisterForm.getTitle(), reviewRegisterForm.getContent(), uploadfile);

        return "redirect:/";
    }

    //로그인한 회원이 작성한 후기 리스트 갖고오기
    @GetMapping("review/reviewList")
    public String reviewList(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        List<ReviewDto> reviewList = reviewService.findAllByMemberId(member.getId());

        //가져온 후기 정보 리스트에서 업로드한 이미지 파일 이름을 넣어 이미지 접근 경로 생성
        //새로운 후기 정보 리스트 생성해 이미지 접근 경로로 세팅
        List<ReviewDto> newReviewList = new ArrayList<>();
        for (ReviewDto reviewDto : reviewList) {
            reviewDto.setStoreFileName(awsS3Service.getThumbnailPath(reviewDto.getStoreFileName()));
            newReviewList.add(reviewDto);
        }

        model.addAttribute("reviewList",newReviewList);

        return "reviews/reviewList";
    }

    //items/{id} 로 업로드한 파일 조회, 화면에서 첨부파일 링크 클릭하면 파일 다운로드 되도록
    //@ResponseBody 안쓰고 ResponseEntity<Resource> 이거 씀
    @GetMapping("attach/{reviewId}")
    public ResponseEntity<byte[]> downloadAttach(@PathVariable Long reviewId) throws IOException {
        //아이템을 접근할 수 있는 사용자만 파일을 다운받게 하는
        Review review = reviewRepository.findById(reviewId).get();

        //컴퓨터에 저장된 파일명
        String storeFileName = review.getStoreFileName();

        //사용자가 파일을 다운받을때 다운받은 파일명이 사용자가 업로드한 파일명으로 하게
        String uploadFileName = review.getUploadFileName();

        return awsS3Service.getObject(storeFileName, uploadFileName);
    }

    //후기 리스트에서 후기 중 삭제 버튼을 눌렀을때
    @PostMapping("review/{id}/cancel")
    public String reviewCancel(@PathVariable("id") long reviewId) {

        //s3 스토리지에서 이미지 파일 삭제
        Review review = reviewRepository.findById(reviewId).get();
        String storeFileName = review.getStoreFileName();
        awsS3Service.deleteFile(storeFileName);

        //후기 삭제
        reviewService.delete(reviewId);

        return "redirect:/review/reviewList";
    }
}