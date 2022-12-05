package project.myBoard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.myBoard.dto.ReplyDto;
import project.myBoard.entity.Post;
import project.myBoard.entity.Reply;
import project.myBoard.repository.ReplyRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;

    @Transactional
    public Long createReply(ReplyDto replyDto, Post post) {
        Reply reply = new Reply().createReply(replyDto, post);
        return replyRepository.save(reply);
    }

    @Transactional
    public void editReply(ReplyDto replyDto, Long reply_id) {
        Reply reply = replyRepository.findById(reply_id);
        reply.editReply(replyDto);
    }

    public List<Reply> findByPostId(Post post) {
        return replyRepository.findByPostId(post.getId());
    }

    @Transactional
    public void deleteReply(Long reply_id) {
        replyRepository.delete(reply_id);
    }
}
