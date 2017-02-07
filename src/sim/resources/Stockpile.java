package sim.resources;

// Where all collected resources are stored and updated
import control.PopulationControl;

public class Stockpile {

    public static int FOOD_STOCK_LIMIT_MULTIPLIER = 20;
    public static int food = 0;
    public static int wood = 0;
    private final PopulationControl populationControl;

    public Stockpile(PopulationControl populationControl) {
        this.populationControl = populationControl;
    }

    public int getFood() {
        return food;
    }

    public int getWood() {
        return wood;
    }

    public static void updateFoodStore(int food) {
        Stockpile.food += food;
    }

    public static void updateWoodStore(int wood) {
        Stockpile.wood += wood;
    }

    public static Boolean isFoodStockEmpty() {
        return food == 0;
    }

    public static boolean isWoodStockEmpty() {
        // TODO may need to check isWoodStockEnough() instead
        return wood == 0;
    }

    private int getFoodStockLimit() {
        return populationControl.getPopulation().size() * FOOD_STOCK_LIMIT_MULTIPLIER;
    }

    public Boolean isFoodStockFull() {
        return food >= getFoodStockLimit();
    }
}
