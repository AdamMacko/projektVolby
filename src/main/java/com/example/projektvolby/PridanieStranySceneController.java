package com.example.projektvolby;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class PridanieStranySceneController {

    @FXML
    private ListView<Kandidati> listKandidatov;

    @FXML
    private TextField meno;

    @FXML
    private TextField nazov;

    @FXML
    private Button pridaniekKandidata;

    @FXML
    private Button ulozenie;

    @FXML
    private TextField vek;

    @FXML
    private TextArea volebnyPlan;

    @FXML
    private Button vymazanieKandidata;

    @FXML
    private Button zosuboru;

    private StranyFxModel stranyFxModel;

    public PridanieStranySceneController(){
        stranyFxModel=new StranyFxModel();
    }
    public  PridanieStranySceneController(Strany strana){
        stranyFxModel=new StranyFxModel(strana);
    }

    @FXML
    public void initialize() {//nazov je bindnuty na fxmodel nastavenie listu a listener ak nie su values vo veku aj mene naraz neda sa ulozit
        nazov.textProperty().bindBidirectional(stranyFxModel.nazovProperty());
        listKandidatov.setItems(stranyFxModel.kandidatiModel());
        pridaniekKandidata.setDisable(true);
        ChangeListener<String> listener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {
                boolean isNazovEmpty = meno.getText().isEmpty();
                boolean isVekEmpty = vek.getText().isEmpty();
                pridaniekKandidata.setDisable(isNazovEmpty || isVekEmpty);
            }
        };

        meno.textProperty().addListener(listener);
        vek.textProperty().addListener(listener);


    }


    @FXML
    void extrahujeKandidatov(ActionEvent event) {

    }

    @FXML
    void novyKandidat(ActionEvent event) {
        String celeMeno= meno.getText().trim();
        int age= Integer.parseInt(vek.getText().trim());
        int index = celeMeno.lastIndexOf(' ');
        String krstne="";
        String priezvisko="";
        if (index == -1) {
            priezvisko = celeMeno;
        }else {
            krstne = celeMeno.substring(0, index).trim();
            priezvisko = celeMeno.substring(index + 1).trim();
        }
        Kandidati kandidat = new Kandidati(krstne,priezvisko,age);
        stranyFxModel.kandidatiModel().add(kandidat);
        meno.clear();
        vek.clear();





    }

    @FXML
    void pridanieMena(ActionEvent event) {

    }

    @FXML
    void pridanieNazvu(ActionEvent event) {

    }

    @FXML
    void pridanieVeku(ActionEvent event) {

    }

    @FXML
    void ulozStranu(ActionEvent event) {

    }

    @FXML
    void vymazKanidata(ActionEvent event) {
        Kandidati kandidat=listKandidatov.getSelectionModel().getSelectedItem();
        System.out.print(kandidat);
        if(kandidat !=null){
            stranyFxModel.kandidatiModel().remove(kandidat);

        }

    }

}
