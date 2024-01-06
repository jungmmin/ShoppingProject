package ShoppingProejct.shoppingmall.Domain.Product;

import ShoppingProejct.shoppingmall.Domain.File.File;
import ShoppingProejct.shoppingmall.Domain.Member.Member;
import ShoppingProejct.shoppingmall.Domain.ProductFile.ProductFile;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Entity
@ToString
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;

    private String description;

    private int price;

    private int continents;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<File> files = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public Product() {

    }

    @Builder
    public Product(Member member, String title, String description, int price, int continents) {
        this.member = member;
        this.title = title;
        this.description = description;
        this.price = price;
        this.continents = continents;
    }

    public void updateImages(List<File> files) {
        files.forEach(file -> file.updateProduct(this));
        this.files = files;
    }
}
