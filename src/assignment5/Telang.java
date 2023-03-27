/*
 * EE422C Project 5 submission by
 * Replace <...> with your actual data.
 * Jesus Hernandez
 * jh69848
 * 17155
 * Jaime Sanchez
 * js96757
 * 17160
 * Slip days used: <0>
 * Spring 2023
 */
package assignment5;

import javafx.scene.paint.Color;

import java.util.List;
/**
 * The Telang Critter will have an equal probability of either wlaking, running, or reproducing
 * Telang will always fight a valvano, Goblin, or Clover, but will run away from anything else
 * Telang is very indecisive with which direction to go, so there is an equal Probability of it going in any direction every timestep
 */
public class Telang extends Critter{
    @Override
    public String toString() {
        return "T";
    }
    private int dir; // current Direction
    private static int allTime = 0; // keeps track of how many Telangs have been produced all time
    private static int offspring = 0; //keeps track of how many Telangs have been generated as offspring


    public Telang(){
        dir =Critter.getRandomInt(8);
        allTime ++;
    }

    public void reset(){
        allTime =0;
        offspring =0;
    }

    @Override
    public void doTimeStep() {
        int probability = Critter.getRandomInt(9);
        if (probability < 3 ){
            walk(dir);
        } else if (probability < 6) {
            run(dir);
        }else {
            if (getEnergy() > Params.MIN_REPRODUCE_ENERGY){
                offspring++;
                Telang child = new Telang();
                reproduce(child, Critter.getRandomInt(8));
            }
        }
        dir = Critter.getRandomInt(8);

    }

    @Override
    public boolean fight(String oponent) {
        if (oponent.equals("V") | oponent.equals("@") | oponent.equals("G")){
            return true;
        }
        return false;
    }

    public static String runStats(List<Critter> Telang ){
        return "Alive: " + Telang.size() + "\n" + "Produced as Offspring: " + offspring ;
    }

    @Override
    public CritterShape viewShape() {
    return CritterShape.TRIANGLE;
}

    @Override
    public javafx.scene.paint.Color viewOutlineColor() {
        return Color.RED;
    }
}

