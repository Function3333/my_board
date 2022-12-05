package project.myBoard.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.myBoard.dto.AccountDto;

import java.sql.Timestamp;

@Entity
@Getter @Setter
public class Account {
    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String username;
    private String password;
    private Timestamp regDate;

    public Account createAccount(AccountDto accountDto) {


        this.email = accountDto.getEmail();
        this.username = accountDto.getUsername();
        this.password = accountDto.getPassword();
        this.regDate = accountDto.getRegDate();

        return this;
    }

    public void changeAccount(AccountDto accountDto) {
        this.email = accountDto.getEmail();
        this.username = accountDto.getUsername();
        this.password = accountDto.getPassword();
        this.regDate = accountDto.getRegDate();
    }
}
