package customer.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import customer.addtech.CustomerMail;
import home.controller.HomePageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import vo.MemberVO;





public class SendEmailController implements Initializable{

    @FXML
    private TextArea txtContent;

    @FXML
    private TextField txtSubject;

    @FXML
    private Label label_logo;
    
    
    
    
    
    
    
    private EnquireController parentController;
    
    
    public void setParentController(EnquireController enquireController) {
    	this.parentController = enquireController;
    }
    
    

    @FXML
    void sendEmail(ActionEvent event) {
    	CustomerMail mail = new CustomerMail();
    	
    	MemberVO logVO = HomePageController.session;
    	
    	mail.mysubject = txtSubject.getText();
    	mail.mycontents = txtContent.getText() + "\n\n\n\n\n\n\n" + "고객님 E-mail주소 : " + logVO.getMem_email();
    	if((mail.mysubject.trim().equals(""))||(txtContent.getText().trim().equals(""))){ //내용이 비어있다면 발송불가능
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setTitle("메일전송 실패");
    		alert.setHeaderText("제목과 내용을 제대로 입력해주세요.");
    		alert.show();
    	}else{
    		try {
    			mail.sendCustomerEmail();
    		} catch (AddressException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (MessagingException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
    		
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
    		alert.setTitle("메일전송");
    		alert.setHeaderText("메일이 전송 되었습니다.");
    		
    		alert.show();
    	}
    	
    	Stage stage = (Stage) label_logo.getScene().getWindow();
		stage.close();
    }

    
    
    
    @FXML
    void cancel(ActionEvent event) {
    	Stage stage = (Stage) label_logo.getScene().getWindow();
		stage.close();
    }

    
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
