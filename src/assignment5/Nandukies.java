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
import java.util.Objects;

/**
 * Nandukies is a critter that is super active in that it will never fight unless it encounters a Lee Critter,
 * rather any encounter with another critter will result of Nandukies always wanting to reproduce, if it has enough energy
 */
public class Nandukies extends Critter{

    @Override
    public String toString() {
        return "N";
    }
    private int dir;
    private static int total;
    private static int offspring;
    public Nandukies(){
        total++;
        dir =Critter.getRandomInt(8);
    }

    public static void reset(){
        total =0;
        offspring =0;
    }


    @Override
    public void doTimeStep() {
        int probability = Critter.getRandomInt(10);
        if (probability < 5){
            run(dir);
        } else{
            walk(dir);
        }
        //otherwise stay still

        //picking a new direction
        probability = Critter.getRandomInt(10);

        if (probability > 8){
            dir = Critter.getRandomInt(8);
        }//otherwise direction stays the same

    }

    @Override
    public boolean fight(String oponent) {
        if (Objects.equals(look(dir, false), "N")){
            if(getEnergy() > Params.MIN_REPRODUCE_ENERGY){
                offspring ++;
                Nandukies child = new Nandukies();
                reproduce(child, getRandomInt(8));
            }
            return false;
        }

        if(Objects.equals(oponent, "L")){
            return true;
        }
        if(getEnergy() > Params.MIN_REPRODUCE_ENERGY){
            offspring ++;
            Nandukies child = new Nandukies();
            reproduce(child, getRandomInt(8));
        }
        return false;
    }

    public static String runStats(List<Critter> Nandukies) {
        int totalCurrentSize = Nandukies.size();
        return "Alive: " +totalCurrentSize + "\n" + "Produced as Offspring: " +offspring;

    }
    @Override
    public CritterShape viewShape() {
        return CritterShape.STAR;
    }

    @Override
    public javafx.scene.paint.Color viewOutlineColor() {
        return javafx.scene.paint.Color.RED;
    }

    @Override
    public javafx.scene.paint.Color viewFillColor() {
        return javafx.scene.paint.Color.RED;
    }
}