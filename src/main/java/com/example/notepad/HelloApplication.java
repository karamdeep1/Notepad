package com.example.notepad;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {
    public Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 845, 557);
        HelloController controller = fxmlLoader.getController();
        controller.setApplication(this);
        stage.setTitle("Notepad");
        stage.setScene(scene);
        stage.show();
    }
    //creates a new scene
    public void createNewScene(){
        try{
            //loads the hello view fxml file
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            //creates a parent variable called root that loads the fxml
            Parent root = fxmlLoader.load();
            //Creates a HelloController variable that gets the controller
            HelloController controller = fxmlLoader.getController();
            //sets the HelloApplication variable in the HelloController class to this
            controller.setApplication(this);
            //Sets the scene
            stage.setScene(new Scene(root));
            //Sets the Title
            stage.setTitle("Notepad");
        }  //catches the exception
        catch(IOException e) {
            //prints exception
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}