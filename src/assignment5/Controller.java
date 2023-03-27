package assignment5;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Controller {

    private static String myPackage = Main.class.getPackage().toString().split(" ")[1];
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField populateAmount;
    @FXML
    private ChoiceBox CritterDrropDown;
    @FXML
    private Button AddCritterBtn;
    @FXML
    private TextField seedTF;
    @FXML
    private Button SetSeedBTN;
    @FXML
    private TextField stepAmountTF;
    @FXML
    private Button StepBtn;
    @FXML
    private CheckBox AnimateToggle;
    @FXML
    private Slider AnimateSlider;
    @FXML
    private CheckBox statsToggle0;
    @FXML
    private CheckBox statsToggle1;
    @FXML
    private CheckBox statsToggle2;
    @FXML
    private CheckBox statsToggle3;
    @FXML
    private CheckBox statsToggle4;
    @FXML
    private Label stats0;
    @FXML
    private Label stats1;
    @FXML
    private Label stats2;
    @FXML
    private Label stats3;
    @FXML
    private Label stats4;
    @FXML
    private GridPane worldGrid;
    @FXML
    private Button initializebtn;

    private static boolean statsb0;
    private static boolean statsb1;
    private static boolean statsb2;
    private static boolean statsb3;
    private static boolean statsb4;


    /**
     * Method used to find all the critters for dropdown functionality
     * @return String []
     */
    public static String [] findCritters() {
        ArrayList<String> arrString = new ArrayList<>();
        String[] classPathsArr = System.getProperty("java.class.path").split(System.getProperty("path.separator"));

        for (String classPath : classPathsArr) {
            String packagePath = classPath + File.separator + myPackage; //get a path
            File packageDir = new File(packagePath);

            if (packageDir.isDirectory()) {//need to check if it's a directory, breaks otherwise
                File[] fileArr = packageDir.listFiles();

                for (File file : fileArr) { //look at all the classpathEntries
                    if (file.getName().endsWith(".class")) {
                        String className = file.getName().substring(0, file.getName().length() - 6); //keep just the colver name
                        arrString.add(className);
                    }
                }
                break;
            }
        }

        Iterator<String> iter = arrString.iterator();
        while(iter.hasNext()){
            if(iter.next().contains("Main")){
                iter.remove();
            }
        }

        //remove all the unnecessary classes, if more classes are added. Remove Here
        arrString.remove("Controller");
        arrString.remove("Critter$CritterShape");
        arrString.remove("Critter$TestCritter");
        arrString.remove("Critter");
        arrString.remove("InvalidCritterException");
        arrString.remove("Params");
        arrString.remove("Clover");

        //arrString.toArray();
        String [] critterNames = new String[arrString.size()];
        for(int i=0; i< arrString.size(); i++){
            critterNames[i] = arrString.get(i);
        }
//        String[] critterNames = new String[5];
//        critterNames[0] = "Goblin";
//        critterNames[1] = "Telang";
//        critterNames[2] = "Nandukies";
//        critterNames[3] = "Lee";
//        critterNames[4] = "Valvano";
        return critterNames;
    }

    /**
     * method to switch from Title screen to main screen
     * @param event
     */
    public void SwitchScene(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("CrittersDisplay.fxml"));
            stage =  (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();





        }catch (Exception e){
            stats0.setText("Hi");
        }
    }

    /**
     * Initializes values to Scene
     * @param event
     */
    public void init(ActionEvent event) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvalidCritterException {
        String[] Critters = findCritters();
        statsToggle0.setText(Critters[0]);
        statsToggle1.setText(Critters[1]);
        statsToggle2.setText(Critters[2]);
        statsToggle3.setText(Critters[3]);
        statsToggle4.setText(Critters[4]);
        statsb0 =false;
        statsb1 =false;
        statsb2 =false;
        statsb3 =false;
        statsb4 =false;

        CritterDrropDown.setItems(FXCollections.observableArrayList(Critters));

        //initializes gridpane for the World
        int numCols = Params.WORLD_WIDTH;
        int numRows = Params.WORLD_HEIGHT;
        for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / numCols);
            worldGrid.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / numRows);
            worldGrid.getRowConstraints().add(rowConst);
        }
        worldGrid.getColumnConstraints().remove(0);
        worldGrid.getRowConstraints().remove(0);

        Critter.displayWorld(worldGrid);
        initializebtn.setDisable(true);
//        Circle temp = new Circle();
//        temp.setFill(Color.BLUE);
//        temp.setRadius(5);
//        worldGrid.add(temp,0,0);


    }

    /**
     * WHen Button is pressed, Critter selected is populated to world by amount specified
     * @param event
     */
    public void critterPoulate(ActionEvent event){
        try {
            int amount = Integer.parseInt(populateAmount.getText());
            String crit = (String)CritterDrropDown.getValue();
            for(int i = 0; i < amount; i++){
                Critter.createCritter(crit);
                Critter.displayWorld(worldGrid);
            }
        }
        catch(NumberFormatException e ){
            populateAmount.setText("num only");
        } catch (InvalidCritterException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets the seed for the world
     * @param event
     */
    public void setSeed(ActionEvent event) {
        try{
            if(!seedTF.getText().isEmpty()){
                int amount = Integer.parseInt(seedTF.getText());
                Critter.setSeed(amount);
            }
        }
        catch (Exception e){
            seedTF.setText("Numbers Only");
        }

    }

    /**
     * does the amount of steps specified by user when Step button is pressed
     * @param event
     */
    public void step(ActionEvent event){
        try{
            int amount = Integer.parseInt(stepAmountTF.getText());
            for(int i = 0; i < amount; i++){
                Critter.worldTimeStep();
                Critter.displayWorld(worldGrid);
            }
        }catch (NumberFormatException e){
            stepAmountTF.setText("Numbers Only");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvalidCritterException e) {
            throw new RuntimeException(e);
        }
    }


    //stats0-4 are responsible for displaying the stats of the critters
    public void stats0(ActionEvent e){
        try{
            Timeline changeGolbinTextStart = new Timeline(
                    new KeyFrame(Duration.millis(1), event ->{
                        setCritStatText(statsToggle0.getText(), stats0);
                    })
            );
            statsStop(changeGolbinTextStart, stats0, statsToggle0);


        }catch (Exception exept){
            System.out.println(exept);
        }
    }
    public void stats1(ActionEvent e){
        try{
            Timeline changeLeeTextStart = new Timeline(
                    new KeyFrame(Duration.millis(1), event ->{
                        setCritStatText(statsToggle1.getText(), stats1);
                    })
            );
            statsStop(changeLeeTextStart, stats1, statsToggle1);


        }catch (Exception exept){
            System.out.println(exept);
        }
    }
    public void stats2(ActionEvent e){
        try{
            Timeline changeNandukiesTextStart = new Timeline(
                    new KeyFrame(Duration.millis(1), event ->{
                        setCritStatText(statsToggle2.getText(), stats2);
                    })
            );
            statsStop(changeNandukiesTextStart, stats2, statsToggle2);


        }catch (Exception exept){
            System.out.println(exept);
        }
    }
    public void stats3(ActionEvent e){
        try{
            Timeline changeTelangTextStart = new Timeline(
                    new KeyFrame(Duration.millis(1), event ->{
                        setCritStatText(statsToggle3.getText(), stats3);
                    })
            );
            statsStop(changeTelangTextStart, stats3, statsToggle3);


        }catch (Exception exept){
            System.out.println(exept);
        }
    }
    public void stats4(ActionEvent e){
        try{
            Timeline changeValvanoTextStart = new Timeline(
                    new KeyFrame(Duration.millis(1), event ->{
                        setCritStatText(statsToggle4.getText(), stats4);
                    })
            );
            statsStop(changeValvanoTextStart, stats4, statsToggle4);


        }catch (Exception exept){
            System.out.println(exept);
        }
    }

    /**
     * animates the world based on a toggle and a slider
     * @param event
     */
    public void animate(ActionEvent event){
        //TODO

    }

    /**
     * Method used to add the text-field functionality, when stopping
     * @param changeTextStart
     * @param nText
     * @param TBtn
     */
    private void statsStop(Timeline changeTextStart, Label nText, CheckBox TBtn) {
        Timeline changeTextStop = new Timeline(
                new KeyFrame(Duration.millis(1), event ->{
                    nText.setText(null);
                })
        );
        changeTextStart.setCycleCount(Animation.INDEFINITE);
        changeTextStop.setCycleCount(Animation.INDEFINITE);
        TBtn.setOnAction(event ->{
            if(TBtn.isSelected()){
                changeTextStop.stop();
                changeTextStart.play();
            }
            else{
                changeTextStart.stop();
                changeTextStop.play();
            }
        } );
    }

    /**
     * Method used to invoke the runstats methods in each critter
     * @param crit
     * @param goblinText
     */
    private void setCritStatText(String crit, Label goblinText) {
        try {
            String infoCritter = crit;
            List<Critter> tempList = Critter.getInstances(infoCritter);
            Class<?>[] c = {List.class};
            Method runStats = Class.forName(myPackage + "." + infoCritter).getMethod("runStats", c);
            String stats = (String) runStats.invoke(null, tempList);
            goblinText.setText(stats);
        }
        catch(  java.lang.reflect.InvocationTargetException | NoSuchMethodException| SecurityException |ClassNotFoundException| IllegalAccessException | InvalidCritterException e){ //removed InstantiationException
            return;
        }
    }



}
