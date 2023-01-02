package project.myBoard.serviceTest;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.myBoard.dto.AccountDto;
import project.myBoard.entity.Account;
import project.myBoard.service.AccountService;

@SpringBootTest
@Transactional
public class AccountServiceTest {
    @Autowired
    private EntityManager em;
    @Autowired
    private AccountService service;


    @Test
    @DisplayName("이메일로 계정 가져오기")
    void findByEmail() {
        Account account = createAccount("test@spring.com", "test", "password");
        em.persist(account);

        Account findAccount = service.findByEmail("test@spring.com");

        Assertions.assertThat(findAccount).isEqualTo(account);
    }


    @Test
    @DisplayName("해당 이메일 계정이 없으면 null 반환")
    void findByEmailFail() {
        Account account = createAccount("test@spring.com", "test", "password");
        em.persist(account);

        Account findAccount = service.findByEmail("fail@spring.com");

        Assertions.assertThat(findAccount).isNull();
    }


    public Account createAccount(String email, String username, String password) {
        AccountDto accountDto = new AccountDto();
        accountDto.setEmail(email);
        accountDto.setUsername(username);
        accountDto.setPassword(password);

        return new Account().createAccount(accountDto);
    }
}
