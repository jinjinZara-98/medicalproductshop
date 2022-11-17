package capstonedesign.medicalproduct.controller;

import capstonedesign.medicalproduct.Login.argumentresolver.Login;
import capstonedesign.medicalproduct.domain.ItemSearch;
import capstonedesign.medicalproduct.domain.entity.Member;
import capstonedesign.medicalproduct.dto.ItemDto;
import capstonedesign.medicalproduct.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final ItemService itemService;

    @GetMapping("/")
    public String home(@Login Member loginMember,
                       @ModelAttribute("itemSearch") ItemSearch itemSearch, Model model,
                       @RequestParam(required = false, defaultValue = "0", value ="page") int page) {

        //검색어와 page를 파라미터로 넣어 결과 가져옴
        Page<ItemDto> listPage = itemService.findNameSearch(itemSearch, page);

        List<ItemDto> result = listPage.getContent();//실제 상품 데이터
        int totalPage = listPage.getTotalPages(); // 총 페이지 수

        log.info("home controller");

        //세션에 회원 데이터가 없으면 home
        if (loginMember == null) {

            //검색 조건을 넣어, 검색 조건 값이 비어있다면 모든 상품 갖고오는
            model.addAttribute("items",  result);

            //totalPage는 총 페이지 수 반환, 100개 데이터 10개씩 쪼개지면 10페이지 반환되는
            model.addAttribute("totalPage", totalPage);
            return "home";
        }

        //세션에 회원 데이터가 있으면 loginhome
        model.addAttribute("items",  result);
        model.addAttribute("totalPage", totalPage);

        return "loginhome";
    }
}
