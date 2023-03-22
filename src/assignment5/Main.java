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

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import javafx.scene.control.ToggleButton;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;


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
    private Label cloverStatsLabel;

    //Text fields
    private TextField amountText;
    private TextField stepsText;
    private TextField seedText;
    private TextField animationText;


    //Buttons
    private Button amountBtn;
    private Button setSeedBtn;
    private Button runBtn;
    private Button quitBtn;
    private Button cloverBtn;
    private Button goblinBtn;
    private Button leeBtn;
    private Button nandukiesBtn;
    private Button telangBtn;
    private Button valvanoBtn;

    public static Boolean cloverPressed;


    @Override
    public void start(Stage primaryStage) throws Exception {
//        int numCritters =0;
//        ArrayList<ArrayList<String>> runningCrits = new ArrayList<ArrayList<String>>();
//        for(int i=0 ;i<8; i++){
//            ArrayList<String> buttonSet = new ArrayList<String>();
//            buttonSet.add("");
//            runningCrits.add(buttonSet);
//
//        }


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
        String [] critterNames = findCritters();
        final String[] selectedCrit = {new String()};

        ComboBox comboBox = new ComboBox(FXCollections.observableArrayList(critterNames));
        Label selectedDropDown = new Label("default item selected");
        //action for dropdown
//        int rowNumForStats =4; //where the buttons can be pressed
//        int numCritTaken =0;
        EventHandler<ActionEvent> eventDropDown = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                selectedDropDown.setText(comboBox.getValue() + " selected");
                selectedCrit[0] = (String) comboBox.getValue();
//                ArrayList<String> tempStr = new ArrayList<String>();
//                tempStr.add(selectedCrit[0]);
//                for(int i=0; i<8;i++){
//                    if(runningCrits.get(i).contains(selectedCrit[0])){
//                        break;
//                    }
//                    runningCrits.set(i,tempStr); //add any newly added crits for stats
//                    break;
//
//                }
            }
        };
        comboBox.setOnAction(eventDropDown);
        gridPane.add(comboBox, 1,0);

        //for stats buttons

        cloverBtn = new Button(critterNames[0]);
        //cloverTBtn = new ToggleButton(critterNames[0]);

        gridPane.add(cloverBtn, 0,4);
        goblinBtn = new Button(critterNames[1]);
        gridPane.add(goblinBtn, 0,5);
        leeBtn = new Button(critterNames[2]);
        gridPane.add(leeBtn, 0,6);
        nandukiesBtn = new Button(critterNames[3]);
        gridPane.add(nandukiesBtn, 0,7);
        telangBtn = new Button(critterNames[4]);
        gridPane.add(telangBtn, 0,8);
        valvanoBtn = new Button(critterNames[5]);
        gridPane.add(valvanoBtn, 0,9);


        cloverStatsLabel = new Label("empty");
//        if(cloverPressed){
//            cloverStatsLabel.setText("changed");
//        }
        gridPane.add(cloverStatsLabel, 1,4);


        cloverBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cloverPressed = !cloverPressed; //toggle
            }
        });



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
                }else{
                    int amount = Integer.parseInt((stepsText.getText()));
                    for(int i=0; i<amount; i++){
                        Critter.worldTimeStep();
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
        border_pane.setAlignment(label_bottom, Pos.CENTER);
        border_pane.setAlignment(label_right, Pos.TOP_RIGHT);

        // create a scene
        Scene scene = new Scene(border_pane, 800, 800);

        primaryStage.setScene(scene);    // Set window's scene
        primaryStage.setTitle("Critters"); // Set window's title
        primaryStage.show();             // Display window

    }
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

