package citizenManagment;

// Citizen Age management

import control.PopulationControl;
import sim.Citizen;

public class Age {

    public static void updateAge(PopulationControl populationControl, Citizen citizen) {
        citizen.happyBirthday();

        if (citizen.getAge() == citizen.getLifespan()) {
            populationControl.removeCitizen();
        }
    }
}
