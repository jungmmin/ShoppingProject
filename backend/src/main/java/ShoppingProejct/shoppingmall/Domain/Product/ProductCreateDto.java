package ShoppingProejct.shoppingmall.Domain.Product;

import lombok.Data;

import java.util.List;

@Data
public class ProductCreateDto {

    private String title;
    private String description;
    private int price;
    private int continents;
    private List<String> images;

}
