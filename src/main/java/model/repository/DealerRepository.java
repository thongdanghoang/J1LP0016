package model.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import model.entity.DealerEntity;
import model.repository.exceptions.NonexistentEntityException;
import model.repository.exceptions.PreexistingEntityException;
import model.repository.exceptions.ResultListEmptyException;

import java.io.Serializable;
import java.util.List;

/**
 * @author thongdanghoang
 */
public class DealerRepository implements Serializable, IRepository<String, DealerEntity> {

    private EntityManagerFactory emf = null;

    public DealerRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DealerEntity dealerEntity) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(dealerEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findById(dealerEntity.getId()) != null) {
                throw new PreexistingEntityException("Dealer with id " + dealerEntity + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DealerEntity dealerEntity) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            dealerEntity = em.merge(dealerEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = dealerEntity.getId();
                if (findById(id) == null) {
                    throw new NonexistentEntityException("The dealer with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DealerEntity dealerEntity;
            try {
                dealerEntity = em.getReference(DealerEntity.class, id);
                dealerEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dealer with id " + id + " no longer exists.", enfe);
            }
            em.remove(dealerEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    public DealerEntity findById(String id) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        DealerEntity found = em.find(DealerEntity.class, id);
        try {
            if (found == null) {
                throw new NonexistentEntityException("The dealer with id " + id + " no longer exists.");
            } else {
                return found;
            }
        } finally {
            em.close();
        }
    }

    public List<DealerEntity> findAll() throws ResultListEmptyException {
        List<DealerEntity> resultList = findAll(true, -1, -1);
        if (resultList.isEmpty()) throw new ResultListEmptyException("The database have no dealer record.");
        return resultList;
    }

    public List<DealerEntity> findAll(int maxResults, int firstResult) {
        return findAll(false, maxResults, firstResult);
    }

    private List<DealerEntity> findAll(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Object> cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DealerEntity.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }


    public int getDealerEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Object> cq = em.getCriteriaBuilder().createQuery();
            Root<DealerEntity> rt = cq.from(DealerEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
