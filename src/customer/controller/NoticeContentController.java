package customer.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import customer.dao.NoticeDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import vo.NoticeVO;

public class NoticeContentController implements Initializable{

    @FXML
    private Label label_boardId;

    @FXML
    private TextArea textArea_boardContent;

    @FXML
    private Label label_boardWriter;

    @FXML
    private Label label_boardDate;

    @FXML
    private TextField textField_boardTitle;
    
    
    private NoticeDAO dao = new NoticeDAO();
    
    private NoticeBoardController parentController;
    
    
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	// TODO Auto-generated method stub
    	
    }

    
    public void setParentController(NoticeBoardController noticeBoardController) {
    	this.parentController = noticeBoardController;
    }
    
    
    
    
    
    
    
    public void setBoardData(String ntcNum) {
    	try {
			NoticeVO vo = dao.selectBoard(ntcNum);
			label_boardId.setText(vo.getNtcNum());
			textField_boardTitle.setText(vo.getNtcSubject());
			label_boardWriter.setText(vo.getNtcMemid());
			label_boardDate.setText(vo.getNtcDate());
			textArea_boardContent.setText(vo.getNtcContent());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    
    
   
    
    
    
    @FXML
    void toList(ActionEvent event) {
    	
    	parentController.search(null);
    	
    	Stage stage = (Stage) label_boardDate.getScene().getWindow();
    	stage.close();
    }
    
    
    
    
    
    
    
    
    @FXML
    void toNext(ActionEvent event) {
    	try {
			NoticeVO vo = dao.nextContents(label_boardId.getText());
			
			label_boardId.setText(vo.getNtcNum());
			textField_boardTitle.setText(vo.getNtcSubject());
			label_boardWriter.setText(vo.getNtcMemid());
			label_boardDate.setText(vo.getNtcDate());
			textArea_boardContent.setText(vo.getNtcContent());
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	
    }

    
    
    
    
    
    
    
    @FXML
    void toPrevious(ActionEvent event) {
    	try {
			NoticeVO vo = dao.previousContents(label_boardId.getText());
			
			label_boardId.setText(vo.getNtcNum());
			textField_boardTitle.setText(vo.getNtcSubject());
			label_boardWriter.setText(vo.getNtcMemid());
			label_boardDate.setText(vo.getNtcDate());
			textArea_boardContent.setText(vo.getNtcContent());
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	
    }


    
    
    
    
    
    
    

}
