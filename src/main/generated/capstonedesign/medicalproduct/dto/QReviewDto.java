package capstonedesign.medicalproduct.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * capstonedesign.medicalproduct.dto.QReviewDto is a Querydsl Projection type for ReviewDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QReviewDto extends ConstructorExpression<ReviewDto> {

    private static final long serialVersionUID = -1591569723L;

    public QReviewDto(com.querydsl.core.types.Expression<Long> reviewId, com.querydsl.core.types.Expression<String> memberName, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<java.time.LocalDateTime> reviewDate, com.querydsl.core.types.Expression<String> uploadFileName, com.querydsl.core.types.Expression<String> storeFileName) {
        super(ReviewDto.class, new Class<?>[]{long.class, String.class, String.class, String.class, java.time.LocalDateTime.class, String.class, String.class}, reviewId, memberName, title, content, reviewDate, uploadFileName, storeFileName);
    }

    public QReviewDto(com.querydsl.core.types.Expression<Long> reviewId, com.querydsl.core.types.Expression<Long> itemId, com.querydsl.core.types.Expression<String> itemName, com.querydsl.core.types.Expression<String> itemImageSrc, com.querydsl.core.types.Expression<java.time.LocalDateTime> reviewDate, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<String> uploadFileName, com.querydsl.core.types.Expression<String> storeFileName) {
        super(ReviewDto.class, new Class<?>[]{long.class, long.class, String.class, String.class, java.time.LocalDateTime.class, String.class, String.class, String.class, String.class}, reviewId, itemId, itemName, itemImageSrc, reviewDate, title, content, uploadFileName, storeFileName);
    }

}

