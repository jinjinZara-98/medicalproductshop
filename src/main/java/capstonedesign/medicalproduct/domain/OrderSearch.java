package capstonedesign.medicalproduct.domain;

import lombok.Getter;
import lombok.Setter;

//주문 내역 리스트에서 주문 상품 찾는데 사용
@Getter @Setter
public class OrderSearch {

    private String itemName; //상품 이름

    private OrderStatus orderStatus;//주문 상태[ORDER, CANCEL]
}

