package me.bttb.crs.beans.user;
////trying to push this

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import me.bttb.crs.model.Dctr;
import me.bttb.crs.model.Nrs;
import me.bttb.crs.model.Usr;

@ManagedBean
@SessionScoped
public class UserBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8723350275155916934L;
	@ManagedProperty(value = "#{userDAO}")
	private UserDAO userDAO;
	private String username;
	private String password;
	private boolean authenticated;
	private Usr userInDb;
	@ManagedProperty("#{msg}")
	private ResourceBundle msg;

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
			FacesContext.getCurrentInstance()
					.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							msg.getString("Wrong_login_credintals"), msg.getString(
									"if_you_forgot_your_login_information_contact_the_system_administrator")));
			result = null;
		} else {
			if (checkGoodPassword(this.password)) {
				setAuthenticated(true);
				result = "good";
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						msg.getString("Wrong_login_credintals"),
						msg.getString("if_you_forgot_your_login_information_contact_the_system_administrator")));
			}
		}
		return result;
	}

	private boolean checkGoodPassword(String password) {
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

	public boolean changePassWord(String oldPass, String newPass) {
		if (!isAuthenticated() || userInDb == null || checkGoodPassword(oldPass))
			return false;
		else
			try {
				Integer saltValue = (int) (Math.random() * 1000000);
				userInDb.setSalt(saltValue.toString());
				MessageDigest md;
				md = MessageDigest.getInstance("SHA-256");
				String passwordWithSalt = password + userInDb.getSalt();
				byte[] hash = md.digest(passwordWithSalt.getBytes());
				userInDb.setHashSha256(hash);
				userDAO.updateUser(userInDb);
				return true;
			} catch (Exception e) {
				return false;
			}
	}

	public boolean isAdmin() {
		if (userInDb == null) {
			return false;
		}
		return userInDb instanceof Usr && userInDb.getRole().equalsIgnoreCase("admin");
	}

	public boolean isDoctor() {
		if (userInDb == null) {
			return false;
		}
		return userInDb instanceof Dctr && userInDb.getRole().equalsIgnoreCase("doctor");
	}

	public boolean isNurse() {
		if (userInDb == null) {
			return false;
		}
		return userInDb instanceof Nrs && userInDb.getRole().equalsIgnoreCase("nurse");
	}

	public ResourceBundle getMsg() {
		return msg;
	}

	public void setMsg(ResourceBundle msg) {
		this.msg = msg;
	}
}
