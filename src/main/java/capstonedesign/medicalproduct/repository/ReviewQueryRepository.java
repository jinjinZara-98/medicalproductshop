package capstonedesign.medicalproduct.repository;

import capstonedesign.medicalproduct.domain.entity.QItem;
import capstonedesign.medicalproduct.domain.entity.QMember;
import capstonedesign.medicalproduct.domain.entity.QReview;
import capstonedesign.medicalproduct.dto.QReviewDto;
import capstonedesign.medicalproduct.dto.QReviewRegisterForm;
import capstonedesign.medicalproduct.dto.ReviewDto;
import capstonedesign.medicalproduct.dto.ReviewRegisterForm;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<ReviewDto> reviewList(long memberId) {

        QMember member = QMember.member;
        QItem item = QItem.item;
        QReview review = QReview.review;

        List<ReviewDto> result = jpaQueryFactory
                                .select(new QReviewDto(review.id, item.id, item.name, item.imageSrc, review.reviewDate,
                                        review.title, review.content, review.uploadFileName, review.storeFileName))
                .from(review)
                .join(review.item, item)
                .join(review.member, member)
                .where(member.id.eq(memberId))
                .fetch();

        return result;
    }

    //상품 상세 화면으로 갈 때 해당 상품의 리뷰들 갖고오는
    public List<ReviewDto> oneItemReviews(long itemId) {

        QMember member = QMember.member;
        QItem item = QItem.item;
        QReview review = QReview.review;

        List<ReviewDto> result = jpaQueryFactory
                .select(new QReviewDto(review.id, member.name, review.title, review.content, review.reviewDate,
                                        review.uploadFileName,review.storeFileName))
                .from(review)
                .join(review.item, item)
                .join(review.member, member)
                .where(item.id.eq(itemId))
                .fetch();

        return result;
    }
}
