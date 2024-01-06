package ShoppingProejct.shoppingmall.service;

import ShoppingProejct.shoppingmall.Domain.File.File;
import ShoppingProejct.shoppingmall.Domain.Member.Member;
import ShoppingProejct.shoppingmall.Domain.Product.Product;
import ShoppingProejct.shoppingmall.Domain.Product.ProductCreateDto;
import ShoppingProejct.shoppingmall.Domain.Product.ProductInfoDto;
import ShoppingProejct.shoppingmall.Domain.Product.ProductSearchDto;
import ShoppingProejct.shoppingmall.Domain.ProductFile.ProductFile;
import ShoppingProejct.shoppingmall.repository.FileRepository;
import ShoppingProejct.shoppingmall.repository.MemberRepository;
import ShoppingProejct.shoppingmall.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final FileRepository fileRepository;


    // 상품 저장
    @Transactional
    public void saveProduct(ProductCreateDto productCreateDto, Long memberId) {

        // 회원 정보 찾기
        Member findMember = memberRepository.findById(memberId);

        // product entity로 변환
        Product product = productCreateDto.dtoToEntity(findMember);

        // 이미지 id list 조회
        List<File> files = fileRepository.findByNames(productCreateDto.getImages());
        log.info("조회할 이미지 이름 목록 = {}", productCreateDto.getImages());
        log.info("이미지 목록 = {}", files);

        //cascade로 전파하여 저장
        product.updateImages(files);

        log.info("저장될 상품 = {}", product);

        productRepository.saveProduct(product);

    }

    public ProductInfoDto findProduct(Long productId) {
        return ProductInfoDto.EntityToDto(productRepository.findById(productId));
    }

    public List<ProductInfoDto> findAll(ProductSearchDto productSearchDto) {

        List<Product> products = productRepository.findAll(productSearchDto);

        return products.stream().map(ProductInfoDto::EntityToDto).toList();
    }

}
