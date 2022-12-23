package capstonedesign.medicalproduct.restAPIController;

import capstonedesign.medicalproduct.dto.api.MemberAPIDto;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemberAPIControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("특정 회원 조회")
    public void findMember() throws Exception{

        String url = "http://localhost:" + port + "/api/member/1";
        MemberAPIDto memberDto = restTemplate.getForObject(url, MemberAPIDto.class);
    }
}