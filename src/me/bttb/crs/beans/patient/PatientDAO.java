package me.bttb.crs.beans.patient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.eclipse.persistence.jpa.JpaEntityManager;
import org.eclipse.persistence.queries.QueryByExamplePolicy;
import org.eclipse.persistence.queries.ReadAllQuery;
import org.eclipse.persistence.sessions.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import me.bttb.crs.beans.db.JPAEntityManagerFactoryBean;
import me.bttb.crs.model.Ptnt;
import me.bttb.crs.model.Ptnt_;

@Repository
public class PatientDAO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5332156723664781596L;
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
		cq.where(cb.and(cb.equal(r.get(Ptnt_.prsnType), "Ptnt"), cb.equal(r.get(Ptnt_.pid), pid)));
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

	public List<Ptnt> findPatientsByFullNameSearch(String fullNameSearchTerm) {
		List<String> searchTerms = Arrays.asList(fullNameSearchTerm.split("\\s++"));
		searchTerms = searchTerms.stream().map(st -> "%" + st + "%").collect(Collectors.toList());

		EntityManager em = jpaEntityManagerFactoryBean.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Ptnt> cq = cb.createQuery(Ptnt.class);
		Root<Ptnt> from = cq.from(Ptnt.class);
		Expression<String> fullName = concat(cb, "%", from.get(Ptnt_.firstName), from.get(Ptnt_.fatherName),
				from.get(Ptnt_.familyName), from.get(Ptnt_.motherName));
		Predicate condition = cb.like(cb.lower(fullName),
				("%" + fullNameSearchTerm.replaceAll("\\s++", "%") + "%").toLowerCase());
		cq.where(condition);
		return em.createQuery(cq).getResultList();
	}

	@SafeVarargs
	private final Expression<String> concat(CriteriaBuilder criteriaBuilder, String delimiter,
			Expression<String>... expressions) {
		Expression<String> result = null;
		for (int i = 0; i < expressions.length; i++) {
			final boolean first = i == 0, last = i == (expressions.length - 1);
			final Expression<String> expression = expressions[i];
			if (first && last) {
				result = expression;
			} else if (first) {
				result = criteriaBuilder.concat(expression, delimiter);
			} else {
				result = criteriaBuilder.concat(result, expression);
				if (!last) {
					result = criteriaBuilder.concat(result, delimiter);
				}
			}
		}
		return result;
	}

	public List<Ptnt> findByExample(Ptnt example) {
		// Create a native EclipseLink query using QBE policy
		QueryByExamplePolicy policy = new QueryByExamplePolicy();
		policy.excludeDefaultPrimitiveValues();
		policy.addSpecialOperation(String.class, "like");
		ReadAllQuery query = new ReadAllQuery(Ptnt.class);
		query.setExampleObject(example);
		query.setQueryByExamplePolicy(policy);
		// Wrap the native query in a standard JPA Query and execute it
		Session session = ((JpaEntityManager) jpaEntityManagerFactoryBean.createEntityManager().getDelegate())
				.getActiveSession();
		List<?> l1 = (List<?>) session.executeQuery(query);
		List<Ptnt> result = new ArrayList<>();
		l1.stream().filter(t -> t instanceof Ptnt).forEachOrdered(o -> result.add((Ptnt) o));
		return result;
	}

	public boolean addPatient(Ptnt patient) {
		try {
			EntityManager em = jpaEntityManagerFactoryBean.createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			em.persist(patient);
			et.commit();
			em.close();
			return true;
		} catch (PersistenceException pe) {
			return false;
		}

	}

	public boolean updatePatient(Ptnt patient) {
		try {
			EntityManager em = jpaEntityManagerFactoryBean.createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			em.merge(patient);
			et.commit();
			em.close();
			return true;
		} catch (PersistenceException pe) {
			return false;
		}

	}

}
