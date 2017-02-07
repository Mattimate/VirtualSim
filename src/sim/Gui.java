package sim;

import javax.swing.JFrame;
import javax.swing.JLabel;

import control.PopulationControl;
import sim.resources.Consumption;
import sim.resources.Environment;
import sim.resources.Stockpile;

import java.awt.List;
import java.awt.Window;

public class Gui {

	private JFrame frame;
	private JLabel populationC;
	private JLabel timerC;
	private JLabel woodC;
	private JLabel foodC;
	private JLabel treesC;
	private JLabel wildlifeC;
	private JLabel fdConRateC;
	private List idList;
	private List ageList;
	private List dobList;
	private List lsList;
	private List hpList;
	private List hungerList;
	private List jobList;

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 513, 581);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel populationL = new JLabel("Population:");
		populationL.setBounds(123, 11, 75, 14);
		frame.getContentPane().add(populationL);

		populationC = new JLabel("pop");
		populationC.setBounds(189, 11, 46, 14);
		frame.getContentPane().add(populationC);

		JLabel timerL = new JLabel("Timer:");
		timerL.setBounds(10, 11, 40, 14);
		frame.getContentPane().add(timerL);

		timerC = new JLabel("time");
		timerC.setBounds(56, 11, 57, 14);
		frame.getContentPane().add(timerC);

		woodC = new JLabel("wood");
		woodC.setBounds(56, 64, 57, 14);
		frame.getContentPane().add(woodC);

		JLabel woodL = new JLabel("Wood:");
		woodL.setBounds(10, 64, 40, 14);
		frame.getContentPane().add(woodL);

		foodC = new JLabel("food");
		foodC.setBounds(169, 64, 57, 14);
		frame.getContentPane().add(foodC);

		JLabel foodL = new JLabel("Food:");
		foodL.setBounds(123, 64, 40, 14);
		frame.getContentPane().add(foodL);

		JLabel treesL = new JLabel("Trees:");
		treesL.setBounds(10, 104, 40, 14);
		frame.getContentPane().add(treesL);

		treesC = new JLabel("trees");
		treesC.setBounds(56, 104, 57, 14);
		frame.getContentPane().add(treesC);

		JLabel wildLifeL = new JLabel("Wildlife:");
		wildLifeL.setBounds(123, 104, 46, 14);
		frame.getContentPane().add(wildLifeL);

		wildlifeC = new JLabel("wildlife");
		wildlifeC.setBounds(190, 104, 57, 14);
		frame.getContentPane().add(wildlifeC);

		JLabel fdConRateL = new JLabel("Food Consumption:");
		fdConRateL.setBounds(236, 64, 111, 14);
		frame.getContentPane().add(fdConRateL);

		fdConRateC = new JLabel("ConRate");
		fdConRateC.setBounds(357, 64, 57, 14);
		frame.getContentPane().add(fdConRateC);

		JLabel idL = new JLabel("Citizen");
		idL.setBounds(10, 279, 57, 14);
		frame.getContentPane().add(idL);

		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(73, 279, 57, 14);
		frame.getContentPane().add(lblAge);

		JLabel lblDob = new JLabel("DOB");
		lblDob.setBounds(136, 279, 57, 14);
		frame.getContentPane().add(lblDob);

		JLabel lblLifespan = new JLabel("Lifespan");
		lblLifespan.setBounds(199, 279, 57, 14);
		frame.getContentPane().add(lblLifespan);

		JLabel lblHitPoints = new JLabel("Hit Points");
		lblHitPoints.setBounds(262, 279, 57, 14);
		frame.getContentPane().add(lblHitPoints);

		JLabel lblHunger = new JLabel("Hunger");
		lblHunger.setBounds(325, 279, 57, 14);
		frame.getContentPane().add(lblHunger);

		JLabel lblJob = new JLabel("Job");
		lblJob.setBounds(388, 279, 57, 14);
		frame.getContentPane().add(lblJob);

		idList = new List();
		idList.setBounds(10, 299, 57, 238);
		frame.getContentPane().add(idList);

		ageList = new List();
		ageList.setBounds(73, 299, 57, 238);
		frame.getContentPane().add(ageList);

		dobList = new List();
		dobList.setBounds(136, 299, 57, 238);
		frame.getContentPane().add(dobList);

		lsList = new List();
		lsList.setBounds(199, 299, 57, 238);
		frame.getContentPane().add(lsList);

		hpList = new List();
		hpList.setBounds(262, 299, 57, 238);
		frame.getContentPane().add(hpList);

		hungerList = new List();
		hungerList.setBounds(325, 299, 57, 238);
		frame.getContentPane().add(hungerList);

		jobList = new List();
		jobList.setBounds(388, 299, 99, 238);
		frame.getContentPane().add(jobList);

	}

	public void update(PopulationControl populationControl) {
		populationC.setText(populationControl.getPopulation().size() + "");
		timerC.setText(Timer.time + "");
		woodC.setText(Stockpile.wood + "");
		foodC.setText(Stockpile.food + "");
		fdConRateC.setText(Consumption.foodConsumptionRate + "");
		treesC.setText((int) Environment.forest + "");
		wildlifeC.setText((int) Environment.wildlife + "");

		idList.removeAll();
		ageList.removeAll();
		dobList.removeAll();
		lsList.removeAll();
		hpList.removeAll();
		hungerList.removeAll();
		jobList.removeAll();

		
		for (Citizen citizen : populationControl.getPopulation()) {
			idList.add(citizen.getId() + "");
			ageList.add(citizen.getAge() + "");
			dobList.add(citizen.getDOB() + "");
			lsList.add(citizen.getLifespan() + "");
			hpList.add(citizen.getHealth() + "");
			hungerList.add(citizen.getHunger() + "");
			jobList.add(citizen.getJob() + "");
		}

	}
}
