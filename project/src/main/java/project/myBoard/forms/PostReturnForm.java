package project.myBoard.forms;

import lombok.Data;
import org.springframework.core.io.UrlResource;
import project.myBoard.entity.Image;
import project.myBoard.entity.Post;
import project.myBoard.entity.Reply;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
public class PostReturnForm {
    private Long postId;
    private String title;
    private String content;
    private Timestamp regDate;
    private List<ImageReturnForm> images;
    private AccountReturnForm account;
    private List<ReplyReturnForm> replies;

    public PostReturnForm(Post post, List<UrlResource> postImages) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.regDate = post.getRegDate();
        this.images = imageToImageReturnForm(postImages);
        this.account = new AccountReturnForm(post.getAccount());
        this.replies = replyToReplyReturnForm(post.getReplies());
    }

    public List<ReplyReturnForm> replyToReplyReturnForm(List<Reply> replies) {
        List<ReplyReturnForm> replyReturnForms = new ArrayList<>();

        for(Reply reply : replies) {
            ReplyReturnForm replyReturnForm = new ReplyReturnForm(reply);
            replyReturnForms.add(replyReturnForm);
        }

        return replyReturnForms;
    }

    public List<ImageReturnForm> imageToImageReturnForm(List<UrlResource> postImagesUrlResources) {
        List<ImageReturnForm> imageReturnForms = new ArrayList<>();

        for (UrlResource urlResource : postImagesUrlResources) {
            ImageReturnForm imageReturnForm = new ImageReturnForm().createImageReturnForm(urlResource);

            imageReturnForms.add(imageReturnForm);
        }

        return imageReturnForms;
    }
}
