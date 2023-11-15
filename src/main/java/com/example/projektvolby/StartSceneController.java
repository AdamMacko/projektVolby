package com.example.projektvolby;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StartSceneController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Vitajte vo volbach!");
    }
}