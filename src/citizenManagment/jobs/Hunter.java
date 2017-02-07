package citizenManagment.jobs;

// Hunting and food collection processes
import java.util.ArrayList;

import sim.Citizen;
import sim.resources.Consumption;
import sim.resources.Environment;
import sim.resources.Stockpile;

public class Hunter extends Workforce {

	private static final int HUNTING_RATE = 1; // Number of wildlife hunted by a hunter in a single cycle
	private static final int FOOD_PER_WILDLIFE = 3; // Number of food that can be collected from a single animal
	private int numberOfHunters;
	private int totalHuntingRate;
	private int totalFoodCollected;

	public void hunt(ArrayList<Citizen> hunters) {
		numberOfHunters = hunters.size();
		totalHuntingRate = getTotalHuntingRate();
		totalFoodCollected = getTotalFoodCollected();

		updateFoodStore();
		updateFoodReplnishRate();
		updateWildLife();

	}

	private void updateFoodStore() {
		Stockpile.updateFoodStore(totalFoodCollected);
	}

	private void updateFoodReplnishRate() {
		Consumption.updateFoodReplenishRate(totalFoodCollected);
	}

	private void updateWildLife() {
		int totalWildlifeHunted = totalHuntingRate * -1;
		Environment.updateWildlife(totalWildlifeHunted);
	}

	// Total number of wildlife hunted by all hunters in a single cycle
	private int getTotalHuntingRate() {
		return numberOfHunters * HUNTING_RATE;
	}

	// Total number of food collected by all hunters in a single cycle
	private int getTotalFoodCollected() {
		return totalHuntingRate * FOOD_PER_WILDLIFE;
	}

	@Override
	public void doJob(ArrayList<Citizen> hunters) {
		numberOfHunters = hunters.size();
		totalHuntingRate = getTotalHuntingRate();
		totalFoodCollected = getTotalFoodCollected();

		updateFoodStore();
		updateFoodReplnishRate();
		updateWildLife();
	}


}
