package capstonedesign.medicalproduct.dto.api;

import capstonedesign.medicalproduct.domain.CartStatus;
import capstonedesign.medicalproduct.domain.entity.Cart;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartAPIDto {
    private long id;
    private long quantity;
    private CartStatus status;
    private long memberId;
    private String memberName;
    private long itemId;
    private String itemName;

    public CartAPIDto(Cart cart) {
        this.id = cart.getId();
        this.quantity = cart.getQuantity();
        this.status = cart.getStatus();
        this.memberId = cart.getMember().getId();
        this.memberName = cart.getMember().getName();
        this.itemId = cart.getItem().getId();
        this.itemName = cart.getItem().getName();
    }
}
