package ShoppingProejct.shoppingmall.exception.advice;

import ShoppingProejct.shoppingmall.exception.ErrorResult;
import ShoppingProejct.shoppingmall.exception.JwtException;
import ShoppingProejct.shoppingmall.exception.LoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ValidationControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(JwtException.class)
    public ErrorResult jwtExHandler(JwtException e) {
        log.error("[LoginException]", e);
        return new ErrorResult("BAD", e.getMessage());
    }
}
