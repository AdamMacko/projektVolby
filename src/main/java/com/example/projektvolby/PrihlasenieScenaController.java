package com.example.projektvolby;

import java.io.IOException;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class PrihlasenieScenaController {

    @FXML
    private TextField hesloTextField;

    @FXML
    private TextField menoTextField;

    @FXML
    private Button prihlasenieButton;

    @FXML
    void prihlasenie(ActionEvent event) {

        String meno = menoTextField.getText().trim();
        String heslo = hesloTextField.getText().trim();
        if ("admin".equals(meno) && "admin".equals(heslo)) {
            openAdminlayout();
            ((Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow()).close();
        } else if ("volic".equals(meno) && "volic".equals(heslo)) {
            openInstrukcieOkno();
            ((Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow()).close();
        } else {
            menoTextField.setText("");
            hesloTextField.setText("");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("UPOZORNENIE!");
            alert.setHeaderText("Zadali ste nesprávne meno alebo heslo");
            alert.setContentText("Zopakujte prihlásenie");

            alert.showAndWait();
        }


    }


    private void openInstrukcieOkno() {
        try {
            VolenieOknoController controller = new VolenieOknoController();
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

    private void openAdminlayout() {
        try {
            AdminLayoutSceneController controller = new AdminLayoutSceneController();
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("adminlayout.fxml"));
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