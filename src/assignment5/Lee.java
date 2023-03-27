/*
 * EE422C Project 5 submission by
 * Jesus Hernandez
 * jh69848
 * 17155
 * Jaime Sanchez
 * js96757
 * 17160
 * Slip days used: 0
 * Spring 2023
 */
package assignment5;

import javafx.scene.paint.Color;

import java.util.List;
/**
 * this Critter tends to run most of the time but will sometimes walk or stay still
 * will always try to reproduce
 * during fights, Lee will choose to fight if he has atleast 20 percent of start energy, otherwise he will walk away
 * Lee's do not like to change direction very often so they  will almost always continue in a straight path
 */
public class Lee extends Critter{

    @Override
    public String toString() {
        return "L";
    }
    private int dir;

    private static int allTime =0; //keeps track of total lee's ever produced;

    private static int offspring = 0; //keeps track of how many Lees were generated as offspring

    public Lee(){
        dir =Critter.getRandomInt(8);
        allTime++;
    }

    public static void reset(){
        allTime =0;
        offspring =0;
    }

    @Override
    public void doTimeStep() {
        int probability = Critter.getRandomInt(10);
        if (probability < 6){
            run(dir);
        } else if (probability < 8) {
            walk(dir);
        }
        //otherwise stay still

        if(getEnergy() > Params.MIN_REPRODUCE_ENERGY){
            offspring++;
            Lee child = new Lee();
            reproduce(child, getRandomInt(8));
        }

        //picking a new direction
        probability = Critter.getRandomInt(10);

        if (probability > 8){
            dir = Critter.getRandomInt(8);
        }//otherwise direction stays the same


    }

    @Override
    public boolean fight(String oponent) {
        if (getEnergy() > Params.START_ENERGY * 0.2){
            return true;
        }
        walk(dir);
        return false;
    }
    public static String runStats(List<Critter> Lee ){
        return "Alive: " + Lee.size() + "\n" + "Produced as Offspring: " + offspring ;
    }

    @Override
    public CritterShape viewShape() {
        return CritterShape.TRIANGLE;
    }

    @Override
    public javafx.scene.paint.Color viewOutlineColor() {
        return javafx.scene.paint.Color.BLUE;
    }

    @Override
    public javafx.scene.paint.Color viewFillColor() {
        return Color.PURPLE;
    }
}
