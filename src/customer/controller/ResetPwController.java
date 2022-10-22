package customer.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class ResetPwController implements Initializable{

    @FXML
    private Pane resetPwPane;

    @FXML
    private Button btnUpdateInfo;

    @FXML
    private Button btnResetPw;

    @FXML
    private Button btnDrop;

    @FXML
    private TextArea MyInfoArea;
    
    public static Pane targetPane;
    
    
    
    
    

    @FXML
    void updateInfo(ActionEvent event) {
    	targetPane.getChildren().clear();
    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
    	try {
			Pane root = FXMLLoader.load(loader.getResource("fxml/UpdateInfoCont.fxml"));
			targetPane.getChildren().add(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    
    
    
    
    @FXML
    void resetPw(ActionEvent event) {

    }

    
    
    
    
    @FXML
    void dropMember(ActionEvent event) {
    	targetPane.getChildren().clear();
    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
    	try {
			Pane root = FXMLLoader.load(loader.getResource("fxml/DropAccountCont.fxml"));
			targetPane.getChildren().add(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    
    
    
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		targetPane = resetPwPane;
	}

}
