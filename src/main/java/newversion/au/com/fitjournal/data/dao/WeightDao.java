package newversion.au.com.fitjournal.data.dao;

import newversion.au.com.fitjournal.data.entity.WeightEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class WeightDao
{
    @PersistenceContext
    EntityManager em;

    @SuppressWarnings("unchecked")
    public List<WeightEntity> findAll()
    {
        return em.createNamedQuery("WeightEntity.findAll").getResultList();
    }
    public WeightEntity save(WeightEntity entity)
    {
        if (entity.getId() == null) {
            em.persist(entity);
        }
        else {
            em.merge(entity);
            em.flush();
        }

        return entity;
    }

    public boolean deleteAll()
    {
        return em.createNamedQuery("WeightEntity.deleteAll")
                .executeUpdate() > 0;
    }
}
