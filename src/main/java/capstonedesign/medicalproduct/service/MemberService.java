package capstonedesign.medicalproduct.service;

import capstonedesign.medicalproduct.dto.mvc.IdFindForm;
import capstonedesign.medicalproduct.domain.entity.Member;
import capstonedesign.medicalproduct.dto.mvc.MemberRegisterForm;
import capstonedesign.medicalproduct.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //비밀번호 찾기에 사용
    public Member findByLoginId(String loginId) {

        //로그인할때 입력한 아이디, 비번 가져와 sql문 파라미터에 넣어줌
        Member loginMember = memberRepository.findByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원은 없습니다. 아이디 = " + loginId));

        return loginMember;
    }

    //로그인에 사용
    public Member findByLoginIdAndPassword(String loginId, String password) {

        //로그인할때 입력한 아이디, 비번 가져와 sql문 파라미터에 넣어줌
        Member loginMember = memberRepository.findByLoginIdAndPassword(loginId, password)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원은 없습니다. 아이디 = " + loginId + " 비밀번호 = " + password));

        return loginMember;
    }

    //아이디 찾기에 사용
    public Member findByNameAndPhoneNumber(IdFindForm idFindForm) {

        String name = idFindForm.getName();
        String phoneNumber = idFindForm.getPhoneNumber();

        Member findMember = memberRepository.findByNameAndPhoneNumber(name, phoneNumber)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원은 없습니다. 아이디 = 이름 = " +  name + " 전화번호 = " + phoneNumber));

        return findMember;
    }

    //회원가입
    @Transactional
    public Member save(MemberRegisterForm form) {

        Member member = Member.createMember(form.getLoginId(), form.getPassword(), form.getName(), form.getPhoneNumber(), form.getAddress(),
                                            form.getAddressDetail(), form.getEmail(), form.getAccountHost(), form.getBankName(),
                                            form.getAccountNumber(), form.getHospitalName(), form.getBusinessRegisterNumber(), form.getDoctorLicenseNumber());
        //중복되지 않으면 저장소에 저장하고 저장한 회원 엔티티 반환
        Member registedMember = memberRepository.save(member);

        return registedMember;
    }

    //중복 회원 검증 메서드
    public int existsByLoginId(String joinId) {

        //파라미터로 들어온 Member객체의 아이디가 db에도 존재하는지 비교
        boolean exist = memberRepository.existsByLoginId(joinId);

        //존재한다면
        if (exist) {
            return 1;
        }
        else
            return 0;
    }

    //비밀번호 변경
    @Transactional
    public void updatePassword(long memberId, String password) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원은 없습니다. id = " + memberId+ " 비밀번호 = " + password));
        member.updatePassword(password);
    }

    //이름 변경
    @Transactional
    public void updateName(long memberId, String name) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원은 없습니다. id = " + memberId+ " 이름 = " + name));
        member.updateName(name);
    }

    //번호 변경
    @Transactional
    public void updatePhoneNumber(long memberId, String phoneNumber) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원은 없습니다. id = " + memberId+ " 전화번호 = " + phoneNumber));
        member.updatePhoneNumber(phoneNumber);
    }

    //주소 변경
    @Transactional
    public void updateAddress(long memberId, String address) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원은 없습니다. id = " + memberId+ " 주소 = " + address));
        member.updateAddress(address);
    }

    //상세주소 변경
    @Transactional
    public void updateAddressDetail(long memberId, String addressDetail) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원은 없습니다. id = " + memberId+ " 상세주소 = " + addressDetail));
        member.updateAddressDetail(addressDetail);
    }

    //이메일 변경
    @Transactional
    public void updateEmail(long memberId, String email) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원은 없습니다. id = " + memberId+ " 이메일 = " +email));
        member.updateEmail(email);
    }

    //예금주 변경
    @Transactional
    public void updateAccountHost(long memberId, String accountHost) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원은 없습니다. id = " + memberId+ "  = " + accountHost));
        member.updateAccountHost(accountHost);
    }

    //은행명 변경
    @Transactional
    public void updateBankName(long memberId, String bankName) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원은 없습니다. id = " + memberId+ " 은행명 = " + bankName));
        member.updateBankName(bankName);
    }

    //계좌번호 변경
    @Transactional
    public void updateAccountNumber(long memberId, String accountNumber) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원은 없습니다. id = " + memberId+ " 계좌번호 = " + accountNumber));
        member.updateAccountNumber(accountNumber);
    }

    //병원/ 사업체명 변경
    @Transactional
    public void updateHospitalName(long memberId, String hospitalName) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원은 없습니다. id = " + memberId+ " 병원명 = " + hospitalName));
        member.updateHospitalName(hospitalName);
    }

    //사업자등록번호 변경
    @Transactional
    public void updateBusinessRegisterNumber(long memberId, String businessRegisterNumber) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원은 없습니다. id = " + memberId+ " 사업자등록번호 = " + businessRegisterNumber));
        member.updateBusinessRegisterNumber(businessRegisterNumber);
    }

    //의사면허번호 변경
    @Transactional
    public void updateDoctorLicenseNumber(long memberId, int doctorLicenseNumber) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원은 없습니다. id = " + memberId+ " 의사면허번호 = " + doctorLicenseNumber));
        member.updateDoctorLicenseNumber(doctorLicenseNumber);
    }
}
