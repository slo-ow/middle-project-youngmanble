package customer.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EnquireController implements Initializable{

    @FXML
    private Pane enquire;

    @FXML
    private Button btnChat;

    @FXML
    private Button btnEmail;

    @FXML
    private ImageView backImage;

    
    private EnquireController enquireController = this;
    
    
    
    @FXML
    void Chatting(ActionEvent event) {
    	Stage stage = new Stage();
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ChatBone.fxml"));
    	try {
			Pane pane = (Pane)loader.load();
		
			ChatBoneController chatBoneController = (ChatBoneController)loader.getController();
			chatBoneController.setParentController(enquireController);

			Scene scene = new Scene(pane, 570, 550);
			
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			
			stage.show();
			
    	
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }

    
    
    
    
    
    
    
    
    @FXML
    void sendEmail(ActionEvent event) {
    	Stage stage = new Stage();
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SendEmail.fxml"));
    	try {
			Pane pane = (Pane)loader.load();
		
			SendEmailController sendEmailController = (SendEmailController)loader.getController();
			sendEmailController.setParentController(enquireController);

			Scene scene = new Scene(pane, 450, 450);
			
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			
			stage.show();
			
    	
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
    	
    	
    }

    
    
    
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

}
