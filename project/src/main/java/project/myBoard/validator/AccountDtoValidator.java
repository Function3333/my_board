package project.myBoard.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import project.myBoard.dto.AccountDto;
import project.myBoard.entity.Account;
import project.myBoard.service.AccountService;

@Component
@RequiredArgsConstructor
public class AccountDtoValidator implements Validator {
    private final AccountService accountService;

    @Override
    public boolean supports(Class<?> clazz) {
        return AccountDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AccountDto accountDto = (AccountDto) target;

        if(accountDto.getEmail().isEmpty()) {
            errors.rejectValue("email", "empty");
        }

        if(accountDto.getUsername().isEmpty()) {
            errors.rejectValue("username", "empty");
        }

        if(accountDto.getPassword().isEmpty()) {
            errors.rejectValue("password", "empty");
        }

        if(accountDto.getRepeatPassword().isEmpty()) {
            errors.rejectValue("repeatPassword", "empty");
        }

        if(passwordSameWithRepeatPassword(accountDto.getPassword(), accountDto.getRepeatPassword()) == false) {
            errors.rejectValue("password", "misMatch");
            errors.rejectValue("repeatPassword", "misMatch");
        }

        if(checkDuplicateEmail(accountDto.getEmail())) {
            errors.rejectValue("email", "duplicate");
        }
    }

    /*
    * password와 repeatPassword 동일하면 false, 아니면 true
    * */
    public boolean passwordSameWithRepeatPassword(String password, String repeatPassword) {
        if(password.equals(repeatPassword)) {
            return true;
        }
        return false;
    }

    /*
    * email이 중복이면 true 반환, 중복이 아니면 false 반환
    * */
    public boolean checkDuplicateEmail(String email) {
        Account findEmail = accountService.findByEmail(email);

        if(findEmail == null) {
            return false;
        }

        return true;
    }
}
