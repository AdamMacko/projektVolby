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
    private Button pridanieUlicButton;

    @FXML
    void editaciaStrany(ActionEvent event) {

    }

    @FXML
    void predbezne(ActionEvent event) {

    }

    @FXML
    void pridanieLudi(ActionEvent event) {
        openvoliciOkno();
        ((Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    void pridanieStrany(ActionEvent event) {

    }

    @FXML
    void pridanieUlic(ActionEvent event) {

    }

    private void openvoliciOkno() {
        try {
            VoliciScenaController controller = new VoliciScenaController();
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("volici.fxml"));
            loader.setController(controller);
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("ADMIN");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


