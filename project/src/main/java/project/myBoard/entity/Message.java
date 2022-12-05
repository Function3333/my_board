package project.myBoard.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.myBoard.dto.MessageDto;

import java.sql.Timestamp;

@Entity
@Getter @Setter
public class Message {
    @Id
    @Column(name = "message_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private Timestamp regDate;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Account sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Account receiver;

    public Message createMessage(MessageDto messageDto, Account sender, Account receiver) {
        this.title = messageDto.getTitle();
        this.content = messageDto.getContent();
        this.sender = sender;
        this.receiver = receiver;

        return this;
    }
}
