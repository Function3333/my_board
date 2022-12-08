package project.myBoard.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.myBoard.dto.AccountDto;
import project.myBoard.entity.Account;
import project.myBoard.forms.AccountReturnForm;
import project.myBoard.repository.AccountRepository;

@Service
@Transactional(readOnly = true)
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
    public void editAccount(Long account_id, AccountDto dto) {
        Account byId = accountRepository.findById(account_id);
        byId.changeAccount(dto);
    }
}
