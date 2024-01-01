package ShoppingProejct.shoppingmall.Domain.Product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductInfoDto {

    private int _id;
    private int continents;
    private String description;
    private String[] images;
    private int price;
    private int sold;
    private String title;
    private int views;
    private String member = "member";
}
