package com.example.projektvolby;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;





public class StartScene extends Application {
	
    @Override
	public void start(Stage primaryStage) throws Exception {
		StartSceneController controller = new StartSceneController();
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("prihlasenie.fxml"));
		loader.setController(controller);
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Attender");
		primaryStage.show();
	

		
	}


    public static void main(String[] args) {

        launch();
    }
    




	

	


	
}