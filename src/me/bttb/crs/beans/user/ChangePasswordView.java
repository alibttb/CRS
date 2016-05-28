package me.bttb.crs.beans.user;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean
@ViewScoped
public class ChangePasswordView implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8561243563047405096L;
	@ManagedProperty(value = "#{userBean}")
	private UserBean userBean;
	@ManagedProperty(value="#{msg}")
	private ResourceBundle msg;
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
					getMsg().getString("Password_changed"), msg.getString("pwdChLogOff")));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, msg.getString("Wrong_password"), msg.getString("WrngPwd")));
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

	public ResourceBundle getMsg() {
		return msg;
	}

	public void setMsg(ResourceBundle msg) {
		this.msg = msg;
	}

}
