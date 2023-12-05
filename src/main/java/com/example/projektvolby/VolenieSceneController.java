package com.example.projektvolby;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class VolenieSceneController {

    @FXML
    private Label maxKandidatovLabel;

    @FXML
    private Button odvolenieButton;

    @FXML
    private TextArea volebnyPlanTextArea;

    @FXML
    private FlowPane vyberKandidatovFlowPane;

    @FXML
    private ComboBox<?> vyberStranyComboBox;

    @FXML
    private Button zvolitStranuButton;

    @FXML
    void odvolenieVolica(ActionEvent event) {

        ((Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow()).close();
        prihlasenie();

    }

    @FXML
    void zvoliStranu(ActionEvent event) {

    }
    private void prihlasenie () {
        try {
            PrihlasenieScenaController controller = new PrihlasenieScenaController();
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("prihlasenie.fxml"));
            loader.setController(controller);
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("PRIHLASENIE");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

