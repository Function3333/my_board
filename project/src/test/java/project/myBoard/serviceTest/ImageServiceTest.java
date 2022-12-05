package project.myBoard.serviceTest;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.myBoard.entity.Post;
import project.myBoard.service.ImageService;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class ImageServiceTest {
    @Autowired
    private ImageService imageService;

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("이미지 불러오기 테스트")
     void findImage() {
        Resource image = imageService.getImage("testImage");

        Assertions.assertThat(image.getFilename()).isEqualTo("testImage");
    }

    @Test
    @DisplayName("이미지 저장 테스트")
    void saveImage() {
        String text = "text";
        byte[] bytes = text.getBytes(StandardCharsets.UTF_8);

        Post post = new Post();
        post.setTitle("test");
        em.persist(post);

        List<MultipartFile> multipartFileList = new ArrayList<>();
        MultipartFile multipartFile = new MockMultipartFile("saveImage.text", bytes);
        multipartFileList.add(multipartFile);

        imageService.saveImage(multipartFileList, post);

        Resource saveImage = imageService.getImage("saveImage");

        Assertions.assertThat(saveImage.getFilename()).isEqualTo("saveImage");
    }
}
