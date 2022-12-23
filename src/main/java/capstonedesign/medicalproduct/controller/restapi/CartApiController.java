package capstonedesign.medicalproduct.controller.restapi;

import capstonedesign.medicalproduct.Login.SessionConst;
import capstonedesign.medicalproduct.domain.entity.Cart;
import capstonedesign.medicalproduct.domain.entity.Member;
import capstonedesign.medicalproduct.dto.api.CartAPIDto;
import capstonedesign.medicalproduct.repository.CartRepository;
import capstonedesign.medicalproduct.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static java.util.stream.Collectors.toList;

//json 통신, N+1 문제 확인하기 위해
@RestController
@RequiredArgsConstructor
public class CartApiController {

    private final CartService cartService;
    private final CartRepository cartRepository;

    /** 장바구니에 상품 추가 */
    @PostMapping("/api/carts/{quantity}/items/{itemId}")
    public CartAPIDto putItemToCart(@PathVariable("quantity") int quantity, @PathVariable("itemId") long itemId ,
                                    HttpServletRequest request) {

        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        long cartId = cartService.save(member.getId(), itemId, quantity);

        Cart cart = cartRepository.findById(cartId).get();

        CartAPIDto cartAPIDto = new CartAPIDto(cart);

        //추가한 장바구니 상품 반환
        return cartAPIDto;
    }

    /** 모든 장바구니 상품 조회 */
    @GetMapping("/api/carts")
    public List<CartAPIDto> findCarts(){

        List<Cart> carts = cartRepository.fetchJoinCartFindAll();

        List<CartAPIDto> result = carts.stream()
                //new SimpleOrderDto(o)) 생성하면서 get메서드 쓸때 지연로딩 초기화
                //그 객체를 통해 메서드 사용해 직접 사용하므로
                .map(o -> new CartAPIDto(o))
                .collect(toList());

        return result;
    }

    /** 특정 회원에 해당하는 회원의 장바구니 상품 조회 */
    @GetMapping("/api/carts/members/{memberId}")
    public List<CartAPIDto> findCart(@PathVariable("memberId") long memberId){

        List<Cart> carts = cartRepository.memberIdNamedQueryCartFindAll(memberId);

        List<CartAPIDto> result = carts.stream()
                //new SimpleOrderDto(o)) 생성하면서 get메서드 쓸때 지연로딩 초기화
                //그 객체를 통해 메서드 사용해 직접 사용하므로
                .map(o -> new CartAPIDto(o))
                .collect(toList());

        return result;
    }

    /** 장바구니 상품 수량 증가 */
    @PatchMapping("/api/carts/{cartId}/increment")
    public CartAPIDto cartQuantityPlus(@PathVariable("cartId") long cartId){

        cartService.quantityPlus(cartId);

        Cart cart = cartRepository.findById(cartId).get();
        CartAPIDto cartAPIDto = new CartAPIDto(cart);

        return cartAPIDto;
    }

    /** 장바구니 상품 수량 감소 */
    @PatchMapping("/api/carts/{cartId}/decrement")
    public CartAPIDto cartQuantityMinus(@PathVariable("cartId") long cartId){

        cartService.quantityMinus(cartId);

        Cart cart = cartRepository.findById(cartId).get();
        CartAPIDto cartAPIDto = new CartAPIDto(cart);

        return cartAPIDto;
    }

    /** 장바구니 상품 삭제 */
    @DeleteMapping("/api/carts/{cartId}")
    public String deleteCart(@PathVariable("cartId") long cartId){

        cartService.delete(cartId);

        return "삭제 성공";
    }
}

