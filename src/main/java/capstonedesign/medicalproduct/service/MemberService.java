package capstonedesign.medicalproduct.service;

import capstonedesign.medicalproduct.dto.IdFindForm;
import capstonedesign.medicalproduct.dto.LoginForm;
import capstonedesign.medicalproduct.domain.entity.Member;
import capstonedesign.medicalproduct.dto.MemberRegisterForm;
import capstonedesign.medicalproduct.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //로그인
    public Member login(String loginId, String password) {

        //로그인할때 입력한 아이디, 비번 가져와 sql문 파라미터에 넣어줌
        Member loginMember = memberRepository.findByLoginIdAndPassword(loginId, password);

        return loginMember;
    }

    public Member findLoginId(IdFindForm idFindForm) {

        String name = idFindForm.getName();
        String phoneNumber = idFindForm.getPhoneNumber();

        Member findMember = memberRepository.findByNameAndPhoneNumber(name, phoneNumber);

        return findMember;
    }

    //회원가입
    @Transactional
    public Member join(MemberRegisterForm form) {

        Member member = Member.createMember(form.getLoginId(), form.getPassword(), form.getName(), form.getPhoneNumber(), form.getAddress(),
                                            form.getAddressDetail(), form.getEmail(), form.getAccountHost(), form.getBankName(),
                                            form.getAccountNumber(), form.getHospitalName(), form.getBusinessRegisterNumber(), form.getDoctorLicenseNumber());
        //중복되지 않으면 저장소에 저장하고 저장한 회원 엔티티 반환
        Member registedMember = memberRepository.save(member);

        return registedMember;
    }

    //중복 회원 검증 메서드
    public int validateDuplicateMember(String joinId) {

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
    public void updatePassword(long id, String password) {
        Member member = memberRepository.findById(id).get();
        member.updatePassword(password);
    }

    //이름 변경
    @Transactional
    public void updateName(long id, String name) {
        Member member = memberRepository.findById(id).get();
        member.updateName(name);
    }

    //번호 변경
    @Transactional
    public void updatePhoneNumber(long id, String phoneNumber) {
        Member member = memberRepository.findById(id).get();
        member.updatePhoneNumber(phoneNumber);
    }

    //주소 변경
    @Transactional
    public void updateAddress(long id, String address) {
        Member member = memberRepository.findById(id).get();
        member.updateAddress(address);
    }

    //상세주소 변경
    @Transactional
    public void updateAddressDetail(long id, String addressDetail) {
        Member member = memberRepository.findById(id).get();
        member.updateAddressDetail(addressDetail);
    }

    //이메일 변경
    @Transactional
    public void updateEmail(long id, String email) {
        Member member = memberRepository.findById(id).get();
        member.updateEmail(email);
    }

    //예금주 변경
    @Transactional
    public void updateAccountHost(long id, String accountHost) {
        Member member = memberRepository.findById(id).get();
        member.updateAccountHost(accountHost);
    }

    //은행명 변경
    @Transactional
    public void updateBankName(long id, String bankName) {
        Member member = memberRepository.findById(id).get();
        member.updateBankName(bankName);
    }

    //계좌번호 변경
    @Transactional
    public void updateAccountNumber(long id, String accountNumber) {
        Member member = memberRepository.findById(id).get();
        member.updateAccountNumber(accountNumber);
    }

    //병원/ 사업체명 변경
    @Transactional
    public void updateHospitalName(long id, String hospitalName) {
        Member member = memberRepository.findById(id).get();
        member.updateHospitalName(hospitalName);
    }

    //사업자등록번호 변경
    @Transactional
    public void updateBusinessRegisterNumber(long id, String businessRegisterNumber) {
        Member member = memberRepository.findById(id).get();
        member.updateBusinessRegisterNumber(businessRegisterNumber);
    }

    //의사면허번호 변경
    @Transactional
    public void updateDoctorLicenseNumber(long id, int doctorLicenseNumber) {
        Member member = memberRepository.findById(id).get();
        member.updateDoctorLicenseNumber(doctorLicenseNumber);
    }
}
