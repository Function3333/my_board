package project.myBoard.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import project.myBoard.entity.Image;

@Repository
public class ImageRepository {
    @PersistenceContext
    private EntityManager em;

    public Image findById(Long id) {
        return em.find(Image.class, id);
    }

    public Long save(Image image) {
        em.persist(image);

        return image.getId();
    }
}
