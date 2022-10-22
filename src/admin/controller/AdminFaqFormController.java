package admin.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import admin.dao.FaqDAO;
import customer.controller.FreeBoardController;
import customer.dao.FreeBoardDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import vo.FaqVO;

public class AdminFaqFormController implements Initializable{

    @FXML
    private TextField txFaqtitle;

    @FXML
    private Button btnFaqWrite;

    @FXML
    private Button btnFaqCancel;

    @FXML
    private TextArea taFaqContent;

    @FXML
    private Pane FaqFormPane;

    private FaqDAO dao = new FaqDAO();
    
    
    @FXML
    void wirte(ActionEvent event) {
    	//sql문 작성하기 !!!
    	FaqVO vo = new FaqVO();
    	
    	vo.setFaqSubject(txFaqtitle.getText());
    	vo.setFaqContent(taFaqContent.getText());
    	
    	try {
			dao.insertBoard(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("FAQ Dialog");
    	alert.setHeaderText("FAQ게시판에 글 등록되었습니다.");
    	
    	alert.show();
    	
    	FaqFormPane.getChildren().clear();
    	
    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
    	try {
			Pane root = FXMLLoader.load(loader.getResource("fxml/AdminFaq.fxml"));
			FaqFormPane.getChildren().add(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }

    @FXML
    void cancel(ActionEvent event) {
    	FaqFormPane.getChildren().clear(); 
    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
     	try {
 			Pane root = FXMLLoader.load(loader.getResource("fxml/AdminFaq.fxml"));
 			FaqFormPane.getChildren().add(root);
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
    }
    
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}




}
