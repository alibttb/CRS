package me.bttb.crs.beans.hssmptm;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import me.bttb.crs.beans.db.JPAEntityManagerFactoryBean;
import me.bttb.crs.model.HasSymptom;
import me.bttb.crs.model.Visit;

@Repository
public class HasSymptomDAO {
	@Autowired
	JPAEntityManagerFactoryBean jemfb;

	public HasSymptomDAO() {
	}

	public List<HasSymptom> findAllHasSymptoms() {
		EntityManager em = jemfb.createEntityManager();
		return em.createQuery(em.getCriteriaBuilder().createQuery(HasSymptom.class)).getResultList();
	}

	public List<HasSymptom> findHasSymptomsInVisit(Visit visit) {
		EntityManager em = jemfb.createEntityManager();
		return em.createQuery("select hs from HasSymptom hs where hs.visit.id = :vst_id", HasSymptom.class)
				.setParameter("vst_id", visit.getId()).getResultList();
	}

	public boolean addHasSymptom(HasSymptom hsmp) {
		try {
			EntityManager em = jemfb.createEntityManager();
			em.getTransaction().begin();
			em.persist(hsmp);
			em.getTransaction().commit();
			return true;
		} catch (PersistenceException e) {
			return false;
		}
	}

	public boolean updateHasSymptom(HasSymptom hsmp) {
		try {
			EntityManager em = jemfb.createEntityManager();
			em.getTransaction().begin();
			em.merge(hsmp);
			em.getTransaction().commit();
			return true;
		} catch (PersistenceException e) {
			return false;
		}
	}

	public boolean deleteHasSymptom(HasSymptom hsmp) {
		try {
			EntityManager em = jemfb.createEntityManager();
			hsmp = em.find(HasSymptom.class, hsmp.getId());
			em.getTransaction().begin();
			em.remove(hsmp);
			em.getTransaction().commit();
			return true;
		} catch (PersistenceException e) {
			return false;
		}
	}
}
