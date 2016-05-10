package me.bttb.crs.beans.diagnosedwith;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import me.bttb.crs.beans.db.JPAEntityManagerFactoryBean;
import me.bttb.crs.model.DiagnosedWith;
import me.bttb.crs.model.Visit;

@Repository
public class DiagnosedWithDAO {
	@Autowired
	JPAEntityManagerFactoryBean jemfb;

	public DiagnosedWithDAO() {
	}

	public List<DiagnosedWith> findAllDiagnosedWith() {
		EntityManager em = jemfb.createEntityManager();
		return em.createQuery(em.getCriteriaBuilder().createQuery(DiagnosedWith.class)).getResultList();
	}

	public List<DiagnosedWith> findDiagnosedWithInVisit(Visit visit) {
		EntityManager em = jemfb.createEntityManager();
		return em.createQuery("select hs from DiagnosedWith hs where hs.visit.id = :vst_id", DiagnosedWith.class)
				.setParameter("vst_id", visit.getId()).getResultList();
	}

	public boolean addDiagnosedWith(DiagnosedWith dgnss) {
		try {
			EntityManager em = jemfb.createEntityManager();
			em.getTransaction().begin();
			em.persist(dgnss);
			em.getTransaction().commit();
			return true;
		} catch (PersistenceException e) {
			return false;
		}
	}

	public boolean updateDiagnosedWith(DiagnosedWith tmsnt) {
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

	public boolean deleteDiagnosedWith(DiagnosedWith dgnss) {
		try {
			EntityManager em = jemfb.createEntityManager();
			dgnss = em.find(DiagnosedWith.class, dgnss.getId());
			em.getTransaction().begin();
			em.remove(dgnss);
			em.getTransaction().commit();
			return true;
		} catch (PersistenceException e) {
			return false;
		}
	}
}
