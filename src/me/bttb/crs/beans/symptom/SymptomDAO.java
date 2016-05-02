package me.bttb.crs.beans.symptom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import me.bttb.crs.beans.db.JPAEntityManagerFactoryBean;
import me.bttb.crs.model.Symptom;

@Repository
public class SymptomDAO {
	@Autowired
	JPAEntityManagerFactoryBean jemfb;

	public SymptomDAO() {
	}

	public void addSymptom(Symptom smptm) {
		EntityManager em = jemfb.createEntityManager();
		em.getTransaction().begin();
		em.persist(smptm);
		em.getTransaction().commit();
	}

	public void updateSymptom(Symptom smptm) {
		EntityManager em = jemfb.createEntityManager();
		em.getTransaction().begin();
		em.merge(smptm);
		em.getTransaction().commit();
	}

	public List<Symptom> findAllSymptoms() {
		EntityManager em = jemfb.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Symptom> cq = cb.createQuery(Symptom.class);
		cq.from(Symptom.class);
		return em.createQuery(cq).getResultList();
	}
}
