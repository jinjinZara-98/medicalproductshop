package capstonedesign.medicalproduct.dto.ordered;

import capstonedesign.medicalproduct.dto.mvc.ordered.OrderedItemDto;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * capstonedesign.medicalproduct.dto.ordered.QOrderedItemDto is a Querydsl Projection type for OrderedItemDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QOrderedItemDto extends ConstructorExpression<OrderedItemDto> {

    private static final long serialVersionUID = -627190366L;

    public QOrderedItemDto(com.querydsl.core.types.Expression<Long> itemId, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> imageSrc, com.querydsl.core.types.Expression<java.time.LocalDateTime> orderDate, com.querydsl.core.types.Expression<Long> orderId, com.querydsl.core.types.Expression<Integer> quantity, com.querydsl.core.types.Expression<Integer> price, com.querydsl.core.types.Expression<capstonedesign.medicalproduct.domain.OrderStatus> status, com.querydsl.core.types.Expression<Long> reviewId) {
        super(OrderedItemDto.class, new Class<?>[]{long.class, String.class, String.class, java.time.LocalDateTime.class, long.class, int.class, int.class, capstonedesign.medicalproduct.domain.OrderStatus.class, long.class}, itemId, name, imageSrc, orderDate, orderId, quantity, price, status, reviewId);
    }

    public QOrderedItemDto(com.querydsl.core.types.Expression<Long> itemId, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> imageSrc, com.querydsl.core.types.Expression<java.time.LocalDateTime> orderDate, com.querydsl.core.types.Expression<Long> orderId, com.querydsl.core.types.Expression<Integer> quantity, com.querydsl.core.types.Expression<Integer> price, com.querydsl.core.types.Expression<capstonedesign.medicalproduct.domain.OrderStatus> status) {
        super(OrderedItemDto.class, new Class<?>[]{long.class, String.class, String.class, java.time.LocalDateTime.class, long.class, int.class, int.class, capstonedesign.medicalproduct.domain.OrderStatus.class}, itemId, name, imageSrc, orderDate, orderId, quantity, price, status);
    }

}

