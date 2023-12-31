package ShoppingProejct.shoppingmall.File;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 사용자로부터 파일을 받는 형식
 */
@Data
public class FileItemForm {

    private List<MultipartFile> imageFiles;

}
