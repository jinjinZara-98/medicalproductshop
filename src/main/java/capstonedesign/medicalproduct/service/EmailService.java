package capstonedesign.medicalproduct.service;

import capstonedesign.medicalproduct.domain.entity.Member;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {

    private JavaMailSender emailSender;

    public void sendPassword(Member member) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("Mr.Heo55@gmail.com");
        message.setTo(member.getEmail());
        message.setSubject("JJ Medical " + member.getName()  + " 회원님의 비밀번호입니다.");
        message.setText(member.getPassword());
        emailSender.send(message);
    }
}