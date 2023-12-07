package com.example.projektvolby;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VolicFxModel {
    private Long id;
    private StringProperty meno = new SimpleStringProperty();
    private StringProperty priezvisko = new SimpleStringProperty();
    private StringProperty cOP = new SimpleStringProperty();
    private BooleanProperty dochadzka = new SimpleBooleanProperty();
    private ObservableList<Kandidat> kandidat;

    public VolicFxModel() {

        kandidat = FXCollections.observableArrayList();
    }

    public VolicFxModel(Volic volic) {
        setMeno(volic.getMeno());
        setPriezvisko(volic.getPriezvisko());
        setcOP(volic.getcOP());
        id = volic.getId();
        setDochadzka(false);
    }

    public void setDochadzka(boolean dochadzka) {
        this.dochadzka.set(dochadzka);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMeno(String meno) {
        this.meno.set(meno);
    }

    public void setPriezvisko(String priezvisko) {
        this.priezvisko.set(priezvisko);
    }

    public void setcOP(String cOP) {
        this.cOP.set(cOP);
    }


}
