package capstonedesign.medicalproduct.exhandler.advice;

import capstonedesign.medicalproduct.exception.DuplicateIdException;
import capstonedesign.medicalproduct.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "capstonedesign.medicalproduct.restApiController")
public class ExControllerAdvice {

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(DuplicateIdException.class)
    public ErrorResult DuplicateIdExHandler(DuplicateIdException e) {

        return new ErrorResult("FORBIDDEN", "이 아이디는 다른 회원이 사용중입니다.");
    }
}