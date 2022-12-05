package project.myBoard.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import project.myBoard.entity.Reply;

import java.util.List;

@Repository
public class ReplyRepository {
    @PersistenceContext
    private EntityManager em;

    public Long save(Reply reply) {
        em.persist(reply);

        return reply.getId();
    }

    public Reply findById(Long reply_id) {
        return em.find(Reply.class, reply_id);
    }

    public List<Reply> findAll() {
        return em.createQuery("select r from Reply r").getResultList();
    }

    public List<Reply> findByPostId(Long post_id) {
        return em.createQuery("select r from Reply r where r.post.id=:post_id")
                .setParameter("post_id", post_id)
                .getResultList();
    }

    public void delete(Long reply_id) {
        Reply reply = findById(reply_id);
        em.remove(reply);
    }
}
