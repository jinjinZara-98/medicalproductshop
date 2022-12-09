package capstonedesign.medicalproduct.domain.entity;

import capstonedesign.medicalproduct.domain.OrderStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


//주문 엔티티
@Getter
@Table(name = "orders")
@NamedEntityGraph(name="Order.all", attributeNodes = @NamedAttributeNode("member"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long id;

    //여러개의 주문 하나의 회원, 항상 다 쪽이 외래키를 가짐
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String recipientName;

    //수령자 정보
    //db에 번호 저장할때 타입 int로 지정하면 앞에 010부터 저장되므로 맨 앞에 0인 숫자는 없으므로 앞에 0삭제하고 저장됨
    //char varchar로
    @Column(nullable = false)
    private String recipientPhoneNumber;

    @Column(nullable = false)
    private String recipientAddress;

    @Column(nullable = false)
    private String recipientAddressDetail;

    //배송메시지 필수아님
    private String deliveryMessage;

    @Column(nullable = false)
    private String orderAccountHost;

    @Column(nullable = false)
    private String orderBankName;

    @Column(nullable = false)
    private String orderAccountNumber;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문상태 [ORDER, CANCEL]

    @Builder
    public Order(String recipientName, String recipientPhoneNumber, String recipientAddress,
                 String recipientAddressDetail, String deliveryMessage, String orderAccountHost,
                 String orderBankName, String orderAccountNumber, OrderStatus status, LocalDateTime orderDate) {
        this.recipientName = recipientName;
        this.recipientPhoneNumber = recipientPhoneNumber;
        this.recipientAddress = recipientAddress;
        this.recipientAddressDetail = recipientAddressDetail;
        this.deliveryMessage = deliveryMessage;
        this.orderAccountHost = orderAccountHost;
        this.orderBankName = orderBankName;
        this.orderAccountNumber = orderAccountNumber;
        this.orderDate = orderDate;
        this.status = status;
    }

    //주문 취소
    public void cancelOrder() {
        this.status = OrderStatus.CANCEL;
    }

    //==연관관계 메서드==//
    public void updateMember(Member member) {
        this.member = member;
        if(member.getOrders() != null) {
            member.getOrders().add(this);
        }
    }

    //파라미터로 들어온 주문된 상품을 주문한 상품 리스트에 추가하고 어떤 주문에 속해있는지 세팅
    //주문한 상품 리스트는 Order객체를 저장할때 같이 저장되므로 파라미터로 들어오는 주문된 상품 객체는 필드들이 이미 세팅되어있음
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.inputOrder(this);
    }

    //==주문 생성 메서드==//
    public static Order createOrder(Member member, String recipientName, String recipientPhoneNumber,
                                    String recipientAddress, String recipientAddressDetail, String deliveryMessage,
                                    String orderAccountHost, String orderBankName, String orderAccountNumber,
                                    List<OrderItem> orderItems) {

        //주문 객체 생성해 파라미터로 들어온 값들 세팅
        Order order = Order.builder()
                .recipientName(recipientName)
                .recipientPhoneNumber(recipientPhoneNumber)
                .recipientAddress(recipientAddress)
                .recipientAddressDetail(recipientAddressDetail)
                .deliveryMessage(deliveryMessage)
                .orderAccountHost(orderAccountHost)
                .orderBankName(orderBankName)
                .orderAccountNumber(orderAccountNumber)
                //이 주문의 상태 order로 세팅하고 날짜도 현재 시간으로 세팅
                .orderDate(LocalDateTime.now())
                .status(OrderStatus.ORDER).build();

        order.updateMember(member);

        //여러 개 받은 주문상품을 하나씩 꺼내 Order 객체에 추가
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }

        return order;
    }

    //==비즈니스 로직==//
    //주문 취소
    public void cancel() {
        this.cancelOrder();
    }

    // 전체 주문 가격 조회
    public int getTotalPrice() {
        int totalPrice = 0;

        for (OrderItem orderItem : orderItems) {
            //각 물품의 가격을 더해
            totalPrice += orderItem.getTotalPrice();
        }

        return totalPrice;
    }
}
