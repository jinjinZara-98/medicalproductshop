package capstonedesign.medicalproduct.repository;

import capstonedesign.medicalproduct.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //테스트에 사용
    Optional<Member> findByLoginId(String loginId);

    //중복 아이디가 있는지 검증하기 위한
    boolean existsByLoginId(String loginId);

    //로그인시 아이디와 비번이 일치한지 확인
    Optional<Member> findByLoginIdAndPassword(String loginId, String password);

    Optional<Member> findByNameAndPhoneNumber(String name, String phoneNumber);
}
