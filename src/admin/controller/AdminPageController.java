package admin.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AdminPageController implements Initializable{

    @FXML
    private Button btnNotice;

    @FXML
    private Button btnStats;

    @FXML
    private Button btnLogout;

    @FXML
    private Label laLogo;

    @FXML
    private Button btnInquiry;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnBlackList;

    @FXML
    private Button btnReport;

    @FXML
    private Button btnFAQ;

    @FXML
    private Button btnFree;

    @FXML
    private Pane adminPane;
    public static Pane targetPane;

    @FXML
    private Button btnUserCheck;

    @FXML
    void gotoAdmin(MouseEvent event) {
    	targetPane.getChildren().clear();
    }

    @FXML
    void gotoNotice(ActionEvent event) {
    	targetPane.getChildren().clear();
    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
    	try {
			Pane root = FXMLLoader.load(loader.getResource("fxml/AdminNotice.fxml"));
			targetPane.getChildren().add(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    

    @FXML
    void gotoFAQ(ActionEvent event) {
    	targetPane.getChildren().clear();
    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
    	try {
			Pane root = FXMLLoader.load(loader.getResource("fxml/AdminFaq.fxml"));
			targetPane.getChildren().add(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    }

    @FXML
    void gotoFree(ActionEvent event) {
    	targetPane.getChildren().clear();
    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
    	try {
			Pane root = FXMLLoader.load(loader.getResource("fxml/AdminFree.fxml"));
			targetPane.getChildren().add(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void gotoBlackList(ActionEvent event) {
    	targetPane.getChildren().clear();
    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
    	try {
			Pane root = FXMLLoader.load(loader.getResource("fxml/AdminBlacklist.fxml"));
			targetPane.getChildren().add(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void gotoInquiry(ActionEvent event) {
    	targetPane.getChildren().clear();
    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
    	try {
			Pane root = FXMLLoader.load(loader.getResource("fxml/AdminInquiry.fxml"));
			targetPane.getChildren().add(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    

    @FXML
    void gotoReport(ActionEvent event) {
    	targetPane.getChildren().clear();
    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
    	try {
			Pane root = FXMLLoader.load(loader.getResource("fxml/AdminReport.fxml"));
			targetPane.getChildren().add(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    

    @FXML
    void gotoUserCheck(ActionEvent event) {
    	targetPane.getChildren().clear();
    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
    	try {
			Pane root = FXMLLoader.load(loader.getResource("fxml/AdminUserCheck.fxml"));
			targetPane.getChildren().add(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void gotoStats(ActionEvent event) {
    	targetPane.getChildren().clear();
    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
    	try {
			Pane root = FXMLLoader.load(loader.getResource("fxml/AdminStats.fxml"));
			targetPane.getChildren().add(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void gotoUpdate(ActionEvent event) {
    	
    	targetPane.getChildren().clear();
    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
    	try {
			Pane root = FXMLLoader.load(loader.getResource("fxml/AdminUpdate.fxml"));
			targetPane.getChildren().add(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void gotoLogout(ActionEvent event) {
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
    	alert.setTitle("LogOut Dialog");
    	alert.setHeaderText("관리자 로그아웃 됩니다.");
    	
    	alert.show();
    	
    	Stage stage = (Stage) laLogo.getScene().getWindow();
    	stage.close();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		targetPane = adminPane;
		
	}



}
