package project.myBoard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import project.myBoard.forms.BoardReturnForm;
import project.myBoard.service.BoardService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/board/{board_id}")
    public String getBoard(@PathVariable Long board_id, Model model) {
        BoardReturnForm boardReturnForm = boardService.findBoardById(board_id);
        model.addAttribute("board", boardReturnForm);

        return "template";
    }
}
