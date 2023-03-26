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

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.util.Duration;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Main extends Application {
    private static String myPackage;

    static {
        myPackage = Main.class.getPackage().toString().split(" ")[1];
    }

    public static void main(String[] args) {
//        String [] critterNames = find();
//        String temp = myPackage;
        launch(args);
    }


    //Label fields
    private Label critterLabel;
    private Label amountLabel;
    private Label stepsLabel;
    private Label seedLabel;
    private Label animationLabel;
    private Label statsLabel;

    //Text fields
    private TextField amountText;
    private TextField stepsText;
    private TextField seedText;
    private TextField animationText;
    private TextField goblinText;
    private TextField leeText;
    private TextField nandukiesText;
    private TextField telangText;
    private TextField valvanoText;


    //Buttons
    private Button amountBtn;
    private Button setSeedBtn;
    private Button runBtn;
    private Button quitBtn;
    private ToggleButton goblinTBtn;
    private ToggleButton leeTBtn;
    private ToggleButton nandukiesTBtn;
    private ToggleButton telangTBtn;
    private ToggleButton valvanoTBtn;

    @Override
    public void start(Stage primaryStage) throws Exception {


        //zybooks
//        Scene scene = null;         // Scene contains all content
        GridPane gridPane = null;   // Positions components within scene

        gridPane = new GridPane();   // Create an empty pane
        //scene = new Scene(gridPane); // Create scene containing the grid pane

        //Labels
        critterLabel = new Label("Critter:");
        amountLabel = new Label("Amount:");
        stepsLabel = new Label("Steps:");
        seedLabel = new Label("Seed:");
        animationLabel = new Label("Animation:");
        statsLabel = new Label("Stats:");

        //User input texts
        amountText = new TextField();
        amountText.setPrefColumnCount(8); //size of the text box?
        amountText.setEditable(true); //allows user to input text
        amountText.setText("");

        stepsText = new TextField();
        stepsText.setPrefColumnCount(8);
        stepsText.setEditable(true);
        stepsText.setText("");

        seedText = new TextField();
        seedText.setPrefColumnCount(8);
        seedText.setEditable(true);
        seedText.setText("");

        animationText = new TextField();
        animationText.setPrefColumnCount(8);
        animationText.setEditable(true);
        animationText.setText("0"); //change this

        goblinText = new TextField();
        goblinText.setPrefColumnCount(35);
        goblinText.setEditable(false);
        goblinText.setText("");

        leeText = new TextField();
        leeText.setPrefColumnCount(35);
        leeText.setEditable(false);
        leeText.setText("");

        nandukiesText = new TextField();
        nandukiesText.setPrefColumnCount(35);
        nandukiesText.setEditable(false);
        nandukiesText.setText("");

        telangText = new TextField();
        telangText.setPrefColumnCount(35);
        telangText.setEditable(false);
        telangText.setText("");

        valvanoText = new TextField();
        valvanoText.setPrefColumnCount(35);
        valvanoText.setEditable(false);
        valvanoText.setText("");



        gridPane.add(goblinText, 1, 5);
        gridPane.add(leeText, 1, 6);
        gridPane.add(nandukiesText, 1, 7);
        gridPane.add(telangText, 1, 8);
        gridPane.add(valvanoText, 1, 9);


        //Gridpane settings
        gridPane.setPadding(new Insets(10, 10, 10, 10)); // Padding around  grid
        gridPane.setHgap(5);                            // Spacing between columns
        gridPane.setVgap(10);                            // Spacing between rows

        gridPane.add(critterLabel, 0, 0);

        gridPane.add(amountLabel, 2, 0);
        gridPane.add(amountText, 3, 0);

        gridPane.add(stepsLabel, 0, 1);
        gridPane.add(stepsText, 1, 1);

        gridPane.add(seedLabel, 2, 1);
        gridPane.add(seedText, 3, 1);

        gridPane.add(animationLabel, 0, 2);
        gridPane.add(animationText, 1, 2);

        gridPane.add(statsLabel, 0,3);

        //critters drop down menu
        //String [] critterNames = findCritters();
        String [] critterNames = new String[5];
        //critterNames = findCritters();
        final String[] selectedCrit = {new String()};

        ComboBox comboBox = new ComboBox(FXCollections.observableArrayList(critterNames));
        Label selectedDropDown = new Label("default item selected");

        //action for dropdown
        EventHandler<ActionEvent> eventDropDown = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                selectedDropDown.setText(comboBox.getValue() + " selected");
                selectedCrit[0] = (String) comboBox.getValue();
            }
        };
        comboBox.setOnAction(eventDropDown);
        gridPane.add(comboBox, 1,0);


        //Toggle Button functionality
        goblinTBtn = new ToggleButton("Goblin");
        gridPane.add(goblinTBtn, 0,5);
        Timeline changeGolbinTextStart = new Timeline(
                new KeyFrame(Duration.millis(1), event ->{
                    setCritStatText("Goblin", goblinText);
                })
        );
        statsStop(changeGolbinTextStart, goblinText, goblinTBtn);


        leeTBtn = new ToggleButton("Lee");
        gridPane.add(leeTBtn, 0,6);
        Timeline changeLeeTextStart = new Timeline(
                new KeyFrame(Duration.millis(1), event ->{
                    setCritStatText("Lee", leeText);
                })
        );
        statsStop(changeLeeTextStart, leeText, leeTBtn);


        nandukiesTBtn = new ToggleButton("Nandukies");
        gridPane.add(nandukiesTBtn, 0,7);
        Timeline changeNandukiesTextStart = new Timeline(
                new KeyFrame(Duration.millis(1), event ->{
                    setCritStatText("Nandukies", nandukiesText);
                })
        );
        statsStop(changeNandukiesTextStart, nandukiesText, nandukiesTBtn);

        telangTBtn = new ToggleButton("Telang");
        gridPane.add(telangTBtn, 0,8);
        Timeline changeTelangTextStart = new Timeline(
                new KeyFrame(Duration.millis(1), event ->{
                    setCritStatText("Telang", telangText);
                })
        );
        statsStop(changeTelangTextStart, telangText, telangTBtn);

        valvanoTBtn = new ToggleButton("Valvano");
        gridPane.add(valvanoTBtn, 0,9);
        Timeline changeValvanoTextStart = new Timeline(
                new KeyFrame(Duration.millis(1), event ->{
                    setCritStatText("Valvano", valvanoText);
                })
        );
        statsStop(changeValvanoTextStart, valvanoText, valvanoTBtn);


        //Buttons
        amountBtn = new Button("ADD Critter/s");
        gridPane.add(amountBtn, 4,0);
        amountBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String userInput =selectedCrit[0];
                String tempAmount = amountText.getText();
                if(tempAmount.isEmpty()){
                    try {
                        Critter.createCritter(userInput);
                    } catch (InvalidCritterException e) {
                    }
                }else{
                    int amount = Integer.parseInt(amountText.getText());
                    for(int i =0; i<amount ;i++){
                        try {
                            Critter.createCritter(userInput);
                        } catch (InvalidCritterException e) {
                        }
                    }
                }
            }
        });

        setSeedBtn = new Button("Set Seed");
        gridPane.add(setSeedBtn, 4,1);
        setSeedBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!seedText.getText().isEmpty()){
                    int amount = Integer.parseInt(seedText.getText());
                    Critter.setSeed(amount);
                }
            }
        });

        runBtn = new Button("Run");
        gridPane.add(runBtn, 3,2);
        runBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String tempAmount = stepsText.getText();
                if(tempAmount.isEmpty()){//single step
                    Critter.worldTimeStep();
                    System.out.println("ran");
                }else{
                    int amount = Integer.parseInt((stepsText.getText()));
                    for(int i=0; i<amount; i++){
                        Critter.worldTimeStep();
                        System.out.println("ran");
                    }
                }
            }
        });

        quitBtn = new Button("QUIT");
        gridPane.add(quitBtn, 4,2);
        quitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });


        // create a label
        Label label_center = new Label("BorderPane center"); //TODO add the game view here
        Label label_top = new Label("BorderPane top");
        Label label_bottom = new Label("BorderPane bottom");
//        Label label_left = new Label("this is BorderPane left");
        Label label_right = new Label("BorderPane right");

        //Create a BorderPane
        BorderPane border_pane = new BorderPane(label_center,
                label_top, label_right, label_bottom, gridPane); //placed in the center by default?

        //Set alignment
        border_pane.setAlignment(gridPane, Pos.TOP_LEFT);
        border_pane.setAlignment(label_top, Pos.CENTER);
        border_pane.setAlignment(label_bottom, Pos.BOTTOM_CENTER); //TODO
        border_pane.setAlignment(label_right, Pos.TOP_RIGHT);

        // create a scene
        Scene scene = new Scene(border_pane, 1000, 800);

        primaryStage.setScene(scene);    // Set window's scene
        primaryStage.setTitle("Critters"); // Set window's title
        primaryStage.show();             // Display window

    }

    /**
     * Method used to add the text-field functionality, when stopping
     * @param changeTextStart
     * @param nText
     * @param TBtn
     */
    private void statsStop(Timeline changeTextStart, TextField nText, ToggleButton TBtn) {
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
    private void setCritStatText(String crit, TextField goblinText) {
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

    /**
     * Method used to find all the critters for dropdown functionality
     * @return String []
     */
    public static String [] findCritters() {
        //String scannedPath = scannedPackage.replace('.', '/');
        URL scannedUrl = Thread.currentThread().getContextClassLoader().getResource(myPackage);
        File scannedDir = new File(scannedUrl.getFile());

        ArrayList<String> arrString = new ArrayList<String>();
        for (File file : scannedDir.listFiles()) {
            String className = file.getName();
            int endIndex = className.length() - ".class".length();
            className = className.substring(0, endIndex);
            arrString.add(className);
        }

        Iterator<String> iter = arrString.iterator();
        while(iter.hasNext()){
            if(iter.next().contains("Main")){
                iter.remove();
            }
        }

        //remove all the unnecessary classes, if more classes are added. Remove Here
        arrString.remove("Critter$CritterShape");
        arrString.remove("Critter$TestCritter");
        arrString.remove("InvalidCritterException");
        arrString.remove("Params");
        arrString.remove("Critter");

        //arrString.toArray();
        String [] critterNames = new String[arrString.size()];
        for(int i=0; i< arrString.size(); i++){
            critterNames[i] = arrString.get(i);
        }
        return critterNames;
    }


}

