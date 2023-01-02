package project.myBoard.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.myBoard.entity.Account;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginService {
    private final AccountService accountService;

    public String login(String email, String password) {
        Account findAccount = accountService.findByEmail(email);

        if(findAccount != null) {
            return findAccount.getEmail();
        }

        return null;
    }
}
