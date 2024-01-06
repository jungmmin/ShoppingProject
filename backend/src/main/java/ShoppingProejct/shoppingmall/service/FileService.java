package ShoppingProejct.shoppingmall.service;

import ShoppingProejct.shoppingmall.Domain.File.File;
import ShoppingProejct.shoppingmall.File.FileItemForm;
import ShoppingProejct.shoppingmall.File.FileStore;
import ShoppingProejct.shoppingmall.File.UploadFile;
import ShoppingProejct.shoppingmall.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FileService {

    private final FileStore fileStore;
    private final FileRepository fileRepository;

    /**
     * 이미지 업로드
     */
    @Transactional
    public String saveImage(FileItemForm fileItemForm) throws IOException {
        // 이미지 업로드
        List<MultipartFile> imagesFiles = fileItemForm.getImageFiles();
        List<UploadFile> uploadFiles = fileStore.storeFiles(imagesFiles);


        // 이미지 정보 DB에 저장
        fileRepository.save(new File(uploadFiles.get(0).getStoreFileName()));


        return "../images/" + uploadFiles.get(0).getStoreFileName();
    }
}
