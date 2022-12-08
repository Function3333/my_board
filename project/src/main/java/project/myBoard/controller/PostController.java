package project.myBoard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project.myBoard.dto.PostDto;
import project.myBoard.forms.PostReturnForm;
import project.myBoard.service.PostService;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/post/{post_id}")
    public String getPost(@PathVariable Long post_id, Model model) {
        PostReturnForm postReturnForm = postService.createPostReturnForm(post_id);
        model.addAttribute("post", postReturnForm);

        return "template";
    }

    @PostMapping("/{board_id}/post")
    public String registerPost(@ModelAttribute PostDto postDto, @PathVariable Long board_id) {
        postService.addPost(postDto, 1L, board_id);

        return "template";
    }

    @PostMapping("/post/edit/{post_id}")
    public String editPost(@ModelAttribute PostDto postDto, @PathVariable Long post_id) {
        postService.editPost(postDto, post_id);
        return "template";
    }

    @GetMapping("/post/delete/{post_id}")
    public String deletePost(@PathVariable Long post_id){
        postService.deletePost(post_id);

        return "template";
    }
}
