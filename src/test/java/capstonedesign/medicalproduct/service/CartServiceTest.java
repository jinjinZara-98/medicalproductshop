package capstonedesign.medicalproduct.service;

import capstonedesign.medicalproduct.domain.entity.Cart;
import capstonedesign.medicalproduct.domain.entity.Item;
import capstonedesign.medicalproduct.domain.entity.Member;
import capstonedesign.medicalproduct.dto.MemberRegisterForm;
import capstonedesign.medicalproduct.repository.CartRepository;
import capstonedesign.medicalproduct.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class CartServiceTest {

    @Autowired
    CartService cartService;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    Member joinedMember;

    //장바구니에 상품 담는 회원 생성
    @BeforeEach
    public void joinMemberAndPutItemToCart() {
        MemberRegisterForm member = new MemberRegisterForm();

        member.setLoginId("gildong123");
        member.setPassword("gildong");
        member.setName("홍길동");
        member.setPhoneNumber("01012345678");
        member.setAddress("충북 충주시 대소원면 대학로 50");
        member.setAddressDetail("예상생활관");
        member.setEmail("gildong123@naver.com");
        member.setAccountHost("홍길동");
        member.setBankName("농협");
        member.setAccountNumber("3520459732493");
        member.setHospitalName(null);
        member.setBusinessRegisterNumber(null);
        member.setDoctorLicenseNumber(null);

        //회원가입된 회원 엔티티 반환
        joinedMember = memberService.join(member);
    }

    @Test
    @DisplayName("장바구니에 있는 상품 확인")
    public void cartItem() throws Exception {

        //장바구니에 상품 넣기
        long cartId = cartService.itemPut(joinedMember.getId(), 1, 1);
        Cart cart = cartRepository.getById(cartId);

        //장바구니에 상품을 담은 회원 이름
        assertThat(joinedMember.getName()).isEqualTo(cart.getMember().getName());

        //장바구니에 담긴 상품
        assertThat(1).isEqualTo(cart.getItem().getId());

        //장바구니에 담긴 상품 수량
        assertThat(1).isEqualTo(cart.getQuantity());
    }

    @Test
    @DisplayName("장바구니에 있는 상품 개수 확인")
    public void cartItems() throws Exception {

        //장바구니에 상품 넣기
        cartService.itemPut(joinedMember.getId(), 1, 1);
        cartService.itemPut(joinedMember.getId(), 2, 1);
        cartService.itemPut(joinedMember.getId(), 3, 1);

        List<Cart> carts = cartRepository.findAll();

        assertThat(3).isEqualTo(carts.size());
    }

    @Test
    @DisplayName("장바구니에 있는 상품 수량 증가 확인")
    public void cartItemQuantityRegulate() throws Exception {

        //장바구니에 상품 넣기
        long cartId = cartService.itemPut(joinedMember.getId(), 1, 1);
        cartService.quantityPlus(cartId);
        Cart cart = cartRepository.getById(cartId);

        assertThat(2).isEqualTo(cart.getQuantity());
    }

    @Test
    @DisplayName("장바구니에 있는 상품 수량 감소 확인")
    public void cartItemQuantityMinus() throws Exception {

        //장바구니에 상품 넣기
        long cartId = cartService.itemPut(joinedMember.getId(), 1, 2);
        cartService.quantityMinus(cartId);
        Cart cart = cartRepository.getById(cartId);

        assertThat(1).isEqualTo(cart.getQuantity());
    }

    @Test
    @DisplayName("장바구니에 있는 상품 삭제 확인")
    public void cartItemDelete() throws Exception {

        //장바구니에 상품 넣기
        long cartId = cartService.itemPut(joinedMember.getId(), 1, 1);
        cartService.cartItemDelete(cartId);

        List<Cart> carts = cartRepository.findAll();

        assertThat(0).isEqualTo(carts.size());
    }
}