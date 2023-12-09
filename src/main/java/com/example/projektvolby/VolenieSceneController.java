package com.example.projektvolby;

import com.example.projektvolby.storage.DaoFactory;
import com.example.projektvolby.storage.StranaDao;
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


    private StranaDao stranadao= DaoFactory.INSTANCE.getStranyDao();

    private Map<Kandidat, BooleanProperty> kandidatiMapa = new LinkedHashMap<>();

    @FXML
    void odvolenieVolica(ActionEvent event) {

        ((Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow()).close();
        prihlasenie();

    }
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

}

