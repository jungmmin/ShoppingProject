package ShoppingProejct.shoppingmall.controller;

import ShoppingProejct.shoppingmall.Domain.Product.ProductCreateDto;
import ShoppingProejct.shoppingmall.Domain.Product.ProductInfoDto;
import ShoppingProejct.shoppingmall.Domain.Product.ProductSearchDto;
import ShoppingProejct.shoppingmall.File.FileItemForm;
import ShoppingProejct.shoppingmall.File.FileStore;
import ShoppingProejct.shoppingmall.File.UploadFile;
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

    private final FileStore fileStore;

    // 상품 이미지 등록
    @PostMapping("/products/image")
    public ResponseEntity<Map<String, String>> uploadImage(@ModelAttribute FileItemForm fileItemForm) throws IOException {

        List<MultipartFile> imagesFiles = fileItemForm.getImageFiles();
        List<UploadFile> uploadFiles = fileStore.storeFiles(imagesFiles);

        return ResponseEntity.ok().body(Map.of("fileName", "https://i.imgur.com/rDZbFUA.png"));
    }

    /**
     * DB 저장 필요
     * 스프링 시큐리티 Auth 검사 및 멤버 아이디 가져오기 필요
     */
    @PostMapping("/products")
    public void uploadProduct(@RequestBody ProductCreateDto productCreateDto, HttpServletRequest request) {
        int memberId = (Integer) request.getAttribute("auth_memberId");
        log.info("요청 아이디 = {}", memberId);
        log.info("상품 업로드 = {}", productCreateDto);

    }

    /**
     * 상품 조회
     */
    @GetMapping("/products")
    public ResponseEntity<Map<String, Object>> getProducts(@ModelAttribute ProductSearchDto productSearchDto) {
        log.info("상품 조회 = {}", productSearchDto);

        List<ProductInfoDto> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ProductInfoDto productInfoDto = new ProductInfoDto(
                    i + 1,
                    1,
                    "des" + i,
                    new String[]{"https://i.imgur.com/rDZbFUA.png", "https://i.imgur.com/rDZbFUA.png"},
                    i + 1,
                    i + 2,
                    "title" + i,
                    i + 3,
                    "member" + 1
            );
            list.add(productInfoDto);
        }

        boolean hasMore = false;
        if (productSearchDto.getSkip() + productSearchDto.getLimit() < 12) {
            hasMore = true;
        }
        log.info("hasMore = {}", hasMore);
        return ResponseEntity.ok().body(Map.of("products", list, "hasMore", hasMore));

    }

    @GetMapping("/products/{id}")
    public ProductInfoDto getProduct(@PathVariable("id") String productId) {
        int i = 1;
        return new ProductInfoDto(
                i + 1,
                1,
                "des" + i,
                new String[]{"https://i.imgur.com/rDZbFUA.png", "https://i.imgur.com/rDZbFUA.png"},
                i + 1,
                i + 2,
                "title" + i,
                i + 3,
                "member" + 1
        );
    }
}
