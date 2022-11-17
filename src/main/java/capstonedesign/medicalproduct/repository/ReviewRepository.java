package capstonedesign.medicalproduct.repository;

import capstonedesign.medicalproduct.domain.entity.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @EntityGraph(value = "Review.all", type = EntityGraph.EntityGraphType.LOAD)
    List<Review> findAll();

    List<Review> findByMember_id(long memberId);

    List<Review> findByItem_id(long itemId);
}
