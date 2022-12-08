package project.myBoard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.myBoard.entity.Board;
import project.myBoard.entity.Post;
import project.myBoard.forms.BoardReturnForm;
import project.myBoard.forms.PostReturnForm;
import project.myBoard.repository.BoardRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;
    private final PostService postService;

    public List<PostReturnForm> findPostsByBoardId(Long board_id) {
        List<PostReturnForm> postReturnForms = new ArrayList<>();

        Board board = boardRepository.findById(board_id);

        List<Post> posts = board.getPosts();
        for(Post post :posts) {
            PostReturnForm postReturnForm = new PostReturnForm(post, postService.getPostImages(post));
            postReturnForms.add(postReturnForm);
        }

        return postReturnForms;
    }

    public BoardReturnForm findBoardById(Long board_id) {
        List<PostReturnForm> postReturnForms = new ArrayList<>();

        Board board = boardRepository.findById(board_id);

        List<Post> posts = board.getPosts();
        for (Post post : posts) {
            PostReturnForm postReturnForm = postService.createPostReturnForm(post.getId());
            postReturnForms.add(postReturnForm);
        }

        return new BoardReturnForm(board.getCategory(), postReturnForms);
    }
}
