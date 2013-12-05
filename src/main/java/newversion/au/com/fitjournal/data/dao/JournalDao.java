package newversion.au.com.fitjournal.data.dao;

import newversion.au.com.fitjournal.data.entity.JournalEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class JournalDao
{
    @PersistenceContext
    EntityManager em;

    @SuppressWarnings("unchecked")
    public List<JournalEntity> findAll()
    {
        return em.createNamedQuery("JournalEntity.findAll").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<JournalEntity> findBetweenDates(Date start, Date end)
    {
        return em.createNamedQuery("JournalEntity.findBetweenDates")
                .setParameter("start", start)
                .setParameter("end", end)
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    public JournalEntity findById(long id)
    {
        List<JournalEntity> entities = em.createNamedQuery("JournalEntity.findById")
                .setParameter("id", id)
                .getResultList();

        return (entities.size() > 0 ? entities.get(0) : null);
    }

    public JournalEntity save(JournalEntity entity)
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

    public boolean deleteById(long id)
    {
        return em.createNamedQuery("JournalEntity.deleteById")
                .setParameter("id", id)
                .executeUpdate() > 0;
    }
}
