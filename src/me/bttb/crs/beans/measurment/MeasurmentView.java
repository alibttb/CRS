package me.bttb.crs.beans.measurment;

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
import me.bttb.crs.model.Measurment;

@ManagedBean
@ViewScoped
public class MeasurmentView implements MetaDataTableEventHandler {

	@ManagedProperty(value = "#{measurmentService}")
	private MeasurmentService service;
	private List<Measurment> list;

	public MeasurmentView() {
	}

	@PostConstruct
	public void init() {
		service.setSelected(null);
		list = service.getAllMeasurments();
	}

	public MeasurmentService getService() {
		return service;
	}

	public void setService(MeasurmentService service) {
		this.service = service;
	}

	public List<Measurment> getList() {
		return list;
	}

	public void setList(List<Measurment> list) {
		this.list = list;
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
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Measurment meta data saved"));
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
