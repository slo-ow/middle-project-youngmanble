package customer.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import customer.dao.CustomerHomeDAO;
import home.controller.HomePageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.stage.Modality;
import javafx.stage.Stage;
import vo.MemberVO;

public class CustomerHomeController implements Initializable{

    @FXML
    private Button btnNotice;

    @FXML
    private Button btnEnquire;

    @FXML
    private Button btnMyAccount;

    @FXML
    private ImageView banner;

    @FXML
    private Button btnFAQ;

    @FXML
    private Button btnFreeboard;

    @FXML
    private ImageView backImage;

    @FXML
    private Label label_MyName;

    @FXML
    private Button btnCenter;

    @FXML
    private Button logout;

    @FXML
    private Button btnAccuse;

    @FXML
    private Label logo;
    
    @FXML
    private Pane paneSpace;
    
    @FXML
    private Pane mainPane;
    
  
    
    public static Pane targetPane;
    
    public static Pane mainTargetPane;
    
    
    
    
    

    private CustomerHomeDAO dao = new CustomerHomeDAO();
    
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	// TODO Auto-generated method stub
    	targetPane = paneSpace;
    	mainTargetPane = mainPane;
    	
    	MemberVO vo = new MemberVO();
    	MemberVO logVO = HomePageController.session;
		try {
			vo = dao.getInfo(logVO.getMem_id());
		} catch (SQLException e) {
			e.printStackTrace();
		} 
    	
    	label_MyName.setText(vo.getMem_id() + " 님");
    	
    	
    	
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
    void gotoCenter(ActionEvent event) {
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
    void gotoNotice(ActionEvent event) {
    	targetPane.getChildren().clear();
    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
    	try {
			Pane root = FXMLLoader.load(loader.getResource("fxml/NoticeCont.fxml"));
			targetPane.getChildren().add(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    @FXML
    void gotoFAQ(ActionEvent event) {
    	targetPane.getChildren().clear();
    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
    	try {
			Pane root = FXMLLoader.load(loader.getResource("fxml/FAQBoardCont.fxml"));
			targetPane.getChildren().add(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    
    @FXML
    void gotoFreeboard(ActionEvent event) {
    	targetPane.getChildren().clear();
    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
    	try {
			Pane root = FXMLLoader.load(loader.getResource("fxml/FreeBoardCont.fxml"));
			targetPane.getChildren().add(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    @FXML
    void gotoAccuse(ActionEvent event) {
    	targetPane.getChildren().clear();
    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
    	try {
			Pane root = FXMLLoader.load(loader.getResource("fxml/AccuseCont.fxml"));
			targetPane.getChildren().add(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    
    @FXML
    void gotoEnquire(ActionEvent event) {
    	targetPane.getChildren().clear();
    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
    	try {
			Pane root = FXMLLoader.load(loader.getResource("fxml/EnquireCont.fxml"));
			targetPane.getChildren().add(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void executeLogout(ActionEvent event) {
    	
    	
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("로그아웃");
		alert.setHeaderText("로그아웃 하였습니다.");
		
		alert.show();
    	
    	
    	
    	
    	mainPane.getChildren().clear();
    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
    	try {
			Pane root = FXMLLoader.load(loader.getResource("fxml/HomePage.fxml"));
			mainPane.getChildren().add(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    
    
    
    @FXML
    void viewMyAccount(ActionEvent event) {
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
    void gotoMain(MouseEvent event) {
    	mainTargetPane.getChildren().clear();
    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
    	try {
			Pane root = FXMLLoader.load(loader.getResource("fxml/MainPage.fxml"));
			mainTargetPane.getChildren().add(root);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    double x;
    double y;
    Stage mapstage;
    
    @FXML
    void gotoMap(ActionEvent event) {
    	mapstage = new Stage();
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Map.fxml"));
    	
    	Pane pane;
		try {
			pane = loader.load();
			
			Scene sc = new Scene(pane);
			mapstage.setScene(sc);
			mapstage.setX(x+1240);
			mapstage.setY(y+100);
			mapstage.initModality(Modality.APPLICATION_MODAL);
			
			mapstage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
 
    }
    
    
  
    

}
