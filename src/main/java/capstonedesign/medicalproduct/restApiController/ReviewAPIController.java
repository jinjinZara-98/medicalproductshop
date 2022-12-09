package capstonedesign.medicalproduct.restApiController;

import capstonedesign.medicalproduct.domain.entity.Review;
import capstonedesign.medicalproduct.repository.ReviewRepository;
import capstonedesign.medicalproduct.service.ReviewService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class ReviewAPIController {

    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;

    /** 전체 후기 조회 */
    @GetMapping("/api/reviews")
    public List<ReviewAPIDto> findReviews() {

        List<Review> reviews = reviewRepository.findAll();

        List<ReviewAPIDto> result = reviews.stream()
                .map(review -> new ReviewAPIDto(review))
                .collect(toList());

        return result;
    }

    /** 특정 후기 조회 */
    @GetMapping("/api/reviews/{reviewId}")
    public ReviewAPIDto findReview(@PathVariable("reviewId") long reviewId) {

        Review review = reviewRepository.findById(reviewId).get();

        ReviewAPIDto result = new ReviewAPIDto(review);

        return result;
    }

    /** 해당 회원이 작성한 모든 후기 조회 */
    @GetMapping("/api/reviews/members/{memberId}")
    public List<ReviewAPIDto> reviewsWrittenByMember(@PathVariable("memberId") long memberId) {

        List<Review> reviews = reviewRepository.findByMember_id(memberId);

        List<ReviewAPIDto> result = reviews.stream()
                .map(review -> new ReviewAPIDto(review))
                .collect(toList());

        return result;
    }

    /** 해당 상품에 대한 모든 후기 조회 */
    @GetMapping("/api/reviews/items/{itemId}")
    public List<ReviewAPIDto> allReviewsOnProducts(@PathVariable("itemId") long itemId) {

        List<Review> reviews = reviewRepository.findByItem_id(itemId);

        List<ReviewAPIDto> result = reviews.stream()
                .map(review -> new ReviewAPIDto(review))
                .collect(toList());

        return result;
    }

    /** 특정 후기 식제 */
    @DeleteMapping("/api/reviews/{id}")
    public String deleteReview(@PathVariable("id") long reviewId) {

        reviewRepository.deleteById(reviewId);

        return "삭제 성공";
    }

    @Data
    public static class ReviewAPIDto {

        private long id;
        private String title;
        private String content;
        private String memberName;
        private String itemName;
        private String uploadFileName;
        private String storeFileName;
        private LocalDateTime reviewDate;

        public ReviewAPIDto(Review review) {
            this.id = review.getId();
            this.title = review.getTitle();
            this.content = review.getContent();
            this.memberName = review.getMember().getName();
            this.itemName = review.getItem().getName();
            this.uploadFileName = review.getUploadFileName();
            this.storeFileName = review.getStoreFileName();
            this.reviewDate = review.getReviewDate();
        }
    }
}
