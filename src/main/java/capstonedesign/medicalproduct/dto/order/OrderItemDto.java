package capstonedesign.medicalproduct.dto.order;

import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

//체크된 상품 데이터 담는 클래스, 카트아이디 상품아이디 이름 이미지 수량 총가격
//주문할때도 그대로 갖고와 리스트 보여주는
@Data
public class OrderItemDto {

    private Integer cartId;

    private long itemId;

    private String name;

    private String imageSrc;

    private int quantity;

//    @NumberFormat(pattern = "###, ###")
    private int price;

    private int totalPrice;
}

