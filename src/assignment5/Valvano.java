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

import java.util.List;
/** Valvano is a runner critter, the critter will always run no matter an encounter or not.
 * The critter is scared to face challenges so the easiest thing to do is to run away from everything, even clovers.
 * The critter is infertile
 * The is also a gene functionality for the critter where it can offspring a critter to go more likely in a direction
 */
public class Valvano extends Critter{

    private int dir;
    private static int total;
    private static int offspring;




    public Valvano(){
        dir =Critter.getRandomInt(8);
        total++;
    }

    @Override
    public String toString() {
        return "V";
    }

    @Override
    public void doTimeStep() {
        dir = Critter.getRandomInt(8);
        run(dir);//will always run
        //picking a new direction





    }

    @Override
    public boolean fight(String oponent) {
        run(dir);
        return false;
    }


    public static String runStats(List<Critter> Valvano) {
        // TODO
        int totalCurrentSize = Valvano.size();

        System.out.print("Alive: " +totalCurrentSize + "   ");
        System.out.print("Total produced: " +total + "   ");
        System.out.print("Produced as Offspring: " +offspring);
        System.out.println();

        return null;
    }

    @Override
    public CritterShape viewShape() {
    return CritterShape.SQUARE;
}

    @Override
    public javafx.scene.paint.Color viewOutlineColor() {
        return javafx.scene.paint.Color.BLUE;
    }
}
