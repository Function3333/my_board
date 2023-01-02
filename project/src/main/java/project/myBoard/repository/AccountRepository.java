package project.myBoard.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import project.myBoard.entity.Account;

import java.util.List;

@Repository
public class AccountRepository {
    @PersistenceContext
    private EntityManager em;

    public Long save(Account account) {
        em.persist(account);

        return account.getId();
    }

    public Account findById(Long account_id) {
        return em.find(Account.class, account_id);
    }

    public List<Account> findAll() {
        return em.createQuery("select a from Account a").getResultList();
    }

    public void delete(Long account_id) {
        Account byId = findById(account_id);
        em.remove(byId);
    }

    public List<Account> findByEmail(String email) {
        return em.createQuery("select a from Account a where a.email=:email")
                .setParameter("email", email)
                .getResultList();
    }
}
