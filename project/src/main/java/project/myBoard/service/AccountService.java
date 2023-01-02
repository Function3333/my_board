package project.myBoard.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import project.myBoard.dto.AccountDto;
import project.myBoard.entity.Account;
import project.myBoard.forms.AccountReturnForm;
import project.myBoard.repository.AccountRepository;

import java.util.List;

@Service
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    @Transactional
    public Long register(AccountDto dto) {
        Account account = new Account().createAccount(dto);
        Long saveId = accountRepository.save(account);

        return saveId;
    }

    public AccountReturnForm getAccount(Long account_id) {
        Account account = accountRepository.findById(account_id);

        AccountReturnForm accountReturnForm = new AccountReturnForm(account);

        return accountReturnForm;
    }

    @Transactional
    public void deleteAccount(Long account_id) {
        accountRepository.delete(account_id);
    }

    @Transactional
    public Account findByEmail(String email) {
        List<Account> byEmail = accountRepository.findByEmail(email);

        if(byEmail.isEmpty()) {
            return null;
        }

        return byEmail.get(0);
    }

    @Transactional
    public void editAccount(Long account_id, AccountDto dto) {
        Account byId = accountRepository.findById(account_id);
        byId.changeAccount(dto);
    }
}

