package com.example.projektvolby;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VolicFxModel {
    private Long id;
    private StringProperty meno = new SimpleStringProperty();
    private StringProperty priezvisko = new SimpleStringProperty();
    private StringProperty cOP = new SimpleStringProperty();
    private StringProperty trvaleBydlisko = new SimpleStringProperty();
    private StringProperty PSC = new SimpleStringProperty();
    private BooleanProperty dochadzka = new SimpleBooleanProperty();
    private ObservableList<Volic> volic;


    public VolicFxModel() {

        volic= FXCollections.observableArrayList();
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

    public String getTrvaleBydlisko() {
        return trvaleBydlisko.get();
    }

    public StringProperty trvaleBydliskoProperty() {
        return trvaleBydlisko;
    }

    public void setTrvaleBydlisko(String trvaleBydlisko) {
        this.trvaleBydlisko.set(trvaleBydlisko);
    }

    public String getPSC() {
        return PSC.get();
    }

    public StringProperty PSCProperty() {
        return PSC;
    }

    public void setPSC(String PSC) {
        this.PSC.set(PSC);
    }

    public void setVolic(ObservableList<Volic> volic) {
        this.volic = volic;
    }

    public Long getId() {
        return id;
    }

    public String getMeno() {
        return meno.get();
    }

    public String getPriezvisko() {
        return priezvisko.get();
    }

    public String getcOP() {
        return cOP.get();
    }

    public boolean isDochadzka() {
        return dochadzka.get();
    }

    public BooleanProperty dochadzkaProperty() {
        return dochadzka;
    }

    public StringProperty cOP() {
        return cOP;
    }
    public BooleanProperty dochadzka(){return dochadzka;}

    public ObservableList <Volic>volic(){return volic;}


    public Property<String> menoProperty() {
        return meno;
    }

    public Property<String> priezviskoProperty() {
        return priezvisko;
    }

    public Property<String> cOPProperty() {
        return cOP;
    }

    public Volic getVolic() {
        return new Volic( getMeno(), getPriezvisko(),getcOP(), getTrvaleBydlisko(),getPSC());
    }
}
