package capstonedesign.medicalproduct.repository;

import capstonedesign.medicalproduct.domain.entity.Cart;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("select c from Cart c join fetch c.member join fetch c.item")
    List<Cart> fetchJoinCartFindAll();

    //NamedQuery 사용
    @Query(name = "Member.findMemberId")
    List<Cart> memberIdNamedQueryCartFindAll(@Param("memberId") long memberId);
}
