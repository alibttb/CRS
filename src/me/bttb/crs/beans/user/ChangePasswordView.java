package me.bttb.crs.beans.user;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean
@ViewScoped
public class ChangePasswordView {
	@ManagedProperty(value = "#{userBean}")
	private UserBean userBean;
	private String oldPass;
	private String newPass;

	@PostConstruct
	public void init() {
		oldPass = null;
		newPass = null;
	}

	///////////////////////// EVENTS////////////////////////
	public void onSaveButtonClicked(ActionEvent e) {
		if (userBean.changePassWord(oldPass, newPass)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Password changed", "You're going to be logged out, you should login with the new password!!"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Wrong password", "You're going to be logged out!!"));
		}
		init();
	}

	public void onCancelButtonClicked(ActionEvent e) {
		init();
	}

	public void onChPwdButtonClicked(ActionEvent e) {
		init();
	}

	public String getOldPass() {
		return oldPass;
	}

	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

}
