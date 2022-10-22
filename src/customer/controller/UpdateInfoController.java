package customer.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import customer.dao.CustomerHomeDAO;
import home.controller.HomePageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import vo.MemberVO;

public class UpdateInfoController implements Initializable{

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPwCheck;

    @FXML
    private TextField txtYear;

    @FXML
    private Label logo;

    @FXML
    private Pane upinfoPane;

    @FXML
    private TextField txtEmAdd;

    @FXML
    private PasswordField txtPassword;
    
    @FXML
    private ImageView profileImage;
    
    
    

    
    public static Pane targetPane;
    

    private CustomerHomeDAO dao = new CustomerHomeDAO();
    
    MemberVO logVO = HomePageController.session;

    String imagePath = logVO.getMem_propic(); //프로필사진 경로 , default 이미지값은 sample.jpg
    
    
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	targetPane = upinfoPane;
    	
    	MemberVO vo = new MemberVO();
    	MemberVO logVO = HomePageController.session;
    	try {
			vo = dao.getInfo(logVO.getMem_id());
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	txtId.setText(vo.getMem_id());
    	txtPassword.setText(vo.getMem_pass());
    	txtPwCheck.setText(vo.getMem_pass());
    	txtName.setText(vo.getMem_name());
    	txtYear.setText(vo.getMem_ihidnum());
    	txtPhoneNumber.setText(vo.getMem_mbp());
    	txtEmAdd.setText(vo.getMem_email());
    	
    	//프로필수정에서 내 프로필의 이미지를 불러옴.
    	File file = new File("upload/"+vo.getMem_propic());
		System.out.println(file+" || 프로필사진로드");
        Image image = new Image(file.toURI().toString());
        System.out.println(image+" || 이미지경로");
    	profileImage.setImage(image);
    	
    }
    
    @FXML
    void updatePicture(ActionEvent event) {
    	System.out.println("프로필사진등록 호출");
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Open File");
		setExtFilters(chooser);
		File file = chooser.showOpenDialog(new Stage());
		System.out.println(file + "\n // file 로 넘어온값");
		System.out.println(file.getPath() + "\n // getPath()로 넘어온값");
		System.out.println(file.getName() + "\n // getName()로 넘어온값");
		System.out.println(file.toURI().toString() + "\n // toURI().toString()로 넘어온값");
		
		imagePath = file.getName();//파일이름 저장
		System.out.println(imagePath +"\t || 변수에 저장되어있는 이미지이름");
		if (file != null) {
			try {
				BufferedImage bi = ImageIO.read(file); //이미지로딩
				//File outFile = new File("D:\\javaFileIo\\" +file.getName());
				//File outFile = new File("D:\\jspwork\\youngmanble\\upload\\"+file.getName());
				File outFile = new File("upload/"+file.getName());				
				System.out.println(outFile.getPath() + "\t || 프로필사진이 저장되어있는 위치");
				ImageIO.write(bi, "png", outFile);
				//이미지파일변경
				//File file2 = new File("D:\\jspwork\\youngmanble\\src\\upload\\"+file.getName());
				File file2 = new File("upload/"+file.getName());
		        Image image2 = new Image(file.toURI().toString());
		        profileImage.setImage(image2);
				
			} catch (IOException e) {				
				e.printStackTrace();
			}
			
			System.out.println("저장 끝 ");
		}
    }
    
    //이미지 필터
  	private void setExtFilters(FileChooser chooser){
  		chooser.getExtensionFilters().addAll(
  				new FileChooser.ExtensionFilter("All Images", "*.*"),
  				new FileChooser.ExtensionFilter("PNG", "*.png")
  				);
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

    
    
    
    @FXML
    void updateInfo(ActionEvent event) {
    	
    	
    	
    	if(txtPassword.getText().equals(txtPwCheck.getText())){
    		
    	
    	MemberVO vo = new MemberVO();
    	
    	vo.setMem_id(txtId.getText());
    	vo.setMem_pass(txtPassword.getText());
    	vo.setMem_name(txtName.getText());
    	vo.setMem_ihidnum(txtYear.getText());
    	vo.setMem_mbp(txtPhoneNumber.getText());
    	vo.setMem_email(txtEmAdd.getText());
    	vo.setMem_propic(imagePath);
    	try {
			dao.updateInfo(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("정보 수정");
		alert.setHeaderText("회원정보 수정 완료되었습니다.");
		
		alert.show();
    	
		targetPane.getChildren().clear();

		
		
    	} else {
    		
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
    		alert.setTitle("비밀번호 오류");
    		alert.setHeaderText("비밀번호란과 비밀번호 확인란의 내용이 같아야 합니다.");
    		
    		alert.show();
    		
    	}
    	
    	
    	
    	
    	
    }
    
    
    
    @FXML
    void cancel(ActionEvent event) {
    	targetPane.getChildren().clear();
    }
    
    
    
    @FXML
    void gotoUpdateInfo(ActionEvent event) {
    	
    }

   

}
