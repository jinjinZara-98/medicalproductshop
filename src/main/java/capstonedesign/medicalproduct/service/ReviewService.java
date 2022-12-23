package capstonedesign.medicalproduct.service;

import capstonedesign.medicalproduct.domain.Uploadfile;
import capstonedesign.medicalproduct.domain.entity.Item;
import capstonedesign.medicalproduct.domain.entity.Member;
import capstonedesign.medicalproduct.domain.entity.Review;
import capstonedesign.medicalproduct.dto.mvc.ReviewDto;
import capstonedesign.medicalproduct.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewQueryRepository reviewQueryRepository;

    //후기 등록
    @Transactional
    public Review save(long memberId, long itemId, String title, String content, Uploadfile uploadfile) {

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품은 없습니다. id" + itemId));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원은 없습니다. id" + itemId));

        Review review = Review.createReview(member, item, title, content, uploadfile.getUploadFileName(), uploadfile.getStoreFileName());

        Review registeredReview1 = reviewRepository.save(review);

        return registeredReview1;
    }

    //로그인한 회원이 작성한 후기 리스트들 갖고오기
    public List<ReviewDto> findAllByMemberId(long memberId) {

        return reviewQueryRepository.findAllByMemberId(memberId);
    }

    //후기 삭제하기, 삭제는 readOnly아님
    @Transactional
    public void delete(long reviewId) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 후기는 없습니다. id = " + reviewId));

        reviewRepository.delete(review);
    }

    //상품 상세 화면으로 갈 때 해당 상품의 리뷰들 갖고오는
    public List<ReviewDto> findAllByItemId(long itemId) {

        return reviewQueryRepository.findAllByItemId(itemId);
    }
}
