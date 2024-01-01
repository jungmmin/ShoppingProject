package ShoppingProejct.shoppingmall.Domain.Cart;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartInfoDto {

    private int id;
    private String title;
    private String description;
    private int price;
    private int quantity;
    private int views;

}
