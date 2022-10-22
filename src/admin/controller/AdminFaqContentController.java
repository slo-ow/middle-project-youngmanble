package admin.controller;

import java.io.IOException;
import java.sql.SQLException;

import admin.dao.FaqDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import vo.FaqVO;

public class AdminFaqContentController {


    @FXML
    private Pane FaqContentPane;
	
    @FXML
    private Label laPre;

    @FXML
    private TextField tfTitle;

    @FXML
    private Button btnDelete;

    @FXML
    private TextArea taContent;

    @FXML
    private Button btnRewrite;

    @FXML
    private Button btnList;

    @FXML
    private TextField tfDate;

    @FXML
    private Label laNext;
    
    @FXML
    private Label laFaqNum;
    
    @FXML
    private Label laFaqWriter;
    
    private FaqDAO dao = new FaqDAO();
    //아버지 컨트롤러(상위 컨트롤러)
    private AdminFaqController parent;
    
    public void setParent(AdminFaqController adminFaqController){
    	this.parent = adminFaqController;
    }

    @FXML
    void gotoPre(MouseEvent event) {
    	try {
			FaqVO vo = dao.previousContents(laFaqNum.getText());
			
			laFaqNum.setText(vo.getFaqNum());
			laFaqWriter.setText(vo.getFaqMemid());
			tfDate.setText(vo.getFaqDate());
			tfTitle.setText(vo.getFaqSubject());
			taContent.setText(vo.getFaqContent());
			
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void gotoNext(MouseEvent event) {
    	try {
			FaqVO vo = dao.nextContents(laFaqNum.getText());
			
			laFaqNum.setText(vo.getFaqNum());
			laFaqWriter.setText(vo.getFaqMemid());
			tfDate.setText(vo.getFaqDate());
			tfTitle.setText(vo.getFaqSubject());
			taContent.setText(vo.getFaqContent());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    @FXML
    void delete(ActionEvent event) {
    	parent.p_delete(null);
    	
    	
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("글 삭제");
		alert.setHeaderText("게시글이 삭제되었습니다.");
		
		alert.show();
    	
    	parent.setTableData(null, null);
    	
    	Stage stage = (Stage) laFaqNum.getScene().getWindow();
    	stage.close();
    	
    }

    @FXML
    void showlist(ActionEvent event) {
    	
    	
    	parent.setTableData(null, null);
    	
    	Stage stage = (Stage) laFaqNum.getScene().getWindow();
    	stage.close();
    	
    	/*
    	FaqContentPane.getChildren().clear();
    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try {
			Pane root = FXMLLoader.load(loader.getResource("fxml/AdminFaq.fxml"));
			FaqContentPane.getChildren().add(root);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
    }

    @FXML
    void rewirte(ActionEvent event) {
    	FaqVO vo = new FaqVO();
    	
    	vo.setFaqNum(laFaqNum.getText());
    	vo.setFaqSubject(tfTitle.getText());
    	vo.setFaqContent(taContent.getText());
    	
    	try {
			dao.updateBoard(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	parent.setTableData(null, null);
    	
    	Stage stage = (Stage) laFaqNum.getScene().getWindow();
    	stage.close();
    	
    }
    
    public void setBoardData(String FaqNum){
    	try {
			FaqVO vo = dao.selectBoard(FaqNum);
			
			laFaqNum.setText(vo.getFaqNum());
			tfTitle.setText(vo.getFaqSubject());
			laFaqWriter.setText(vo.getFaqMemid());
			taContent.setText(vo.getFaqContent());
			tfDate.setText(vo.getFaqDate());
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

}
