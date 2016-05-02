package me.bttb.crs.beans.user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import me.bttb.crs.model.Dctr;
import me.bttb.crs.model.Nrs;
import me.bttb.crs.model.Usr;

@ManagedBean
@ViewScoped
public class UserView {
	@ManagedProperty(value = "#{userService}")
	private UserService service;
	private List<Usr> list;
	private boolean rowSelected;
	private String password;

	public UserView() {
	}

	@PostConstruct
	public void init() {
		this.password = null;
		service.setSelected(null);
		setList(service.getAllUsers());
	}

	public UserService getService() {
		return service;
	}

	public void setService(UserService service) {
		this.service = service;
	}

	public List<Usr> getList() {
		return list;
	}

	public void setList(List<Usr> list) {
		this.list = list;
	}

	public boolean isRowSelected() {
		return rowSelected;
	}

	public void setRowSelected(boolean rowSelected) {
		this.rowSelected = rowSelected;
	}

	//////////////////////// EVENTS//////////////////////////
	public void onAddAdminButtonClicked(ActionEvent event) {
		service.setSelected(new Usr());
		service.getSelected().setPrsnType("Usr");
		service.getSelected().setContactInfoList(new ArrayList<>());
	}

	public void onAddDoctorButtonClicked(ActionEvent event) {
		service.setSelected(new Dctr());
		service.getSelected().setPrsnType("Dctr");
		service.getSelected().setContactInfoList(new ArrayList<>());
	}

	public void onAddNurseButtonClicked(ActionEvent event) {
		service.setSelected(new Nrs());
		service.getSelected().setPrsnType("Nrs");
		service.getSelected().setContactInfoList(new ArrayList<>());
	}

	public void onEditButtonClicked(ActionEvent event) {
		password = null;
	}

	public void onRefreshButtonClicked(ActionEvent event) {
		init();
	}

	public void onSaveButtonClicked(ActionEvent event) {
		Usr user = service.getSelected();
		if (password != null) { //// save password
			try {
				Integer saltValue = (int) (Math.random() * 1000000);
				user.setSalt(saltValue.toString());
				MessageDigest md;
				md = MessageDigest.getInstance("SHA-256");
				String passwordWithSalt = password + user.getSalt();
				byte[] hash = md.digest(passwordWithSalt.getBytes());
				user.setHashSha256(hash);
			} catch (NoSuchAlgorithmException e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error during save operation", e.getMessage()));
			}
		}
		service.save();
		init();
	}

	public void onCancelButtonClicked(ActionEvent event) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Canceled", ""));
		service.cancel();
		init();
	}


	//////////////////////////// ACTIONS/////////////////////
	public void unSelect() {
		service.setSelected(null);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
