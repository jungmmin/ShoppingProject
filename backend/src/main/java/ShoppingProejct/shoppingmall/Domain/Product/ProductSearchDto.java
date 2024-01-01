package ShoppingProejct.shoppingmall.Domain.Product;

import lombok.Data;

import java.util.List;

@Data
public class ProductSearchDto {
    private int skip;
    private int limit;
    private String searchTerm;
}
