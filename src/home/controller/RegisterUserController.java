package home.controller;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import home.service.HService;
import home.service.HServiceImpl;
import home.sms.SendSMS;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import vo.MemberVO;

/**
 * @author 문성철
 * @version 1.0 
 * 회원가입 처리 컨트롤러
 * */
public class RegisterUserController implements Initializable{
	MemberVO vo = new MemberVO();
	HService service = HServiceImpl.getInstance();
	Alert alert = new Alert(Alert.AlertType.INFORMATION);
	String checkNum; // 인증번호를 임시로 담아둘 공간
	String checkNum2; // 최종 회원가입시 인증번호비교할 변수
	int getId; // 유효성검사를마친 결과값을 담아둔다
	String imagePath = "sample.jpg"; //프로필사진 경로 , default 이미지값은 sample.jpg
	int idCheck = 0; //아이디 중복체크를 안했다면 가입이 불가능함.
	String defaultId; //아이디 변경여부를 확인함.
	String defaultPw; //비밀번호 변경여부를 확인.
	
	public static Pane targetPane;
	
	@FXML
	private TextField txtJoinpass;

	@FXML
	private Button addbtn;

	@FXML
	private ImageView memPic;

	@FXML
	private Button joinCheck;

	@FXML
	private TextField txtjoinId;

	@FXML
	private Button submit;

	@FXML
	private TextField txtjoinName;

	@FXML
	private ImageView joinPage;

	@FXML
	private TextField txtCheckN;

	@FXML
	private TextField joinPscheck;

	@FXML
	private TextField txtP1;

	@FXML
	private RadioButton girl;

	@FXML
	private Button joinCertified;

	@FXML
	private TextField txtBrt;

	@FXML
	private Button txtRepetiCheck;

	@FXML
	private TextField txtMail;

	@FXML
	private RadioButton man;

	@FXML
	private Label joinPgLabel;

	@FXML
	private ToggleGroup radioGroup;
	
	@FXML
	private Button backbtn;
	
	@FXML
	private Pane registerPane;
	
	@FXML
	void addPic(ActionEvent event ) { //프로필사진등록		
		//size : fitHeight="150.0" fitWidth="200.0"
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
				memPic.setImage(image2);
				
			} catch (Exception e) {				
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
	void reCheck(ActionEvent event) { //아이디 중복체크
		String mem_id = txtjoinId.getText();
		System.out.println(mem_id);
		if(mem_id.trim().equals("")){
			alert.setTitle("아이디확인");
			alert.setHeaderText("아이디를 입력해주세요.");        	
			alert.show();
			return;
		}else{

			vo.setMem_id(mem_id);
			int loginCheck = service.checkId(vo); //아이디체크
			this.getId = loginCheck; // 중복확인결과값을 담음

			System.out.println(mem_id);
			System.out.println(loginCheck);

			if(loginCheck==1){
				alert.setTitle("중복확인");
				alert.setHeaderText("이미 가입되어있는 아이디입니다.");        	
				alert.show();    		
			}else if(loginCheck==0){
				alert.setTitle("중복확인");
				alert.setHeaderText("가입 하실수 있는 아이디입니다.");        	
				alert.show();
				idCheck = 1; //아이디 중복체크확인.
				defaultId = txtjoinId.getText(); //필드에 입력되어있는 아이디값을 읽어옴
			}

			System.out.println(getId);

		}
	}

	@FXML
	void check(ActionEvent event) { //핸드폰번호 인증
		//회원의 전화번호를 읽어옴
		String number = txtP1.getText();
		if(number.trim().equals("")){
			alert.setTitle("인증번호발송");
			alert.setHeaderText("인증번호를 받으실 번호를 입력해주세요.");        	
			alert.show();
		}else{
			//싱글턴패턴의 SMS 호출
			SendSMS sms = new SendSMS();
			checkNum = sms.SendSMS(number);
			System.out.println(checkNum);
			Stage stage = new Stage();
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FindIdAlert.fxml"));
	    	
	    	try {
				Pane pane = loader.load();
				Scene scene = new Scene(pane);
				stage.setScene(scene);
				stage.show();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	void joinCheck(ActionEvent event) { //인증번호 확인
		String ck = txtCheckN.getText();
		System.out.println(ck);
		if(ck.equals(checkNum)){
			alert.setTitle("인증번호확인 완료");
			alert.setHeaderText("인증번호가 확인완료되었습니다.");        	
			alert.show(); 
			checkNum2 = checkNum;
			System.out.println("인증번호를 제대로 입력했다면 저장 :" + checkNum2);
		}else{
			alert.setTitle("인증번호확인 실패");
			alert.setHeaderText("인증번호가 다릅니다.");        	
			alert.show(); 
		}
	}

	@FXML
	void submit(ActionEvent event) { //가입하기
		System.out.println("가입하기호출");
		
		String ck = txtCheckN.getText();
		man.setToggleGroup(radioGroup);
		girl.setToggleGroup(radioGroup);

		String mem_id = txtjoinId.getText();	 //아이디
		String mem_pass = txtJoinpass.getText();   //비밀번호
		String pw2 = joinPscheck.getText();  //비밀번호 확인
		String mem_name = txtjoinName.getText(); //이름
		//라디오버튼
		RadioButton chk = (RadioButton)radioGroup.getSelectedToggle();
		String mem_sexdstn = chk.getText();  	 //성별

		String mem_ihidnum = txtBrt.getText(); 	 //주민번호앞자리
		System.out.println(mem_ihidnum);
		String mem_mbp = txtP1.getText(); 		 //핸드폰번호
		System.out.println(mem_mbp);
		String cknum = txtCheckN.getText();  //인증번호
		System.out.println(cknum);
		String mem_email = txtMail.getText();	 //이메일
		System.out.println(mem_email);
		String mem_propic = imagePath;
		System.out.println(mem_propic + "|| 저장된 이미지이름");
		
		//필드에 값이 비어있다면 무조건 회원가입에 실패함.
		if((getId==1)||!(ck.equals(checkNum))||!(mem_pass.equals(pw2))||(mem_name.trim().equals("")
				||(mem_ihidnum.equals("")||(cknum.equals("")||mem_email.equals(""))))){
			alert.setTitle("회원가입 실패");
			alert.setHeaderText("회원정보를 정확히 입력해주세요.");        	
			alert.show();

			if(mem_id.trim().equals("")){//아이디창이 비어있다면
				alert.setTitle("정보를 입력해주세요");
				alert.setHeaderText("아이디를 입력하세요.");        	
				alert.show();
			} else if(mem_pass.trim().equals("")) { //비밀번호가 비어있다면
				alert.setTitle("정보를 입력해주세요");
				alert.setHeaderText("비밀번호를 입력하세요.");        	
				alert.show();
			} else if(mem_name.trim().equals("")) { //이름창이 비어있다면
				alert.setTitle("정보를 입력해주세요");
				alert.setHeaderText("이름을 입력하세요.");        	
				alert.show();
			} else if(mem_ihidnum.trim().equals("")) { //주민등록창이 비어있다면
				alert.setTitle("정보를 입력해주세요");
				alert.setHeaderText("주민번호 앞자리를 입력하세요.");        	
				alert.show();
			} else if(mem_mbp.trim().equals("")) { //전화번호창이 비어있다면
				alert.setTitle("정보를 입력해주세요");
				alert.setHeaderText("전화번호를 입력하세요.");        	
				alert.show();
			} else if(cknum.trim().equals("")) { //인증번호창이 비어있다면
				alert.setTitle("정보를 입력해주세요");
				alert.setHeaderText("인증번호를 입력하세요.");        	
				alert.show();
			} else if(mem_email.trim().equals("")) { //이메일창이 비어있다면
				alert.setTitle("정보를 입력해주세요");
				alert.setHeaderText("이메일을 입력하세요.");        	
				alert.show();
			} 

		} else {
			if((idCheck==0)){
				alert.setTitle("회원가입 실패");
				alert.setHeaderText("아이디 중복검사를 해주세요.");        	
				alert.show();
				return;
			}else if(!(checkNum.equals(checkNum2))){
				alert.setTitle("회원가입 실패");
				alert.setHeaderText("인증번호 확인을 해주세요.");        	
				alert.show();
				return;
			}else if((idCheck==1)&&(checkNum.equals(checkNum2))){
			vo.setMem_id(mem_id);
			vo.setMem_pass(mem_pass);
			vo.setMem_name(mem_name);
			vo.setMem_sexdstn(mem_sexdstn);
			vo.setMem_ihidnum(mem_ihidnum);
			vo.setMem_mbp(mem_mbp);
			vo.setMem_email(mem_email);
			vo.setMem_propic(mem_propic);
			service.insertMem(vo);
			System.out.println(vo);
								
			alert.setTitle("회원가입 성공");
			alert.setHeaderText("회원가입을 축하드립니다.");        	
			alert.show();
			
			System.out.println(ck);
			System.out.println(checkNum);
			
			targetPane.getChildren().clear();
	    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
	    	try {
				Pane root = FXMLLoader.load(loader.getResource("./fxml/HomePage.fxml"));
				targetPane.getChildren().add(root);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	
			}
			
		}
	
	}
	
	@FXML
	public void backBtn(ActionEvent event) { //RegisterUser 호출과동시에 기본프로필사진 로드
		System.out.println("취소 호출");
		
		Stage stage = (Stage)targetPane.getScene().getWindow();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HomePage.fxml"));
    	
    	try {
			Pane pane = loader.load();
			Scene scene = new Scene(pane);
			stage.setScene(scene);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
	}
	
	//회원이 아이디를 변경하려고할때
	@FXML
	public void changeId(){
		String filedId = txtjoinId.getText();
		System.out.println(filedId + "아이디창에 입력되어있는 ID");
		if(filedId.equals(defaultId)){ //입력되어있는아이디가 변경되지않았다면.
			System.out.println("아이디필드 클릭함 || 아이디가 변경되지않았다면 1  ||  " + idCheck);
			idCheck = 1;	
		}else if(!(filedId.equals(defaultId))){ //입력되어있는아이디가 중복체크한 아이디와 같지 않다면
			System.out.println("아이디필드 클릭함 || 아이디가 변경되었다면 0  ||  " + idCheck);
			idCheck = 0;		
		}
	}
	//비밀번호 재확인 여부
	@FXML
	public void changePw(){
		defaultPw = txtJoinpass.getText();
		System.out.println("현재 입력되어있는 비밀번호 || " + defaultPw);
		String txt2 = joinPscheck.getText();
		
		if(defaultPw.equals(txt2)){ // 비밀번호창에입력된 비밀번호와 비밀번호 확인창에있는 값이 같다면
			System.out.println("비밀번호 재확인필드 클릭함 || 비밀번호가 일치하다면 1  ||  " + idCheck);
			idCheck = 1;
			
		}else if(!(defaultPw.equals(txt2))){ // 비밀번호창에입력된 비밀번호와 비밀번호 확인창에있는 값이 틀리다면
			System.out.println("비밀번호 재확인필드 클릭함 || 비밀번호가 일치하지않다면 0  ||  " + idCheck);
			idCheck = 0;
			return;
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) { //RegisterUser 호출과동시에 기본프로필사진 로드
		System.out.println("initiallize 호출");
		targetPane = registerPane;
		//File file = new File("D:\\jspwork\\youngmanble\\src\\images\\sample.jpg");
		File file = new File("upload/sample.jpg");
		System.out.println(file+" || 프로필사진로드");
        Image image = new Image(file.toURI().toString());
        System.out.println(image+" || 이미지경로");
        memPic.setImage(image);
	}

}