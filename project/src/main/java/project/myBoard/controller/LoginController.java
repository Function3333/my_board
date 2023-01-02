package project.myBoard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import project.myBoard.service.LoginService;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;


}
