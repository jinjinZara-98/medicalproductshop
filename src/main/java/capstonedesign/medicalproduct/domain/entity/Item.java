package capstonedesign.medicalproduct.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//상품 엔티티
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private long id;

    @Column(nullable = false)
    private String name;

    //이미지 경로
    @Column(nullable = false)
    private String imageSrc;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private int price;

    @Lob @Column(nullable = false)
    private String introduction;

    //상품 등급, 1등급은 의사/의료사업자만
    @Column(nullable = false)
    private int rate;

    //하나의 아이템은 주문한 여러 아이템에 속함, 주인이 아닌 곳에 mappedBy
    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderedItems = new ArrayList<>();

    //장바구니
    @OneToMany(mappedBy = "item")
    private List<Cart> carts = new ArrayList<>();

    //리뷰
    @OneToMany(mappedBy = "item")
    private List<Review> reviews = new ArrayList<>();

    @Builder
    public Item(String name, String imageSrc, String category, int price, String introduction, int rate) {
        this.name = name;
        this.imageSrc = imageSrc;
        this.category = category;
        this.price = price;
        this.introduction = introduction;
        this.rate = rate;
    }

    public static Item createItem(String name, String imageSrc, String category, int price, String introduction, int rate) {

        Item item = Item.builder()
                .name(name)
                .imageSrc(imageSrc)
                .category(category)
                .price(price)
                .introduction(introduction)
                .rate(rate).build();

        return item;
    }
}
