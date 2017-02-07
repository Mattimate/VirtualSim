package control;

import java.util.ArrayList;
import java.util.Iterator;

import citizenManagment.jobs.Hunter;
import citizenManagment.jobs.Lumberjack;
import citizenManagment.jobs.Workforce;
import sim.Citizen;
import sim.Citizen.Job;
import sim.resources.Consumption;
import sim.resources.Environment;
import sim.resources.Stockpile;

public class WorkforceControl {

	private static final int AGE_OF_WORKER = 15;

	private Citizen idleCitizen;
	private Iterator<Citizen> anIdleCitizen;
	private Workforce hunter = new Hunter();
	private Workforce lumberjack = new Lumberjack();
	
	private static final ArrayList<Citizen> idle = new ArrayList<>();
	private static final ArrayList<Citizen> hunters = new ArrayList<>();
	private static final ArrayList<Citizen> lumberjacks = new ArrayList<>();
	private static final ArrayList<Citizen> builders = new ArrayList<>();

	private int coolDown;
	private int piority; // TODO Needs a better identifier for AI to control

	public void updateWorkforce(ArrayList<Citizen> population) {
		for (anIdleCitizen = idle.iterator(); anIdleCitizen.hasNext();) {
			idleCitizen = anIdleCitizen.next();
			if (isAgeOfWorker()) {
				setJob();
			}
		}
	}

	public static void updateIdleCitizens(Citizen citizen) {
		citizen.setJob(Job.UNASSIGNED);
		idle.add(citizen);
	}

	private void setJob() {
		switch (getJobPiority()) {
		case HUNTER:
			setProfession(hunters, Job.HUNTER);
			break;
		case LUMBERJACK:
			setProfession(lumberjacks, Job.LUMBERJACK);
			break;
		case BUILDER:
			setProfession(builders, Job.BUILDER);
			break;
		default:
			break;
		}
	}

	private Job getJobPiority() {
		if (piority <= 0) {
			if (!Consumption.isTheNumOfHuntersSustainable() && Environment.isWildlifeSustainable() || Stockpile.isFoodStockEmpty()) {
				piority++;
				return Job.HUNTER;
			}
		} else if (piority <= 1) {
			if (!Consumption.isWoodStockSustainable() && Environment.isForestSustainable() || Stockpile.isWoodStockEmpty()) {
				piority++;
				return Job.LUMBERJACK;
			}
		} else if (piority <= 2) {
			piority++;
			return Job.BUILDER;
		}
		return Job.UNASSIGNED;
	}

	private void setProfession(ArrayList<Citizen> profession, Job job) {
		idleCitizen.setJob(job);
		profession.add(idleCitizen);
		anIdleCitizen.remove();

	}

	public void doJobs(Stockpile stockpile) {
		doHunt(stockpile);
		doCut();
	}

	private void doHunt(Stockpile stockpile) {
		Consumption.resetFoodReplenishRate();
		if (doHuntingConditionsMeet(stockpile)) {
			hunter.doJob(hunters);
		}
	}

	private void doCut() {
		Consumption.resetWoodConsumptionRate();
		if (Environment.isForestSustainable()) {
			lumberjack.doJob(lumberjacks);
		}
	}

	private Boolean doHuntingConditionsMeet(Stockpile stockpile) {
		return Environment.isWildlifeSustainable() && !Consumption.isFoodStockSustainable() && !stockpile.isFoodStockFull();
	}

	private Boolean isAgeOfWorker() {
		return idleCitizen.getAge() > AGE_OF_WORKER;
	}

	public static void resignJob(Citizen citizen) {
		switch (citizen.getJob()) {
		case HUNTER:
			resign(hunters, citizen);
			break;
		case LUMBERJACK:
			resign(lumberjacks, citizen);
			break;
		case BUILDER:
			resign(builders, citizen);
			break;
		default:
			break;
		}
	}

	private static void resign(ArrayList<Citizen> profession, Citizen citizen) {
		for (Iterator<Citizen> aProfession = profession.iterator(); aProfession.hasNext();) {
			if (aProfession.next().getId() == citizen.getId()) {
				aProfession.remove();
			}
		}
	}

	public ArrayList<Citizen> getHunters() {
		return hunters;
	}

	public ArrayList<Citizen> getIdleCitizen() {
		return idle;
	}

	public void resetPiority() {
		piority = 0;
	}

}
