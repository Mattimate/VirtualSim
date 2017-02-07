package control;

// All citizens processes
import citizenManagment.Age;

import java.util.ArrayList;
import java.util.Iterator;

import citizenManagment.Health;
import sim.Citizen;
import sim.Timer;
import sim.resources.Consumption;

public class PopulationControl {

	int id = 1;
	int newArrivalInterval = 10;

	private static final ArrayList<Citizen> population = new ArrayList<>();
	private Citizen citizen;
	private Iterator<Citizen> aCitizen;

	public void intialPopulation() {
		for (int i = 0; i < 3; i++) {
			addNewCitizen();
		}
	}

	public void updatePopulation() {
		newArrival();
		updateCitizen();
	}

	public void newArrival() {
		if (Timer.time % newArrivalInterval == 0) {
			addNewCitizen();
		}
	}

	public void addNewCitizen() {
		Citizen newCitizen = new Citizen(id++);
		WorkforceControl.updateIdleCitizens(newCitizen);
		population.add(newCitizen);
	}

	private void updateCitizen() {
		Consumption.resetFoodConsumptionRate();
		for (aCitizen = population.iterator(); aCitizen.hasNext();) {
			citizen = aCitizen.next();

			Age.updateAge(this, citizen);
			Health.updateHealth(this, citizen);
		}
	}

	public void removeCitizen() {
		WorkforceControl.resignJob(citizen);
		aCitizen.remove();
	}

	public ArrayList<Citizen> getPopulation() {
		return population;
	}
}
