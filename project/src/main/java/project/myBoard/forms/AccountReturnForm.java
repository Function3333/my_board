package project.myBoard.forms;

import lombok.Data;
import project.myBoard.entity.Account;

import java.sql.Timestamp;

@Data
public class AccountReturnForm {
    private Long accountId;
    private String email;
    private String username;
    private String password;
    private Timestamp regDate;

    public AccountReturnForm(Account account) {
        this.accountId = account.getId();
        this.email = account.getEmail();
        this.username = account.getUsername();
        this.password = account.getPassword();
        this.regDate = account.getRegDate();
    }
}
