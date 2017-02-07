package citizenManagment.jobs;

// Wood chopping and collection process

import java.util.ArrayList;

import sim.Citizen;
import sim.resources.Consumption;
import sim.resources.Environment;
import sim.resources.Stockpile;

public class Lumberjack extends Workforce {

	private static final int CUTTING_RATE = 1; // Number of wildlife hunted by a hunter in a single cycle
	private static final int WOOD_PER_TREE = 3; // Number of food that can be collected from a single animal
	private int numberOfLumberjacks;
	private int totalCuttingRate;
	private int totalWoodCollected;

	public void cut(ArrayList<Citizen> lumberjacks) {
		numberOfLumberjacks = lumberjacks.size();
		totalCuttingRate = getTotalCuttingRate();
		totalWoodCollected = getTotalWoodCollected();

		updateWoodStore();
		updateWoodReplnishRate();
		updateTrees();
	}

	private void updateWoodStore() {
		Stockpile.updateWoodStore(totalWoodCollected);
	}

	private void updateWoodReplnishRate() {
		Consumption.updateWoodReplenishRate(totalWoodCollected);
	}

	private void updateTrees() {
		int totalTreesCut = totalCuttingRate * -1;
		Environment.updateTrees(totalTreesCut);
	}

	// Total number of trees cut by all lumberjacks in a single cycle
	private int getTotalCuttingRate() {
		return numberOfLumberjacks * CUTTING_RATE;
	}

	// Total number of wood collected by all lumberjacks in a single cycle
	private int getTotalWoodCollected() {
		return totalCuttingRate * WOOD_PER_TREE;
	}

	@Override
	public void doJob(ArrayList<Citizen> lumberjacks) {
		numberOfLumberjacks = lumberjacks.size();
		totalCuttingRate = getTotalCuttingRate();
		totalWoodCollected = getTotalWoodCollected();

		updateWoodStore();
		updateWoodReplnishRate();
		updateTrees();
	}

}
