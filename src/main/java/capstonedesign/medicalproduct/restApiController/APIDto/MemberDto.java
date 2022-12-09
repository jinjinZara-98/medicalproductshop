package capstonedesign.medicalproduct.restApiController.APIDto;

import capstonedesign.medicalproduct.domain.entity.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//테스트 코드 작성할 때 없으면 에러남
@NoArgsConstructor
public class MemberDto {
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
//        private List<Order> orders = new ArrayList<>();
//        private List<Cart> carts= new ArrayList<>();
//        private List<Review> reviews= new ArrayList<>();

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
//            this.orders = member.getOrders();
//            this.carts = member.getCarts();
//            this.reviews = member.getReviews();
    }
}
