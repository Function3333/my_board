package project.myBoard.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import project.myBoard.dto.AccountDto;

@Component
public class AccountDtoValidator implements Validator {
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

        if(passwordSameWithRepeatPassword(accountDto.getPassword(), accountDto.getRepeatPassword())) {

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
}
