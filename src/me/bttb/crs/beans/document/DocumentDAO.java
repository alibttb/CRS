package me.bttb.crs.beans.document;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import me.bttb.crs.beans.db.JPAEntityManagerFactoryBean;
import me.bttb.crs.model.Document;
import me.bttb.crs.model.Document_;
import me.bttb.crs.model.Visit;

@Repository
public class DocumentDAO {
	@Autowired
	JPAEntityManagerFactoryBean jemfb;

	public DocumentDAO() {
	}

	public List<Document> findAllDocuments() {
		EntityManager em = jemfb.createEntityManager();
		return em.createQuery(em.getCriteriaBuilder().createQuery(Document.class)).getResultList();
	}

	public List<Document> findDocumentsInVisit(Visit visit) {
		EntityManager em = jemfb.createEntityManager();
		return em.createQuery("select hs from Document hs where hs.visit.id = :vst_id", Document.class)
				.setParameter("vst_id", visit.getId()).getResultList();
	}

	public boolean addDocument(Document doc) {
		try {
			EntityManager em = jemfb.createEntityManager();
			em.getTransaction().begin();
			em.persist(doc);
			em.getTransaction().commit();
			return true;
		} catch (PersistenceException e) {
			return false;
		}
	}

	public boolean updateDocument(Document doc) {
		try {
			EntityManager em = jemfb.createEntityManager();
			em.getTransaction().begin();
			em.merge(doc);
			em.getTransaction().commit();
			return true;
		} catch (PersistenceException e) {
			return false;
		}
	}

	public boolean deleteDocument(Document doc) {
		try {
			EntityManager em = jemfb.createEntityManager();
			doc = em.find(Document.class, doc.getId());
			em.getTransaction().begin();
			em.remove(doc);
			em.getTransaction().commit();
			return true;
		} catch (PersistenceException e) {
			return false;
		}
	}

	public List<String> findAllDocumentNames() {
		EntityManager em = jemfb.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> cq = cb.createQuery(String.class);
		Root<Document> from = cq.from(Document.class);
		cq.distinct(true);
		cq.select(from.get(Document_.name));
		return em.createQuery(cq).getResultList();
	}
}
