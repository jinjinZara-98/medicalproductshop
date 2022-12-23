package capstonedesign.medicalproduct.service;

import capstonedesign.medicalproduct.domain.*;
import capstonedesign.medicalproduct.domain.entity.Item;
import capstonedesign.medicalproduct.domain.entity.Member;
import capstonedesign.medicalproduct.domain.entity.Order;
import capstonedesign.medicalproduct.domain.entity.OrderItem;
import capstonedesign.medicalproduct.dto.mvc.order.OrderItemDto;
import capstonedesign.medicalproduct.dto.mvc.ordered.OrderedItemDto;
import capstonedesign.medicalproduct.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    /** 주문 */
    @Transactional
    public Long save(Long memberId, String recipientName, String recipientPhoneNumber,
                      String recipientAddress, String recipientAddressDetail, String deliveryMessage, String accountHost,
                      String bankName, String accountNumber, List<OrderItemDto> orders) {

        //엔티티 조회
        //id에 맞는 Member객체 Item객체 찾기, 주문하는 회원과 주문되는 상품이 뭔지
        //스프링 데이터 JPA는 그냥 값 반환하면 Optional로 받아야해서 .get()으로, 제공해주는 메서드에 한해서만
        Member member = memberRepository.findById(memberId).get();

        //하나의 주문에 속해있는 주문 상품들 리스트 생성
        List<OrderItem> orderItems = new ArrayList<>();

        //파라미터로 들어온 주문 화면의 주문 상품 리스트를 갖고와
        for (OrderItemDto orderInfo : orders) {

            //장바구니에서 체크한 상품 주문했을때 체크한 상품 삭제
            //상품 상세 화면에서 주문하기 버튼을 누르면 장바구니 아이디 없을 수도 있으니 비어있는지 판별
            if(orderInfo.getCartId() != null)
                cartRepository.deleteById(Long.valueOf(orderInfo.getCartId()));

            //주문하는 상품의 아이디로 상품 조회하여 상품 객체 생성
            Item item = itemRepository.findById(orderInfo.getItemId()).get();

            //주문하는 상품 객체과 가격 수량 세팅해 주문에 넣을 주문상품 객체 생성, 이외에 다른 생성방법은 막아야함
            OrderItem orderItem = OrderItem.createOrderItem(item, orderInfo.getTotalPrice(), orderInfo.getQuantity());

            //생성한 주문 상품을 주문 상품 리스트에 넣음
            orderItems.add(orderItem);
        }

        //주문 상품 리스트에 주문 상품을 다 넣은 후 주문하는 회원 엔티티, 수령자 정보, 주문 상품 리스트를 주문 생성 메서드 파라미터로 넣어
        //주문 엔티티 생성
        Order order = Order.createOrder(member, recipientName, recipientPhoneNumber, recipientAddress, recipientAddressDetail,
                                        deliveryMessage, accountHost, bankName, accountNumber, orderItems);

        orderRepository.save(order);

        return order.getId();
    }

    //로그인한 회원의 주문 상품 리스트를 갖고오는
    public List<OrderedItemDto> findAllOrderItemByMemberIdAndOrderInfo(long memberId, OrderSearch orderSearch) {
        return orderQueryRepository.findAllOrderItemByMemberIdAndOrderInfo(memberId, orderSearch);
    }

    //주문 상품 리스트에서 상품 하나를 주문 취소하기 버튼 눌러 해당 상품과 같이 주문된 상품들 갖고오는
    public List<OrderedItemDto> findAllOrderItemById(long orderId) {
        return orderQueryRepository.findAllOrderItemById(orderId);
    }

    /** 주문 취소 */
    @Transactional
    public void cancel(Long orderId) {

        //주문 엔티티 조회
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("해당 주문은 없습니다. id = " + orderId));

        //주문취소
        order.cancel();
    }

    //주문 아이디에 맞는 주문 상품 수령자 정보 가져오기
    public Order findById(long orderId) {

        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("해당 주문은 없습니다. id = " + orderId));
    }
}
