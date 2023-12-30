package ShoppingProejct.shoppingmall.controller;

import ShoppingProejct.shoppingmall.Domain.Member.MemberCreateDto;
import ShoppingProejct.shoppingmall.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
        log.info("회원가입 정보 확인 = {}" , memberCreateDto.toString());
        memberService.saveMember(memberCreateDto);
    }
}
