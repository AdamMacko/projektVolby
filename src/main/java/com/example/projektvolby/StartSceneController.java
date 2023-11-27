package com.example.projektvolby;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class StartSceneController {

	@FXML
    private TextField hesloTextField;

    @FXML
    private TextField menoTextField;

    @FXML
    private Button prihlasenieButton;

    @FXML
    void prihlasenie(ActionEvent event) {
    	openInstrukcieOkno();
    	((Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow()).close();
    }
    
    
    private void openInstrukcieOkno() {
		try {
			VolenieSceneController controller = new VolenieSceneController();
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("instrukcie.fxml"));
			loader.setController(controller);
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("INSTRUKCIE");
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    
   

}