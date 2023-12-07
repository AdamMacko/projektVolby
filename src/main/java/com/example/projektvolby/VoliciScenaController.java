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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

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
    private Button vymazVsetkoButton;



    @FXML
    //nacitanie zo suboru
    void extrahovat(ActionEvent event) {
        System.out.println("ahoj");
        File voliciZoznam = new File("volici.csv");
        nacitajVolicov(voliciZoznam);

    }

    public List<Volic>  nacitajVolicov(File file){
    try (Scanner sc = new Scanner(file,"utf-8")){
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            System.out.println(line);

        }

    } catch (FileNotFoundException e) {
            System.err.println("Export sa nenašiel");
        }


        return null;
    }

    @FXML
    //spracovanie mena
    void menoText(ActionEvent event) {

    }

    @FXML
        //spracovanie bydliska
    void bydliskoText(ActionEvent event) {

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
        //tlacidlo pridat
    void pridat(ActionEvent event) {

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

    @FXML
    void vymazeVsetkychVolicov(ActionEvent event){

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