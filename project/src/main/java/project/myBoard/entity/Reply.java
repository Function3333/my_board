package project.myBoard.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.myBoard.dto.ReplyDto;
import project.myBoard.uitls.timeStamp.CurrentTimeStamp;

import java.sql.Timestamp;

@Entity
@Getter @Setter
public class Reply {
    @Id
    @Column(name = "reply_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private Timestamp regDate;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Reply createReply(ReplyDto replyDto, Post post) {
        this.content = replyDto.getContent();
        this.regDate = CurrentTimeStamp.currentTimeToTimeStamp();
        this.post = post;
        post.getReplies().add(this);
        return this;
    }

    public void editReply(ReplyDto replyDto) {
        this.content = replyDto.getContent();
        this.regDate = CurrentTimeStamp.currentTimeToTimeStamp();
    }
}
