package ShoppingProejct.shoppingmall.Domain.Product;

import lombok.Data;

@Data
public class ProductSearchDto {
    private int skip;
    private int limit;
    private String searchTerm;
}
