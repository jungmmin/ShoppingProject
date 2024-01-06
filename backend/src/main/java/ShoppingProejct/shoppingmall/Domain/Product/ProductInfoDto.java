package ShoppingProejct.shoppingmall.Domain.Product;

import ShoppingProejct.shoppingmall.Domain.File.File;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ProductInfoDto {

    private Long _id;
    private int continents;
    private String description;
    private List<String> images;
    private int price;
    private int sold;
    private String title;
    private int views;
//    private String member = "member";

    public static ProductInfoDto EntityToDto(Product product) {
        List<String> productImages = product.getFiles().stream().map(file -> "../images/" + file.getFilename()).toList();

        return ProductInfoDto.builder()
                ._id(product.getProductId())
                .continents(product.getContinents())
                .description(product.getDescription())
                .images(productImages)
                .price(product.getPrice())
                .sold(0)
                .title(product.getTitle())
                .views(0).build()
        ;
    }


    public ProductInfoDto() {
    }
}
