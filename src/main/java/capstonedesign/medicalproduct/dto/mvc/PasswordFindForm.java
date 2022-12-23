package capstonedesign.medicalproduct.dto.mvc;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

//비밀번호 변경할 때 입력하는 정보
@Data
public class PasswordFindForm {

    @NotEmpty(message = "아이디 입력이 필수입니다.")
    private String loginId;
}
