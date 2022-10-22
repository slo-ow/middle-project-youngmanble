package admin.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import chat.Client_board;
import chat.Client_tetris;
import chat.Server_board;
import chat.Server_tetris;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AdminUpadteController implements Initializable {
	public static Pane targetPane;

	@FXML
    private Button btnTetServ;

    @FXML
    private Button btnTetClient;

    @FXML
    private Button btnMabServ;
    
    @FXML
    private Label labTit;
    
    @FXML
    private Pane UpPane;

    @FXML
    private Button btnMabClient;


	@FXML
	void MabServ(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Update Dialog");
		alert.setHeaderText("마블 서버를 오픈합니다.");
		alert.show();
		
		 Server_board.main(null);
	}


	@FXML
	void MabClient(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Update Dialog");
		alert.setHeaderText("마블 클라이언트를 오픈합니다.");
		alert.show();

		Client_board.main(null);

	}
    
	@FXML
	void TetServ(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Update Dialog");
		alert.setHeaderText("테트리스 서버를 오픈합니다.");
		alert.show();
		
		 Server_tetris.main(null);
	}


	@FXML
	void TetClient(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Update Dialog");
		alert.setHeaderText("테트리스 클라이언트를 오픈합니다.");
		alert.show();
		
		 Client_tetris.main(null);
	}


	/*targetPane.getChildren().clear();
	
	ClassLoader loader = Thread.currentThread().getContextClassLoader();
	try {
	Pane root = FXMLLoader.load(loader.getResource("boardEx/Board.fxml"));
	targetPane.getChildren().add(root);
	} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	*/

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		targetPane = UpPane;
	}

}
