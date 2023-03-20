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
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;


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
        //remove all the unnecessary classes, if more classes are added. Remove Here
        arrString.remove("Critter$CritterShape");
        arrString.remove("Critter$TestCritter");
        arrString.remove("InvalidCritterException");
        arrString.remove("Main");
        arrString.remove("Main$1");
        arrString.remove("Params");
        arrString.remove("Critter");

        //arrString.toArray();
        String [] critterNames = new String[arrString.size()];
        for(int i=0; i< arrString.size(); i++){
            critterNames[i] = arrString.get(i);
        }
        return critterNames;
    }

    //Label fields
    private Label critterLabel;
    private Label amountLabel;
    private Label stepsLabel;
    private Label seedLabel;
    private Label animationLabel;

    //Text fields
    private TextField amountText;
    private TextField stepsText;
    private TextField seedText;
    private TextField animationText;


    @Override
    public void start(Stage primaryStage) throws Exception {

        //zybooks
        Scene scene = null;         // Scene contains all content
        GridPane gridPane = null;   // Positions components within scene

        gridPane = new GridPane();   // Create an empty pane
        scene = new Scene(gridPane); // Create scene containing the grid pane

        //Labels
        critterLabel = new Label("Critter:");
        amountLabel = new Label("Amount:");
        stepsLabel = new Label("Steps:");
        seedLabel = new Label("Seed:");
        animationLabel = new Label("Animation:");

        //User input texts
        amountText = new TextField();
        amountText.setPrefColumnCount(8); //size of the text box?
        amountText.setEditable(true); //allows user to input text
        amountText.setText("0");

        stepsText = new TextField();
        stepsText.setPrefColumnCount(8);
        stepsText.setEditable(true);
        stepsText.setText("0");

        seedText = new TextField();
        seedText.setPrefColumnCount(8);
        seedText.setEditable(true);
        seedText.setText("0");

        animationText = new TextField();
        animationText.setPrefColumnCount(8);
        animationText.setEditable(true);
        animationText.setText("0");


        //Gridpane settings
        gridPane.setPadding(new Insets(10, 10, 10, 10)); // Padding around  grid
        gridPane.setHgap(5);                            // Spacing between columns
        gridPane.setVgap(10);                            // Spacing between rows

        gridPane.add(critterLabel, 0, 0);

        gridPane.add(amountLabel, 2, 0);
        gridPane.add(amountText, 3, 0);

        gridPane.add(stepsLabel, 0, 2);
        gridPane.add(stepsText, 1, 2);

        gridPane.add(seedLabel, 2, 2);
        gridPane.add(seedText, 3, 2);

        gridPane.add(animationLabel, 0, 4);
        gridPane.add(animationText, 1, 4);

        //critters drop down menu
        String [] critterNames = findCritters();

        ComboBox comboBox = new ComboBox(FXCollections.observableArrayList(critterNames));
        Label selectedDropDown = new Label("default item selected");
        //action for dropdown
        EventHandler<ActionEvent> eventDropDown = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                selectedDropDown.setText(comboBox.getValue() + " selected");

            }
        };

        comboBox.setOnAction(eventDropDown);
        gridPane.add(comboBox, 1,0);

        primaryStage.setScene(scene);    // Set window's scene
        primaryStage.setTitle("Critters"); // Set window's title
        primaryStage.show();             // Display window

    }


}
