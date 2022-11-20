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
        name="Member.findMemberId",
        query="select c from Cart c where c.member.id = :memberId")
@Entity
public class Cart {

    @Id @GeneratedValue
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

    public void setMember(Member member) {
        this.member = member;
        if(member.getCarts() != null) {
            member.getCarts().add(this);
        }
    }

    public void setItem(Item item) {
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

        cart.setMember(member); cart.setItem(item); cart.enterQuantity(quantity); cart.addProduct(CartStatus.PUT);

        return cart;
    }
}
