package me.bttb.crs.beans.manager;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

@ManagedBean
@ViewScoped
public class ManagerView {
	private DashboardModel model;

	public ManagerView() {
	}

	@PostConstruct
	public void init() {
		model = new DefaultDashboardModel();
		DashboardColumn c1 = new DefaultDashboardColumn();
		DashboardColumn c2 = new DefaultDashboardColumn();
		c1.addWidget("symptoms");

		model.addColumn(c1);
		model.addColumn(c2);
	}

	public DashboardModel getModel() {
		return model;
	}

	public void setModel(DashboardModel model) {
		this.model = model;
	}

	////////////////// EVENTS//////////////////////////

	//////////////////////////////////////////////////
}
