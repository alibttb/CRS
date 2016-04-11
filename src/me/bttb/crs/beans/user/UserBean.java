package me.bttb.crs.beans.user;
////trying to push this

import javax.annotation.ManagedBean;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;


@Named("userBean")
@ManagedBean()
@Scope("session")
public class UserBean {
	private String username;
	private String password;
	private String role;
	private boolean authenticated;
	public UserBean() {
		// TODO Auto-generated constructor stub
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String authenticate() {
		String result = "";
		if (getUsername().equals("Admin") && getPassword().equals("Admin")) {
			setAuthenticated(true);
			setRole("Admin");
			result = "admin";
		} else {
			result = "wrong";
		}
		return result;
	}
	

}
