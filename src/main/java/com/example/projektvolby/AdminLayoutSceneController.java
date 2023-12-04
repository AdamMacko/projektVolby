package com.example.projektvolby;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminLayoutSceneController {

    @FXML
    private Button editaciaStranyButton;

    @FXML
    private Button predbezneButton;

    @FXML
    private Button pridaniaStranyButton;

    @FXML
    private Button pridanieLudiButton;

    @FXML
    void editaciaStrany(ActionEvent event) {

    }

    @FXML
    void predbezne(ActionEvent event) {

    }

    @FXML
    void pridanieLudi(ActionEvent event) {

    }

    @FXML
    void pridanieStrany(ActionEvent event) {
        pridanieNovejStrany ();
    }
    private void pridanieNovejStrany () {
        try {
            PridanieStranySceneController controller = new PridanieStranySceneController();
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("novastrana.fxml"));
            loader.setController(controller);
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("NOVA STRANA");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


