package home.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import chat.Client_board;
import chat.Client_tetris;

import customer.controller.CustomerHomeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.HomePageApp;
import vo.MemberVO;

public class MainPageController implements Initializable {

	@FXML
	private ImageView infoImg;

	@FXML
	private Button logoutBtn;

	@FXML
	private Button mabBtn;

	@FXML
	private ImageView logoutimg;

	@FXML
	private Button tetBtn;

	@FXML
	private ImageView mainBacking;

	@FXML
	private ImageView mabimg;

	@FXML
	private Label label_info;

	@FXML
	private Button gotoAccountBtn;

	@FXML
	private ImageView tetimg;

	@FXML
	private Label label_center;

	@FXML
	private Pane mainPane;

	@FXML
	private Label youngmanble;

	public static Pane targetPane;

	@FXML
	void gotoCenter(MouseEvent event) {
		targetPane.getChildren().clear();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try {
			Pane root = FXMLLoader.load(loader.getResource("fxml/CustomerHome.fxml"));
			targetPane.getChildren().add(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void tet(ActionEvent e) {
		/*
		Stage stage = (Stage)targetPane.getScene().getWindow();
		stage.close();
		*/
		if (e.getSource() == tetBtn) {
			Client_tetris.main(null);
		}
	}

	
	Stage marblestage;
	
	@FXML
	void mab(ActionEvent e) {
		/*
		targetPane.getChildren().clear();
		
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try {
			Pane root = FXMLLoader.load(loader.getResource("boardEx/Board.fxml"));
			targetPane.getChildren().add(root);
		} catch (IOException e1) {
			
		}
		*/
		marblestage = new Stage();
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/boardEx/Board.fxml"));
    	
    	Pane pane;
		try {
			pane = loader.load();
			Scene sc = new Scene(pane);
			
			marblestage.setScene(sc);
			marblestage.initModality(Modality.APPLICATION_MODAL);
			marblestage.show();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		
		
	}

	@FXML
	void gotoAccount(ActionEvent event) {
		targetPane.getChildren().clear();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try {
			Pane root = FXMLLoader.load(loader.getResource("fxml/CustomerHome.fxml"));
			targetPane.getChildren().add(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	void logout(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("LogOut Dialog");
		alert.setHeaderText("로그아웃 됩니다.");

		alert.show();

		targetPane.getChildren().clear();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try {
			Pane root = FXMLLoader.load(loader.getResource("fxml/Homepage.fxml"));
			targetPane.getChildren().add(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		MemberVO logVO = HomePageController.session;
		targetPane = mainPane;		
		label_info.setText(logVO.getMem_id() + " 님");
	}

}
