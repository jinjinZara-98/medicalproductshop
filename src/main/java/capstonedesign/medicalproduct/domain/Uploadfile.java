package capstonedesign.medicalproduct.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//후기 이미지 파일의 업로드 파일명과 저장 파일명
@Data
@NoArgsConstructor
public class Uploadfile {

    private String uploadFileName;

    private String storeFileName;

    public Uploadfile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}