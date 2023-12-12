package com.example.projektvolby;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;


public class StartScene extends Application {
	
    @Override
	public void start(Stage primaryStage) throws Exception {
		PrihlasenieScenaController controller = new PrihlasenieScenaController();
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("prihlasenie.fxml"));
		loader.setController(controller);
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		File file = new File("C:\\Users\\macko\\IdeaProjects\\projektVolby\\sr.png");
		Image icon = new Image(file.toURI().toString());
		primaryStage.getIcons().add(icon);
		primaryStage.setScene(scene);
		primaryStage.setTitle("PRIHLÁSENIE");
		primaryStage.show();
	}


    public static void main(String[] args) {
        launch();
    }

}