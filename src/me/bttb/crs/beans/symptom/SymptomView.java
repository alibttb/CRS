package me.bttb.crs.beans.symptom;

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
import me.bttb.crs.model.Symptom;

@ManagedBean
@ViewScoped
public class SymptomView implements MetaDataTableEventHandler {
	@ManagedProperty(value = "#{symptomService}")
	private SymptomService service;
	private List<Symptom> list;

	@PostConstruct
	public void init() {
		service.setSelected(null);
		list = service.getAllSymptoms();
	}

	public SymptomService getService() {
		return service;
	}

	public void setService(SymptomService symptomService) {
		this.service = symptomService;
	}

	public List<Symptom> getList() {
		return list;
	}

	public void setList(List<Symptom> symptoms) {
		this.list = symptoms;
	}

	/////////////////////////// EVENTS////////////////////////////

	@Override
	public void onRefreshButtonClicked(ActionEvent event) {
		init();
	}

	@Override
	public void onAddNewDialogOpen(ActionEvent event) {
		service.createNewSymptom();
	}

	@Override
	public void onEditSelectedDialogOpen(ActionEvent event) {

	}

	@Override
	public void onSaveButtonClicked(ActionEvent event) {
		if (service.save()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Symptom meta data saved"));
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

	}

	@Override
	public void onRowUnSelect(UnselectEvent event) {
	}

}