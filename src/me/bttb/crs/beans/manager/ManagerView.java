package me.bttb.crs.beans.manager;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

@ManagedBean
@ViewScoped
public class ManagerView implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6831187100124197159L;
	private DashboardModel model;

	public ManagerView() {
	}

	@PostConstruct
	public void init() {
		model = new DefaultDashboardModel();
		DashboardColumn c1 = new DefaultDashboardColumn();
		DashboardColumn c2 = new DefaultDashboardColumn();
		c1.addWidget("symptoms");
		c1.addWidget("measurments");
		c1.addWidget("documents");
		c2.addWidget("diagnoses");
		c2.addWidget("treatments");
		c2.addWidget("orders");
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
