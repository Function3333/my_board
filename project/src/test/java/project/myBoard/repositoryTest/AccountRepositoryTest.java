package project.myBoard.repositoryTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.myBoard.entity.Account;
import project.myBoard.repository.AccountRepository;
import project.myBoard.uitls.timeStamp.CurrentTimeStamp;

@SpringBootTest
@Transactional
public class AccountRepositoryTest {
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


}
