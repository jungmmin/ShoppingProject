package ShoppingProejct.shoppingmall.Domain.Product;

import ShoppingProejct.shoppingmall.Domain.Member.Member;
import lombok.Data;

import java.util.List;

@Data
public class ProductCreateDto {

    private String title;
    private String description;
    private int price;
    private int continents;
    private List<String> images;

    public List<String> getImages() {
        return images.stream()
                .map(image -> image.substring(10))
                .toList();

    }



    public Product dtoToEntity(Member member) {
        return Product.builder()
                .member(member)
                .title(title)
                .description(description)
                .price(price)
                .continents(continents).build();
    }
}
