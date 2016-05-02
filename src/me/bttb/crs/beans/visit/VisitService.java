package me.bttb.crs.beans.visit;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import me.bttb.crs.beans.patient.PatientService;
import me.bttb.crs.model.Ptnt;
import me.bttb.crs.model.Visit;

@Service
@Scope(value = "session")
public class VisitService {
	private Visit selectedVisit;
	@Autowired
	private PatientService patientService;
	@Autowired
	private VisitDAO visitDAO;

	public VisitService() {
	}

	public Visit getSelectedVisit() {
		return selectedVisit;
	}

	public void setSelectedVisit(Visit selectedVisit) {
		this.selectedVisit = selectedVisit;
	}

	public boolean addNewVisit() {
		if (visitDAO.isThereAnEmptyVisitForPatient(patientService.getSelected())) {
			return false;
		} else {
			Visit vst = new Visit();
			vst.setVstType("New");
			vst.setPtnt(patientService.getSelected());
			vst.setVstDate(new Date());
			visitDAO.saveVisit(vst);
			return true;
		}

	}

	public PatientService getPatientService() {
		return patientService;
	}

	public void setPatientService(PatientService patientService) {
		this.patientService = patientService;
	}

	public VisitDAO getVisitDAO() {
		return visitDAO;
	}

	public void setVisitDAO(VisitDAO visitDAO) {
		this.visitDAO = visitDAO;
	}

	public List<Visit> getVisitsForPatient(Ptnt patient) {
		return visitDAO.findVisitsByPatient(patient);
	}

}
