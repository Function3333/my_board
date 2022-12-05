package project.myBoard.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import project.myBoard.dto.PostDto;
import project.myBoard.uitls.timeStamp.CurrentTimeStamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Post {
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private Timestamp regDate;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private List<Reply> replies = new ArrayList<>();

    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private List<Image> images;

    public Post createPost(PostDto postDto, Account account, Board board) {
        this.title = postDto.getTitle();
        this.content = postDto.getContent();
        this.regDate = CurrentTimeStamp.currentTimeToTimeStamp();
        this.account = account;
        this.board = board;
        this.images = new ArrayList<>();
        return this;
    }

    public Post editPost(PostDto postDto) {
        this.title = postDto.getTitle();
        this.content = postDto.getContent();
        this.regDate = CurrentTimeStamp.currentTimeToTimeStamp();

        return this;
    }
}
