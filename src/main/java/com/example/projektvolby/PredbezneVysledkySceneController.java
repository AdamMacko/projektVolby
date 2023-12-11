package com.example.projektvolby;

import com.example.projektvolby.storage.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class PredbezneVysledkySceneController {

    @FXML
    private Label celkovyPocetLabel;

    @FXML
    private TableView<KandidatOverview> kandidatiTableView;

    @FXML
    private Label percentavolicovLabel;

    @FXML
    private Label pocetOdvolenychLabel;

    @FXML
    private Label pocetHlasovLabel;

    @FXML
    private ComboBox<Strana> stranyComboBox;

    private StranaDao stranaDao= DaoFactory.INSTANCE.getStranyDao();

    private KandidatDao kandidatDao=DaoFactory.INSTANCE.getKandidatiDao();
    private VolebnyListokDao volebnyListokDao=DaoFactory.INSTANCE.getVolebnyListokDao();
    private VolenyKandidatDao volenyKandidatDao=DaoFactory.INSTANCE.getVolenyKandidatDao();
    private VolicDao volicDao=DaoFactory.INSTANCE.getVoliciDao();
    private ObservableList<Strana> stranyModel;
    private OverviewManager overviewManager=new OverviewManagerImpl();


    public void initialize(){
        List<Volic> volici=volicDao.getAll();
        int pocetVolicov=volici.size();
        celkovyPocetLabel.setText(String.valueOf(pocetVolicov));
        List<VolebnyListok> volebneListky=volebnyListokDao.getAll();
        int pocetHlasov=volebneListky.size();
        pocetOdvolenychLabel.setText(String.valueOf(pocetHlasov));

        double percentualne=((double) pocetHlasov/pocetVolicov)*100;
        System.out.println(percentualne);
        percentavolicovLabel.setText(String.format("%.2f", percentualne)+"%");


        List<Strana> strany=stranaDao.getAll();
        stranyModel= FXCollections.observableArrayList(strany);
        stranyComboBox.setItems(stranyModel);
        stranyComboBox.getSelectionModel().selectFirst();
        stranyComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                update();
            }
        });


        update();
        TableColumn<KandidatOverview,String> menoCol=new TableColumn<>("Meno");
        menoCol.setCellValueFactory(new PropertyValueFactory<>("meno"));
        kandidatiTableView.getColumns().add(menoCol);

        TableColumn<KandidatOverview,String> priezviskoCol=new TableColumn<>("Priezvisko");
        priezviskoCol.setCellValueFactory(new PropertyValueFactory<>("priezvisko"));
        kandidatiTableView.getColumns().add(priezviskoCol);

        TableColumn<KandidatOverview, Integer> pocetCol=new TableColumn<>("Pocet hlasov");
        pocetCol.setCellValueFactory(new PropertyValueFactory<>("pocetHlasov"));
        kandidatiTableView.getColumns().add(pocetCol);

        TableColumn<KandidatOverview,Double> percentaCol=new TableColumn<>("Percenta za stranu");
        percentaCol.setCellValueFactory(new PropertyValueFactory<>("percentaHlasov"));
        kandidatiTableView.getColumns().add(percentaCol);





    }
    private void update() {
        Strana strana=stranyComboBox.getSelectionModel().getSelectedItem();
        List<VolebnyListok> hlasyStrany=volebnyListokDao.getAllByStranaId(strana.getId());
        int pocethlasovStrany=hlasyStrany.size();
        pocetHlasovLabel.setText(String.valueOf(pocethlasovStrany));

        List<KandidatOverview> overviews=overviewManager.getOverviews(strana);
        kandidatiTableView.setItems(FXCollections.observableArrayList(overviews));


    }

}

