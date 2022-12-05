package project.myBoard.serviceTest;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.myBoard.dto.AccountDto;
import project.myBoard.dto.MessageDto;
import project.myBoard.entity.Account;
import project.myBoard.entity.Message;
import project.myBoard.service.MessageService;

import java.util.List;

@SpringBootTest
@Transactional
public class MessageServiceTest {
    @Autowired
    MessageService messageService;

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("메세지 보내기")
    void sendMessage() {
        Account sender = new Account().createAccount(createAccountDto("sender", "sender", "sender"));
        em.persist(sender);

        Account receiver = new Account().createAccount(createAccountDto("receiver", "receiver", "receiver"));
        em.persist(receiver);

        Long saveId = messageService.createMessage(sender.getId(), receiver.getId(), createMessageDto("test", "test"));

        Message saveMessage = em.find(Message.class, saveId);

        Assertions.assertThat(saveMessage.getSender()).isEqualTo(sender);
    }

    @Test
    @DisplayName("보낸 메세지 가져오기")
    void getSendMessages() {
        Account sender = new Account().createAccount(createAccountDto("sender", "sender", "sender"));
        em.persist(sender);

        Account receiver = new Account().createAccount(createAccountDto("receiver", "receiver", "receiver"));
        em.persist(receiver);

        messageService.createMessage(sender.getId(), receiver.getId(), createMessageDto("test", "test"));

        List<Message> sendMessages = messageService.findSendMessages(sender.getId());

        Assertions.assertThat(sendMessages.get(0).getSender()).isEqualTo(sender);
    }

    @Test
    @DisplayName("받은 메세지 가져오기")
    void getReceiveMessages() {
        Account sender = new Account().createAccount(createAccountDto("sender", "sender", "sender"));
        em.persist(sender);

        Account receiver = new Account().createAccount(createAccountDto("receiver", "receiver", "receiver"));
        em.persist(receiver);

        messageService.createMessage(sender.getId(), receiver.getId(), createMessageDto("test", "test"));

        List<Message> receiveMessages = messageService.findReceiveMessages(receiver.getId());

        Assertions.assertThat(receiveMessages.get(0).getReceiver()).isEqualTo(receiver);
    }

    @Test
    @DisplayName("메세지 삭제")
    void deleteMessage() {
        Account sender = new Account().createAccount(createAccountDto("sender", "sender", "sender"));
        em.persist(sender);

        Account receiver = new Account().createAccount(createAccountDto("receiver", "receiver", "receiver"));
        em.persist(receiver);

        Long saveId = messageService.createMessage(sender.getId(), receiver.getId(), createMessageDto("test", "test"));

        messageService.deleteMessage(saveId);

        Message findMessage = em.find(Message.class, saveId);

        Assertions.assertThat(findMessage).isNull();
    }

    public AccountDto createAccountDto(String email, String username, String password) {
        AccountDto accountDto = new AccountDto();
        accountDto.setEmail(email);
        accountDto.setUsername(username);
        accountDto.setPassword(password);

        return accountDto;
    }

    public MessageDto createMessageDto(String title, String content) {
        MessageDto messageDto = new MessageDto();
        messageDto.setTitle(title);
        messageDto.setContent(content);

        return messageDto;
    }
}
