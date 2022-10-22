package customer.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import customer.dao.FAQBoardDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import vo.FaqVO;

public class FAQContentController implements Initializable{

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
    
    
    
    private FAQBoardDAO dao = new FAQBoardDAO();
    
    private FAQBoardController parentController;
    
    
    
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	// TODO Auto-generated method stub
    	
    }
    
    
    
   
    public void setParentController(FAQBoardController faqBoardController) {
    	this.parentController = faqBoardController;
    }
    
    
    
    
    public void setBoardData(String faqNum) {
    	try {
			FaqVO vo = dao.selectBoard(faqNum);
			label_boardId.setText(vo.getFaqNum());
			textField_boardTitle.setText(vo.getFaqSubject());
			label_boardWriter.setText(vo.getFaqMemid());
			label_boardDate.setText(vo.getFaqDate());
			textArea_boardContent.setText(vo.getFaqContent());
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
			FaqVO vo = dao.nextContents(label_boardId.getText());
			
			label_boardId.setText(vo.getFaqNum());
			textField_boardTitle.setText(vo.getFaqSubject());
			label_boardWriter.setText(vo.getFaqMemid());
			label_boardDate.setText(vo.getFaqDate());
			textArea_boardContent.setText(vo.getFaqContent());
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }

    
    
    
    @FXML
    void toPrevious(ActionEvent event) {
    	try {
			FaqVO vo = dao.previousContents(label_boardId.getText());
			
			label_boardId.setText(vo.getFaqNum());
			textField_boardTitle.setText(vo.getFaqSubject());
			label_boardWriter.setText(vo.getFaqMemid());
			label_boardDate.setText(vo.getFaqDate());
			textArea_boardContent.setText(vo.getFaqContent());
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

    }



}
