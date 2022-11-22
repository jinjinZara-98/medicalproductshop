package capstonedesign.medicalproduct.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//회원 엔티티
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    //member테이블의 id열과 매칭
    @Column(name = "member_id")
    private Long id;

    //로그인 ID, notnull
    @Column(nullable = false)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String addressDetail;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String accountHost;

    @Column(nullable = false)
    private String bankName;

    @Column(nullable = false)
    private String accountNumber;

    private String hospitalName;

    private String businessRegisterNumber;

    private Integer doctorLicenseNumber;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Cart> carts= new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Review> reviews= new ArrayList<>();

    @Builder
    public Member(Long id, String loginId, String password, String name, String phoneNumber, String address,
                  String addressDetail, String email, String accountHost, String bankName, String accountNumber,
                  String hospitalName, String businessRegisterNumber, Integer doctorLicenseNumber, List<Order> orders,
                  List<Cart> carts, List<Review> reviews) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.addressDetail = addressDetail;
        this.email = email;
        this.accountHost = accountHost;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.hospitalName = hospitalName;
        this.businessRegisterNumber = businessRegisterNumber;
        this.doctorLicenseNumber = doctorLicenseNumber;
        this.orders = orders;
        this.carts = carts;
        this.reviews = reviews;
    }

    public static Member createMember(String loginId, String password, String name, String phoneNumber,
                                      String address, String addressDetail, String email, String accountHost, String bankName, String accountNumber,
                                      String hospitalName, String businessRegisterNumber, Integer doctorLicenseNumber) {

        Member member = Member.builder()
                .loginId(loginId)
                .password(password)
                .name(name)
                .phoneNumber(phoneNumber)
                .address(address)
                .addressDetail(addressDetail)
                .email(email)
                .accountHost(accountHost)
                .bankName(bankName)
                .accountNumber(accountNumber)
                .hospitalName(hospitalName)
                .businessRegisterNumber(businessRegisterNumber)
                .doctorLicenseNumber(doctorLicenseNumber).build();

        return member;
    }

    //비밀번호 변경
    public void updatePassword(String password) {
        this.password = password;
    }

    //이름 변경
    public void updateName(String name) {
        this.name = name;
    }

    //전화버호 변경
    public void updatePhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    //주소 변경
    public void updateAddress(String address) {
        this.address = address;
    }

    //상세 주소 변경
    public void updateAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    //이메일 변경
    public void updateEmail(String email) {
        this.email = email;
    }

    //예금주 변경
    public void updateAccountHost(String accountHost) {
        this.accountHost = accountHost;
    }

    //은행명 변경
    public void updateBankName(String bankName) {
        this.bankName = bankName;
    }

    //계좌번호 변경
    public void updateAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    //병원/ 사업체명 변경
    public void updateHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    //사업자등록번호 변경
    public void updateBusinessRegisterNumber(String businessRegisterNumber) {
        this.businessRegisterNumber = businessRegisterNumber;
    }

    //의사면허번호 변경
    public void updateDoctorLicenseNumber(int doctorLicenseNumber) {
        this.doctorLicenseNumber = doctorLicenseNumber;
    }
}
