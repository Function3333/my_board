package project.myBoard.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import project.myBoard.entity.Post;

import java.util.List;

@Repository
public class PostRepository {
    @PersistenceContext
    private EntityManager em;

    public Long save(Post post) {
        em.persist(post);

        return post.getId();
    }

    public Post findById(Long post_id) {
        return em.find(Post.class, post_id);
    }

    public List<Post> findAll() {
        return em.createQuery("select p from Post p").getResultList();
    }

    public void delete(Long post_id) {
        Post post = em.find(Post.class, post_id);
        em.remove(post);
    }
}
