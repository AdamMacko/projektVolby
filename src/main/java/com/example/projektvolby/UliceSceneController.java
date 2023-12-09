package com.example.projektvolby;

import com.example.projektvolby.storage.DaoFactory;
import com.example.projektvolby.storage.UliceDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class UliceSceneController {

    @FXML
    private Button ExtrakciaButton;

    @FXML
    private TextField nazovTextField;

    @FXML
    private TextField popisneCisloTextField;

    @FXML
    private Button pridatButton;

    @FXML
    private TextField pscTextField;

    @FXML
    private ListView<Ulica> uliceListView;

    @FXML
    private Button ulozitButton;

    @FXML
    private Button vymazatButton;

    @FXML
    private Button vymazvsetkoButton;

    private UliceDao uliceDao= DaoFactory.INSTANCE.getUliceDao();

    private ObservableList<Ulica> ulicaModel;
    public void initialize() {
        List<Ulica> ulice=uliceDao.getAll();
        ulicaModel= FXCollections.observableArrayList(ulice);
        uliceListView.setItems(ulicaModel);

    }

    @FXML
    void extrahujeUlice(ActionEvent event) {

    }

    @FXML
    void pridaUlicu(ActionEvent event) {
        String nazov=nazovTextField.getText().trim();
        int popisne=Integer.parseInt(popisneCisloTextField.getText().trim());
        String PSC=pscTextField.getText().trim();
        Ulica ulica = new Ulica( 0L,nazov,popisne,PSC);
        ulicaModel.add(ulica);
        nazovTextField.clear();
        popisneCisloTextField.clear();
        pscTextField.clear();


    }

    @FXML
    void uloziUlicu(ActionEvent event) {
        for(Ulica ulica:ulicaModel){
            uliceDao.save(ulica);
            System.out.println(ulicaModel);
        }

    }

    @FXML
    void vymazeObec(ActionEvent event) {
        Ulica ulica=uliceListView.getSelectionModel().getSelectedItem();
        if(ulica.getId() !=0){
            uliceDao.delete(ulica.getId());
        }
        ulicaModel.remove(ulica);
        System.out.println(ulicaModel);

    }

    @FXML
    void vymazeVsetkyUlice(ActionEvent event) {
        if(ConfirmationDialog.show("naozaj chcete vymazat vsetky ulice?Tato operacia sa neda vratit")){
            uliceDao.deleteall();
            ulicaModel.clear();

        }


    }
    public class ConfirmationDialog {

        public static boolean show(String message) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText(message);

            // Customize the buttons
            ButtonType buttonTypeYes = new ButtonType("Ano");
            ButtonType buttonTypeNo = new ButtonType("Nie");

            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

            // Show the dialog and wait for a button to be clicked
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.setAlwaysOnTop(true); // Set to true if you want the dialog to be always on top

            // Wait for the user's choice
            boolean result = alert.showAndWait().orElse(buttonTypeNo) == buttonTypeYes;

            return result;
        }
    }

}




