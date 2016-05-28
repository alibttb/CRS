package me.bttb.crs.beans.user;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import me.bttb.crs.model.Usr;

@Component
@Scope("session")
public class UsersBean implements Serializable {

	private static final long serialVersionUID = -2373315308806782176L;
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
