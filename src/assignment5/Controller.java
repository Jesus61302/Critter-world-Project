package assignment5;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

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

    public static void findCritters() {
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
        //return critterNames;
    }
    public void SwitchScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("CrittersDisplay.fxml"));
        stage =  (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);



        stage.show();

    }



}
