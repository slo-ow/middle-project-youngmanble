package customer.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import customer.dao.CustomerHomeDAO;
import home.controller.HomePageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import vo.MemberVO;

public class DropAccountController implements Initializable{

    @FXML
    private Pane dropPane;

    @FXML
    private Label label_logo;
    
    
    public static Pane targetPane;
    
    
    Pane bigPane = CustomerHomeController.mainTargetPane;
    
    
    private CustomerHomeDAO dao = new CustomerHomeDAO();
    
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	targetPane = dropPane;
    }
    
    
    
    
    
    
    
    @FXML
    void gotoUpdateInfo(ActionEvent event) {
    	targetPane.getChildren().clear();
    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
    	try {
			Pane root = FXMLLoader.load(loader.getResource("fxml/UpdateInfoCont.fxml"));
			targetPane.getChildren().add(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    
    
    
    
    @FXML
    void dropMember(ActionEvent event) {
    	
    	// 로그인유저VO 반드시 넣어 수정해야한다!!
    	
    	MemberVO logVO = HomePageController.session;
    	
    	try {
			dao.deleteAccount(logVO.getMem_id());
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	
    	
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("회원탈퇴");
		alert.setHeaderText("회원탈퇴처리 되었습니다.");
		
		alert.show();
    	
    	
    	
    	bigPane.getChildren().clear();
    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
    	try {
			Pane root = FXMLLoader.load(loader.getResource("fxml/HomePage.fxml"));
			bigPane.getChildren().add(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }

    
    
    
    
    
    
    @FXML
    void cancel(ActionEvent event) {
    	targetPane.getChildren().clear();
    }

    
    
    
    
    
    

}
