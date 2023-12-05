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

public class IntrukcieSceneController {
    @FXML
    private Button volenieButton;

    @FXML
    void zacnevolit(ActionEvent event) {
        volenie();
        ((Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow()).close();

    }
    private void volenie () {
        try {
            VolenieSceneController controller = new VolenieSceneController();
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("volenie.fxml"));
            loader.setController(controller);
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("VOLENIE");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
