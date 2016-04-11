package me.bttb.crs.beans.user;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import me.bttb.crs.beans.db.JPAEntityManagerFactoryBean;
import me.bttb.crs.model.Usr;

@Component
@ManagedBean
public class UserDAO {
	@Autowired
	@Qualifier("jpaEmfBean")
	JPAEntityManagerFactoryBean jpaEntityManagerFactoryBean;

	private List<Usr> users;

	public List<Usr> getUsers() {
		EntityManager em = jpaEntityManagerFactoryBean.createEntityManager();

		TypedQuery<Usr> usrQuery = em.createNamedQuery("Usr.findAll", Usr.class);
		users = usrQuery.getResultList();
		em.close();
		return users;
	}

	@SuppressWarnings("deprecation")
	public String addRandomUser() {
		String randomStr = "rnd" + Math.random() * 10;
		EntityManager emf = jpaEntityManagerFactoryBean.createEntityManager();
		emf.getTransaction().begin();
		Usr u = new Usr();
		u.setPrsnType("usr");
		u.setFirstName(randomStr);
		u.setFamilyName(randomStr);
		u.setFatherName(randomStr);
		u.setBirthDate(new Date((int) (1980 + Math.random() * 10), (int) Math.random() * 12, 1));
		u.setBirthPlace(randomStr);
		u.setMotherFamilyName(randomStr);
		u.setUserName(randomStr);
		u.setHashSha256(randomStr);
		u.setRole("dctr");
		u.setSalt(randomStr);
		emf.persist(u);
		emf.getTransaction().commit();
		return "added";
	}
}
