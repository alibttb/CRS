package me.bttb.crs.beans.treatedwith;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import me.bttb.crs.beans.db.JPAEntityManagerFactoryBean;
import me.bttb.crs.model.TreatedWith;
import me.bttb.crs.model.Visit;

@Repository
public class TreatedWithDAO {
	@Autowired
	JPAEntityManagerFactoryBean jemfb;

	public TreatedWithDAO() {
	}

	public List<TreatedWith> findAllTreatedWith() {
		EntityManager em = jemfb.createEntityManager();
		return em.createQuery(em.getCriteriaBuilder().createQuery(TreatedWith.class)).getResultList();
	}

	public List<TreatedWith> findTreatedWithInVisit(Visit visit) {
		EntityManager em = jemfb.createEntityManager();
		return em.createQuery("select hs from TreatedWith hs where hs.visit.id = :vst_id", TreatedWith.class)
				.setParameter("vst_id",  visit == null ? -1 : visit.getId()).getResultList();
	}

	public boolean addTreatedWith(TreatedWith trtmn) {
		try {
			EntityManager em = jemfb.createEntityManager();
			em.getTransaction().begin();
			em.persist(trtmn);
			em.getTransaction().commit();
			return true;
		} catch (PersistenceException e) {
			return false;
		}
	}

	public boolean updateTreatedWith(TreatedWith tmsnt) {
		try {
			EntityManager em = jemfb.createEntityManager();
			em.getTransaction().begin();
			em.merge(tmsnt);
			em.getTransaction().commit();
			return true;
		} catch (PersistenceException e) {
			return false;
		}
	}

	public boolean deleteTreatedWith(TreatedWith trtmn) {
		try {
			EntityManager em = jemfb.createEntityManager();
			trtmn = em.find(TreatedWith.class, trtmn.getId());
			em.getTransaction().begin();
			em.remove(trtmn);
			em.getTransaction().commit();
			return true;
		} catch (PersistenceException e) {
			return false;
		}
	}
}
