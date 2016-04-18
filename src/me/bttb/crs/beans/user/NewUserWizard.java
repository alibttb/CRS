package me.bttb.crs.beans.user;

import java.security.MessageDigest;

import javax.annotation.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.FlowEvent;
import org.springframework.beans.factory.annotation.Autowired;

import me.bttb.crs.model.Usr;

@Named
@ManagedBean
@ViewScoped
public class NewUserWizard {
	@Autowired
	private UserDAO dao;

	private String password;
	private Usr user = new Usr();

	public String save() throws Exception {
		Integer saltValue = (int) (Math.random() * 1000000);
		user.setSalt(saltValue.toString());
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		String passwordWithSalt = password + user.getSalt();
		byte[] hash = md.digest(passwordWithSalt.getBytes());
		user.setHashSha256(hash);
		dao.addUser(user);
		if (user.getPid() != null) {
			return "success";
		} else {
			return "failure";
		}

	}

	public String onFlowProcess(FlowEvent event) {
		return event.getNewStep();
	}

	public UserDAO getDao() {
		return dao;
	}

	public void setDao(UserDAO dao) {
		this.dao = dao;
	}

	public Usr getUser() {
		return user;
	}

	public void setUser(Usr user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
