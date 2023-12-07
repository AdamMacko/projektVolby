package com.example.projektvolby;

import com.example.projektvolby.storage.DaoFactory;
import com.example.projektvolby.storage.UliceDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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
        }

    }

    @FXML
    void vymazeObec(ActionEvent event) {

    }

    @FXML
    void vymazeVsetkyUlice(ActionEvent event) {

    }

}




