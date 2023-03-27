package assignment5;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static javafx.scene.media.AudioClip.INDEFINITE;

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
    @FXML
    private Button resetBtn;

    @FXML
    private Button quitBtn;


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
        arrString.remove("CritterShapes");

        //arrString.toArray();
        String [] critterNames = new String[arrString.size()];
        for(int i=0; i< arrString.size(); i++){
            critterNames[i] = arrString.get(i);
        }

        return critterNames;
    }

    public void reset(ActionEvent event) throws InvalidCritterException {
        Critter.critterReset(worldGrid);
        Node node = worldGrid.getChildren().get(0);
        worldGrid.getChildren().clear();
        worldGrid.getChildren().add(0,node);
    }


    /**
     * method to exit the program
     * @param event
     */
    public void quit(ActionEvent event){
        System.exit(0);
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

//        String musicFile = "Heroic_Time.mp3";     // change name
//        Media sound = new Media(new File(musicFile).toURI().toString());
//        MediaPlayer mediaPlayer = new MediaPlayer(sound);
//        mediaPlayer.play();
        Thread thread = new Thread(music_task);
        thread.start();



    }

    /**
     * Method for music thread
     */
    final Task music_task = new Task() {

        @Override
        protected Object call() throws Exception {
            int s = INDEFINITE;
            AudioClip audio = new AudioClip(getClass().getResource("Minecraft Music [Full Playlist].mp3").toExternalForm()); //might need to change to a wav file
            audio.setVolume(0.3f);
            audio.setCycleCount(s);
            audio.play();
            return null;
        }
    };


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
        }catch (Exception e){
            System.out.println(e.toString());
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
                Critter.worldTimeStep(worldGrid);
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
        try{
            Timeline animate  = new Timeline(
                    new KeyFrame(Duration.seconds(1/AnimateSlider.getValue()), event1 -> {
                        try {
                            Critter.worldTimeStep(worldGrid);
                        } catch (InvalidCritterException e) {
                            throw new RuntimeException(e);
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        } catch (InstantiationException e) {
                            throw new RuntimeException(e);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    })
            );
            animateStop(animate,AnimateSlider,AnimateToggle);
        }catch (Exception except){
            System.out.println((except));
        }


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
     * Method used to add animation to world
     * @param AnimateStart
     * @param speed
     * @param TBtn
     */
    private void animateStop(Timeline AnimateStart, Slider speed, CheckBox TBtn) {
        AnimateStart.setCycleCount(Animation.INDEFINITE);
        TBtn.setOnAction(event ->{
            if(TBtn.isSelected()){
                AnimateStart.play();
                turnOff();


            }
            else{
                AnimateStart.stop();
                turnOn();
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

    private void turnOff(){
        populateAmount.setDisable(true);
        CritterDrropDown.setDisable(true);
        AddCritterBtn.setDisable(true);
        seedTF.setDisable(true);
        SetSeedBTN.setDisable(true);
        stepAmountTF.setDisable(true);;
        StepBtn.setDisable(true);
        statsToggle0.setDisable(true);
        statsToggle1.setDisable(true);
        statsToggle2.setDisable(true);
        statsToggle3.setDisable(true);
        statsToggle4.setDisable(true);


    }
    private void turnOn(){
        populateAmount.setDisable(false);
        CritterDrropDown.setDisable(false);
        AddCritterBtn.setDisable(false);
        seedTF.setDisable(false);
        SetSeedBTN.setDisable(false);
        stepAmountTF.setDisable(false);;
        StepBtn.setDisable(false);
        AnimateToggle.setDisable(false);
        AnimateSlider.setDisable(false);
        statsToggle0.setDisable(false);
        statsToggle1.setDisable(false);
        statsToggle2.setDisable(false);
        statsToggle3.setDisable(false);
        statsToggle4.setDisable(false);


    }



}
