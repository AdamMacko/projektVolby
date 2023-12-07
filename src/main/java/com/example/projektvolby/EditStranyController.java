package com.example.projektvolby;

import com.example.projektvolby.storage.DaoFactory;
import com.example.projektvolby.storage.KandidatDao;
import com.example.projektvolby.storage.StranaDao;
import com.mysql.cj.conf.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.List;

public class EditStranyController {

    @FXML
    private ComboBox<Strana> stranaComboBox;

    @FXML
    private Button aktualizovatButton;

    @FXML
    private Button extrakciaButton;

    @FXML
    private ListView<Kandidat> kandidatiListView;

    @FXML
    private TextField menoTextField;

    @FXML
    private Button pridatButton;

    @FXML
    private TextField vekTextField;

    @FXML
    private TextArea volebnyPlanTextArea;

    @FXML
    private Button vymazStranuButton;

    @FXML
    private Button vymazatButton;
    private StranaDao stranaDao= DaoFactory.INSTANCE.getStranyDao();
    private KandidatDao kandidatDao= DaoFactory.INSTANCE.getKandidatiDao();
    private ObservableList<Strana> stranyModel;
    private StranyFxModel stranyFxModel= new StranyFxModel();
    private ObservableList<Kandidat> kandidatiModel;
    private Strana aktualizovanaStrana;


    public void initialize() {
        List<Strana> strany=stranaDao.getAll();
        stranyModel= FXCollections.observableArrayList(strany);
        stranaComboBox.setItems(stranyModel);
        stranaComboBox.getSelectionModel().selectFirst();
        stranaComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                update();
            }
        });

        update();




    }
    private void update() {
        Strana vybranaStrana=stranaComboBox.getSelectionModel().getSelectedItem();
        List<Kandidat> kandidati=kandidatDao.getAllByStranaId(vybranaStrana.getId());
        kandidatiModel=FXCollections.observableArrayList(kandidati);
        kandidatiListView.setItems(kandidatiModel);
        String volebnyPlan=vybranaStrana.getVolebny_plan();
        volebnyPlanTextArea.textProperty().setValue(volebnyPlan);
    }



    @FXML
    void aktualizujeStranu(ActionEvent event) {

            Strana vybranaStrana = stranaComboBox.getSelectionModel().getSelectedItem();
            stranyFxModel.setNazov(vybranaStrana.getNazov());
            stranyFxModel.setVolebnyPlan(volebnyPlanTextArea.getText());
            stranyFxModel.kandidatiModel().addAll(kandidatiModel);
            System.out.println(kandidatiModel);
            Strana updatedStrana = stranyFxModel.getStrana();
            updatedStrana.setId(vybranaStrana.getId());
            System.out.println(updatedStrana);
            aktualizovanaStrana = stranaDao.save(updatedStrana);
            System.out.println(aktualizovanaStrana);
        }



    @FXML
    void extrahujeKandidatov(ActionEvent event) {

    }

    @FXML
    void pridaKandidata(ActionEvent event) {
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
        kandidatiModel.add(kandidat);
        menoTextField.clear();
        vekTextField.clear();

    }

    @FXML
    void vymazeKandidata(ActionEvent event) {
        Kandidat kandidat=kandidatiListView.getSelectionModel().getSelectedItem();
        if (kandidat != null){
            kandidatiModel.remove(kandidat);
        }

    }

    @FXML
    void vymazeStranu(ActionEvent event) {
        Strana vybranaStrana=stranaComboBox.getSelectionModel().getSelectedItem();
        stranaDao.delete(vybranaStrana.getId());
        List<Strana> strany=stranaDao.getAll();
        stranyModel= FXCollections.observableArrayList(strany);
        stranaComboBox.setItems(stranyModel);
        stranaComboBox.getSelectionModel().selectFirst();

    }

}