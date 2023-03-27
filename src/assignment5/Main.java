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
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import static javafx.scene.media.AudioClip.INDEFINITE;



public class Main extends Application {
    public Thread thread;

    public static void main(String[] args) {
        launch(args);
    }
    //https://www.youtube.com/watch?v=yqUntgnOXbY
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(this.getClass().getResource("TitleScreen.fxml"));
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Critters");
        primaryStage.centerOnScreen();
        primaryStage.show();
        thread = new Thread(music_task);
        thread.start();


    }


    /**
     * Method for music thread
     */
    final Task music_task = new Task() {

        @Override
        protected Object call() throws Exception {
            int s = INDEFINITE;
            AudioClip audio = new AudioClip(getClass().getResource("Minecraft_song.mp3").toExternalForm()); //might need to change to a wav file
            audio.setVolume(0.5f);
            audio.setCycleCount(s);
            audio.play();
            return null;
        }
    };
}
