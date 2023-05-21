package com.example.notepad;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private Stage stage;
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

    public void createNewScene(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Parent root = fxmlLoader.load();
            HelloController controller = fxmlLoader.getController();
            controller.setApplication(this);
            stage.setScene(new Scene(root));
            stage.setTitle("Notepad");
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}