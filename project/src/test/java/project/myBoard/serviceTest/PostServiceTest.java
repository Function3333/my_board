package project.myBoard.serviceTest;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.myBoard.dto.PostDto;
import project.myBoard.entity.Account;
import project.myBoard.entity.Board;
import project.myBoard.entity.Post;
import project.myBoard.service.ImageService;
import project.myBoard.service.PostService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class PostServiceTest {
    @Autowired
    private PostService postService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("게시글 저장")
    void addPost() {
        Account account = new Account();
        em.persist(account);

        Board board = new Board();
        em.persist(board);

        PostDto postDto = createPostDto();
        Long saveId = postService.addPost(postDto, account.getId(), board.getId());


        Post findPost = em.find(Post.class, saveId);

        Assertions.assertThat(findPost.getImages().size()).isEqualTo(3);
    }


    public static PostDto createPostDto()  {
        PostDto postDto = new PostDto();
        postDto.setContent("postA");
        postDto.setTitle("postA");
        postDto.setFiles(createFiles());

        return postDto;
    }

    public static List<MultipartFile> createFiles() {
        List<MultipartFile> multipartFileList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            String text = String.valueOf(i);
            byte[] bytes = text.getBytes(StandardCharsets.UTF_8);

            MultipartFile multipartFile = new MockMultipartFile(i + ".text", bytes);
            multipartFileList.add(multipartFile);
        }

        return multipartFileList;
    }

}
