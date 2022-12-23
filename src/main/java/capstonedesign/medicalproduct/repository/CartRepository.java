package capstonedesign.medicalproduct.repository;

import capstonedesign.medicalproduct.domain.entity.Cart;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("select c from Cart c join fetch c.member join fetch c.item")
    List<Cart> fetchJoinCartFindAll();

    //NamedQuery 사용
    @Query(name = "Cart.findMemberId")
    List<Cart> memberIdNamedQueryCartFindAll(@Param("memberId") long memberId);

    //장바구니 상품 수량 증가
    @Modifying(clearAutomatically = true)
    @Query("update Cart c set c.quantity = c.quantity + 1 where c.id = :cartId")
    int quantityPlus(@Param("cartId") long cartId);

    //장바구니 상품 수량 감소
    @Modifying(clearAutomatically = true)
    @Query("update Cart c set c.quantity = c.quantity - 1 where c.id = :cartId")
    int quantityMinus(@Param("cartId") long cartId);
}
