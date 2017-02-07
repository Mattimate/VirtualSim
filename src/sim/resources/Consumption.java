package sim.resources;

public class Consumption {

    /**
     * FOOD *
     */
    public static int foodConsumptionRate;
    public static int foodReplenishRate;
    private static int huntersCollectionRate;

    public static void updateFoodConsumptionRate(int consumptionCount) {
        foodConsumptionRate = foodConsumptionRate + consumptionCount;
    }

    public static void resetFoodConsumptionRate() {
        foodConsumptionRate = 0;
    }

    public static void updateFoodReplenishRate(int totalFoodCollected) {
        foodReplenishRate = totalFoodCollected;
        huntersCollectionRate = totalFoodCollected;
    }

    public static void resetFoodReplenishRate() {
        foodReplenishRate = 0;
    }

    public static Boolean isFoodStockSustainable() {
        return foodConsumptionRate < foodReplenishRate;
    }

    public static Boolean isTheNumOfHuntersSustainable() {
        return foodConsumptionRate < huntersCollectionRate;
    }

    /**
     * WOOD *
     */
    public static int woodConsumptionRate;
    public static int woodReplenishRate;

    public static void updateWoodConsumptionRate() {
        woodConsumptionRate++;
    }

    public static void resetWoodConsumptionRate() {
        woodConsumptionRate = 0;
    }

    public static void updateWoodReplenishRate(int num) {
        woodReplenishRate = num;
    }

    public static void resetWoodReplenishRate() {
        woodReplenishRate = 0;
    }

    public static Boolean isWoodStockSustainable() {
        return woodConsumptionRate < woodReplenishRate;
    }

}
