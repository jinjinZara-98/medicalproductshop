package capstonedesign.medicalproduct.service;

import capstonedesign.medicalproduct.domain.entity.Cart;
import capstonedesign.medicalproduct.domain.CartSearch;
import capstonedesign.medicalproduct.domain.entity.Item;
import capstonedesign.medicalproduct.domain.entity.Member;
import capstonedesign.medicalproduct.dto.mvc.CartDto;
import capstonedesign.medicalproduct.repository.CartQueryRepository;
import capstonedesign.medicalproduct.repository.CartRepository;
import capstonedesign.medicalproduct.repository.ItemRepository;
import capstonedesign.medicalproduct.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import capstonedesign.medicalproduct.dto.api.CartAPIDto;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartService {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;
    private final CartQueryRepository cartQueryRepository;

    //홈화면에서 상품상세 가지 않고 상품카드에서 장바구니버튼 눌렀을 때
    @Transactional
    public long save(long memberId, long itemId, int quantity) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id = " + memberId));

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품은 없습니다. id = " + itemId));

        Cart cart = Cart.createCart(member, item, quantity);

        cartRepository.save(cart);

        return cart.getId();
    }

    //API 컨트롤러에서 사용
    public CartAPIDto findById(long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new  IllegalArgumentException("해당 장바구니 상품은 없습니다. id = " + cartId));

        CartAPIDto cartAPIDto = new CartAPIDto(cart);

        return cartAPIDto;
    }

    //로그인한 회원의 장바구니 아이템 가져오기
    public List<CartDto> cartItems (CartSearch cartSearch, Long memberId) {

        return cartQueryRepository.cartItems(cartSearch, memberId);
    }

    //restapi 에 사용, 장바구니 가져올 때 페치 조인으로 회원까지
    public List<Cart> fetchJoinCartFindAll() {

        return cartRepository.fetchJoinCartFindAll();
    }

    //파라미터로 받은 회원 식별자에 해당하는 장바구니 리스트
    public List<Cart> memberIdNamedQueryCartFindAll (long memberId) {

        return cartRepository.memberIdNamedQueryCartFindAll(memberId);
    }

    //장바구니 상품 리스트에서 상품 수량 플러스 버튼 눌렀을때
    @Transactional
    public void quantityPlus(long cartId){

       cartRepository.quantityPlus(cartId);
    }

    //장바구니 상품 리스트에서 상품 수량 마이너스 버튼 눌렀을때
    @Transactional
    public void quantityMinus(long cartId){

        cartRepository.quantityMinus(cartId);
    }

    //장바구니에서 아이템 취소 버튼 눌러 장바구니에서 삭제,
    //삭제는 읽기 전용이 아니므로
    @Transactional
    public void delete(Long cartId) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new  IllegalArgumentException("해당 장바구니 상품은 없습니다. id = " + cartId));

        cartRepository.delete(cart);
    }
}