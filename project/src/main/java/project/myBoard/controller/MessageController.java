package project.myBoard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project.myBoard.dto.MessageDto;
import project.myBoard.entity.Message;
import project.myBoard.service.MessageService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @GetMapping("/send_messages")
    public String getSendMessages(Model model) {
        List<Message> sendMessages = messageService.findSendMessages(1L);

        model.addAttribute("sendMessages", sendMessages);
        return "template";
    }

    @GetMapping("/receive_messages")
    public String getReceiveMessages(Model model) {
        List<Message> receiveMessages = messageService.findReceiveMessages(1L);

        model.addAttribute("receiveMessages", receiveMessages);
        return "template";
    }

    @PostMapping("/message/{receiver_id}")
    public String createMessage(@ModelAttribute MessageDto messageDto, @PathVariable Long receiver_id) {
        messageService.createMessage(1L, receiver_id, messageDto);

        return "template";
    }

    @GetMapping("/message/delete/{message_id}")
    public String deleteMessage(@PathVariable Long message_id) {
        messageService.deleteMessage(message_id);

        return "template";
    }
}
