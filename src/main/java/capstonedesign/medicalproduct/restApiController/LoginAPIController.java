package capstonedesign.medicalproduct.restApiController;

import capstonedesign.medicalproduct.repository.MemberRepository;
import capstonedesign.medicalproduct.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginAPIController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

}
