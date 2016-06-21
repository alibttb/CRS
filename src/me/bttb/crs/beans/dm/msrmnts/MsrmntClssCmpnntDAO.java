package me.bttb.crs.beans.dm.msrmnts;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import me.bttb.crs.beans.db.JPAEntityManagerFactoryBean;
import me.bttb.crs.model.MsrmntClssCmpnnt;

@Repository
public class MsrmntClssCmpnntDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	@Autowired
	JPAEntityManagerFactoryBean jemfb;

	public MsrmntClssCmpnntDAO() {
	}

	public List<MsrmntClssCmpnnt> findAll() {
		EntityManager em = jemfb.createEntityManager();
		return em.createQuery(em.getCriteriaBuilder().createQuery(MsrmntClssCmpnnt.class)).getResultList();
	}

	public boolean add(MsrmntClssCmpnnt mcc) {
		try {
			EntityManager em = jemfb.createEntityManager();
			em.getTransaction().begin();
			em.persist(mcc);
			em.getTransaction().commit();
			return true;
		} catch (PersistenceException e) {
			return false;
		}
	}

	public boolean update(MsrmntClssCmpnnt mcc) {
		try {
			EntityManager em = jemfb.createEntityManager();
			em.getTransaction().begin();
			em.merge(mcc);
			em.getTransaction().commit();
			return true;
		} catch (PersistenceException e) {
			return false;
		}
	}

	public boolean delete(MsrmntClssCmpnnt mcc) {
		try {
			EntityManager em = jemfb.createEntityManager();
			mcc = em.find(MsrmntClssCmpnnt.class, mcc.getId());
			em.getTransaction().begin();
			em.remove(mcc);
			em.getTransaction().commit();
			return true;
		} catch (PersistenceException e) {
			return false;
		}
	}

}
