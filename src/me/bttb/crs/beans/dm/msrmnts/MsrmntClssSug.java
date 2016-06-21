package me.bttb.crs.beans.dm.msrmnts;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import me.bttb.crs.beans.tkmsrmnt.TkMsrmntDAO;
import me.bttb.crs.beans.tkmsrmnt.TkMsrmntService;
import me.bttb.crs.beans.visit.VisitService;
import me.bttb.crs.model.Measurment;
import me.bttb.crs.model.MsrmntClss;
import me.bttb.crs.model.TkMsrmnt;
import me.bttb.crs.model.Visit;

@ManagedBean(name = "mcsv")
@SessionScoped
public class MsrmntClssSug implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{msrmntClssService}")
	private MsrmntClssService service;
	@ManagedProperty("#{visitService}")
	private VisitService visitService;
	@ManagedProperty("#{tkMsrmntDAO}")
	private TkMsrmntDAO dao;
	@ManagedProperty("#{tkMsrmntService}")
	private TkMsrmntService tkMsrmntService;
	private MsrmntClss suggestedClass;

	@PostConstruct
	public void init() {
		System.out.println("Building suggester view");
	}

	@PreDestroy
	public void des() {
		System.out.println("Destroying suggester view");
	}

	public void onSuggestButtonPressed(ActionEvent ev) {
		suggestedClass = service.predictMeasurementClass(visitService.getSelected());
	}

	public void onAcceptSuggestionPressed(ActionEvent ev) {
		List<Measurment> ml = suggestedClass.getCmpnnts().stream().map(mcc -> mcc.getMeasurment())
				.collect(Collectors.toList());
		Visit vs = visitService.getSelected();
		List<TkMsrmnt> tml = dao.findTkMsrmntInVisit(vs);
		tml.forEach(tm -> getDao().deleteTkMsrmnt(tm));
		ml.stream().forEach(m -> {
			TkMsrmnt tm = new TkMsrmnt();
			tm.setMeasurment(m);
			tm.setVisit(vs);
			getDao().addTkMsrmnt(tm);
		});
		tkMsrmntService.setList(dao.findTkMsrmntInVisit(vs));
	}

	public VisitService getVisitService() {
		return visitService;
	}

	public void setVisitService(VisitService visitService) {
		this.visitService = visitService;
	}

	public MsrmntClssService getService() {
		return service;
	}

	public void setService(MsrmntClssService service) {
		this.service = service;
	}

	public MsrmntClss getSuggestedClass() {
		return suggestedClass;
	}

	public void setSuggestedClass(MsrmntClss suggestedClass) {
		this.suggestedClass = suggestedClass;
	}

	public TkMsrmntDAO getDao() {
		return dao;
	}

	public void setDao(TkMsrmntDAO dao) {
		this.dao = dao;
	}

	public TkMsrmntService getTkMsrmntService() {
		return tkMsrmntService;
	}

	public void setTkMsrmntService(TkMsrmntService tkMsrmntService) {
		this.tkMsrmntService = tkMsrmntService;
	}

}
