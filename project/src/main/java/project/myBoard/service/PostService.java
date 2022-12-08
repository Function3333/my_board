package project.myBoard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.myBoard.dto.PostDto;
import project.myBoard.entity.Account;
import project.myBoard.entity.Board;
import project.myBoard.entity.Image;
import project.myBoard.entity.Post;
import project.myBoard.forms.PostReturnForm;
import project.myBoard.repository.AccountRepository;
import project.myBoard.repository.BoardRepository;
import project.myBoard.repository.PostRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final AccountRepository accountRepository;
    private final BoardRepository boardRepository;
    private final ImageService imageService;

    @Transactional
    public Long addPost(PostDto postDto, Long accountId, Long boardId) {
        Account account = accountRepository.findById(accountId);
        Board board = boardRepository.findById(boardId);

        Post post = new Post().createPost(postDto, account, board);
        postRepository.save(post);

        imageService.saveImage(postDto.getFiles(), post);

        return post.getId();
    }

    public Post findByPostId(Long postId) {
        return postRepository.findById(postId);
    }

    public PostReturnForm createPostReturnForm(Long postId) {
        Post post = findByPostId(postId);

        List<UrlResource> postImageUrlResources = getPostImages(post);

        PostReturnForm postReturnForm = new PostReturnForm(post, postImageUrlResources);
        return postReturnForm;
    }

    public List<UrlResource> getPostImages(Post post) {
        List<Image> postImages = post.getImages();

        return imageService.findPostImages(postImages);
    }

    @Transactional
    public void editPost(PostDto postDto, Long postId) {
        Post post = postRepository.findById(postId);
        post.editPost(postDto);
    }

    @Transactional
    public void deletePost(Long postId) {
        postRepository.delete(postId);
    }
}
