package picture.repository.impl;

import picture.model.Picture;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class PictureRepository implements IPictureRepository {
    @PersistenceContext
    public EntityManager em;

    @Override
    public List<Picture> findAll() {
        TypedQuery<Picture> query = em.createQuery("select p from Picture as p", Picture.class);
        return query.getResultList();
    }

    @Override
    public Picture findById(Long id) {
        TypedQuery<Picture> query = em.createQuery("select p FROM Picture as p where p.id = :id", Picture.class);
        return query.getSingleResult();
    }

    @Override
    public void save(Picture model) {
        if (model.getId() != null) {
            em.merge(model);
        } else {
            em.persist(model);
        }
    }

    @Override
    public void remove(Long id) {
        if (findById(id) != null) {
            em.remove(id);
        }
    }

    @Override
    public void updateLike(Long id) {
        Picture picture = findById(id);
        if (picture != null){
            picture.setLikes(picture.getLikes()+1);
            em.merge(picture);
        }



////        EntityManagerFactory emf = null;
////        em = emf.createEntityManager();
//        em.getTransaction();
//        TypedQuery<Picture> query = em.createQuery("UPDATE Picture as p SET p.likes = p.likes + 1 where p.id = :id", Picture.class);
////        query.setParameter("newlike", 1);
////        query.setParameter("id",id);
//        query.executeUpdate();
//        em.getTransaction().commit();
//        em.close();
    }
}
