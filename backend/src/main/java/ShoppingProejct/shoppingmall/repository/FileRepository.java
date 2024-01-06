package ShoppingProejct.shoppingmall.repository;

import ShoppingProejct.shoppingmall.Domain.File.File;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FileRepository {

    private final EntityManager em;

    /**
     * 파일 저장
     */
    public void save(File file){
        em.persist(file);
    }

    /**
     *  파일이름 리스트로 파일 아이디 리스트 찾기
     */
    public List<File> findByNames(List<String> fileNames) {

        List<File> Files = em.createQuery("select f from File f where f.filename in :fileNames", File.class)
                .setParameter("fileNames", fileNames)
                .getResultList();

        return Files;
    }



}
