package capstonedesign.medicalproduct.domain.entity;

import capstonedesign.medicalproduct.domain.CartStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

//장바구니 엔티티
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@NamedQuery(
        name="Cart.findMemberId",
        query="select c from Cart c where c.member.id = :memberId")
@Entity
public class Cart {

    //Maria DB 전략
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private long id;

    //수량
    @Column(nullable = false)
    private int quantity;

    //장바구니 상태
    @Enumerated(EnumType.STRING)
    private CartStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public void updateMember(Member member) {
        this.member = member;
        if(member.getCarts() != null) {
            member.getCarts().add(this);
        }
    }

    public void updateItem(Item item) {
        this.item = item;
        item.getCarts().add(this);
    }

    public void enterQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addProduct(CartStatus status) {
        this.status = status;
    }

    public static Cart createCart(Member member, Item item, int quantity) {

        Cart cart = new Cart();

        cart.updateMember(member); cart.updateItem(item); cart.enterQuantity(quantity); cart.addProduct(CartStatus.PUT);

        return cart;
    }
}
