package ShoppingProejct.shoppingmall.controller;

import ShoppingProejct.shoppingmall.Domain.Cart.CartAddDto;
import ShoppingProejct.shoppingmall.Domain.Cart.CartInfoDto;
import ShoppingProejct.shoppingmall.Domain.Product.ProductInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CartController {

    @PostMapping("/users/cart")
    public ProductInfoDto addCart(@RequestBody CartAddDto cartAddDto) {

        log.info("productId = {}", cartAddDto.getProductId());
        int i = 1;

        return null;
    }

    @GetMapping("user/getCartItems")
    public ResponseEntity<Map<String, List<CartInfoDto>>> getCart() {

        List<CartInfoDto> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            CartInfoDto productInfoDto = new CartInfoDto(
              i,
                    "title" + i,
                    "des" + i,
                    i,
                    i + 100,
                    i+5
            );
            list.add(productInfoDto);
        }

        return ResponseEntity.ok().body(Map.of("carts", list));
    }

}
