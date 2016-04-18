package me.bttb.crs.beans.user;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import me.bttb.crs.model.Usr;

@Named
@ManagedBean
@Scope("session")
public class UsersBean {
	@Autowired
	private UserDAO userDAO;

	private Usr selectedUser;

	public List<Usr> getUsers() {
		return userDAO.getUsers();
	}

	public int getUsersCount() {
		return userDAO.getUsersCount();
	}

	public String isFirstUser() {
		if (getUsersCount() == 0) {
			return "fisrt-user";
		} else
			return "not-first-user";
	}

	public Usr getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(Usr selectedUser) {
		this.selectedUser = selectedUser;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

}
