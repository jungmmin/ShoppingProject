package ShoppingProejct.shoppingmall.controller;

import ShoppingProejct.shoppingmall.Domain.Member.MemberCreateDto;
import ShoppingProejct.shoppingmall.Domain.Member.MemberInfoDto;
import ShoppingProejct.shoppingmall.Domain.Member.MemberLoginDto;
import ShoppingProejct.shoppingmall.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 멤버 컨트롤러
 */
@RestController
@AllArgsConstructor
@Slf4j
public class MemberController {

    private MemberService memberService;

    // 회원가입
    @PostMapping("/users/register")
    public void registerUser(@RequestBody MemberCreateDto memberCreateDto){
        memberService.saveMember(memberCreateDto);
    }

    @PostMapping("/users/login")
    public ResponseEntity<Object> loginUser(@RequestBody MemberLoginDto memberLoginDto){
        Object[] objects = memberService.loginMember(memberLoginDto);

        MemberInfoDto memberInfoDto = (MemberInfoDto) objects[0];
        String jwt = (String) objects[1];

        return ResponseEntity.ok().body(Map.of("user", memberInfoDto, "accessToken", jwt));

    }
}
