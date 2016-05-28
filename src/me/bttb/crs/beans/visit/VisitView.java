package me.bttb.crs.beans.visit;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;

import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

@ManagedBean
@ViewScoped
public class VisitView {

	@ManagedProperty(value = "#{visitService}")
	private VisitService service;

	private DashboardModel dashboardModel;

	public VisitView() {
	}

	@PostConstruct
	public void init() {
		this.setDashboardModel(new DefaultDashboardModel());
		DashboardColumn c1 = new DefaultDashboardColumn();
		DashboardColumn c2 = new DefaultDashboardColumn();
		c1.addWidget("symptoms");
		c1.addWidget("measurments");
		c1.addWidget("orders");
		c2.addWidget("diaganosis");
		c2.addWidget("treatments");
		c2.addWidget("documents");
		dashboardModel.addColumn(c1);
		dashboardModel.addColumn(c2);
	}

	public DashboardModel getDashboardModel() {
		return dashboardModel;
	}

	public void setDashboardModel(DashboardModel dashboardModel) {
		this.dashboardModel = dashboardModel;
	}

	public VisitService getService() {
		return service;
	}

	public void setService(VisitService service) {
		this.service = service;
	}

	///////////////// EVENTS//////////////////////////////////

	public void onEndVisitClicked(ActionEvent ae) {
		this.service.endVisit();
	}

	public void onOpenVisitClicked(ActionEvent ae) {
		this.service.openVisit();
	}
	public void onSaveNotesClicked(ActionEvent ev){
		this.service.saveVisit();
	}

}
