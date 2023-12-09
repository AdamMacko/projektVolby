package com.example.projektvolby;

import com.example.projektvolby.storage.DaoFactory;
import com.example.projektvolby.storage.StranaDao;
import com.example.projektvolby.storage.VolebnyListokDao;
import com.example.projektvolby.storage.VolenyKandidatDao;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class VolenieSceneController {

    @FXML
    private Label maxKandidatovLabel;

    @FXML
    private Button odvolenieButton;

    @FXML
    private TextArea volebnyPlanTextArea;

    @FXML
    private FlowPane vyberKandidatovFlowPane;

    @FXML
    private ComboBox<Strana> vyberStranyComboBox;

    @FXML
    private Button zvolitStranuButton;
    private StranyFxModel stranafxmodel;
    private ObservableList<Strana> stranyfxmodel;

    private VolebnyListokDao volebnyListokDao=DaoFactory.INSTANCE.getVolebnyListokDao();

    private VolenyKandidatDao volenyKandidatDao=DaoFactory.INSTANCE.getVolenyKandidatDao();


    private StranaDao stranadao= DaoFactory.INSTANCE.getStranyDao();

    private Map<Kandidat, BooleanProperty> kandidatiMapa = new LinkedHashMap<>();
    private Long listokId;
    private VolebnyListok volebnyListok=new VolebnyListok();
    private VolenyKandidat volenyKandidat=new VolenyKandidat();





    @FXML
    public void initialize() {
        volebnyPlanTextArea.setEditable(false);
        List<Strana> strany=stranadao.getAll();
        stranyfxmodel= FXCollections.observableArrayList(strany);
        vyberStranyComboBox.setItems(stranyfxmodel);
        vyberStranyComboBox.getSelectionModel().selectFirst();
        vyberStranyComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                vyberKandidatovFlowPane.getChildren().clear();
                kandidatiMapa.clear();
                update();

            }
        });
        vyberKandidatovFlowPane.getChildren().clear();
        update();

    }
    private void update() {
        vyberKandidatovFlowPane.setDisable(true);
        Strana strana=vyberStranyComboBox.getSelectionModel().getSelectedItem();
        List<Kandidat> kandidati=strana.getKandidati();
        for(Kandidat kandidat :kandidati){
            kandidatiMapa.put(kandidat,new SimpleBooleanProperty());
        }
        for (Entry<Kandidat, BooleanProperty> pair: kandidatiMapa.entrySet()){
            CheckBox checkBox=new CheckBox(pair.getKey().toString());
            checkBox.selectedProperty().bindBidirectional(pair.getValue());
            vyberKandidatovFlowPane.getChildren().add(checkBox);
        }

        volebnyPlanTextArea.setText(strana.getVolebny_plan());


    }

    @FXML
    void zvoliStranu(ActionEvent event) {
        if(UliceSceneController.ConfirmationDialog.show("naozaj chceťe zvoliť túto stranu?Táto operácia sa nedá vrátiť")){
            zvolitStranuButton.setDisable(true);
            Strana strana=vyberStranyComboBox.getSelectionModel().getSelectedItem();
            vyberStranyComboBox.setDisable(true);
            vyberKandidatovFlowPane.setDisable(false);
            volebnyListok.setId(0L);
            volebnyListok.setStranaId(strana.getId());
            volebnyListokDao.save(volebnyListok);
            listokId=volebnyListokDao.najvacsieId();
            System.out.println(listokId);
        }




    }
    @FXML
    void odvolenieVolica(ActionEvent event) {
        List<Kandidat> kandidati=new ArrayList<>();
        int pocet=0;
        for (Map.Entry<Kandidat, BooleanProperty> entry : kandidatiMapa.entrySet()) {
            if (entry.getValue().get()){
                kandidati.add(entry.getKey());
                pocet+=1;
            }
            System.out.println(pocet);
            System.out.println(kandidati);
        }

        if(pocet>4){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Upozornenie");
            alert.setHeaderText("Vela zvolených");
            alert.setContentText("Zvolili ste viac kandidátov ako je povelené");
            alert.showAndWait();

        }else{
            for (Kandidat kandidat :kandidati){
                volenyKandidat=new VolenyKandidat(0L,kandidat.getId(),listokId);
                volenyKandidatDao.save(volenyKandidat);
            }
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Odvolené");
            alert.setHeaderText("Gratulujem!");
            alert.setContentText("Gratulujem,práve ste úspešne odvolili môžťe odísť");
            alert.showAndWait();
            ((Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow()).close();
            prihlasenie();
        }




    }
    private void prihlasenie () {
        try {
            PrihlasenieScenaController controller = new PrihlasenieScenaController();
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("prihlasenie.fxml"));
            loader.setController(controller);
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("PRIHLASENIE");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
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

