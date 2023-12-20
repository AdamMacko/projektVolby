package com.example.projektvolby;

import java.io.File;
import java.io.IOException;

import com.example.projektvolby.storage.DaoFactory;
import com.example.projektvolby.storage.VolicDao;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class PrihlasenieScenaController {

	@FXML
    private TextField hesloTextField;

    @FXML
    private TextField menoTextField;

    @FXML
    private Button prihlasenieButton;


    @FXML
    void prihlasenie(ActionEvent event) {
			String[] celeMeno = menoTextField.getText().split(" ");

			String menoA =  menoTextField.getText().trim();
			int index=menoA.lastIndexOf(" ");

			String heslo = hesloTextField.getText().trim();
			boolean admin = false;
		if ("admin".equals(menoA) && "admin".equals(heslo)) {
			admin = true;
			openAdminlayout();
			((Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow()).close();
		}
		String meno="";
		String priezvisko="";
		if (index == -1) {
			priezvisko = menoA;
		}else {
			meno = menoA.substring(0, index).trim();
			priezvisko = menoA.substring(index + 1).trim();
		}
		VolicDao volicDao = DaoFactory.INSTANCE.getVoliciDao();
		boolean isMenoHesloValid = volicDao.overHesloMeno(meno, priezvisko, heslo);
		boolean bolVolit = volicDao.bolVolit(meno, priezvisko, heslo);
		if(bolVolit){

			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Upozornenie");
			alert.setHeaderText("Zamietnute");
			alert.setContentText("Volit mozete len raz");
			alert.showAndWait();
			menoTextField.clear();
			hesloTextField.clear();
		}else {
			if (celeMeno.length == 2 && admin == false) {
				if (isMenoHesloValid) {
					volicDao.aktualizujDochadzku(meno, priezvisko, heslo);
					openInstrukcieOkno();
					((Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow()).close();
				}
				if (!isMenoHesloValid && !heslo.equals("admin")) {
					menoTextField.setText("");
					hesloTextField.setText("");
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Upozornenie");
					alert.setHeaderText("Zadajte údaje znova");
					alert.setContentText("Napisali ste zle meno alebo cislo OP");

					alert.showAndWait();
				}
			} else if (admin == false) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Upozornenie");
				alert.setHeaderText("Zadajte meno znova");
				alert.setContentText("V tvare |Meno Priezvisko|");

				alert.showAndWait();

			}
		}
    }
    
    
    private void openInstrukcieOkno() {

		try {
			IntrukcieSceneController controller = new IntrukcieSceneController();
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("instrukcie.fxml"));
			loader.setController(controller);
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			File file = new File("C:\\Users\\macko\\IdeaProjects\\projektVolby\\sr.png");
			Image icon = new Image(file.toURI().toString());
			stage.getIcons().add(icon);
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("INSTRUKCIE");
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	private void openAdminlayout() {
		try {
			AdminLayoutSceneController controller = new AdminLayoutSceneController();
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("adminlayout.fxml"));
			loader.setController(controller);
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			File file = new File("C:\\Users\\macko\\IdeaProjects\\projektVolby\\sr.png");
			Image icon = new Image(file.toURI().toString());
			stage.getIcons().add(icon);
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("ADMIN");
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}