package project.myBoard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project.myBoard.dto.ReplyDto;
import project.myBoard.entity.Post;
import project.myBoard.service.PostService;
import project.myBoard.service.ReplyService;

@Controller
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;
    private final PostService postService;

    @PostMapping("/reply/{post_id}")
    public String createReply(@ModelAttribute ReplyDto replyDto, @PathVariable Long post_id) {
        Post post = postService.findByPostId(post_id);

        replyService.createReply(replyDto, post);

        return "template";
    }

    @PostMapping("/reply/edit/{reply_id}")
    public String editReply(@PathVariable Long reply_id, @ModelAttribute ReplyDto replyDto) {
        replyService.editReply(replyDto, reply_id);

        return "template";
    }

    @GetMapping("/reply/delete/{reply_id}")
    public String deleteReply(@PathVariable Long reply_id) {
        replyService.deleteReply(reply_id);

        return "template";
    }
}
