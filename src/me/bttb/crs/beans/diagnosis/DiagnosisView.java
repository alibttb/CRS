package me.bttb.crs.beans.diagnosis;

import java.util.List;

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
import me.bttb.crs.model.Diagnosis;

@ManagedBean
@ViewScoped
public class DiagnosisView implements MetaDataTableEventHandler {

	@ManagedProperty(value = "#{diagnosisService}")
	private DiagnosisService service;
	private List<Diagnosis> list;
	private boolean rowSelected;

	public DiagnosisView() {
	}

	@PostConstruct
	public void init() {
		rowSelected = false;
		service.setSelected(null);
		list = service.getAllDiagnoses();
	}

	public DiagnosisService getService() {
		return service;
	}

	public void setService(DiagnosisService service) {
		this.service = service;
	}

	public List<Diagnosis> getList() {
		return list;
	}

	public void setList(List<Diagnosis> list) {
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
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Diagnosis meta data saved"));
			init();
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("There was an error, notiong saved!"));
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

}
