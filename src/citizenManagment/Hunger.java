package citizenManagment;

// Citizen hunger management
import control.PopulationControl;
import sim.Citizen;
import sim.Timer;
import sim.resources.Consumption;
import sim.resources.Stockpile;

public class Hunger {

    private static final int FEED_RATE = 1;
    private static final int HUNGER_RECOVER_RATE = 2;
    private final Citizen citizen;
    private final PopulationControl populationControl;

    public Hunger(PopulationControl populationControl, Citizen citizen) {
        this.populationControl = populationControl;
        this.citizen = citizen;
    }

    public void updateHunger() {
        reduceHunger();
        feedCitizen();

        if (citizen.getHunger() == 0) {
            populationControl.removeCitizen();
        }
    }

    private void reduceHunger() {
        if (Timer.time % Citizen.HUNGER_RATE == 0) {
            citizen.reduceHunger();
        }
    }

    private void feedCitizen() {
        if (Stockpile.food > 0) {
            if (citizen.getHunger() == Citizen.MAX_HUNGER - 1) {
                Stockpile.food -= FEED_RATE;
                citizen.replenishHunger(FEED_RATE);
                Consumption.updateFoodConsumptionRate(FEED_RATE);
            } else if (citizen.getHunger() < Citizen.MAX_HUNGER) {
                Stockpile.food -= HUNGER_RECOVER_RATE;
                citizen.replenishHunger(HUNGER_RECOVER_RATE);
                Consumption.updateFoodConsumptionRate(HUNGER_RECOVER_RATE);
            }
        }
    }
}
