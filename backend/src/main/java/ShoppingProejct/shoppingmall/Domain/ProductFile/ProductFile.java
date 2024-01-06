package ShoppingProejct.shoppingmall.Domain.ProductFile;

import ShoppingProejct.shoppingmall.Domain.File.File;
import ShoppingProejct.shoppingmall.Domain.Product.Product;
import jakarta.persistence.*;

/**
 * 이미지, 상품 정보 저장 entity
 */
@Entity
public class ProductFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_file_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "file_id")
    private File file;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
