package capstonedesign.medicalproduct.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * capstonedesign.medicalproduct.dto.QCartDto is a Querydsl Projection type for CartDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCartDto extends ConstructorExpression<CartDto> {

    private static final long serialVersionUID = -1088005763L;

    public QCartDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<Long> itemId, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> imageSrc, com.querydsl.core.types.Expression<Integer> price, com.querydsl.core.types.Expression<Integer> quantity, com.querydsl.core.types.Expression<capstonedesign.medicalproduct.domain.CartStatus> status) {
        super(CartDto.class, new Class<?>[]{long.class, long.class, String.class, String.class, int.class, int.class, capstonedesign.medicalproduct.domain.CartStatus.class}, id, itemId, name, imageSrc, price, quantity, status);
    }

}

