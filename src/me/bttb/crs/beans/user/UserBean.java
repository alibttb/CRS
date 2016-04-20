package me.bttb.crs.beans.user;
////trying to push this

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import me.bttb.crs.model.Usr;

@Repository
@Scope("session")
public class UserBean {
	@Autowired
	private UserDAO userDAO;
	private String username;
	private String password;
	private boolean authenticated;
	private Usr userInDb;

	public UserBean() {
	}

	public UserBean(String userName, String password) {
		this.username = userName;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public String authenticate() {
		String result = "";
		userInDb = userDAO.getUserByName(getUsername());
		if (userInDb == null) {
			setAuthenticated(false);
			result = "wrong";
		} else {
			if (checkGoodPassword()) {
				setAuthenticated(true);
				result = "good";
			}
		}
		return result;
	}

	private boolean checkGoodPassword() {
		String passwordWithSalt = password + userInDb.getSalt();
		boolean goodPassword = false;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hash = md.digest(passwordWithSalt.getBytes());
			goodPassword = Arrays.equals(hash, userInDb.getHashSha256());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return goodPassword;
	}

	public String isAlreadyLoggedIn() {
		if (isAuthenticated()) {
			return "good";
		} else
			return "";
	}

	public String logout() {
		this.setAuthenticated(false);
		this.setUserInDb(null);
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/index.xhtml?faces-redirect=true";
	}

	public Usr getUserInDb() {
		return userInDb;
	}

	public void setUserInDb(Usr userInDb) {
		this.userInDb = userInDb;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

}
