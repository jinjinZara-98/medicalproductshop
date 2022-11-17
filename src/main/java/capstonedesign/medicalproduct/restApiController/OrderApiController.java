package capstonedesign.medicalproduct.restApiController;

import capstonedesign.medicalproduct.Login.SessionConst;
import capstonedesign.medicalproduct.domain.OrderSearch;
import capstonedesign.medicalproduct.domain.OrderStatus;
import capstonedesign.medicalproduct.domain.entity.Member;
import capstonedesign.medicalproduct.domain.entity.Order;
import capstonedesign.medicalproduct.domain.entity.OrderItem;
import capstonedesign.medicalproduct.dto.ordered.OrderedItemDto;
import capstonedesign.medicalproduct.repository.OrderRepository;
import capstonedesign.medicalproduct.service.OrderService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderService orderService;
    private final OrderRepository orderRepository;

    /** 모든 주문 조회 */
    @GetMapping("/api/orders")
    public List<OrderAPIDto> findOrders(){

        List<Order> orders = orderRepository.findAll();

        List<OrderAPIDto> result = orders.stream()
                .map(review -> new OrderAPIDto(review))
                .collect(toList());

        return result;
    }

    /** 특정 주문 조회 */
    @GetMapping("/api/orders/{orderId}")
    public OrderAPIDto findOrder(@PathVariable("orderId") long orderId){

        Order order = orderRepository.findById(orderId).get();

        OrderAPIDto orderAPIDto = new OrderAPIDto(order);

        return orderAPIDto;
    }

    /** 특정 주문에 해당하는 주문 상품 조회 */
//    @GetMapping("/api/orders/orderItems/members/{memberId}")
//    public OrderAPIDto orderItemsOrderedByMember(@PathVariable("memberId") long memberId,
//                                                 @RequestBody OrderSearch orderSearch){
//
//        List<OrderedItemDto> order = orderService.orderItems(memberId, orderSearch);
//
//        OrderAPIDto orderAPIDto = new OrderAPIDto(order);
//
//        return orderAPIDto;
//    }

    @Data
    static class OrderAPIDto {
        private long id;
        private long memberId;
        private String memberName;
        private String recipientName;
        private String recipientPhoneNumber;
        private String recipientAddress;
        private String recipientAddressDetail;
        private String deliveryMessage;
        private String orderAccountHost;
        private String orderBankName;
        private String orderAccountNumber;
//        private List<OrderItem> orderItems = new ArrayList<>();
        private LocalDateTime orderDate;
        private OrderStatus status;

        public OrderAPIDto(Order order) {
            this.id = order.getId();
            this.memberId = order.getMember().getId();
            this.memberName = order.getMember().getName();
            this.recipientName = order.getRecipientName();
            this.recipientPhoneNumber = order.getRecipientPhoneNumber();
            this.recipientAddress = order.getRecipientAddress();
            this.recipientAddressDetail = order.getRecipientAddressDetail();
            this.deliveryMessage = order.getDeliveryMessage();
            this.orderAccountHost = order.getOrderAccountHost();
            this.orderBankName = order.getOrderBankName();
            this.orderAccountNumber = order.getOrderAccountNumber();
            this.orderDate = order.getOrderDate();
            this.status = order.getStatus();
        }
    }
}
