package project.myBoard.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.myBoard.dto.AccountDto;
import project.myBoard.entity.Account;
import project.myBoard.repository.AccountRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository repository;

    @Transactional
    public Long register(AccountDto dto) {
        Account account = new Account().createAccount(dto);
        Long saveId = repository.save(account);

        return saveId;
    }

    public Account getAccount(Long account_id) {
        return repository.findById(account_id);
    }

    @Transactional
    public void deleteAccount(Long account_id) {
        repository.delete(account_id);
    }

    @Transactional
    public void editAccount(Long account_id, AccountDto dto) {
        Account byId = repository.findById(account_id);
        byId.changeAccount(dto);
    }
}
