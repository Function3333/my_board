package project.myBoard.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import project.myBoard.entity.Message;

import java.util.List;

@Repository
public class MessageRepository {
    @PersistenceContext
    private EntityManager em;

    public Long save(Message message) {
        em.persist(message);

        return message.getId();
    }

    public Message findById(Long message_id) {
        return em.find(Message.class, message_id);
    }

    public List<Message> findAll() {
        return em.createQuery("select m from Message m").getResultList();
    }

    public List<Message> findBySenderId(Long sender_id) {
        return em.createQuery("select m from Message m where m.sender.id=:sender_id")
                .setParameter("sender_id", sender_id)
                .getResultList();
    }

    public List<Message> findByReceiverId(Long receiver_id) {
        return em.createQuery("select m from Message m where m.receiver.id=:receiver_id")
                .setParameter("receiver_id", receiver_id)
                .getResultList();
    }

    public void delete(Long message_id) {
        em.remove(findById(message_id));
    }
}
