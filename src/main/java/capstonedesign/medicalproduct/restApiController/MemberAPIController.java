package capstonedesign.medicalproduct.restApiController;


import capstonedesign.medicalproduct.domain.entity.Member;
import capstonedesign.medicalproduct.dto.MemberRegisterForm;
import capstonedesign.medicalproduct.exception.DuplicateIdException;
import capstonedesign.medicalproduct.repository.MemberRepository;
import capstonedesign.medicalproduct.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
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
    public String deleteMember(@RequestBody @Valid MemberRegisterForm form) {

        //아이디 중복체크
        int exsist = memberService.validateDuplicateMember(form.getLoginId());

        //중복된 아이디면 DuplicateIdException 예외 발생해 ExControllerAdvice 가 처리하도록
        if(exsist == 1)
            throw new DuplicateIdException();

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

        Member member = memberRepository.findById(memberId).get();

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
                                  @RequestBody UpdateDataDto updateDataDto) {

        switch (updateDataDto.getDataKind()) {
            case "name": {
                memberService.updateName(memberId, (String) updateDataDto.getValue());
                break;
            }
            case "phoneNumber": {
                memberService.updatePhoneNumber(memberId, (String) updateDataDto.getValue());
                break;
            }
            case "address": {
                memberService.updateAddress(memberId, (String) updateDataDto.getValue());
                break;
            }
            case "addressDetail": {
                memberService.updateAddressDetail(memberId, (String) updateDataDto.getValue());
                break;
            }
            case "email": {
                memberService.updateEmail(memberId, (String) updateDataDto.getValue());
                break;
            }
            case "AccountHost": {
                memberService.updateAccountHost(memberId, (String) updateDataDto.getValue());
                break;
            }
            case "bankName": {
                memberService.updateBankName(memberId, (String) updateDataDto.getValue());
                break;
            }
            case "accountNumber": {
                memberService.updateAccountNumber(memberId, (String) updateDataDto.getValue());
                break;
            }
            case "hospitalName": {
                memberService.updateHospitalName(memberId, (String) updateDataDto.getValue());
                break;
            }
            case "businessRegisterNumber": {
                memberService.updateBusinessRegisterNumber(memberId, (String) updateDataDto.getValue());
                break;
            }
            case "doctorLicenseNumber": {
                memberService.updateDoctorLicenseNumber(-memberId, (int) updateDataDto.getValue());
                break;
            }
        }

        Member member = memberRepository.findById(memberId).get();
        MemberDto result = new MemberDto(member);

        return result;
    }

    @Data
    static class MemberDto {
        private Long id;
        private String loginId;
        private String password;
        private String name;
        private String phoneNumber;
        private String address;
        private String addressDetail;
        private String email;
        private String accountHost;
        private String bankName;
        private String accountNumber;
        private String hospitalName;
        private String businessRegisterNumber;
        private Integer doctorLicenseNumber;

        public MemberDto(Member member) {
            this.id = member.getId();
            this.loginId = member.getLoginId();
            this.password = member.getPassword();
            this.name = member.getName();
            this.phoneNumber = member.getPhoneNumber();
            this.address = member.getAddress();
            this.addressDetail = member.getAddressDetail();
            this.email =member.getEmail();
            this.accountHost = member.getAccountHost();
            this.bankName = member.getBankName();
            this.accountNumber = member.getAccountNumber();
            this.hospitalName = member.getHospitalName();
            this.businessRegisterNumber = member.getBusinessRegisterNumber();
            this.doctorLicenseNumber = member.getDoctorLicenseNumber();
        }
    }

    @Data
    static class UpdateDataDto<T> {
        private String dataKind;
        private T value;
    }
}
