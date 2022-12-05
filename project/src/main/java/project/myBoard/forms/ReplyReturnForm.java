package project.myBoard.forms;

import lombok.Data;
import project.myBoard.entity.Reply;

import java.sql.Timestamp;

@Data
public class ReplyReturnForm {
    private Long  replyId;
    private String content;
    private Timestamp regDate;

    public ReplyReturnForm(Reply reply) {
        this.replyId = reply.getId();
        this.content = reply.getContent();
        this.regDate = reply.getRegDate();
    }
}
