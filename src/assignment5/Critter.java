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

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * See the PDF for descriptions of the methods and fields in this
 * class.
 * You may add fields, methods or inner classes to Critter ONLY
 * if you make your additions private; no new public, protected or
 * default-package code or data can be added to Critter.
 */

public abstract class Critter {

    /* START --- NEW FOR PROJECT 5 */
    public enum CritterShape {
        CIRCLE,
        SQUARE,
        TRIANGLE,
        DIAMOND,
        STAR
    }


    /* the default color is white, which I hope makes critters invisible by default
     * If you change the background color of your View component, then update the default
     * color to be the same as you background
     *
     * critters must override at least one of the following three methods, it is not
     * proper for critters to remain invisible in the view
     *
     * If a critter only overrides the outline color, then it will look like a non-filled
     * shape, at least, that's the intent. You can edit these default methods however you
     * need to, but please preserve that intent as you implement them.
     */
    public javafx.scene.paint.Color viewColor() {
        return javafx.scene.paint.Color.WHITE;
    }

    public javafx.scene.paint.Color viewOutlineColor() {
        return viewColor();
    }

    public javafx.scene.paint.Color viewFillColor() {
        return viewColor();
    }

    public abstract CritterShape viewShape();

    /**
     * clears the population array
     * @param world
     * @throws InvalidCritterException
     */
    public static void critterReset(GridPane world) throws InvalidCritterException {

        clearWorld(); //removes the critters from the population array
    }



    /**
     * Method look determines if a critter is occupied at a specific location
     * if so return that critter's string
     * o.w. return null if empty
     * @param direction
     * @param steps
     * @return String or null
     */
    protected final String look(int direction, boolean steps) {
        //false = 1 step, true = 2 steps
        this.energy -= Params.LOOK_ENERGY_COST;
        int numSteps;
        if(steps){
            numSteps =2;
        }else{
            numSteps =1;
        }
        int currentX = x_coord;
        int currentY = y_coord;
        if (direction == 0) {
            currentX += numSteps;
        } else if (direction == 1) {
            currentX += numSteps;
            currentY -= numSteps;
        } else if (direction == 2) {
            currentY -= numSteps;
        } else if (direction == 3) {
            currentX -= numSteps;
            currentY -= numSteps;
        } else if (direction == 4) {
            currentX -= numSteps;
        } else if (direction == 5) {
            currentX -= numSteps;
            currentY += numSteps;
        } else if (direction == 6) {
            currentY += numSteps;
        } else if (direction == 7) {
            currentX += numSteps;
            currentY += numSteps;
        }

        //makes sure coordinates are within world
        if (currentX > Params.WORLD_WIDTH) {
            currentX = currentX - Params.WORLD_WIDTH;
        }
        if (currentX < 0) {
            currentX = Params.WORLD_WIDTH + currentX;
        }
        if (currentY < 0) {
            currentY = Params.WORLD_HEIGHT + currentY;
        }
        if (currentY > Params.WORLD_HEIGHT) {
            currentY = currentY - Params.WORLD_HEIGHT;
        }
        //look at all the critters locations
        for(int i =0; i< population.size();i++){
            Critter critObj = population.get(i);
            int tempCritX = critObj.x_coord;
            int tempCritY = critObj.y_coord;
            if((currentX == tempCritX) &&(currentY == tempCritY)){
                return critObj.toString();
            }
        }
        return null;
    }

    public static String runStats(List<Critter> critters) {
        // TODO Implement this method
        return null;
    }


    public static void displayWorld(Object pane) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvalidCritterException {
        GridPane world = (GridPane)pane;
        Double totalLength = world.getWidth();
        Double totalHeight = world.getHeight();
        Double length = (totalLength/Params.WORLD_WIDTH)/2;
        Double width = (totalLength/Params.WORLD_HEIGHT)/2;

        for(int x = 0; x < world.getColumnConstraints().size(); x++){
            for (int y = 0; y < world.getRowConstraints().size(); y++){
                for(int i = 0; i < population.size(); i++){
                    if (population.get(i).x_coord == x & population.get(i).y_coord == y){
                        CritterShape shape = population.get(i).viewShape();
                        Shape temp = CritterShapes.getShape(shape,length,width,population.get(i).viewFillColor(),
                                population.get(i).viewOutlineColor());
                        System.out.println(temp.getClass().toString());
                        world.add(temp,x,y);

                    }
                }


            }
        }

    }

	/* END --- NEW FOR PROJECT 5
			rest is unchanged from Project 4 */
    private boolean moved = false;
    private int energy = 0;

    private int x_coord;
    private int y_coord;


    private static List<Critter> population = new ArrayList<Critter>();
    private static List<Critter> babies = new ArrayList<Critter>();

    /* Gets the package name.  This assumes that Critter and its
     * subclasses are all in the same package. */
    private static String myPackage;

    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    private static Random rand = new Random();

    public static int getRandomInt(int max) {
        return rand.nextInt(max);
    }

    public static void setSeed(long new_seed) {
        rand = new Random(new_seed);
    }

    /**
     * create and initialize a Critter subclass.
     * critter_class_name must be the qualified name of a concrete
     * subclass of Critter, if not, an InvalidCritterException must be
     * thrown.
     *
     * @param critter_class_name
     * @throws InvalidCritterException
     */
    public static void createCritter(String critter_class_name)
            throws InvalidCritterException {
        //call the set of new critters? from new class. do a try and catch here for the exception

        try{
            Critter addingCrit = (Critter) Class.forName(myPackage +"."+critter_class_name).newInstance(); //newInstace might cause a problem?
            initializeCritter(addingCrit);
        }
        catch (IllegalAccessException | InstantiationException | ClassNotFoundException e){
            //return the only one exception that is being asked
            throw new InvalidCritterException(critter_class_name); //throw exception just for the critter_class_name
        }
    }
    /**
     * Initialize a single critter with its energy and x,y coordinates and add into an arraylist
     * @param addingCrit
     * @return void
     */
    private static void initializeCritter(Critter addingCrit) {
        addingCrit.energy = Params.START_ENERGY;
        addingCrit.x_coord = getRandomInt(Params.WORLD_WIDTH);
        addingCrit.y_coord = getRandomInt(Params.WORLD_HEIGHT);
        population.add(addingCrit);
    }


    /**
     * Gets a list of critters of a specific type.
     *
     * @param critter_class_name What kind of Critter is to be listed.
     *                           Unqualified class name.
     * @return List of Critters.
     * @throws InvalidCritterException
     */
    public static List<Critter> getInstances(String critter_class_name)
            throws InvalidCritterException {
        try{
            Critter oneCritterClass = (Critter) Class.forName(myPackage +"."+critter_class_name).newInstance();
            List<Critter> tepmList = new ArrayList<Critter>();

            for(int i=0; i< population.size(); i++){
                if(population.get(i).toString().equals(oneCritterClass.toString())){
                    tepmList.add(population.get(i));
                }
            }
            return tepmList;
        }
        catch (IllegalAccessException | InstantiationException | ClassNotFoundException e){
            //return the only one exception that is being asked
            throw new InvalidCritterException(critter_class_name); //throw exception just for the critter_class_name
        }
    }

    /**
     * Clear the world of all critters, dead and alive
     */
    public static void clearWorld() {
        population.clear();
    }

    /**
     * Simulates the world by:
     * Stepping through all the alive critters
     * Handling encounters
     * Removing dead critters after fighting
     * Generating required clovers
     * Finally, inserting babies into the population arraylist
     */
    public static void worldTimeStep(GridPane world) throws InvalidCritterException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        doTimeSteps();
        // TODO call each doTimeStep


        for (int i =0; i < population.size(); i++){ //updateRest Energy ,removes dead, and resets moved status
            population.get(i).doTimeStep();
            population.get(i).energy -= Params.REST_ENERGY_COST;
            if (population.get(i).energy <= 0){
                population.remove(population.get(i));
                //i--; using .remove messes with the index
            }
            else {
                population.get(i).moved = false;
            }
        }
        doEncounters();
        genClover();

        for(int i=0; i< babies.size(); i++){ //adds babies to the population
            population.add(babies.get(i)); //reproduce will be done inside each subclass in their doTimeStep
        }
        babies.clear();
        displayWorld(world);

    }
    /**
     * Function runs doTimeStep for each critter alive in the population arraylist
     */
    private static void doTimeSteps(){
        int sizeP = population.size();
        for(int i=0; i<sizeP; i++){
            population.get(i).doTimeStep(); //determine how they move, but must move everytime
        }
    }

    /**
     * Generates a specific number of clovers in world specified in the Params class
     */
    private static void genClover(){
        try {
            for (int i = 0; i < Params.REFRESH_CLOVER_COUNT; i++) {
                createCritter("Clover");
            }
        }catch(InvalidCritterException ignored){
            //there should always be a class called Clover
        }
    }


    /**
     * Function handles the encounters of critters
     * If an encounter happens, doFight for the two critters is called, even if a clover is encountered
     * If a fight happens, doEncounter removes any dead critters
     */
    private static void doEncounters() {//determine if two critters are overlapping
        for(int i =0; i< population.size(); i++){
            for(int j=i+1; j< population.size(); j++){
                Critter crit1Obj = population.get(i);
                Critter crit2Obj = population.get(j);
                int crit1Xa = crit1Obj.x_coord;
                int crit1Ya = crit1Obj.y_coord;
                int crit2Xa = crit2Obj.x_coord;
                int crit2Ya = crit2Obj.y_coord;
                boolean crit1Fight = false;
                boolean crit2Fight = false;

                int whoWon= 0;
                if((crit1Xa == crit2Xa) && (crit1Ya==crit2Ya)){ //overlap
                    crit1Fight =crit1Obj.fight(population.get(j).toString()); //returns true if crit1 wants to fight depending on its internal conditions and/or depending on who it is fighting
                    crit2Fight =crit2Obj.fight(population.get(i).toString()); //returns true if crit2 wants to fight depending on its internal conditions and/or depending on who it is fighting

                    int crit1Xb = crit1Obj.x_coord; //x and y coordinates might be updated after invoking fight
                    int crit1Yb = crit1Obj.y_coord;
                    int crit2Xb = crit2Obj.x_coord;
                    int crit2Yb = crit2Obj.x_coord;
                    if((crit1Xb == crit2Xb) && (crit1Yb==crit2Yb) && (crit2Obj.energy>0) && (crit1Obj.energy>0)){   //(crit1Fight & crit2Fight){//if they both want to fight
                        whoWon = doFight(crit1Obj,crit2Obj, crit1Fight, crit2Fight);
                        if(whoWon ==1){//remove crit2Obj
                            crit1Obj.energy = crit1Obj.energy + (crit2Obj.energy/2);
                            population.remove(population.get(j));
                            j--;
                            //continue checking to see if there are more critters in the same space as crit1
                        }else{//remove crit1Obj
                            crit2Obj.energy = crit2Obj.energy + (crit1Obj.energy/2);
                            population.remove(population.get(i));
                            i--; //?
                            break;
                        }
                    }
                    else{
                        //if both ran away?...
                        if(crit2Obj.energy <= 0){
                            population.remove(population.get(j));
                            j--;
                        }
                        else if(crit1Obj.energy <= 0){
                            population.remove(population.get(i));
                            i--;
                            break;
                        }
                    }
                }
            }

        }

    }


    /**
     * Helper function to determine with of the two critters won based on each energy
     * @param critter1
     * @param critter2
     * @param critterFight1
     * @param critterFight2
     * @return int
     */
    private static int doFight(Critter critter1, Critter critter2, boolean critterFight1, boolean critterFight2) {
        int random1 =0;
        int random2 =0;
        if(critterFight1){ //critter 1 decided to fight, o.w. random1 =0
            random1 = getRandomInt(critter1.energy);
        }
        if(critterFight2){ //critter 2 decided to fight, o.w. random2 =0
            random2 = getRandomInt(critter2.energy);
        }


        if(random1 ==random2){
            return 1; //arbitrary return any
        }
        if(random1 >random2){
            return 1; //1 won
        }
        else
        {
            return 2;
        }
    }

    public abstract void doTimeStep(); //must be done inside each critter

    public abstract boolean fight(String oponent);

    /* a one-character long string that visually depicts your critter
     * in the ASCII interface */
    public String toString() {
        return "";
    }

    protected int getEnergy() {
        return energy;
    }

    /**
     * Function that implements the walk functionality for the critters in the world, if run was not already called for
     * a specific critter during a single time step
     * @param direction
     */
    protected final void walk(int direction) {
        this.energy -= Params.WALK_ENERGY_COST; // subtracts energy for walking
        if (!moved) {
            //updates x, y positions
            if (direction == 0) {
                x_coord += 1;
            } else if (direction == 1) {
                x_coord += 1;
                y_coord -= 1;
            } else if (direction == 2) {
                y_coord -= 1;
            } else if (direction == 3) {
                x_coord -= 1;
                y_coord -= 1;
            } else if (direction == 4) {
                x_coord -= 1;
            } else if (direction == 5) {
                x_coord -= 1;
                y_coord += 1;
            } else if (direction == 6) {
                y_coord += 1;
            } else if (direction == 7) {
                x_coord += 1;
                y_coord += 1;
            }
            //makes sure coordinates are within world
            if (x_coord > Params.WORLD_WIDTH) {
                x_coord = x_coord - Params.WORLD_WIDTH;
            }
            if (x_coord < 0) {
                x_coord = Params.WORLD_WIDTH + x_coord;
            }
            if (y_coord < 0) {
                y_coord = Params.WORLD_HEIGHT + y_coord;
            }
            if (y_coord > Params.WORLD_HEIGHT) {
                y_coord = y_coord - Params.WORLD_HEIGHT;
            }
            moved = true;

        }
    }

    /**
     * Function that implements the run functionality for the critters in the world, if run was not already called for
     * a specific critter during a single time step
     * @param direction
     */
    protected final void run(int direction) {
        this.energy -= Params.RUN_ENERGY_COST; // subtracts energy forom running

        if (!moved){
            //updates x, y positions
            if ( direction == 0){
                x_coord += 2;
            } else if (direction ==1) {
                x_coord += 2;
                y_coord -= 2;
            } else if (direction == 2) {
                y_coord -= 2;
            } else if (direction == 3) {
                x_coord -= 2;
                y_coord -= 2;
            } else if (direction == 4) {
                x_coord -= 2;
            } else if (direction == 5) {
                x_coord -= 2;
                y_coord += 2;
            } else if (direction == 6) {
                y_coord += 2;
            } else if (direction == 7) {
                x_coord += 2;
                y_coord += 2;
            }
            //makes sure coordinates are within world
            if (x_coord > Params.WORLD_WIDTH){
                x_coord = x_coord - Params.WORLD_WIDTH;
            }if (x_coord < 0) {
                x_coord = Params.WORLD_WIDTH + x_coord;
            }if (y_coord < 0){
                y_coord = Params.WORLD_HEIGHT + y_coord;
            }if (y_coord > Params.WORLD_HEIGHT){
                y_coord =  y_coord - Params.WORLD_HEIGHT;
            }
            moved = true;

        }

    }

    /**
     * The reproduced function is used for adding a offspring into the world and inserting into the babies ArrayList
     * @param offspring
     * @param direction
     */
    protected final void reproduce(Critter offspring, int direction) {
        if (this.energy < Params.MIN_REPRODUCE_ENERGY){
            return;
        }
        offspring.energy = this.energy/2;
        this.energy -= offspring.energy;
        babies.add(offspring);

        //updates x, y positions
        if ( direction == 0){
            offspring.x_coord = x_coord + 1;
        } else if (direction ==1) {
            offspring.x_coord = x_coord + 1;
            offspring.y_coord = y_coord - 1;
        } else if (direction == 2) {
            offspring.y_coord = y_coord - 1;
        } else if (direction == 3) {
            offspring.x_coord = x_coord - 1;
            offspring.y_coord = y_coord - 1;
        } else if (direction == 4) {
            offspring.x_coord = x_coord - 1;
        } else if (direction == 5) {
            offspring.x_coord = x_coord - 1;
            offspring.y_coord = y_coord + 1;
        } else if (direction == 6) {
            offspring.y_coord = y_coord + 1;
        } else if (direction == 7) {
            offspring.x_coord = x_coord +1;
            offspring.y_coord = y_coord + 1;
        }
        //makes sure coordinates are within world
        if (offspring.x_coord > Params.WORLD_WIDTH){
            offspring.x_coord = offspring.x_coord - Params.WORLD_WIDTH;
        }if (x_coord < 0) {
            offspring.x_coord = Params.WORLD_WIDTH + offspring.x_coord;
        }if (y_coord < 0){
            offspring.y_coord = Params.WORLD_HEIGHT + offspring.y_coord;
        }if (y_coord > Params.WORLD_HEIGHT){
            offspring.y_coord =  offspring.y_coord - Params.WORLD_HEIGHT;
        }


    }

    /**
     * The TestCritter class allows some critters to "cheat". If you
     * want to create tests of your Critter model, you can create
     * subclasses of this class and then use the setter functions
     * contained here.
     * <p>
     * NOTE: you must make sure that the setter functions work with
     * your implementation of Critter. That means, if you're recording
     * the positions of your critters using some sort of external grid
     * or some other data structure in addition to the x_coord and
     * y_coord functions, then you MUST update these setter functions
     * so that they correctly update your grid/data structure.
     */
    static abstract class TestCritter extends Critter {

        protected void setEnergy(int new_energy_value) {
            super.energy = new_energy_value;
        }

        protected void setX_coord(int new_x_coord) {
            super.x_coord = new_x_coord;
        }

        protected void setY_coord(int new_y_coord) {
            super.y_coord = new_y_coord;
        }

        protected int getX_coord() {
            return super.x_coord;
        }

        protected int getY_coord() {
            return super.y_coord;
        }

        /**
         * This method getPopulation has to be modified by you if you
         * are not using the population ArrayList that has been
         * provided in the starter code.  In any case, it has to be
         * implemented for grading tests to work.
         */
        protected static List<Critter> getPopulation() {
            return population;
        }

        /**
         * This method getBabies has to be modified by you if you are
         * not using the babies ArrayList that has been provided in
         * the starter code.  In any case, it has to be implemented
         * for grading tests to work.  Babies should be added to the
         * general population at either the beginning OR the end of
         * every timestep.
         */
        protected static List<Critter> getBabies() {
            return babies;
        }
    }
}
