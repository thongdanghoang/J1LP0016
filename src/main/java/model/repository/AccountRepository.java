package model.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import model.entity.AccountEntity;
import model.repository.exceptions.NonexistentEntityException;
import model.repository.exceptions.PreexistingEntityException;
import model.repository.exceptions.ResultListEmptyException;

import java.io.Serializable;
import java.util.List;

/**
 * @author thongdanghoang
 */
public class AccountRepository implements Serializable, IRepository<String, AccountEntity> {

    private EntityManagerFactory emf = null;

    public AccountRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AccountEntity accountEntity) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(accountEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findById(accountEntity.getUsername()) != null) {
                throw new PreexistingEntityException("The account with username " + accountEntity + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AccountEntity accountEntity) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            accountEntity = em.merge(accountEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = accountEntity.getUsername();
                if (findById(id) == null) {
                    throw new NonexistentEntityException("The account with username " + id + " no longer exists.");
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
            AccountEntity accountEntity;
            try {
                accountEntity = em.getReference(AccountEntity.class, id);
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The account with username " + id + " is not exists.", enfe);
            }
            em.remove(accountEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    public AccountEntity findById(String id) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        AccountEntity found = em.find(AccountEntity.class, id);
        try {
            if (found == null) {
                throw new NonexistentEntityException("The account with username " + id + " is not exists.");
            }
            return found;
        } finally {
            em.close();
        }
    }

    public List<AccountEntity> findAll() throws ResultListEmptyException {
        List resultList = findAll(true, -1, -1);
        if (resultList.isEmpty()) throw new ResultListEmptyException("The database have no account record.");
        return resultList;
    }

    public List<AccountEntity> findAll(int maxResults, int firstResult) {
        return findAll(false, maxResults, firstResult);
    }

    private List findAll(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Object> cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AccountEntity.class));
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


    public int getAccountEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Object> cq = em.getCriteriaBuilder().createQuery();
            Root<AccountEntity> rt = cq.from(AccountEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
