package ShoppingProejct.shoppingmall.controller;

import ShoppingProejct.shoppingmall.File.FileItemForm;
import ShoppingProejct.shoppingmall.File.FileStore;
import ShoppingProejct.shoppingmall.File.UploadFile;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class ProductController {

    private final FileStore fileStore;

    @PostMapping("/products")
    public void newProduct(@ModelAttribute FileItemForm fileItemForm) throws IOException {

        List<MultipartFile> imagesFiles = fileItemForm.getImageFiles();
        List<UploadFile> uploadFiles = fileStore.storeFiles(imagesFiles);


    }
}
