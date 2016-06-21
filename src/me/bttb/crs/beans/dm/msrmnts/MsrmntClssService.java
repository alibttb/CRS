package me.bttb.crs.beans.dm.msrmnts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.bttb.crs.beans.hssmptm.HasSymptomDAO;
import me.bttb.crs.beans.tkmsrmnt.TkMsrmntDAO;
import me.bttb.crs.beans.visit.VisitDAO;
import me.bttb.crs.model.HasSymptom;
import me.bttb.crs.model.Measurment;
import me.bttb.crs.model.MsrmntClss;
import me.bttb.crs.model.MsrmntClssCmpnnt;
import me.bttb.crs.model.MsrmntClssFeature;
import me.bttb.crs.model.Symptom;
import me.bttb.crs.model.TkMsrmnt;
import me.bttb.crs.model.Visit;
import me.bttb.crs.utils.Observable;

@Service
public class MsrmntClssService {
	@Autowired
	HasSymptomDAO hasSymptomDAO;
	@Autowired
	TkMsrmntDAO tkMsrmntDAO;
	@Autowired
	MsrmntClassDAO msrmntClssDAO;
	@Autowired
	MsrmntClssCmpnntDAO msrmntClssCmpnntDAO;
	@Autowired
	private VisitDAO visitDAO;
	private Observable obs = new Observable();

	private Steps step = Steps.IDLE;
	private Integer stepProgress;
	private Integer overAllProgress;

	private Thread learning;

	/////////////////////////////// Steps Enum//////////////////////////////////
	public enum Steps {
		IDLE, DELETE_ALL_KNOWN_CLASSES, GENERATE_NEW_CLASSES_FROM_VISITS, LEARN_FEATURES_OF_KNOWN_CLASSES;
	}

	/////////////////////////////// FUNCTIONAL OBJECTS/////////////////////////
	private final BiFunction<MsrmntClss, List<HasSymptom>, Double> calcDest = (mc, l) -> {
		Double dest = 0.0;
		List<HasSymptom> ls = new ArrayList<>();
		ls.addAll(l);
		for (MsrmntClssFeature f : mc.getFeatures()) {
			HasSymptom hs = ls.stream().filter(hss -> hss.getSymptom().equals(f.getSymptom())).findFirst().orElse(null);
			if (hs != null)
				ls.remove(hs);
			double localDest = Math.pow(getValueOfSympromSeverity(hs) - f.getSeverity(), 2)
					+ Math.pow(getValueOfSymptomRepetation(hs) - f.getRepeat(), 2);
			localDest = localDest * f.getImportanceFactor();
			dest = dest + Math.sqrt(localDest);
		}
		for (HasSymptom hs : ls) {
			double localDest = Math.pow(getValueOfSympromSeverity(hs), 2)
					+ Math.pow(getValueOfSymptomRepetation(hs), 2);
			localDest = localDest * mc.getCountOfVisits();
			dest = dest + Math.sqrt(localDest);
		}
		return Math.sqrt(dest);
	};
	private final Function<MsrmntClss, List<Symptom>> mcSmptms = mc -> mc.getFeatures().stream()
			.map(f -> f.getSymptom()).collect(Collectors.toList());

	public enum Repetaion {
		ONCE("Once", 0.0), RARE("Rare", 1.0), AVERAGE("Average", 2.0), FREQUENT("Frequent", 3.0);
		private String name;
		private double value;

		private Repetaion(String name, double value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public double getValue() {
			return value;
		}

		public void setValue(double value) {
			this.value = value;
		}

	}

	public enum Severity {
		MILD("Mild", 0.0), MODERATE("Moderate", 1.0), STRONG("Strong", 2.0);
		private String name;
		private double value;

		private Severity(String name, double value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public double getValue() {
			return value;
		}

		public void setValue(double value) {
			this.value = value;
		}

	}

	public void startLearning() {
		if (learning != null && learning.isAlive())
			return;
		try {
			learning = new Thread("Learning Thread") {
				@Override
				public void run() {
					removeOldMeasrmentClassesAndLearnAgain();
				};
			};
			learning.start();
		} catch (IllegalThreadStateException itsex) {

		}
	}

	public MsrmntClssService() {
	}

	protected void removeOldMeasrmentClassesAndLearnAgain() {
		step = Steps.DELETE_ALL_KNOWN_CLASSES;
		stepProgress = 1;
		overAllProgress = 1;
		progressNotification();
		removeAllKnownMsrmntClss();

		step = Steps.GENERATE_NEW_CLASSES_FROM_VISITS;
		stepProgress = 1;
		overAllProgress = 20;
		progressNotification();
		generateMeasurementClasses();

		step = Steps.LEARN_FEATURES_OF_KNOWN_CLASSES;
		stepProgress = 1;
		overAllProgress = 55;
		progressNotification();
		learnClassesFeatures();

		step = Steps.IDLE;
		stepProgress = 99;
		overAllProgress = 100;
		progressNotification();
	}

	private void removeAllKnownMsrmntClss() {
		List<MsrmntClss> mcls = msrmntClssDAO.findAll();
		int size = mcls.size();
		int done = 0;
		for (MsrmntClss mc : mcls) {
			msrmntClssDAO.delete(mc);
			stepProgress = (int) (99 * ((double) ++done / (double) size));
			progressNotification();
		}

	}

	public List<MsrmntClss> getAllMeasurementClasses() {
		return msrmntClssDAO.findAll();
	}

	public MsrmntClss findMsrmntClssForVisit(Visit visit) {
		List<TkMsrmnt> tkls = tkMsrmntDAO.findTkMsrmntInVisit(visit);
		final List<Measurment> measurments = tkls.stream().map(tkms -> tkms.getMeasurment())
				.collect(Collectors.toList());
		List<MsrmntClss> msrmntClssList = msrmntClssDAO.findAll();
		Function<MsrmntClssCmpnnt, Measurment> mccMsrMapper = (mcc) -> mcc.getMeasurment();
		Predicate<MsrmntClss> p0 = mc -> {
			List<Measurment> ml = mc.getCmpnnts().stream().map(mccMsrMapper).collect(Collectors.toList());
			return ml.containsAll(measurments) && ml.size() == measurments.size();
		};
		return msrmntClssList.stream().filter(p0).findFirst().orElse(null);
	}

	private void generateMeasurementClasses() {
		stepProgress = 1;
		progressNotification();
		List<TkMsrmnt> tkmList = tkMsrmntDAO.findTkMsrmntInOldVisits();
		stepProgress = 10;
		progressNotification();
		Collections.sort(tkmList, (o1, o2) -> (int) (o1.getVisit().getId() - o2.getVisit().getId()));
		stepProgress = 20;
		progressNotification();
		List<MsrmntClss> classesList = new ArrayList<>();
		int allMsrmnts = tkmList.size();
		while (!tkmList.isEmpty()) {
			TkMsrmnt tk1 = tkmList.get(0); // take the first measurement in the
											// first visit
			List<TkMsrmnt> tmp = tkmList.stream().filter(t -> t.getVisit().getId() == tk1.getVisit().getId())
					.collect(Collectors.toList());// Take all measurements in
													// that visit into a temp
													// list
			tkmList.removeAll(tmp);// remove the taken Msrmnts so they don't
			int msrmntsToGo = tkmList.size();

			stepProgress = (int) (20.0 + (50.0 * ((double) allMsrmnts - (double) msrmntsToGo) / (double) allMsrmnts));
			progressNotification();
			MsrmntClss tmpClass = new MsrmntClss(); // interfere with further
													// operations
			tmpClass.setCountOfVisits(1);
			tmpClass.setCmpnnts(tmp.stream().map(t -> {
				MsrmntClssCmpnnt mmc = new MsrmntClssCmpnnt();
				mmc.setMeasurment(t.getMeasurment());
				mmc.setMc(tmpClass);
				return mmc;
			}).collect(Collectors.toList()));

			try {
				MsrmntClss knownClass = classesList.stream().filter(m -> m.equals(tmpClass)).findFirst().get();
				knownClass.incrementCountOfVisits();
			} catch (NoSuchElementException nsee) {
				classesList.add(tmpClass);
			}
		}
		classesList.stream().distinct().forEach(mc -> msrmntClssDAO.add(mc));
		stepProgress = 99;
		progressNotification();
	}

	private void progressNotification() {
		obs.forceNotifyAllObservers();
		try {
			Thread.sleep(150);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void learnClassesFeatures() {
		stepProgress = 1;
		progressNotification();
		List<Visit> visits = visitDAO.findSavedVisits();
		stepProgress = 10;
		progressNotification();
		int allSteps = 2 * visits.size();
		int stepsDone = 0;
		for (Visit visit : visits) {
			MsrmntClss mc = findMsrmntClssForVisit(visit);
			stepProgress = 10 + (int) (89.0 * ((double) ++stepsDone / (double) allSteps));
			progressNotification();
			if (mc != null) {
				for (HasSymptom hss : hasSymptomDAO.findHasSymptomsInVisit(visit)) {
					// if (mc.getFeatures() == null)
					// mc.setFeatures(new ArrayList<>());
					Optional<MsrmntClssFeature> op = mc.getFeatures().stream()
							.filter(f -> f.getSymptom() != null && f.getSymptom().equals(hss.getSymptom())).findFirst();
					if (op.isPresent()) {
						MsrmntClssFeature mcf = op.get();
						mcf.modifyRepeatAverageBY(getValueOfSymptomRepetation(hss));
						mcf.modifySeverityAvarageBy(getValueOfSympromSeverity(hss));
						mcf.incrementTimesAppeared();
					} else {
						MsrmntClssFeature mcf = new MsrmntClssFeature();
						mcf.setMc(mc);
						mcf.setSymptom(hss.getSymptom());
						mcf.setTimesAppeard(1);
						mcf.setRepeat(getValueOfSymptomRepetation(hss));
						mcf.setSeverity(getValueOfSympromSeverity(hss));
						mc.getFeatures().add(mcf);
					}
				}
				msrmntClssDAO.update(mc);
			}
			stepProgress = 10 + (int) (89.0 * ((double) ++stepsDone / (double) allSteps));
			progressNotification();

		}
	}

	public MsrmntClss predictMeasurementClass(Visit visit) {
		if (visit == null)
			return null;
		List<HasSymptom> lhs = hasSymptomDAO.findHasSymptomsInVisit(visit);
		if (lhs.isEmpty())
			return null; //// visit has no symptoms, nothing to predict

		List<MsrmntClss> msrmntClssList = msrmntClssDAO.findAll();
		List<Symptom> ls = lhs.stream().map(hs -> hs.getSymptom()).collect(Collectors.toList());
		msrmntClssList = msrmntClssList.stream().filter(mc -> mcSmptms.apply(mc).containsAll(ls))
				.collect(Collectors.toList());//// get only candidates with the
												//// same symptoms as features
		//// if the visit contains new symptoms then it can't be mapped to a
		//// known class since the importance of that symptom is zero
		//// but if the class contains symptoms that aren't in the visit the the
		//// destination is the full length of that feature

		if (msrmntClssList.isEmpty()) // no classes can be mapped
			return null;

		if (msrmntClssList.size() == 1) { // only one class, that's our answer
			return msrmntClssList.get(0);
		}

		Map<Double, MsrmntClss> mp = new HashMap<>(); //// destination from each
														//// class
		for (MsrmntClss msrmntClss : msrmntClssList) { // calculate destinations
														// for the visit from
														// each class
			Double dest = calcDest.apply(msrmntClss, lhs);
			mp.put(dest, msrmntClss);
		}

		double leastDestance = mp.keySet().stream().min(Comparator.comparingDouble(d -> d)).get();
		// find the closest class
		return mp.get(leastDestance);
	}

	public double getValueOfSymptomRepetation(HasSymptom hss) {
		if (hss == null)
			return 0;
		return Repetaion.valueOf(hss.getRepetation().toUpperCase()).value;
	}

	public String closestRepetation(double s) {
		int i = (int) Math.round(s);
		return Repetaion.values()[i].name;
	}

	public double getValueOfSympromSeverity(HasSymptom hss) {
		if (hss == null)
			return 0;
		return Severity.valueOf(hss.getSeverity().toUpperCase()).value;
	}

	public String closestSeverity(double s) {
		int i = (int) Math.round(s);
		return Severity.values()[i].name;
	}

	public VisitDAO getVisitDAO() {
		return visitDAO;
	}

	public void setVisitDAO(VisitDAO visitDAO) {
		this.visitDAO = visitDAO;
	}

	public Observable getObs() {
		return obs;
	}

	public void setObs(Observable obs) {
		this.obs = obs;
	}

	public Steps getStep() {
		return step;
	}

	public void setStep(Steps step) {
		this.step = step;
	}

	public Integer getStepProgress() {
		return stepProgress;
	}

	public void setStepProgress(Integer stepProgress) {
		this.stepProgress = stepProgress;
	}

	public Integer getOverAllProgress() {
		return overAllProgress;
	}

	public void setOverAllProgress(Integer overAllProgress) {
		this.overAllProgress = overAllProgress;
	}

}
