package capstonedesign.medicalproduct.service;

import capstonedesign.medicalproduct.domain.CartStatus;
import capstonedesign.medicalproduct.domain.entity.Cart;
import capstonedesign.medicalproduct.domain.CartSearch;
import capstonedesign.medicalproduct.domain.entity.Item;
import capstonedesign.medicalproduct.domain.entity.Member;
import capstonedesign.medicalproduct.dto.CartDto;
import capstonedesign.medicalproduct.repository.CartQueryRepository;
import capstonedesign.medicalproduct.repository.CartRepository;
import capstonedesign.medicalproduct.repository.ItemRepository;
import capstonedesign.medicalproduct.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    public long itemPut(long memberId, long itemId, int quantity) {

        Member member = memberRepository.findById(memberId).get();

        Item item = itemRepository.findById(itemId).get();

        Cart cart = Cart.createCart(member, item, quantity);

        cartRepository.save(cart);

        return cart.getId();
    }

    //로그인한 회원의 장바구니 아이템 가져오기
    public List<CartDto> cartItems (CartSearch cartSearch, Long memberId) {

        return cartQueryRepository.cartItems(cartSearch, memberId);
    }

    //N+1 문제 테스트, 페치조인으로 Cart 엔티티와 연관된 엔티티 다 갖고오는
    public List<Cart> fetchJoinCartFindAll () {

        return cartRepository.fetchJoinCartFindAll();
    }

    public List<Cart> memberIdNamedQueryCartFindAll (long memberId) {

        return cartRepository.memberIdNamedQueryCartFindAll(memberId);

    }
    //장바구니 상품 리스트에서 상품 수량 플러스 버튼 눌렀을때
    //변경은 읽기 전용 아니므로
    @Transactional
    public void quantityPlus(long cartId){

        //url에 들어온 상품아이디로 장바구니 엔티티 조회
        Cart cart = cartRepository.findById(cartId).get();

        //조회한 엔티티 수량 갖고와 1 더해주기
        cart.enterQuantity(cart.getQuantity() + 1);
    }

    //장바구니 상품 리스트에서 상품 수량 마이너스 버튼 눌렀을때
    @Transactional
    public void quantityMinus(long cartId){

        //url에 들어온 상품아이디로 장바구니 엔티티 조회
        Cart cart = cartRepository.findById(cartId).get();

        //조회한 엔티티 수량 갖고와 1 빼주기
        cart.enterQuantity(cart.getQuantity() - 1);
    }

    //장바구니에서 아이템 취소 버튼 눌러 장바구니에서 삭제,
    //삭제는 읽기 전용이 아니므로
    @Transactional
    public void cartItemDelete(Long cartId) {

        cartRepository.deleteById(cartId);
    }
}