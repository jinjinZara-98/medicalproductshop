package capstonedesign.medicalproduct.repository;

import capstonedesign.medicalproduct.domain.entity.OrderItem;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @EntityGraph(value = "OrderItem.all", type = EntityGraph.EntityGraphType.LOAD)
    List<OrderItem> findAll();
}
