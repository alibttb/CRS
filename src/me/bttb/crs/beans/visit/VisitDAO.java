package me.bttb.crs.beans.visit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import me.bttb.crs.beans.db.JPAEntityManagerFactoryBean;
import me.bttb.crs.model.Ptnt;
import me.bttb.crs.model.Visit;
import me.bttb.crs.model.Visit_;

@Repository
public class VisitDAO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6346081709934438419L;
	@Autowired
	JPAEntityManagerFactoryBean jemfb;

	public VisitDAO() {
	}

	public void saveVisit(Visit visit) {
		EntityManager em = jemfb.createEntityManager();
		em.getTransaction().begin();
		em.persist(visit);
		em.getTransaction().commit();
	}

	public void updateVisit(Visit visit) {
		EntityManager em = jemfb.createEntityManager();
		em.getTransaction().begin();
		em.merge(visit);
		em.getTransaction().commit();
	}

	public boolean isThereAnEmptyVisitForPatient(Ptnt patient) {
		EntityManager em = jemfb.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cqv = cb.createQuery(Long.class);
		Root<Visit> r = cqv.from(Visit.class);
		List<Predicate> lsp = new ArrayList<>(2);
		lsp.add(cb.equal(r.get(Visit_.ptnt), patient));
		lsp.add(cb.equal(r.get(Visit_.vstType), "New"));
		cqv.select(cb.count(r.get(Visit_.id))).where(lsp.toArray(new Predicate[] {}));
		return em.createQuery(cqv).getSingleResult() >= 1;
	}

	public List<Visit> findVisitsByPatient(Ptnt patient) {
		EntityManager em = jemfb.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Visit> cqv = cb.createQuery(Visit.class);
		Root<Visit> r = cqv.from(Visit.class);
		List<Predicate> lsp = new ArrayList<>(2);
		lsp.add(cb.equal(r.get(Visit_.ptnt), patient));
		cqv.where(lsp.toArray(new Predicate[] {}));
		return em.createQuery(cqv).getResultList();
	}

	public List<Visit> findSavedVisits() {
		EntityManager em = jemfb.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Visit> cqv = cb.createQuery(Visit.class);
		Root<Visit> r = cqv.from(Visit.class);
		Predicate p0 = cb.equal(r.get(Visit_.vstType), "Old");
		cqv.where(p0);
		return em.createQuery(cqv).getResultList();
	}

}
