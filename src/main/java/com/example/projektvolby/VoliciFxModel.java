package com.example.projektvolby;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.text.Text;

public class VoliciFxModel{
    private Long id;
    private StringProperty meno = new SimpleStringProperty();
    private StringProperty priezvisko = new SimpleStringProperty();
    private StringProperty cOP = new SimpleStringProperty();
    private BooleanProperty dochadzka = new SimpleBooleanProperty();

}
