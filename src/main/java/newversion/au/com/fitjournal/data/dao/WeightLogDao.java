package newversion.au.com.fitjournal.data.dao;

import newversion.au.com.fitjournal.data.entity.WeightEntity;
import newversion.au.com.fitjournal.data.entity.WeightLogEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class WeightLogDao
{
    @PersistenceContext
    EntityManager em;

    @SuppressWarnings("unchecked")
    public List<WeightLogEntity> findAll()
    {
        return em.createNamedQuery("WeightLogEntity.findAll").getResultList();
    }

    @SuppressWarnings("unchecked")
    public WeightLogEntity findByDate(Date date)
    {
        List<WeightLogEntity> results = em.createNamedQuery("WeightLogEntity.findByDate")
                .setParameter("date", date)
                .getResultList();

        return results.size() > 0 ? results.get(0) : null;
    }

    public WeightLogEntity save(WeightLogEntity entity)
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
        return em.createNamedQuery("WeightLogEntity.deleteAll")
                .executeUpdate() > 0;
    }
}
