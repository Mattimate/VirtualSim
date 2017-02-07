package citizenManagment;

// Citizen health management
import control.PopulationControl;
import sim.Citizen;

public class Health {

	private static final int LOW_HUNGER_COUNT = 25;
	private static final int HUNGER_DAMAGE = 1;

	public static void updateHealth(PopulationControl populationControl, Citizen citizen) {
		Hunger hunger = new Hunger(populationControl, citizen);

		if (citizen.getHunger() < LOW_HUNGER_COUNT) {
			citizen.updateDamage(HUNGER_DAMAGE);
		}
		hunger.updateHunger();
	}
}
