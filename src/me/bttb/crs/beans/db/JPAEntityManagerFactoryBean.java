package me.bttb.crs.beans.db;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("jpaEmfBean")
@Scope("application")
public class JPAEntityManagerFactoryBean {
	EntityManagerFactory entityManagerFactory;

	public EntityManager createEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

	@PostConstruct
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory("CRS");
	}

}
