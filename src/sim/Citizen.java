package sim;

import java.util.Random;

public class Citizen {

    public enum Job {
        LUMBERJACK, BUILDER, HUNTER, UNASSIGNED, NONE
    }

    public static final int HUNGER_RATE = 1;
    public static final int MAX_HEALTH = 100;
    public static final int MAX_HUNGER = 50;
    public static final int MAX_HAPPINESS = 100;
    private final int minLifespan = 70;
    private final int maxLifspan = 100 - minLifespan;

    private int id;
    private int age;
    private final int dob;
    private final int lifespan;
    private int health;
    private int hunger;
    private int happiness;
    private Job job;

    private Random random = new Random();

    public Citizen(int id) {
        this.id = id;
        this.dob = (int) Timer.time;
        health = MAX_HEALTH;
        hunger = MAX_HUNGER;
        happiness = MAX_HAPPINESS;
        lifespan = random.nextInt(maxLifspan) + minLifespan;
        job = Job.UNASSIGNED;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public int getDOB() {
        return dob;
    }

    public int getLifespan() {
        return lifespan;
    }

    public int getHealth() {
        return health;
    }

    public int getHunger() {
        return hunger;
    }

    public int getHappiness() {
        return happiness;
    }

    public Job getJob() {
        return job;
    }

    public void happyBirthday() {
        age = (int) (Timer.time - dob);
    }

    public void updateDamage(int damage) {
        health = health - damage;
    }

    public void updateRecoverRate(int recoverRate) {
        health += recoverRate;
    }

    public void reduceHunger() {
        hunger--;
    }

    public void replenishHunger(int feedRate) {
        hunger = hunger + feedRate;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Citizen:" + id + "\tAge:" + age + "\tDOB:" + dob + "\tLS:" + lifespan + "\tHp:" + health + "\tHunger:" + hunger + "\tJob:" + getJob();
    }
}
