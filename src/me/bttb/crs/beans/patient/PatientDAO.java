package me.bttb.crs.beans.patient;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import me.bttb.crs.beans.db.JPAEntityManagerFactoryBean;
import me.bttb.crs.model.Ptnt;

@Repository
public class PatientDAO {
	@Autowired
	JPAEntityManagerFactoryBean jpaEntityManagerFactoryBean;

	public long getPatientsCount() {
		EntityManager em = jpaEntityManagerFactoryBean.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(Ptnt.class)));
		return em.createQuery(cq).getSingleResult();
	}

	public List<Ptnt> getPatients() {
		EntityManager em = jpaEntityManagerFactoryBean.createEntityManager();
		TypedQuery<Ptnt> q = em.createNamedQuery("Ptnt.findAll", Ptnt.class);
		return q.getResultList();
	}

	public Ptnt getPatientById(long pid) {
		EntityManager em = jpaEntityManagerFactoryBean.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Ptnt> cq = cb.createQuery(Ptnt.class);
		Root<Ptnt> r = cq.from(Ptnt.class);
		cq.select(cq.from(Ptnt.class)).where(cb.and(cb.equal(r.get("prsnType"), "Ptnt"), cb.equal(r.get("pid"), pid)));
		return em.createQuery(cq).getSingleResult();
	}

	public List<Ptnt> getPatientByName(String firstName, String familyName) {
		EntityManager em = jpaEntityManagerFactoryBean.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Ptnt> cq = cb.createQuery(Ptnt.class);
		Root<Ptnt> r = cq.from(Ptnt.class);
		cq.select(cq.from(Ptnt.class)).where(cb.and(cb.equal(r.get("prsnType"), "Ptnt"),
				cb.equal(r.get("firstName"), firstName), cb.equal(r.get("familyName"), familyName)));
		return em.createQuery(cq).getResultList();
	}

	public void addpatient(Ptnt patient) {
		EntityManager em = jpaEntityManagerFactoryBean.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(patient);
		et.commit();
		em.close();
	}
}
