package me.bttb.crs.beans.user.nurse;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import me.bttb.crs.beans.db.JPAEntityManagerFactoryBean;
import me.bttb.crs.model.Nrs;

@Repository
public class NurseDAO {
	@Autowired
	JPAEntityManagerFactoryBean jemfb;

	public List<Nrs> findAll() {
		EntityManager em = jemfb.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Nrs> cqn = cb.createQuery(Nrs.class);
		cqn.from(Nrs.class);
		return em.createQuery(cqn).getResultList();
	}

	public boolean addNurse(Nrs n) {
		try {
			EntityManager em = jemfb.createEntityManager();
			em.getTransaction().begin();
			em.persist(n);
			em.getTransaction().commit();
			return true;
		} catch (PersistenceException pe) {
			return false;
		}
	}

	public boolean updateNurse(Nrs n) {
		try {
			EntityManager em = jemfb.createEntityManager();
			em.getTransaction().begin();
			em.merge(n);
			em.getTransaction().commit();
			return true;
		} catch (PersistenceException pe) {
			return false;
		}
	}
}
