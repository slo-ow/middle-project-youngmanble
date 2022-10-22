package home.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FindIdAlertController {
	
	@FXML
	private Button closeBtn;
	
    @FXML
    void responseOK(ActionEvent event) {
    	Stage stage = (Stage) closeBtn.getScene().getWindow();
		stage.close();
    }


}
