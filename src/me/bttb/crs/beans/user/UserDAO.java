package me.bttb.crs.beans.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import me.bttb.crs.beans.db.JPAEntityManagerFactoryBean;
import me.bttb.crs.model.Usr;

@Repository
public class UserDAO {
	@Autowired
	@Qualifier("jpaEmfBean")
	JPAEntityManagerFactoryBean jpaEntityManagerFactoryBean;

	public int getUsersCount() {
		EntityManager em = jpaEntityManagerFactoryBean.createEntityManager();
		TypedQuery<Long> q = em.createNamedQuery("Usr.getCount", Long.class);
		int count = q.getSingleResult().intValue();
		em.close();
		return count;
	}

	public List<Usr> getUsers() {
		EntityManager em = jpaEntityManagerFactoryBean.createEntityManager();
		TypedQuery<Usr> usrQuery = em.createNamedQuery("Usr.findAll", Usr.class);
		List<Usr> users = usrQuery.getResultList();
		em.close();
		return users;
	}

	public Usr getUsrById(long pid) {
		EntityManager em = jpaEntityManagerFactoryBean.createEntityManager();
		TypedQuery<Usr> userQuery = em.createNamedQuery("Usr.findByPid", Usr.class);
		userQuery.setParameter("pid", pid);
		Usr user = userQuery.getSingleResult();
		em.close();
		return user;
	}

	public Usr getUserByName(String userName) {
		EntityManager em = jpaEntityManagerFactoryBean.createEntityManager();
		TypedQuery<Usr> userQuery = em.createNamedQuery("Usr.findByUserName", Usr.class);
		userQuery.setParameter("userName", userName);
		Usr user = null;
		try {
			user = userQuery.getSingleResult();
		} catch (NoResultException nrEx) {
			user = null;
		} catch (NonUniqueResultException nurEx) {
			user = null;
		}
		em.close();
		return user;
	}

	public void addUser(Usr user) {
		EntityManager em = jpaEntityManagerFactoryBean.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(user);
		et.commit();
		em.close();
	}

	public void updateUser(Usr user) {
		EntityManager em = jpaEntityManagerFactoryBean.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.merge(user);
		et.commit();
		em.close();
	}

	public void deleteUser(Usr user) {
		EntityManager em = jpaEntityManagerFactoryBean.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.remove(user);
		et.commit();
		em.close();
	}

}
