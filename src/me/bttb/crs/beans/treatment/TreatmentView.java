package me.bttb.crs.beans.treatment;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import me.bttb.crs.beans.MetaDataTableEventHandler;
import me.bttb.crs.model.Treatment;

@ManagedBean
@ViewScoped
public class TreatmentView implements Serializable,MetaDataTableEventHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 734878836954655082L;
	@ManagedProperty(value = "#{treatmentService}")
	private TreatmentService service;
	@ManagedProperty(value = "#{msg}")
	private ResourceBundle msg;
	private List<Treatment> list;
	private boolean rowSelected;

	public TreatmentView() {
	}

	@PostConstruct
	public void init() {
		rowSelected = false;
		service.setSelected(null);
		list = service.getAllTreatments();
	}

	public TreatmentService getService() {
		return service;
	}

	public void setService(TreatmentService service) {
		this.service = service;
	}

	public List<Treatment> getList() {
		return list;
	}

	public void setList(List<Treatment> list) {
		this.list = list;
	}

	public boolean isRowSelected() {
		return rowSelected;
	}

	public void setRowSelected(boolean rowSelected) {
		this.rowSelected = rowSelected;
	}

	private void refreshSelectedState() {
		rowSelected = service.getSelected() != null;
	}

	///////////////////////// EVENT/////////////////////
	@Override
	public void onRefreshButtonClicked(ActionEvent event) {
		init();
	}

	@Override
	public void onAddNewDialogOpen(ActionEvent event) {
		service.createNew();
	}

	@Override
	public void onEditSelectedDialogOpen(ActionEvent event) {

	}

	@Override
	public void onSaveButtonClicked(ActionEvent event) {
		if (service.save()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(getMsg().getString("TreatmentDataSaved")));
			init();
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(getMsg().getString("SaveError")));
		}

	}

	@Override
	public void onCancelButtonClicked(ActionEvent event) {
		service.cancel();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Canceled"));
	}

	@Override
	public void onRowSelect(SelectEvent event) {
		refreshSelectedState();
	}

	@Override
	public void onRowUnSelect(UnselectEvent event) {
		refreshSelectedState();
	}

	public ResourceBundle getMsg() {
		return msg;
	}

	public void setMsg(ResourceBundle msg) {
		this.msg = msg;
	}

}
