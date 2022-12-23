package capstonedesign.medicalproduct.dto.mvc;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

//등록한 후기 보여줄 때 사용되는 정보
@Data
public class ReviewDto {

    private long reviewId;

    //상품상세화면에서 후기 리스트를 쓸 때 사용하는
    private String memberName;

    private long itemId;

    private String itemName;

    private String itemImageSrc;

    //후기 생성 날짜 포맷팅
    @DateTimeFormat(pattern = "yyyy년 MM월 dd일 HH시 mm분")
    private LocalDateTime reviewDate;

    private String title;

    private String content;

    private String uploadFileName;

    private String storeFileName;

    //상품 상세 화면으로 갈 때 해당 상품의 리뷰들 갖고오는
    @QueryProjection
    public ReviewDto(long reviewId, String memberName, String title, String content,
                     LocalDateTime reviewDate, String uploadFileName, String storeFileName) {
        this.reviewId = reviewId;
        this.memberName = memberName;
        this.title = title;
        this.content = content;
        this.reviewDate = reviewDate;
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }

    //로그인한 회원의 후기 리스트를 가져올때 쓰는 생성자
    @QueryProjection
    public ReviewDto(long reviewId, long itemId, String itemName, String itemImageSrc, LocalDateTime reviewDate,
                     String title, String content, String uploadFileName, String storeFileName) {
        this.reviewId = reviewId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemImageSrc = itemImageSrc;
        this.reviewDate = reviewDate;
        this.title = title;
        this.content = content;
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
