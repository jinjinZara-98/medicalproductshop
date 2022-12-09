package capstonedesign.medicalproduct.exhandler.advice;

import capstonedesign.medicalproduct.exception.DuplicateIdException;
import capstonedesign.medicalproduct.exception.InvalidRegisteredValueException;
import capstonedesign.medicalproduct.exception.InvalidUpdatedValueException;
import capstonedesign.medicalproduct.exception.NotExistMemberException;
import capstonedesign.medicalproduct.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice(basePackages = "capstonedesign.medicalproduct.restApiController")
public class ExControllerAdvice {

    //회원 가입할 때 중복 아이디 체크
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(DuplicateIdException.class)
    public ErrorResult DuplicateIdExHandler(DuplicateIdException e) {

        return new ErrorResult("FORBIDDEN", "이 아이디는 다른 회원이 사용중입니다.");
    }

    //조회하려는 회원 존재하지 않을 때 발생하는 예외 컨트롤러에서 발생하면 잡는다
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotExistMemberException.class)
    public ErrorResult NotExistMemberExHandler(NotExistMemberException e) {

        return new ErrorResult("NOT_FOUND", "해당 회원은 존재하지 않습니다.");
    }

    //회원 정보를 수정하려는 값에 에러가 있을 때 발생하는 예외 컨트롤러에서 발생하면 잡는다
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(InvalidUpdatedValueException.class)
    public ErrorResult InvalidUpdatedValueExHandler(InvalidUpdatedValueException e) {

        return new ErrorResult("FORBIDDEN", "입력하신 값으로는 회원 정보를 수정할 수 없습니다.");
    }

    //회원 가입하려는 값에 에러가 있을 때 발생하는 예외 컨트롤러에서 발생하면 잡는다
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(InvalidRegisteredValueException.class)
    public ErrorResult InvalidRegisteredValuerExHandler(InvalidRegisteredValueException e) {

        return new ErrorResult("FORBIDDEN", "입력하신 값으로는 회원 가입을 할 수 없습니다..");
    }
}