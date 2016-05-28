package me.bttb.crs.beans.diagnosis;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import me.bttb.crs.beans.db.JPAEntityManagerFactoryBean;
import me.bttb.crs.model.Diagnosis;

@Repository
public class DiagnosisDAO implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7132693099841605710L;
	@Autowired
	private JPAEntityManagerFactoryBean jemfb;

	public DiagnosisDAO() {
	}

	public List<Diagnosis> findAll() {
		EntityManager em = jemfb.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Diagnosis> cqd = cb.createQuery(Diagnosis.class);
		cqd.from(Diagnosis.class);
		return em.createQuery(cqd).getResultList();
	}

	public void add(Diagnosis diagnosis) {
		EntityManager em = jemfb.createEntityManager();
		em.getTransaction().begin();
		em.persist(diagnosis);
		em.getTransaction().commit();
	}

	public void update(Diagnosis diagnosis) {
		EntityManager em = jemfb.createEntityManager();
		em.getTransaction().begin();
		em.merge(diagnosis);
		em.getTransaction().commit();
	}
}
