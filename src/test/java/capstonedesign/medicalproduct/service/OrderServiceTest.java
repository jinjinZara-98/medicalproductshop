package capstonedesign.medicalproduct.service;

import capstonedesign.medicalproduct.domain.OrderSearch;
import capstonedesign.medicalproduct.domain.OrderStatus;
import capstonedesign.medicalproduct.domain.entity.Member;
import capstonedesign.medicalproduct.domain.entity.Order;
import capstonedesign.medicalproduct.dto.ItemDto;
import capstonedesign.medicalproduct.dto.MemberRegisterForm;
import capstonedesign.medicalproduct.dto.order.OrderItemDto;
import capstonedesign.medicalproduct.dto.ordered.OrderedItemDto;
import capstonedesign.medicalproduct.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    Member joinedMember; long orderId;

    @BeforeEach
    public void joinMemberAncCreateOrder() {
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

        List<OrderItemDto> orderItemDtos = new ArrayList<>();

        OrderItemDto orderItemDto1 = new OrderItemDto();
        orderItemDto1.setCartId(null);
        orderItemDto1.setItemId(1);
        orderItemDto1.setName("수술가운");
        orderItemDto1.setImageSrc("http://www.yolomarket.kr/data/item/G171103/thumb-4A06002040_L_250x250.jpg");
        orderItemDto1.setQuantity(1);
        orderItemDto1.setPrice(20000);
        orderItemDto1.setTotalPrice(20000);
        orderItemDtos.add(orderItemDto1);

        OrderItemDto orderItemDto2 = new OrderItemDto();
        orderItemDto2.setCartId(null);
        orderItemDto2.setItemId(2);
        orderItemDto2.setName("청진기");
        orderItemDto2.setImageSrc("http://www.yolomarket.kr/data/item/T170602/thumb-dr09_1446_250x250.gif");
        orderItemDto2.setQuantity(2);
        orderItemDto2.setPrice(90000);
        orderItemDto2.setTotalPrice(180000);
        orderItemDtos.add(orderItemDto2);

        orderId = orderService.order(joinedMember.getId(), joinedMember.getName(), joinedMember.getPhoneNumber(),
                joinedMember.getAddress(), joinedMember.getAddressDetail(), "부재 시 경비실에 맡겨주세요.",
                joinedMember.getAccountHost(), joinedMember.getBankName(), joinedMember.getAccountNumber(), orderItemDtos);
    }

    @Test
    @DisplayName("주문 생성 확인")
    public void createOrder() throws Exception {
        Order order = orderRepository.getById(orderId);

        //주문 상품 수령자 이름 확인
        assertThat("홍길동").isEqualTo(order.getMember().getName());

        //주문 번호에 속하는 주문 상품들의 개수 확인
        List<OrderedItemDto> orderedItemDtos = orderService.orderNumberOrderItems(orderId);
        assertThat(2).isEqualTo(orderedItemDtos.size());
    }

    @Test
    @DisplayName("주문 취소 확인")
    public void createOrdr() throws Exception {
        orderService.cancelOrder(orderId);

        //검색 조건에 주문 상태를 취소로 해서 주문 상품 검색
        OrderSearch orderSearch = new OrderSearch();
        orderSearch.setOrderStatus(OrderStatus.CANCEL);
        List<OrderedItemDto> orderedItemDtos = orderService.orderItems(joinedMember.getId(), orderSearch);

        assertThat(2).isEqualTo(orderedItemDtos.size());
    }
}