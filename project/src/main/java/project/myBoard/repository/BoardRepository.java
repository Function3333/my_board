package project.myBoard.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import project.myBoard.entity.Board;

import java.util.List;

@Repository
public class BoardRepository {
    @PersistenceContext
    private EntityManager em;

    public Long save(Board board) {
        em.persist(board);

        return board.getId();
    }

    public Board findById(Long board_id) {
        return em.find(Board.class, board_id);
    }

    public List<Board> findAll() {
        return em.createQuery("select b from Board b").getResultList();
    }
}
