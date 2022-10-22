package admin.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import admin.dao.AdminFreeDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import vo.FreeBoardVO;

public class AdminFreeContentController implements Initializable{

    @FXML
    private Label laWriter;

    @FXML
    private Label laUp;

    @FXML
    private Button btnDelete;

    @FXML
    private Label laDown;

    @FXML
    private Button btnFreeList;

    @FXML
    private TextArea taContent;

    @FXML
    private TextField txTitle;

    @FXML
    private Label laWriteDate;
    
    @FXML
    private Label laNum;

    
    private AdminFreeDAO dao = new AdminFreeDAO();
    
    private AdminFreeController parent;
    
    public void setParentController(AdminFreeController adminFreeController) {
    	this.parent = adminFreeController;
    }
    
    
    @FXML
    void up(MouseEvent event) {
    	try {
			FreeBoardVO vo = dao.nextContents(laNum.getText());
			
			laNum.setText(vo.getFbNum());
			laWriteDate.setText(vo.getFbDate());
			laWriter.setText(vo.getFbMemid());
			txTitle.setText(vo.getFbSubject());
			taContent.setText(vo.getFbContent());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void down(MouseEvent event) {
    	try {
			FreeBoardVO vo = dao.previousContents(laNum.getText());
			
			laNum.setText(vo.getFbNum());
			laWriteDate.setText(vo.getFbDate());
			laWriter.setText(vo.getFbMemid());
			txTitle.setText(vo.getFbSubject());
			taContent.setText(vo.getFbContent());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	
    }

    @FXML
    void delete(ActionEvent event) {
    	try {
			dao.deleteBoard(laNum.getText());
			
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("글 삭제");
			alert.setHeaderText("게시글이 삭제되었습니다.");
			
			alert.show();
			
			
			parent.setTableData(null, null);
			
			
			Stage stage = (Stage) laNum.getScene().getWindow();
	    	stage.close();
	    	
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void freelist(ActionEvent event) {
    	
    	parent.setTableData(null, null);
    	
    	Stage stage = (Stage) laNum.getScene().getWindow();
    	stage.close();
    }
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	public void setBoardData(String fbNum) {
		try {
			FreeBoardVO vo = dao.selectBoard(fbNum);

			laNum.setText(vo.getFbNum());
			txTitle.setText(vo.getFbSubject());
			laWriter.setText(vo.getFbMemid());
			laWriteDate.setText(vo.getFbDate());
			taContent.setText(vo.getFbContent());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
