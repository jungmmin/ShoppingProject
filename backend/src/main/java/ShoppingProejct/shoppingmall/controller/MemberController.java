package ShoppingProejct.shoppingmall.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @PostMapping("/users/register")
    public String registerUser(){

        return "확인";
    }
}
