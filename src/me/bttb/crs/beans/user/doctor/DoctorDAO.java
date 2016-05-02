package me.bttb.crs.beans.user.doctor;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import me.bttb.crs.beans.db.JPAEntityManagerFactoryBean;
import me.bttb.crs.model.Dctr;

@Repository
public class DoctorDAO {
	@Autowired
	JPAEntityManagerFactoryBean jemfb;

	public List<Dctr> findAll() {
		EntityManager em = jemfb.createEntityManager();
		return em.createQuery("select d from Dctr d", Dctr.class).getResultList();
	}

	public boolean addDoctor(Dctr d) {
		try {
			EntityManager em = jemfb.createEntityManager();
			em.getTransaction().begin();
			em.persist(d);
			em.getTransaction().commit();
			return true;
		} catch (PersistenceException pe) {
			return false;
		}
	}

	public boolean updateDoctor(Dctr d) {
		try {
			EntityManager em = jemfb.createEntityManager();
			em.getTransaction().begin();
			em.merge(d);
			em.getTransaction().commit();
			return true;
		} catch (PersistenceException pe) {
			return false;
		}
	}
}
