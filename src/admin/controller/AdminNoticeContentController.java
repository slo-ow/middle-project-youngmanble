package admin.controller;

import java.io.IOException;
import java.sql.SQLException;

import admin.dao.AdminNoticeDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import vo.NoticeVO;

public class AdminNoticeContentController {

    @FXML
    private Label laUp;

    @FXML
    private Label laNoticeNum;

    @FXML
    private TextField tfTitle;

    @FXML
    private Label laNotiWriter;

    @FXML
    private Button btnDelete;

    @FXML
    private Label laDown;

    @FXML
    private TextArea taContent;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnList;

    @FXML
    private TextField tfDate;
    
    @FXML
    private Pane notiContentPane;

    
    
    
    private AdminNoticeDAO dao = new AdminNoticeDAO();
    
    private AdminNoticeController parent;
    
    public void setParent(AdminNoticeController adminNoticeController) {
    	this.parent = adminNoticeController;
    }
    
    
    
    
    
    @FXML
    void up(MouseEvent event) {
    	try {
			NoticeVO vo = dao.nextContents(laNoticeNum.getText());
			
			laNoticeNum.setText(vo.getNtcNum());
			laNotiWriter.setText(vo.getNtcMemid());
			tfDate.setText(vo.getNtcDate());
			tfTitle.setText(vo.getNtcSubject());
			taContent.setText(vo.getNtcContent());
			
		} catch (SQLException e) {
			/*
			Alert al = new Alert(AlertType.INFORMATION);
			
			al.setTitle("Notice Dialog");
	    	al.setHeaderText("더 이상 글이 없습니다.");
	    	
	    	al.show();
			*/
	    	e.printStackTrace();
		}
    }

    @FXML
    void down(MouseEvent event) {
    	try {
			NoticeVO vo = dao.previousContents(laNoticeNum.getText());
			
			laNoticeNum.setText(vo.getNtcNum());
			laNotiWriter.setText(vo.getNtcMemid());
			tfDate.setText(vo.getNtcDate());
			tfTitle.setText(vo.getNtcSubject());
			taContent.setText(vo.getNtcContent());
			
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }

    @FXML
    void updateNoti(ActionEvent event) {
    	NoticeVO vo = new NoticeVO();
    	vo.setNtcNum(laNoticeNum.getText());
    	vo.setNtcSubject(tfTitle.getText());
    	vo.setNtcMemid(laNotiWriter.getText());
    	vo.setNtcContent(taContent.getText());
    	try {
			dao.updateBoard(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	parent.setTableData(null, null);
    	
    	
    	Stage stage = (Stage) laNoticeNum.getScene().getWindow();
    	stage.close();
    	
    }

    @FXML
    void deleteNoti(ActionEvent event) {
    	parent.delete(null);
    	
    	
    	parent.setTableData(null, null);
    	
    	Stage stage = (Stage) laNoticeNum.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void showNoti(ActionEvent event) {
    	
    	
    	parent.setTableData(null, null);
    	
    	
    	Stage stage = (Stage) laNoticeNum.getScene().getWindow();
    	stage.close();
    	/*
    	ClassLoader loader =Thread.currentThread().getContextClassLoader();
    	
    	try {
			Pane root = FXMLLoader.load(loader.getResource("fxml/AdminNotice.fxml"));
			notiContentPane.getChildren().add(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	*/
    }
    

	public void setBoardData(String ntcNum) {
		try {
			NoticeVO vo = dao.selectBoard(ntcNum);
			
			laNoticeNum.setText(vo.getNtcNum());
			tfTitle.setText(vo.getNtcSubject());
			laNotiWriter.setText(vo.getNtcMemid());
			taContent.setText(vo.getNtcContent());
			tfDate.setText(vo.getNtcDate());
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
