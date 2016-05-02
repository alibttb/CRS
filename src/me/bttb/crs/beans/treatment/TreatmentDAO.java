package me.bttb.crs.beans.treatment;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import me.bttb.crs.beans.db.JPAEntityManagerFactoryBean;
import me.bttb.crs.model.Treatment;

@Repository
public class TreatmentDAO {
	@Autowired
	private JPAEntityManagerFactoryBean jemfb;

	public TreatmentDAO() {
	}

	public List<Treatment> findAll() {
		EntityManager em = jemfb.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Treatment> cqm = cb.createQuery(Treatment.class);
		cqm.from(Treatment.class);
		return em.createQuery(cqm).getResultList();
	}

	public void add(Treatment measurment) {
		EntityManager em = jemfb.createEntityManager();
		em.getTransaction().begin();
		em.persist(measurment);
		em.getTransaction().commit();
	}

	public void update(Treatment measurment) {
		EntityManager em = jemfb.createEntityManager();
		em.getTransaction().begin();
		em.merge(measurment);
		em.getTransaction().commit();
	}
}
