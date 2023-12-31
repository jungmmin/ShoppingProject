package ShoppingProejct.shoppingmall.File;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 업로드할때 파일 형식
 */
@Data
@AllArgsConstructor
public class UploadFile {

    private String uploadFileName;
    private String storeFileName;
}
