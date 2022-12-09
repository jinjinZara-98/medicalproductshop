package capstonedesign.medicalproduct.restApiController;

import capstonedesign.medicalproduct.domain.entity.Member;
import capstonedesign.medicalproduct.dto.MemberRegisterForm;
import capstonedesign.medicalproduct.exception.DuplicateIdException;
import capstonedesign.medicalproduct.exception.InvalidRegisteredValueException;
import capstonedesign.medicalproduct.exception.InvalidUpdatedValueException;
import capstonedesign.medicalproduct.exception.NotExistMemberException;
import capstonedesign.medicalproduct.repository.MemberRepository;
import capstonedesign.medicalproduct.restApiController.APIDto.MemberDto;
import capstonedesign.medicalproduct.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class MemberAPIController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    /**
     * 회원 등록
     *
     * {
     *     "loginId": "heojin0620",
     *     "password": "jin",
     *     "name": "홍길동",
     *     "phoneNumber": "01055447340",
     *     "address": "충북 충주시 대소원면 대학로 50",
     *     "addressDetail": "예성생활관",
     *     "email": "heojin0620@naver.com",
     *     "accountHost": "허진",
     *     "bankName": "농협",
     *     "accountNumber": "3520459732493",
     *     "hospitalName": "",
     *     "businessRegisterNumber": "",
     *     "doctorLicenseNumber": null
     * }
     */
    @PostMapping("/api/member")
    public String deleteMember(@Valid @RequestBody MemberRegisterForm form, BindingResult bindingResult) {

        //회원 가입하려는 값에 에러가 잇다면 InvalidRegisteredValueException 예외 발생
        //ExControllerAdvice 가 예외 잡아 예외 메시지 출력
        if(bindingResult.hasErrors())
            throw new InvalidRegisteredValueException();

        //아이디 중복체크
        int exsist = memberService.validateDuplicateMember(form.getLoginId());

        //중복된 아이디면 DuplicateIdException 예외 발생해 ExControllerAdvice 가 처리하도록
        if(exsist == 1)
            throw new DuplicateIdException();

        //이상 없으면 회원 가입
        memberService.join(form);

        return "회원 가입 성공";
    }

    /** 모든 회원 조회 */
    @GetMapping("/api/members")
    public List<MemberDto> findAllMembers() {

        List<Member> members = memberRepository.findAll();

        List<MemberDto> result = members.stream()
                .map(member -> new MemberDto(member))
                .collect(toList());

        return result;
    }

    /** 특정 회원 조회 */
    @GetMapping("/api/member/{memberId}")
    public MemberDto findMember(@PathVariable("memberId") long memberId) {

        Member member = memberRepository.findById(memberId).orElse(null);

        //요청된 식별자로 데이터베이스에서 회원을 못 찾으면 NotExistMemberException 예외 발생
        //ExControllerAdvice 가 예외 잡아 예외 메시지 출력
        if(member == null) {
            throw new NotExistMemberException();
        }

        MemberDto result = new MemberDto(member);

        return result;
    }

    /**
     * 회원 수정
     *
     * {"dataKind": "name", "value" : "홍길동"}
     */
    @PatchMapping("/api/member/{memberId}")
    public MemberDto updateMember(@PathVariable("memberId") long memberId,
                                  @Valid @RequestBody UpdateDataDto updateDataDto,
                                  BindingResult bindingResult) {

        //수정된 값에 에러가 존재하면 InvalidUpdatedValue 예외 발생
        //ExControllerAdvice 가 예외 잡아 예외 메시지 출력
        if (bindingResult.hasErrors())
            throw new InvalidUpdatedValueException();

        switch (updateDataDto.getDataKind()) {
            case "name": {
                memberService.updateName(memberId, updateDataDto.getValue());
                break;
            }
            case "phoneNumber": {
                memberService.updatePhoneNumber(memberId, updateDataDto.getValue());
                break;
            }
            case "address": {
                memberService.updateAddress(memberId, updateDataDto.getValue());
                break;
            }
            case "addressDetail": {
                memberService.updateAddressDetail(memberId, updateDataDto.getValue());
                break;
            }
            case "email": {
                memberService.updateEmail(memberId, updateDataDto.getValue());
                break;
            }
            case "AccountHost": {
                memberService.updateAccountHost(memberId, updateDataDto.getValue());
                break;
            }
            case "bankName": {
                memberService.updateBankName(memberId, updateDataDto.getValue());
                break;
            }
            case "accountNumber": {
                memberService.updateAccountHost(memberId, updateDataDto.getValue());
                break;
            }
            case "hospitalName": {
                memberService.updateHospitalName(memberId, updateDataDto.getValue());
                break;
            }
            case "businessRegisterNumber": {
                memberService.updateBusinessRegisterNumber(memberId, updateDataDto.getValue());
                break;
            }
            case "doctorLicenseNumber": {
                memberService.updateDoctorLicenseNumber(memberId, Integer.parseInt(updateDataDto.getValue()));
                break;
            }
        }

        Member member = memberRepository.findById(memberId).get();
        MemberDto result = new MemberDto(member);

        return result;
    }

    @Data
    static class UpdateDataDto {
        private String dataKind;
        @NotEmpty
        private String value;
    }
}
