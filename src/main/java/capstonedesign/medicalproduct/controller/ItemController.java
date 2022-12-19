package capstonedesign.medicalproduct.controller;

import capstonedesign.medicalproduct.Login.argumentresolver.Login;
import capstonedesign.medicalproduct.domain.entity.Item;
import capstonedesign.medicalproduct.domain.ItemSearch;
import capstonedesign.medicalproduct.domain.entity.Member;
import capstonedesign.medicalproduct.domain.entity.Review;
import capstonedesign.medicalproduct.dto.ItemDetailDto;
import capstonedesign.medicalproduct.dto.ItemDto;
import capstonedesign.medicalproduct.dto.ReviewDto;
import capstonedesign.medicalproduct.service.AwsS3Service;
import capstonedesign.medicalproduct.service.ItemService;
import capstonedesign.medicalproduct.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ItemController {

    private final AwsS3Service awsS3Service;
    private final ItemService itemService;
    private final ReviewService reviewService;

    //상품 상세
    @GetMapping("item/{itemId}")
    public String itemDetail(@Login Member loginMember, @PathVariable("itemId") Long itemId,
                             @ModelAttribute("itemSearch") ItemSearch itemSearch, Model model){

        //홈 화면에서 누른 아이템의 id 얻어와 해당 상품 반환
        Item item = itemService.findOne(itemId);

        //db에서 갖고온 상품의 id, 이름, 가격, 상품설명을 ItemDetailDto에 세팅
        ItemDetailDto itemDetailDto = new ItemDetailDto();

        itemDetailDto.setId(item.getId()); itemDetailDto.setName(item.getName()); itemDetailDto.setQuantity(1);
        itemDetailDto.setImageSrc(item.getImageSrc()); itemDetailDto.setPrice(item.getPrice());
        itemDetailDto.setTotalPrice(item.getPrice()); itemDetailDto.setIntroduction(item.getIntroduction());
        itemDetailDto.setRate(item.getRate());

        List<ReviewDto> reviewList = reviewService.oneItemReviews(itemId);

        List<ReviewDto> newReviewList = new ArrayList<>();

        for (ReviewDto reviewDto : reviewList) {
            reviewDto.setStoreFileName(awsS3Service.getThumbnailPath(reviewDto.getStoreFileName()));
            newReviewList.add(reviewDto);
        }

        //로그인하지 않았다면
        if (loginMember == null) {

            //값 세팅한 itemDetailDto를 model에 담아줌
            model.addAttribute("itemDetailDto", itemDetailDto);
            model.addAttribute("reviewList", newReviewList);

            return "item/itemDetail";
        }

        //로그인 했다면
        model.addAttribute("itemDetailDto", itemDetailDto);
        model.addAttribute("reviewList", newReviewList);

        return "item/loginItemDetail";
    }

    //클릭한 카테고리에 속한 상품 보여주기
    @GetMapping("items/{category}")
    public String categorySearch(@Login Member loginMember, @PathVariable("category") String category,
                                 @ModelAttribute("itemSearch") ItemSearch itemSearch, Model model,
                                 @RequestParam(required = false, defaultValue = "0", value ="page") int page){

        //카테고리이름과, 페이지값을 파라미터로 전달해 값을 얻어옴
        Page<ItemDto> listPage = itemService.findCategorySearch(category, page);

        int totalPage = listPage.getTotalPages(); // 총 페이지 수

        log.info("home controller");

        //세션에 회원 데이터가 없으면 home
        if (loginMember == null) {

            //getContent() 실제 데이터 갖고오는
            model.addAttribute("items",  listPage.getContent());

            //totalPage는 총 페이지 수 반환, 100개 데이터 10개씩 쪼개지면 10페이지 반환되는
            model.addAttribute("totalPage", totalPage);

            return "home";
        }

        //세션에 회원 데이터가 있으면 loginhome
        model.addAttribute("items",  listPage.getContent());
        model.addAttribute("totalPage", totalPage);

        return "loginhome";
    }
}