package com.example.projektvolby;

import com.example.projektvolby.storage.DaoFactory;
import com.example.projektvolby.storage.StranaDao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class PridanieStranySceneController {

    @FXML
    private ListView<Kandidat> kandidatiListView;

    @FXML
    private TextField menoTextField;

    @FXML
    private TextField nazovTextField;

    @FXML
    private Button pridatButton;

    @FXML
    private Button ulozitButton;

    @FXML
    private TextField vekTextField;

    @FXML
    private TextArea volebnyPlanTextArea;

    @FXML
    private Button vymazatButton;

    @FXML
    private Button extrahovatButton;

    private StranyFxModel stranyFxModel;

    private Strana novaStrana;

    public PridanieStranySceneController(){
        stranyFxModel=new StranyFxModel();
    }
    public  PridanieStranySceneController(Strana strana){
        stranyFxModel=new StranyFxModel(strana);
    }

    @FXML
    public void initialize() {//nazov je bindnuty na fxmodel nastavenie listu a listener ak nie su values vo veku aj mene naraz neda sa ulozit
        nazovTextField.textProperty().bindBidirectional(stranyFxModel.nazovProperty());
        volebnyPlanTextArea.textProperty().bindBidirectional(stranyFxModel.volebnyPlanProperty());
        kandidatiListView.setItems(stranyFxModel.kandidatiModel());
        pridatButton.setDisable(true);
        ChangeListener<String> listener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {
                boolean isNazovEmpty = menoTextField.getText().isEmpty();
                boolean isVekEmpty = vekTextField.getText().isEmpty();
                pridatButton.setDisable(isNazovEmpty || isVekEmpty);
            }
        };

        menoTextField.textProperty().addListener(listener);
        vekTextField.textProperty().addListener(listener);


    }


    @FXML
    void extrahujeKandidatov(ActionEvent event) {

    }

    @FXML
    void novyKandidat(ActionEvent event) {
        String celeMeno= menoTextField.getText().trim();
        int age= Integer.parseInt(vekTextField.getText().trim());
        int index = celeMeno.lastIndexOf(' ');
        String krstne="";
        String priezvisko="";
        if (index == -1) {
            priezvisko = celeMeno;
        }else {
            krstne = celeMeno.substring(0, index).trim();
            priezvisko = celeMeno.substring(index + 1).trim();
        }
        Kandidat kandidat = new Kandidat( 0L,krstne,priezvisko,age);
        stranyFxModel.kandidatiModel().add(kandidat);
        menoTextField.clear();
        vekTextField.clear();
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
        Strana strana= stranyFxModel.getStrana();
        StranaDao stranyDao = DaoFactory.INSTANCE.getStranyDao();

        novaStrana=stranyDao.save(strana);
        ulozitButton.getScene().getWindow().hide();


    }

    @FXML
    void vymazKanidata(ActionEvent event) {
        Kandidat kandidat= kandidatiListView.getSelectionModel().getSelectedItem();
        System.out.print(kandidat);
        if(kandidat !=null){
            stranyFxModel.kandidatiModel().remove(kandidat);

        }

    }

}
