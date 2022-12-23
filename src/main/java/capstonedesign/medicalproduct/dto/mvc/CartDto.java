package capstonedesign.medicalproduct.dto.mvc;

import capstonedesign.medicalproduct.domain.CartStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

//장바구니 화면에 장바구니 상품 정보 내보내는
@Data
public class CartDto {

    //장바구니 id
    private long id;

    //상품 id
    private long itemId;

    //상품 이름
    private String name;

    //상품 이미지 경로
    private String imageSrc;

    //상품 가격
    private int price;

    //상품 수량
    private int quantity;

    //상태, PUT, CANCEL
    private CartStatus status;

    //complieQueryDsl해줘서 이 dto도 q파일 생성, 원하는 컬럼만 갖고올때 사용?
    //순수하지 않다, QueryDsl라이브러리에 의존하게 됨
    @QueryProjection
    public CartDto(long id, long itemId, String name, String imageSrc, int price, int quantity, CartStatus status) {
        this.id = id;
        this.itemId = itemId;
        this.name = name;
        this.imageSrc = imageSrc;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
    }
}