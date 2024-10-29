package com.example.week_9_assignment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("scene1.fxml"));
        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("scene2.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        Scene scene2 = new Scene(fxmlLoader2.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }


    public static void changeScene(Stage stage, Scene scene){
        stage.setScene(scene);
    }



    public static void main(String[] args) {
        launch();
    }
}