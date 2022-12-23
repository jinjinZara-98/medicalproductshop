package capstonedesign.medicalproduct.dto;

import capstonedesign.medicalproduct.dto.mvc.ItemDto;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * capstonedesign.medicalproduct.dto.QItemDto is a Querydsl Projection type for ItemDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QItemDto extends ConstructorExpression<ItemDto> {

    private static final long serialVersionUID = 473788586L;

    public QItemDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> imageSrc, com.querydsl.core.types.Expression<Integer> price, com.querydsl.core.types.Expression<Integer> rate) {
        super(ItemDto.class, new Class<?>[]{long.class, String.class, String.class, int.class, int.class}, id, name, imageSrc, price, rate);
    }

}

