package jeju.oneroom.exception;

import jeju.oneroom.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

// Controller 단의 에러 처리
@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    // RequestBody에 대한 에러 핸들링
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        return ErrorResponse.of(e.getBindingResult());
    }

    // URI 변수에 대한 에러 핸들링
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstraintViolationException(ConstraintViolationException e){
        return ErrorResponse.of(e.getConstraintViolations());
    }

    @ExceptionHandler(BusinessLogicException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBusinessLogicException(BusinessLogicException e) {
        log.error("Business logic exception occurred: {}", e.getMessage());
        return ErrorResponse.of(e);
    }
}
