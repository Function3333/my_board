package project.myBoard.serviceTest;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.myBoard.dto.PostDto;
import project.myBoard.dto.ReplyDto;
import project.myBoard.entity.Post;
import project.myBoard.entity.Reply;
import project.myBoard.service.ReplyService;

@SpringBootTest
@Transactional
public class ReplyServiceTest {
    @Autowired
    ReplyService replyService;

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("연관관계 테스트")
    void relationTest() {
        Post post = new Post().createPost(createPost("title", "content"), null, null);

        Long saveId = replyService.createReply(createReplyDto("content"), post);

        Reply findReply = em.find(Reply.class, saveId);
        Assertions.assertThat(findReply.getPost()).isEqualTo(post);
    }

    @Test
    @DisplayName("reply 수정 테스트")
    void editReply() {
        Post post = new Post().createPost(createPost("title", "content"), null, null);

        Long saveId = replyService.createReply(createReplyDto("content"), post);

        replyService.editReply(createReplyDto("edit"), saveId);

        Reply findReply = em.find(Reply.class, saveId);
        Assertions.assertThat(findReply.getContent()).isEqualTo("edit");
    }

    @Test
    @DisplayName("reply 삭제 테스트")
    void deleteReply() {
        Post post = new Post().createPost(createPost("title", "content"), null, null);

        Long saveId = replyService.createReply(createReplyDto("content"), post);

        replyService.deleteReply(saveId);

        Reply findReply = em.find(Reply.class, saveId);
        Assertions.assertThat(findReply).isNull();
    }


    public PostDto createPost(String title, String content) {
        PostDto postDto = new PostDto();
        postDto.setTitle(title);
        postDto.setContent(content);

        return postDto;
    }

    public ReplyDto createReplyDto(String content) {
        ReplyDto replyDto = new ReplyDto();
        replyDto.setContent(content);

        return replyDto;
    }
}
