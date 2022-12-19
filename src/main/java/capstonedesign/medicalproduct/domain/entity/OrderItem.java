package capstonedesign.medicalproduct.domain.entity;

import lombok.*;

import javax.persistence.*;

//주문 상품 엔티티
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OrderItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordereditem_id")
    private long id;

    //여러 주문한 상품은 하나의 주문에 속함,
    //지연로딩(부모인 order를 조회할때 orderitem 다같이 꺼내오지 않음), 항상 다 쪽이 외래키
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    Order order;

    //하나의 상품은 여러 주문한 상품에 속함
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    Item item;

    //주문수량
    @Column(nullable = false)
    private int quantity;

    //주문 가격, 총 가격
    private int orderPrice;

    //주문에 주문 상품 넣기, setter 대신 생성
    public void inputOrder(Order order) {
        this.order = order;
    }

    @Builder
    public OrderItem(Item item, int quantity, int orderPrice) {
        this.item = item;
        this.quantity = quantity;
        this.orderPrice = orderPrice;
    }

    //==생성 메서드==//
    //주문상품 객체 생성 후 값 세팅해 주문상품 반환
    public static OrderItem createOrderItem(Item item, int orderPrice, int quantity) {

        OrderItem orderItem = OrderItem.builder()
                .item(item)
                .orderPrice(orderPrice)
                .quantity(quantity)
                .build();

        return orderItem;
    }

    // 주문상품 전체 가격 조회
    public int getTotalPrice() {

        //주문가격과 수량을 곱해서 반환
        return getOrderPrice() * getQuantity();
    }
}
