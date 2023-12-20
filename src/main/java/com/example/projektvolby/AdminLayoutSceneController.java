package com.example.projektvolby;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class AdminLayoutSceneController {
    @FXML
    private Button zavrietButton;

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
        editaciastrany() ;

    }

    @FXML
    void predbezne(ActionEvent event) {
        predbezneVysledky();

    }
    @FXML
    void pridanieUlic(ActionEvent event) {
        pridanieUlic();

    }

    @FXML
    void pridanieLudi(ActionEvent event) {
        openvoliciOkno();

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
            File file = new File("C:\\Users\\macko\\IdeaProjects\\projektVolby\\sr.png");
            Image icon = new Image(file.toURI().toString());
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("NOVA STRANA");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
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
            File file = new File("C:\\Users\\macko\\IdeaProjects\\projektVolby\\sr.png");
            Image icon = new Image(file.toURI().toString());
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("ADMIN");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void editaciastrany() {
        try {
            EditStranyController controller = new EditStranyController();
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("editstrany.fxml"));
            loader.setController(controller);
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            File file = new File("C:\\Users\\macko\\IdeaProjects\\projektVolby\\sr.png");
            Image icon = new Image(file.toURI().toString());
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Editstrany");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void pridanieUlic() {
        try {
            UliceSceneController controller = new UliceSceneController();
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("ulice.fxml"));
            loader.setController(controller);
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            File file = new File("C:\\Users\\macko\\IdeaProjects\\projektVolby\\sr.png");
            Image icon = new Image(file.toURI().toString());
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("ulice");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void predbezneVysledky() {
        try {
            PredbezneVysledkySceneController controller = new PredbezneVysledkySceneController();
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("predbezne.fxml"));
            loader.setController(controller);
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            File file = new File("C:\\Users\\macko\\IdeaProjects\\projektVolby\\sr.png");
            Image icon = new Image(file.toURI().toString());
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("predbezne");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void zavrietButton(ActionEvent event) {
        ((Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow()).close();
    }


}


