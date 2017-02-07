package sim;

// All simulation processes
import java.util.logging.Level;
import java.util.logging.Logger;

import control.PopulationControl;
import control.WorkforceControl;
import sim.resources.Consumption;
import sim.resources.Environment;
import sim.resources.Stockpile;

public class Samaritan {

	private final Boolean alive = true;
	private final int speed = 5; // Speed 1 = 0.1 seconds

	private final PopulationControl populationControl = new PopulationControl();
	private final WorkforceControl workforceControl = new WorkforceControl();
	private final Stockpile stockpile = new Stockpile(populationControl);
	private Gui gui = new Gui();

	public void Simulate() {

		populationControl.intialPopulation();
		while (alive) {
			try {

				Timer.updateTime();
				populationControl.updatePopulation();
				workforceControl.updateWorkforce(populationControl.getPopulation());
				workforceControl.doJobs(stockpile);
				Environment.naturesLaw();

				printOutput();
				updateGui();
				workforceControl.resetPiority();
				Thread.sleep((long) (speed * 100));
			} catch (InterruptedException ex) {
				Logger.getLogger(Samaritan.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	private void printOutput() {
		System.out.println("=======================================");
		System.out.println("Second\tPopulation\n" + Timer.time + "\t" + populationControl.getPopulation().size());

		System.out.println("");

		System.out.println("Wood\tFood\tFoodConsumptionRate\n" + Stockpile.wood + "\t" + Stockpile.food + "\t"
				+ Consumption.foodConsumptionRate);

		System.out.println("");

		System.out.println("Trees\tWildlife\n" + (int) Environment.forest + "\t" + (int) Environment.wildlife);

		System.out.println("");

		System.out.println("+WoodRate\t+FoodRate\tHunters\tIdle Citizen\n" + Consumption.woodReplenishRate + "\t\t"
				+ Consumption.foodReplenishRate + "\t\t" + workforceControl.getHunters().size() + "\t"
				+ workforceControl.getIdleCitizen().size());

		for (Citizen citizen : populationControl.getPopulation()) {
			System.out.println(citizen.toString());
		}
	}

	private void updateGui() {
		gui.update(populationControl);

	}

	public static void main(String[] args) {
		Samaritan ai = new Samaritan();
		ai.Simulate();
	}
}
