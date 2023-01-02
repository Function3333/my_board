package project.myBoard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project.myBoard.dto.AccountDto;
import project.myBoard.entity.Account;
import project.myBoard.forms.AccountReturnForm;
import project.myBoard.service.AccountService;
import project.myBoard.validator.AccountDtoValidator;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final AccountDtoValidator accountDtoValidator;

    @GetMapping("/user")
    public String registerForm(@ModelAttribute AccountDto accountDto) {
        return "createAccountForm";
    }

    @PostMapping("/user")
    public String register(@ModelAttribute AccountDto accountDto, BindingResult br) {
        accountDtoValidator.validate(accountDto, br);

        log.info("register error = {}", br);
        if(br.hasErrors()) {
            return "createAccountForm";
        }

        return "redirect:/user";
    }

    @GetMapping("/user/{user_id}")
    public String getAccount(@PathVariable Long user_id, Model model) {
        AccountReturnForm accountReturnForm = accountService.getAccount(user_id);
        model.addAttribute("account", accountReturnForm);
        return "template";
    }

    @PostMapping("/user/edit/{user_id}")
    public String editAccount(@PathVariable Long user_id, @ModelAttribute AccountDto accountDto) {
        accountService.editAccount(user_id, accountDto);
        return "template";
    }

    @GetMapping("/user/delete/{user_id}")
    public String deleteAccount(@PathVariable Long user_id) {
        accountService.deleteAccount(user_id);
        return "template";
    }
}
