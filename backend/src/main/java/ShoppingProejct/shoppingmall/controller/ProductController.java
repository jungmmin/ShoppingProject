package ShoppingProejct.shoppingmall.controller;

import ShoppingProejct.shoppingmall.Domain.Product.Product;
import ShoppingProejct.shoppingmall.Domain.Product.ProductCreateDto;
import ShoppingProejct.shoppingmall.Domain.Product.ProductInfoDto;
import ShoppingProejct.shoppingmall.Domain.Product.ProductSearchDto;
import ShoppingProejct.shoppingmall.File.FileItemForm;
import ShoppingProejct.shoppingmall.File.FileStore;
import ShoppingProejct.shoppingmall.File.UploadFile;
import ShoppingProejct.shoppingmall.service.FileService;
import ShoppingProejct.shoppingmall.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@AllArgsConstructor
public class ProductController {

    private final FileService fileService;
    private final ProductService productService;

    // 상품 이미지 등록
    @PostMapping("/products/image")
    public ResponseEntity<Map<String, String>> uploadImage(@ModelAttribute FileItemForm fileItemForm) throws IOException {

        String fileName = fileService.saveImage(fileItemForm);

        return ResponseEntity.ok().body(Map.of("fileName", fileName));
    }

    /**
     * DB 저장 필요
     * 스프링 시큐리티 Auth 검사 및 멤버 아이디 가져오기 필요
     */
    @PostMapping("/products")
    public void uploadProduct(@RequestBody ProductCreateDto productCreateDto, HttpServletRequest request) {
        long memberId = Long.parseLong(String.valueOf(request.getAttribute("auth_memberId")));

        log.info("요청 아이디 = {}", memberId);
        log.info("상품 업로드 = {}", productCreateDto);

        productService.saveProduct(productCreateDto, memberId);

    }

    /**
     * 상품 조회
     */
    @GetMapping("/products")
    public ResponseEntity<Map<String, Object>> getProducts(@ModelAttribute ProductSearchDto productSearchDto) {
        log.info("상품 조회 = {}", productSearchDto);

        // 전체 상품 조회
        List<ProductInfoDto> list = productService.findAll(productSearchDto);

        // 더보기 여부
        boolean hasMore = false;
        if (productSearchDto.getSkip() + productSearchDto.getLimit() < list.size()) {
            hasMore = true;
        }

        log.info("hasMore = {}", hasMore);
        return ResponseEntity.ok().body(Map.of("products", list, "hasMore", hasMore));

    }

    @GetMapping("/products/{id}")
    public ProductInfoDto getProduct(@PathVariable("id") Long productId) {
        return productService.findProduct(productId);
    }
}
