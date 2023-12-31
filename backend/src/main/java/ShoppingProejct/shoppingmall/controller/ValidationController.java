package ShoppingProejct.shoppingmall.controller;

import ShoppingProejct.shoppingmall.Domain.Member.MemberCreateDto;
import ShoppingProejct.shoppingmall.security.jwt.JwtProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
public class ValidationController {

    private final JwtProvider jwtProvider;

    // 토큰 검증
    @GetMapping("/auth")
    public void registerUser(@RequestParam("jwt") String jwt){
        log.info("검증 jwt = {}", jwt);
        jwtProvider.validationToken(jwt);

    }
}
