package capstonedesign.medicalproduct.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.util.List;

//후기 등록할 때 사용되는 정보
@Data
public class ReviewRegisterForm {

    private long reviewId;

    private long itemId;

    private String itemName;

    private String itemImageSrc;

    @NotEmpty(message = "후기 제목을 입력해주세요")
    private String title;

    @NotEmpty(message = "후기 내용을 입력해주세요")
    private String content;

    private MultipartFile imageFile;

    private String uploadFileName;

    private String storeFileName;

    public ReviewRegisterForm() {};

    //후기 수정할때 후기 정보 받아오는 생성자
    @QueryProjection
    public ReviewRegisterForm(long reviewId, long itemId, String itemName, String itemImageSrc,
                              String title, String content, String uploadFileName, String storeFileName) {
        this.reviewId = reviewId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemImageSrc = itemImageSrc;
        this.title = title;
        this.content = content;
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
