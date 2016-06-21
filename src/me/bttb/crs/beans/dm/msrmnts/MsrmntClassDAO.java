package me.bttb.crs.beans.dm.msrmnts;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import me.bttb.crs.beans.db.JPAEntityManagerFactoryBean;
import me.bttb.crs.model.MsrmntClss;
import me.bttb.crs.model.Visit;

@Repository
public class MsrmntClassDAO implements Serializable {

	private static final long serialVersionUID = 4921857692989264551L;
	@Autowired
	JPAEntityManagerFactoryBean jemfb;

	public MsrmntClassDAO() {

	}

	public List<MsrmntClss> findAll() {
		EntityManager em = jemfb.createEntityManager();
		return em.createQuery(em.getCriteriaBuilder().createQuery(MsrmntClss.class)).getResultList();
	}

	public boolean add(MsrmntClss mc) {
		try {
			EntityManager em = jemfb.createEntityManager();
			em.getTransaction().begin();
			em.persist(mc);
			em.getTransaction().commit();
			return true;
		} catch (PersistenceException e) {
			return false;
		}
	}

	public boolean update(MsrmntClss mc) {
		try {
			EntityManager em = jemfb.createEntityManager();
			em.getTransaction().begin();
			em.merge(mc);
			em.getTransaction().commit();
			return true;
		} catch (PersistenceException e) {
			return false;
		}
	}

	public boolean delete(MsrmntClss mc) {
		try {
			EntityManager em = jemfb.createEntityManager();
			mc = em.find(MsrmntClss.class, mc.getId());
			em.getTransaction().begin();
			em.remove(mc);
			em.getTransaction().commit();
			return true;
		} catch (PersistenceException e) {
			return false;
		}
	}

	public MsrmntClss findMsrmntClass(Visit visit) {
		// select msrmntClssId
		// from MsrmntClssCmpnnt mmc join TkMsrmnt tm on tm.msrmnt = mmc.msrmnt
		// group by vstid,msrmntclssid having count(tm.msrmnt) =
		// count(mms.msrmnt)

//		EntityManager em = jemfb.createEntityManager();
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		CriteriaQuery<MsrmntClss> cqmc = cb.createQuery(MsrmntClss.class);
//		Root<MsrmntClss> rmc = cqmc.from(MsrmntClss.class);
//		
//		CriteriaQuery<MsrmntClssCmpnnt> cqmcc = cb.createQuery(MsrmntClssCmpnnt.class);
//		Root<MsrmntClssCmpnnt> rmcc = cqmcc.from(MsrmntClssCmpnnt.class);
//		
//		Join<Measurment, TkMsrmnt> j = rmc.join(MsrmntClss_.cmpnnts).join(MsrmntClssCmpnnt_.measurment).join(Measurment_.tkMsrmntList);
//		
//		Predicate p0 = cb.equal(cb.count(rmc.get(MsrmntClss_.cmpnnts)), visit.getTkMsrmntList().size());
//Predicate p1 = cb.equal(cb.count(j.get(TkMsrmnt_.id)), y) 
//		
//		List<Predicate> plist = new ArrayList<>();
//
//		for (TkMsrmnt tm : visit.getTkMsrmntList()) {
//			plist.add(eq)
//		}

		return null;
	}

}
