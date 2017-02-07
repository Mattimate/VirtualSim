package sim.resources;

// Where all resources is managed. (Wildlife, Trees, stone, etc...)
public class Environment {

	public static final int WILDLIFE_LOW = 25;
	public static final int WILDLIFE_HIGH = 500;
	public static final int WILDLIFE_REPODUCTIVE_RATE = 3;
	public static final int INITIAL_WILDLIFE_COUNT = 100;

	public static final int FOREST_LOW = 25;
	public static final int FOREST_HIGH = 500;
	public static final int FOREST_REPODUCTIVE_RATE = 1;
	public static final int INITIAL_TREE_COUNT = 100;

	public static float forest = INITIAL_TREE_COUNT;
	public static float wildlife = INITIAL_WILDLIFE_COUNT;

	public static void updateTrees(int num) {
		forest += num;
	}

	public static void updateWildlife(int num) {
		wildlife += num;
	}

	public static void naturesLaw() {
		if (wildlife < WILDLIFE_HIGH) {
			wildlife += getPercentageRate(wildlife, WILDLIFE_REPODUCTIVE_RATE);
		}
		if (forest < FOREST_HIGH) {
			forest += getPercentageRate(forest, FOREST_REPODUCTIVE_RATE);
		}
	}

	private static float getPercentageRate(float resource, int repoductiveRate) {
		return (resource * repoductiveRate) / 100;
	}

	public static Boolean isWildlifeSustainable() {
		return Environment.wildlife > WILDLIFE_LOW;
	}

	public static Boolean isForestSustainable() {
		return Environment.forest > FOREST_LOW;
	}
}
