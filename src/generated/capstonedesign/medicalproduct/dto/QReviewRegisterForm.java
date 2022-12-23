package capstonedesign.medicalproduct.dto;

import capstonedesign.medicalproduct.dto.mvc.ReviewRegisterForm;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * capstonedesign.medicalproduct.dto.QReviewRegisterForm is a Querydsl Projection type for ReviewRegisterForm
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QReviewRegisterForm extends ConstructorExpression<ReviewRegisterForm> {

    private static final long serialVersionUID = -1720206367L;

    public QReviewRegisterForm(com.querydsl.core.types.Expression<Long> reviewId, com.querydsl.core.types.Expression<Long> itemId, com.querydsl.core.types.Expression<String> itemName, com.querydsl.core.types.Expression<String> itemImageSrc, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<String> uploadFileName, com.querydsl.core.types.Expression<String> storeFileName) {
        super(ReviewRegisterForm.class, new Class<?>[]{long.class, long.class, String.class, String.class, String.class, String.class, String.class, String.class}, reviewId, itemId, itemName, itemImageSrc, title, content, uploadFileName, storeFileName);
    }

}

