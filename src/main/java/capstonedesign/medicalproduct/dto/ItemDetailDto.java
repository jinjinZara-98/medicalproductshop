package capstonedesign.medicalproduct.dto;

import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

//상품상세화면에 내보낼 상품 정보
@Data
public class ItemDetailDto {

    private long id;

    private String name;

    private String imageSrc;

    private int quantity;

    //상품 가격 1,000 단위로 쉼표
    @NumberFormat(pattern = "###,###")
    private int price;

    private int totalPrice;

    private int rate;

    private String introduction;
}
