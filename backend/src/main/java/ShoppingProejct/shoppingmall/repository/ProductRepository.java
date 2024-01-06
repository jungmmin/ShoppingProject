package ShoppingProejct.shoppingmall.repository;

import ShoppingProejct.shoppingmall.Domain.Product.Product;
import ShoppingProejct.shoppingmall.Domain.Product.ProductSearchDto;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final EntityManager em;

    /**
     * 상품 등록
     */

    public void saveProduct(Product product) {
        em.persist(product);
    }

    public Product findById(Long id) {
        return em.find(Product.class, id);
    }

    public List<Product> findAll(ProductSearchDto productSearchDto) {

        return em.createQuery("select p from Product p where p.title like :searchTerm", Product.class)
                .setParameter("searchTerm", "%" + productSearchDto.getSearchTerm() + "%")
                .getResultList();

    }
}
