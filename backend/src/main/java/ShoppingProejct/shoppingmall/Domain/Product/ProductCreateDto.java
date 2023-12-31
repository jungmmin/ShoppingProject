package ShoppingProejct.shoppingmall.Domain.Product;

import lombok.Data;

@Data
public class ProductCreateDto {

    private String title;
    private String description;
    private int price;
    private String continents;

}
