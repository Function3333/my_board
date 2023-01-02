package project.myBoard.repositoryTest;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Entity;
import project.myBoard.dto.AccountDto;
import project.myBoard.entity.Account;
import project.myBoard.repository.AccountRepository;
import project.myBoard.uitls.timeStamp.CurrentTimeStamp;

import java.util.List;

@SpringBootTest
@Transactional
public class AccountRepositoryTest {
    @Autowired
    EntityManager em;
    @Autowired
    AccountRepository repository;

    @Test
    @DisplayName("회원탈퇴")
    void removeTest() {
        Account account = new Account();
        account.setEmail("jmdw");
        account.setUsername("test");
        account.setPassword("password");
        account.setRegDate(CurrentTimeStamp.currentTimeToTimeStamp());

        repository.save(account);

        repository.delete(account.getId());

        Account findAccount = repository.findById(account.getId());

        Assertions.assertThat(findAccount).isNull();
    }

    @Test
    @DisplayName("이메일로 회원 조회")
    void findByEmail() {
        Account account = createAccount("test@spring.com", "test", "password");
        em.persist(account);

        List<Account> byEmail = repository.findByEmail("test@spring.com");
        Account findAccount = byEmail.get(0);

        Assertions.assertThat(account).isEqualTo(findAccount);
    }

    public Account createAccount(String email, String username, String password) {
        AccountDto accountDto = new AccountDto();
        accountDto.setEmail(email);
        accountDto.setUsername(username);
        accountDto.setPassword(password);

        return new Account().createAccount(accountDto);
    }


}
