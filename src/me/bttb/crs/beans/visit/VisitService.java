package me.bttb.crs.beans.visit;

import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import me.bttb.crs.beans.patient.PatientService;
import me.bttb.crs.model.Ptnt;
import me.bttb.crs.model.Visit;

@Service
@Scope(value = "session")
public class VisitService implements Observer {
	private List<Visit> list;
	private Visit selected;
	@Autowired
	private PatientService patientService;
	@Autowired
	private VisitDAO dao;
	private Observable obs = new Observable();

	public VisitService() {
	}

	@PostConstruct
	private void init() {
		this.getPatientService().getObs().addObserver(this);
		refresh();
	}

	private void refresh() {
		this.setSelected(null);
		list = getVisitsForPatient(patientService.getSelected());
	}

	public boolean addNewVisit() {
		if (dao.isThereAnEmptyVisitForPatient(patientService.getSelected())) {
			return false;
		} else {
			Visit vst = new Visit();
			vst.setVstType("New");
			vst.setPtnt(patientService.getSelected());
			vst.setVstDate(new Date());
			dao.saveVisit(vst);
			return true;
		}

	}

	public PatientService getPatientService() {
		return patientService;
	}

	public void setPatientService(PatientService patientService) {
		this.patientService = patientService;
	}

	public List<Visit> getVisitsForPatient(Ptnt patient) {
		return dao.findVisitsByPatient(patient);
	}

	public List<Visit> getList() {
		return list;
	}

	public void setList(List<Visit> list) {
		this.list = list;
	}

	public Visit getSelected() {
		return selected;
	}

	public void setSelected(Visit selected) {
		this.selected = selected;
		this.obs.notifyObservers();
	}

	public VisitDAO getDao() {
		return dao;
	}

	public void setDao(VisitDAO dao) {
		this.dao = dao;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o == patientService.getObs()) {
			refresh();
		}

	}

	public Observable getObs() {
		return obs;
	}

	public void setObs(Observable obs) {
		this.obs = obs;
	}

}
