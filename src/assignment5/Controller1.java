package assignment5;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller1 {
    private static String myPackage = Main.class.getPackage().toString().split(" ")[1];
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void quit(ActionEvent event){
        System.exit(0);
    }
    public void SwitchScene(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("CrittersDisplay.fxml"));
            stage =  (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();







        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
