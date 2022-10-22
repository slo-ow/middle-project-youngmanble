package home.controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import home.service.HService;
import home.service.HServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.HomePageApp;
import vo.MemberVO;
/**
 * @author 문성철
 * @version 1.0
 * 홈페이지 메인 컨트롤러
 * */
public class HomePageController implements Initializable{
	HService service = HServiceImpl.getInstance();
	Alert alert = new Alert(Alert.AlertType.INFORMATION);	
	Map<String,String> join = new HashMap<String,String>(); //로그인정보
	public static MemberVO session = null; //세션
	
	
	@FXML
	private ImageView joinImg;

	@FXML
	private Pane homePane;


	@FXML
	private ImageView svc;

	@FXML
	private ImageView youngmanble1;

	@FXML
	private ImageView logImg;

	@FXML
	private Button loginImg;

	@FXML
	private Label loginLabel;

	@FXML
	private Button joinBtn;

	@FXML
	private Button idFindbtn;

	@FXML
	private TextField txtpass;

	@FXML
	private TextField txtid;

	@FXML
	private Button psFindBtn;

	@FXML
	private ImageView passFind;

	@FXML
	private ImageView idFind;

	@FXML
	private Label youngmanble;

	public static Pane targetPane;

	@FXML
	void idFind(ActionEvent event) {
		System.out.println("아이디찾기 호출");
		Stage stage = new Stage();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FindId.fxml"));
    	
    	try {
			Pane pane = loader.load();
			Scene scene = new Scene(pane);
			stage.setScene(scene);
			stage.setTitle("아이디 찾기");
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void psFind(ActionEvent event) {
		System.out.println("비밀번호찾기 호출");
		Stage stage = new Stage();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FindPw.fxml"));
    	
    	try {
			Pane pane = loader.load();
			Scene scene = new Scene(pane);
			stage.setScene(scene);
			stage.setTitle("비밀번호 찾기");
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void join(ActionEvent event) {
		System.out.println("회원가입 호출");
		targetPane.getChildren().clear();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try {
			Pane root = FXMLLoader.load(loader.getResource("./fxml/RegisterUser.fxml"));
			targetPane.getChildren().add(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	void loginImage(ActionEvent event) {
		System.out.println("로그인 호출");
		String mem_id = txtid.getText();
		String mem_pass = txtpass.getText();
		//아이디 입력확인
		if((mem_id.trim().equals(""))||(mem_pass.trim().equals(""))){
			alert.setTitle("입력확인");
			alert.setHeaderText("아이디 또는 비밀번호를 제대로 입력해주세요.");        	
			alert.show();
			return;
		}else{ //입력이 제대로 됐다면.

			join.put("mem_id", mem_id);
			join.put("mem_pass", mem_pass);

			int loginCount = service.loginCheck(join);
			System.out.println(loginCount + "\t ||로그인 확인 결과");

			if(loginCount==1){ // 정보가 제대로 넘어왔다면 로그인성공
				alert.setTitle("승인");
				alert.setHeaderText("로그인 성공! Youngmanble에 오신것을 환영합니다.");        	
				alert.show();
				session = (MemberVO)service.getSession(join.get("mem_id"));
				System.out.println(session +"\n||로그인된아이디 세션값 확인||");
				if(session.getMem_se().equals("1")){ //사용자 구분 == 1 은 회원
					//회원이라면 메인페이지로 보낸다.
					System.out.println("||세션 구분확인 1:회원, 2:관리자|| ==> "+session.getMem_se());
																				
					targetPane.getChildren().clear();
			    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
			    	try {
						Pane root = FXMLLoader.load(loader.getResource("fxml/MainPage.fxml"));
						targetPane.getChildren().add(root);
						MediaViewController md = new MediaViewController();
						md.closeMedia();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}else if(session.getMem_se().equals("2")){ //사용자 구분 == 2 은 관리자
					//관리자라면 관리자페이지로 보낸다.
					System.out.println("||세션 구분확인 1:회원, 2:관리자|| ==> "+session.getMem_se());
					
					targetPane.getChildren().clear();
					ClassLoader loader = Thread.currentThread().getContextClassLoader();
					
					try {
						Pane admin = FXMLLoader.load(loader.getResource("fxml/AdminPage.fxml"));
						targetPane.getChildren().add(admin);
						MediaViewController md = new MediaViewController();
						md.closeMedia();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			}else{ // 0 반환된다면 로그인 실패
				alert.setTitle("경고");
				alert.setHeaderText("로그인 실패! \n 아이디 또는 비밀번호를 확인하세요.");        	
				alert.show();
				return;
			}
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		targetPane = homePane;
	}


}
