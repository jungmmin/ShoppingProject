package ShoppingProejct.shoppingmall.controller;

import ShoppingProejct.shoppingmall.Domain.Member.Member;
import ShoppingProejct.shoppingmall.Domain.Member.MemberInfoDto;
import ShoppingProejct.shoppingmall.repository.MemberRepository;
import ShoppingProejct.shoppingmall.security.jwt.JwtProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
public class ValidationController {

    private final JwtProvider jwtProvider;
    private final MemberRepository memberRepository;

    // 토큰 검증
    @GetMapping("/auth")
    public MemberInfoDto registerUser(@RequestHeader("Authorization") String authorization){
        String[] authArray = authorization.split(" ");

        String jwt = authArray[1];

        log.info("검증 jwt = {}", jwt);
        // jwt 검증
        jwtProvider.validationToken(jwt);

        // jwt memberId 조회
        jwtProvider.validationToken(jwt);

        int memberId = jwtProvider.getMemberId(jwt);
        log.info("멤버 아이디 = {}", memberId);

        //멤버 조회
        Member findMember = memberRepository.findById(memberId);

        return MemberInfoDto.entityToDto(findMember);

    }
    @GetMapping("users/logout")
    public void logoutUser(){
        log.info("로그아웃 확인");
    }
}
