package me.bttb.crs.beans.tkmsrmnt;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import me.bttb.crs.beans.db.JPAEntityManagerFactoryBean;
import me.bttb.crs.model.TkMsrmnt;
import me.bttb.crs.model.Visit;

@Repository
public class TkMsrmntDAO {
	@Autowired
	JPAEntityManagerFactoryBean jemfb;

	public TkMsrmntDAO() {
	}

	public List<TkMsrmnt> findAllTkMsrmnt() {
		EntityManager em = jemfb.createEntityManager();
		return em.createQuery(em.getCriteriaBuilder().createQuery(TkMsrmnt.class)).getResultList();
	}

	public List<TkMsrmnt> findTkMsrmntInVisit(Visit visit) {
		EntityManager em = jemfb.createEntityManager();
		return em.createQuery("select hs from TkMsrmnt hs where hs.visit.id = :vst_id", TkMsrmnt.class)
				.setParameter("vst_id", visit.getId()).getResultList();
	}

	public boolean addTkMsrmnt(TkMsrmnt tmsnt) {
		try {
			EntityManager em = jemfb.createEntityManager();
			em.getTransaction().begin();
			em.persist(tmsnt);
			em.getTransaction().commit();
			return true;
		} catch (PersistenceException e) {
			return false;
		}
	}

	public boolean updateTkMsrmnt(TkMsrmnt tmsnt) {
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

	public boolean deleteTkMsrmnt(TkMsrmnt tmsnt) {
		try {
			EntityManager em = jemfb.createEntityManager();
			tmsnt = em.find(TkMsrmnt.class, tmsnt.getId());
			em.getTransaction().begin();
			em.remove(tmsnt);
			em.getTransaction().commit();
			return true;
		} catch (PersistenceException e) {
			return false;
		}
	}
}
