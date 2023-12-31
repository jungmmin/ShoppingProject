package ShoppingProejct.shoppingmall.controller;

import ShoppingProejct.shoppingmall.Domain.Member.MemberCreateDto;
import ShoppingProejct.shoppingmall.Domain.Product.ProductCreateDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
public class ProductController {

    @PostMapping("/products")
    public void registerUser(@RequestBody ProductCreateDto productCreateDto){
        log.info("상품 업로드 = {}", productCreateDto);


    }
}
