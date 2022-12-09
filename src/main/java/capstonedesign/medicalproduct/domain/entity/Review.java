package capstonedesign.medicalproduct.domain.entity;

import capstonedesign.medicalproduct.domain.entity.Item;
import capstonedesign.medicalproduct.domain.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
//이미 엔티티를 생성하는 생성자를 따로 만들어놓음. 일반 생성자를 통해 생성할 수 있게 해놓은다면 유지보수가 여려워짐
//생성로직을 변경할때 파라미터를 추가한다거나 그럴 경우, 분산되니까
//그래서 생성자를 protected로 막는다. 생성자 만들면 컴파일오류 남
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@NamedEntityGraph(name = "Review.all", attributeNodes = { @NamedAttributeNode("member"), @NamedAttributeNode("item") } )
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private long id;

    @Column(nullable = false)
    String title;

    //후기를 길게 작성할 수 있으니 @Lob로
    @Lob @Column(nullable = false)
    private String content;

    //하나의 회원은 여러 후기를 남길 수 있고
    //무조건 지연로딩, 외래키로 member_id 갖는
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    //하나의 상품에 여러 후기를 남길 수 있음
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(nullable = false)
    private String uploadFileName;

    @Column(nullable = false)
    private String storeFileName;

    //후기 작성한 시간
    //LocalDateTime쓰면 하이버네이트가 알아서 지원
    private LocalDateTime reviewDate;

    @Builder
    protected Review(String title, String content, LocalDateTime reviewDate, String uploadFileName, String storeFileName) {
        this.title = title;
        this.content = content;
        this.reviewDate = reviewDate;
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }

    public void updateMember(Member member) {
        this.member = member;
        member.getReviews().add(this);
    }

    public void updateItem(Item item) {
        this.item = item;
        item.getReviews().add(this);
    }

    public static Review createReview(Member member, Item item, String title, String content,
                                      String uploadFileName, String storeFileName) {

        Review review = Review.builder()
                .title(title)
                .content(content)
                .reviewDate(LocalDateTime.now())
                .uploadFileName(uploadFileName)
                .storeFileName(storeFileName).build();

        review.updateMember(member); review.updateItem(item);

        return review;
    }
}
