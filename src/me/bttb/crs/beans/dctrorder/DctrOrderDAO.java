package me.bttb.crs.beans.dctrorder;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import me.bttb.crs.beans.db.JPAEntityManagerFactoryBean;
import me.bttb.crs.model.DctrOrder;
import me.bttb.crs.model.DctrOrder_;
import me.bttb.crs.model.Visit;

@Repository
public class DctrOrderDAO {
	@Autowired
	JPAEntityManagerFactoryBean jemfb;

	public DctrOrderDAO() {
	}

	public List<DctrOrder> findAllDctrOrders() {
		EntityManager em = jemfb.createEntityManager();
		return em.createQuery(em.getCriteriaBuilder().createQuery(DctrOrder.class)).getResultList();
	}

	public List<DctrOrder> findDctrOrdersInVisit(Visit visit) {
		EntityManager em = jemfb.createEntityManager();
		return em.createQuery("select hs from DctrOrder hs where hs.visit.id = :vst_id", DctrOrder.class)
				.setParameter("vst_id", visit.getId()).getResultList();
	}

	public boolean addDctrOrder(DctrOrder hsmp) {
		try {
			EntityManager em = jemfb.createEntityManager();
			em.getTransaction().begin();
			em.persist(hsmp);
			em.getTransaction().commit();
			return true;
		} catch (PersistenceException e) {
			return false;
		}
	}

	public boolean updateDctrOrder(DctrOrder hsmp) {
		try {
			EntityManager em = jemfb.createEntityManager();
			em.getTransaction().begin();
			em.merge(hsmp);
			em.getTransaction().commit();
			return true;
		} catch (PersistenceException e) {
			return false;
		}
	}

	public boolean deleteDctrOrder(DctrOrder hsmp) {
		try {
			EntityManager em = jemfb.createEntityManager();
			hsmp = em.find(DctrOrder.class, hsmp.getId());
			em.getTransaction().begin();
			em.remove(hsmp);
			em.getTransaction().commit();
			return true;
		} catch (PersistenceException e) {
			return false;
		}
	}

	public List<String> findAllDctrOrdersTypes() {
		EntityManager em = jemfb.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> cq = cb.createQuery(String.class);
		Root<DctrOrder> from = cq.from(DctrOrder.class);
		cq.distinct(true);
		cq.select(from.get(DctrOrder_.rdrType));
		return em.createQuery(cq).getResultList();
	}

	public List<String> findAllDctrOrdersNames() {
		EntityManager em = jemfb.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> cq = cb.createQuery(String.class);
		Root<DctrOrder> from = cq.from(DctrOrder.class);
		cq.distinct(true);
		cq.select(from.get(DctrOrder_.name));
		return em.createQuery(cq).getResultList();
	}
}
