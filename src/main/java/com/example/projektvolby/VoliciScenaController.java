package com.example.projektvolby;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class VoliciScenaController {

    @FXML
    private TextField bydliskoTextField;
    @FXML
    private Button spatButton;
    @FXML
    private Button extrahovatButton;

    @FXML
    private TextField menoTextField;

    @FXML
    private Button pridatButton;

    @FXML
    private TextField priezviskoTextFiled;

    @FXML
    private TextField rodneCisloTextField;

    @FXML
    private Button ulozitButton;

    @FXML
    private ListView<Volic> volicListView;

    @FXML
    private Button vymazatbutton;

    @FXML
    //spracovanie bydliska
    void bydliskoText(ActionEvent event) {

    }

    @FXML
    //nacitanie zo suboru
    void extrahovat(ActionEvent event) {

    }

    @FXML
    //spracovanie mena
    void menoText(ActionEvent event) {

    }

    @FXML
    //tlacidlo pridat
    void pridat(ActionEvent event) {

    }

    @FXML
    //spracovanie priezviska
    void priezviskoText(ActionEvent event) {

    }

    @FXML
    //spracovanie rodneho cisla z textu
    void rodneCisloText(ActionEvent event) {

    }

    @FXML
    //tlacidlo ulozit
    void ulozit(ActionEvent event) {

    }

    @FXML
    //Listview
    void volicList(ActionEvent event) {

    }

    @FXML
    //tlacidlo na vymazanie volica
    void vymazat(ActionEvent event) {

    }

    @FXML
    void spatButton(ActionEvent event) {
        openAdminlayout();
        ((Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow()).close();
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

