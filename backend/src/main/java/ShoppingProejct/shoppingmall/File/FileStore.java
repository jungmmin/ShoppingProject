package ShoppingProejct.shoppingmall.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 여러개의 파일을 저장
 */
@Component
public class FileStore {

    @Value("${file.dir}")
    private String fileDir;

    // 서버 폴더에 저장할 경로
    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    // 이미지 여러개 저장
    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadFile> storeFileResult = new ArrayList<>();

        // 반복문으로 파일 여러개 저장
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                storeFileResult.add(storeFile(multipartFile));
            }
        }
        return storeFileResult;
    }

    // multipartFile 으로 이미지 저장
    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {

        if (multipartFile.isEmpty()) {
            return null;
        }

        // 사용자가 보낸 이미지 이름
        String originalFilename = multipartFile.getOriginalFilename();
        // 확장자명 붙인 랜덤 이름
        String storeFileName = createStoreFileName(originalFilename);

        multipartFile.transferTo(new File(getFullPath(storeFileName)));

        return new UploadFile(originalFilename, storeFileName);

    }

    // 서버에 저장할 파일이름 생성
    private static String createStoreFileName(String originalFilename) {
        // 확장자 명 가져오기
        String ext = extracted(originalFilename);

        // 서버에 저장할 파일 이름
        String uuid = UUID.randomUUID().toString();

        return uuid + "." + ext;

    }

    // 파일의 확장자 명 가져오기
    private static String extracted(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
