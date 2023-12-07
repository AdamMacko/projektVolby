package com.example.projektvolby;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.text.Text;


public class StranyFxModel {
    private Long id;
    private StringProperty nazov = new SimpleStringProperty();
    private StringProperty volebnyPlan = new SimpleStringProperty();
    private ObservableList<Kandidat> kandidati;
    public StranyFxModel() {
        kandidati = FXCollections.observableArrayList();
    }
    public StranyFxModel(Strana strana) {
        id= strana.getId();
        setNazov(strana.getNazov());
        setVolebnyPlan(strana.getVolebny_plan());
        kandidati=FXCollections.observableArrayList(strana.getKandidati());
    }



    public String getNazov() {
        return nazov.get();
    }

    public StringProperty nazovProperty() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov.setValue(nazov);
    }

    public String getVolebnyPlan() {
        return volebnyPlan.get();
    }

    public StringProperty volebnyPlanProperty() {
        return volebnyPlan;
    }

    public void setVolebnyPlan(String volebnyPlan) {
        this.volebnyPlan.setValue(volebnyPlan);
    }

    public List<Kandidat> getKandidati() {
        return new ArrayList<Kandidat>(kandidati);
    }


    public ObservableList<Kandidat> kandidatiModel() {
        return kandidati;
    }
    public Strana getStrana() {
        return new Strana(id, getNazov(), getVolebnyPlan(), getKandidati());
    }

    @Override
    public String toString() {
        return "StranyFxModel{" +
                "id=" + id +
                ", nazov=" + nazov +
                ", volebnyPlan=" + volebnyPlan +
                ", kandidati=" + kandidati +
                '}';
    }
}
