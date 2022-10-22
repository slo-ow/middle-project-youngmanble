package customer.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import customer.dao.FreeBoardDAO;
import home.controller.HomePageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import vo.FreeBoardVO;
import vo.MemberVO;

public class FreeContentController implements Initializable{

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
    
    private FreeBoardDAO dao = new FreeBoardDAO();
    
    private FreeBoardController parentController;
    
    
    
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	// TODO Auto-generated method stub
    	
    }
    
    
    public void setParentController(FreeBoardController freeBoardController) {
    	this.parentController = freeBoardController;
    }
    
    
    
    
    
    
    @FXML
    void click_save(ActionEvent event) {
    	
    	MemberVO logVO = HomePageController.session;
    		
    		
    	FreeBoardVO vo = new FreeBoardVO();
    	
    	
    	vo.setFbNum(label_boardId.getText());
    	vo.setFbSubject(textField_boardTitle.getText());
    	vo.setFbMemid(logVO.getMem_id());
    	vo.setFbContent(textArea_boardContent.getText());
    	
		try {
				if(label_boardId.getText().equals("")) {
					dao.insertBoard(vo);
				} else if(logVO.getMem_id().equals(label_boardWriter.getText())){
					dao.updateBoard(vo);
				} else {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
		    		alert.setTitle("수정 오류");
		    		alert.setHeaderText("작성자만이 글을 수정 가능합니다.");
		    		
		    		alert.show();
				}
			} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
	    	
			parentController.search(null);
			
			Stage stage = (Stage) label_boardDate.getScene().getWindow();
			stage.close();
    		
		
    }
    
    
    
    
    
    
    
    
    
    public void setBoardData(String fbNum) {
    	try {
			FreeBoardVO vo = dao.selectBoard(fbNum);
			
			
			label_boardId.setText(vo.getFbNum());
			textField_boardTitle.setText(vo.getFbSubject());
			label_boardWriter.setText(vo.getFbMemid());
			label_boardDate.setText(vo.getFbDate());
			textArea_boardContent.setText(vo.getFbContent());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    
    
    
    
    
    
    
    @FXML
    void click_delete(ActionEvent event) {
    	
    	MemberVO logVO = HomePageController.session;
    	
    	if(logVO.getMem_id().equals(label_boardWriter.getText())){
	    	parentController.click_delete(null);
    	} else {	
	    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("삭제 오류");
			alert.setHeaderText("작성자만이 글을 삭제 가능합니다.");
			
			alert.show();
    	}
    	
    	Stage stage = (Stage) label_boardDate.getScene().getWindow();
    	stage.close();
    	
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
			
    		FreeBoardVO vo = dao.nextContents(label_boardId.getText());
			
			label_boardId.setText(vo.getFbNum());
			textField_boardTitle.setText(vo.getFbSubject());
			label_boardWriter.setText(vo.getFbMemid());
			label_boardDate.setText(vo.getFbDate());
			textArea_boardContent.setText(vo.getFbContent());
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    
    
    
    
    
    
    
    
    @FXML
    void toPrevious(ActionEvent event) {
    	try {
			
    		FreeBoardVO vo = dao.previousContents(label_boardId.getText());
			
			label_boardId.setText(vo.getFbNum());
			textField_boardTitle.setText(vo.getFbSubject());
			label_boardWriter.setText(vo.getFbMemid());
			label_boardDate.setText(vo.getFbDate());
			textArea_boardContent.setText(vo.getFbContent());
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }




}
