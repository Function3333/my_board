package project.myBoard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.myBoard.dto.MessageDto;

import project.myBoard.entity.Account;
import project.myBoard.entity.Message;
import project.myBoard.repository.AccountRepository;
import project.myBoard.repository.MessageRepository;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public Long createMessage(Long senderId, Long receiver_id, MessageDto messageDto) {
        Account sender = accountRepository.findById(senderId);
        Account receiver = accountRepository.findById(receiver_id);

        Message message = new Message().createMessage(messageDto, sender, receiver);

        return messageRepository.save(message);
    }

    public List<Message> findSendMessages(Long sender_id) {
        List<Message> sendList = messageRepository.findBySenderId(sender_id);
        return sendList;
    }

    public List<Message> findReceiveMessages(Long receiver_id) {
        List<Message> receiveList = messageRepository.findByReceiverId(receiver_id);
        return receiveList;
    }

    @Transactional
    public void deleteMessage(Long message_id) {
        messageRepository.delete(message_id);
    }

}
