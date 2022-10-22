package admin.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import admin.dao.AdminNoticeDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import vo.NoticeVO;

public class AdminNoticeFormController implements Initializable{

    @FXML
    private TextField tfNotiTitle;

    @FXML
    private Button btnNotiCancel;

    @FXML
    private TextArea taNotiContent;

    @FXML
    private Button btnNotiCommit;
    
    @FXML
    private Pane NotiFormPane;
    
    private AdminNoticeDAO dao = new AdminNoticeDAO();
    @FXML
    void NotiCommit(ActionEvent event) {
    	NoticeVO vo = new NoticeVO();
    	
    	vo.setNtcSubject(tfNotiTitle.getText());
    	vo.setNtcContent(taNotiContent.getText());
    	
    	try {
			dao.insertBoard(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Notice Dialog");
    	alert.setHeaderText("공지사항 글 등록 되었습니다.");
    	
    	alert.show();
    	
    	NotiFormPane.getChildren().clear();
    	
    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
    	
    	try {
			Pane root = FXMLLoader.load(loader.getResource("fxml/AdminNotice.fxml"));
			NotiFormPane.getChildren().add(root);
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	
    	
    }

    @FXML
    void Cancel(ActionEvent event) {
    	NotiFormPane.getChildren().clear();
    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
    	
    	try {
			Pane root = FXMLLoader.load(loader.getResource("fxml/AdminNotice.fxml"));
			NotiFormPane.getChildren().add(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}

   