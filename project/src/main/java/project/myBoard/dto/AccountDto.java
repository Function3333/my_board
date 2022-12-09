package project.myBoard.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class AccountDto {
    private Long accountId;
    private String email;
    private String username;
    private String password;
}
