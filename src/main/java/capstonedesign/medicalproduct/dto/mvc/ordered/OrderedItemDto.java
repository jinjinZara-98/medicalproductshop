package capstonedesign.medicalproduct.dto.mvc.ordered;

import capstonedesign.medicalproduct.domain.OrderStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDateTime;

//주문하려는 상품 정보
@Data
public class OrderedItemDto {

    private long itemId;

    private String name;

    private String imageSrc;

    //주문 일자
    @DateTimeFormat(pattern = "yyyy년 MM월 dd일 HH시 mm분")
    private LocalDateTime orderDate;

    private long orderId;

    private int quantity;

    @NumberFormat(pattern = "###, ###")
    private int price;

    private OrderStatus status;

    private Long reviewId;

    //complieQueryDsl해줘서 이 dto도 q파일 생성, 원하는 컬럼만 갖고올때 사용?
    //순수하지 않다, QueryDsl라이브러리에 의존하게 됨
    @QueryProjection
    public OrderedItemDto(long itemId, String name, String imageSrc, LocalDateTime orderDate,
                          long orderId, int quantity, Integer price, OrderStatus status, Long reviewId) {
        this.itemId = itemId;
        this.name = name;
        this.imageSrc = imageSrc;
        this.orderDate = orderDate;
        this.orderId = orderId;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.reviewId = reviewId;
    }

    @QueryProjection
    public OrderedItemDto(long itemId, String name, String imageSrc, LocalDateTime orderDate,
                          long orderId, int quantity, Integer price, OrderStatus status) {
        this.itemId = itemId;
        this.name = name;
        this.imageSrc = imageSrc;
        this.orderDate = orderDate;
        this.orderId = orderId;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
    }
}
