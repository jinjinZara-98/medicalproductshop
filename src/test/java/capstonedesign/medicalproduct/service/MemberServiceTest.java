package capstonedesign.medicalproduct.service;

import capstonedesign.medicalproduct.domain.entity.Member;
import capstonedesign.medicalproduct.dto.LoginForm;
import capstonedesign.medicalproduct.dto.MemberRegisterForm;
import capstonedesign.medicalproduct.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@RunWith(SpringRunner.class)와 @SpringBootTest가 있어야
//스프링 인티그레이션 해서 스프링 올려 테스트 가능, 안그럼 @Autowired 다 실패함
//junit실행할때 스프링이랑 엮어서 실행할래
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;

    Member joinedMember;

    //테스트 전 테스트할 회원 먼저 생성
//    @BeforeEach
    public void joinMember() {
        MemberRegisterForm member = new MemberRegisterForm();

        member.setLoginId("gildong123");
        member.setPassword("gildong");
        member.setName("홍길동");
        member.setPhoneNumber("01012345678");
        member.setAddress("충북 충주시 대소원면 대학로 50");
        member.setAddressDetail("예상생활관");
        member.setEmail("gildong123@naver.com");
        member.setAccountHost("홍길동");
        member.setBankName("농협");
        member.setAccountNumber("3520459732493");
        member.setHospitalName(null);
        member.setBusinessRegisterNumber(null);
        member.setDoctorLicenseNumber(null);

        //회원가입된 회원 엔티티 반환
        joinedMember = memberService.join(member);
    }

    @Test
    @DisplayName("회원가입한 회원 확인")
    public void confirmJoinedMember() throws Exception {

        assertThat(joinedMember.getLoginId()).isEqualTo("gildong123");
    }

    @Test
    @DisplayName("로그인 확인")
    public void login() throws Exception{

        Member member = memberService.login("gildong123", "gildong");
        assertThat(member).isNotSameAs(null);
    }

    @Test
    @DisplayName("변경된 회원 비밀번호 확인")
    public void memberPasswordUpdate() throws Exception{

        Member member = memberRepository.findByLoginId("gildong123");
        memberService.updatePassword(member.getId(), "gildong1");
        Member updatedMember = memberRepository.findByLoginId("gildong123");

        assertThat(updatedMember.getPassword()).isEqualTo("gildong1");
    }

    @Test
    @DisplayName("변경된 회원 이름 확인")
    public void memberNameUpdate() throws Exception{

        Member member = memberRepository.findByLoginId("gildong123");
        memberService.updateName(member.getId(), "임꺽정");
        Member updatedMember = memberRepository.findByLoginId("gildong123");

        assertThat(updatedMember.getName()).isEqualTo("임꺽정");
    }

    @Test
    @DisplayName("변경된 회원 전화번호 확인")
    public void memberPhoneNumberUpdate() throws Exception{

        Member member = memberRepository.findByLoginId("gildong123");
        memberService.updatePhoneNumber(member.getId(), "01011112222");
        Member updatedMember = memberRepository.findByLoginId("gildong123");

        assertThat(updatedMember.getPhoneNumber()).isEqualTo("01011112222");
    }

    @Test
    @DisplayName("변경된 회원 주소 확인")
    public void memberAddressUpdate() throws Exception{

        Member member = memberRepository.findByLoginId("gildong123");
        memberService.updateAddress(member.getId(), "대원생활관");
        Member updatedMember = memberRepository.findByLoginId("gildong123");

        assertThat(updatedMember.getAddress() + " " + updatedMember.getAddressDetail())
                .isEqualTo("충북 충주시 대소원면 대학로 50 대원생활관");
    }

    @Test
    @DisplayName("변경된 회원 이메일 확인")
    public void memberEmailUpdate() throws Exception{

        Member member = memberRepository.findByLoginId("gildong123");
        memberService.updateEmail(member.getId(), "gildong456");
        Member updatedMember = memberRepository.findByLoginId("gildong123");

        assertThat(updatedMember.getEmail()).isEqualTo("gildong456");
    }

    @Test
    @DisplayName("변경된 회원 예금주 확인")
    public void memberAccountHostUpdate() throws Exception{

        Member member = memberRepository.findByLoginId("gildong123");
        memberService.updateAccountHost(member.getId(), "임꺽정");
        Member updatedMember = memberRepository.findByLoginId("gildong123");

        assertThat(updatedMember.getAccountHost()).isEqualTo("임꺽정");
    }

    @Test
    @DisplayName("변경된 회원 은행명 확인")
    public void memberBankNameUpdate() throws Exception{

        Member member = memberRepository.findByLoginId("gildong123");
        memberService.updateBankName(member.getId(), "국민은행");
        Member updatedMember = memberRepository.findByLoginId("gildong123");

        assertThat(updatedMember.getBankName()).isEqualTo("국민은행");
    }

    @Test
    @DisplayName("변경된 회원 계좌번호 확인")
    public void memberAccountNumberUpdate() throws Exception{

        Member member = memberRepository.findByLoginId("gildong123");
        memberService.updateAccountNumber(member.getId(), "60694396301015");
        Member updatedMember = memberRepository.findByLoginId("gildong123");

        assertThat(updatedMember.getAccountNumber()).isEqualTo("60694396301015");
    }
}