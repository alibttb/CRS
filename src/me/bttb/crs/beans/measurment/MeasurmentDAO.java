package me.bttb.crs.beans.measurment;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import me.bttb.crs.beans.db.JPAEntityManagerFactoryBean;
import me.bttb.crs.model.Measurment;

@Repository
public class MeasurmentDAO {
	@Autowired
	private JPAEntityManagerFactoryBean jemfb;

	public MeasurmentDAO() {
	}

	public List<Measurment> findAll() {
		EntityManager em = jemfb.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Measurment> cqm = cb.createQuery(Measurment.class);
		cqm.from(Measurment.class);
		return em.createQuery(cqm).getResultList();
	}

	public void add(Measurment measurment) {
		EntityManager em = jemfb.createEntityManager();
		em.getTransaction().begin();
		em.persist(measurment);
		em.getTransaction().commit();
	}

	public void update(Measurment measurment) {
		EntityManager em = jemfb.createEntityManager();
		em.getTransaction().begin();
		em.merge(measurment);
		em.getTransaction().commit();
	}
}
