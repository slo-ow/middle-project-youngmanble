package home.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.mail.MessagingException;

import home.mail.FindNaverMail;
import home.service.HService;
import home.service.HServiceImpl;
import home.sms.SendSMS;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import vo.MemberVO;

/**
 * @author 문성철
 * @version 1.0
 * 
 * 메인화면에서 비밀번호 찾기 눌렀을때 실행되는 클래스
 * 
 * 기능 : SMS / EMAIL 로 인증번호 받아서 패스워드 찾기
 * */
public class FindPwController implements  Initializable{
	HService service = HServiceImpl.getInstance();
	//핸드폰인증번호확인
	SendSMS sms = new SendSMS();
	//핸드폰인증번호 체크
	String checkNum;
	//Alert
	Alert alert = new Alert(Alert.AlertType.INFORMATION);
	//회원정보(이름으로찾아옴)
	MemberVO session = new MemberVO();
	
	private Pane targetPane;

	@FXML
	private TextField resultMyNumber;

	@FXML
	private TextField find_PWN;

	@FXML
	private TextField find_PWMID;

	@FXML
	private TextField find_PWP;

	@FXML
	private ToggleButton qtoggle2;

	@FXML
	private TextField find_PWE;

	@FXML
	private ToggleButton qtoggle3;

	@FXML
	private Pane findPw_Pane;

	@FXML
	private TextField find_PWID;

	@FXML
	private TextField find_PWMN;

	@FXML
	private TextField resultMyMail;

	//아코디언의 타이틀을 처음부터 오픈시켜준다.
	@FXML
	private Accordion accord2;

	@FXML
	private TitledPane titleP;


	@FXML
	void getMyPWN(ActionEvent event) { //PASSWORD찾기 => 휴대번호인증 => 인증번호받기
		System.out.println("비밀번호찾기/휴대전화로인증/인증번호받기 호출");
		String mem_id = find_PWID.getText(); //아이디
		String findpwn = find_PWN.getText(); //이름
		String findpwp = find_PWP.getText(); //휴대폰번호
		System.out.println(mem_id);
		System.out.println(findpwn);
		System.out.println(findpwp);
		session = (MemberVO)service.getSession(mem_id); //입력된 회원의 아이디에 대한 정보를가져옴
		if(mem_id.trim().equals("")||findpwn.trim().equals("")||findpwp.trim().equals("")){//텍스트필드가비어있다면 발송불가능
			alert.setTitle("비밀번호찾기 실패");
			alert.setHeaderText("회원정보를 정확히 입력해주세요.");        	
			alert.show();
			return;
		}else if(session==null||!(findpwn.equals(session.getMem_name()))||!(findpwp.equals(session.getMem_mbp()))){ //입력한회원의 핸드폰번호,이름이 일치하지않다면
			System.out.println(session.getMem_mbp() + " 저장되어있는 회원의 핸드폰번호");
			alert.setTitle("비밀번호찾기 실패"); 
			alert.setHeaderText("입력하신 정보가 일치하지 않습니다.");        	
			alert.show();
			return;
		}else{ //입력한정보가 모두 일치하다면
		String number = findpwp;//입력받은 핸드폰번호
		//SMS 객체호출
		SendSMS sms = new SendSMS();
		checkNum = sms.SendSMS(number);
		System.out.println(checkNum);
		
    	System.out.println("ID찾기/휴대폰/인증번호 호출");    	
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FindPwAlert.fxml"));

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
	void addQToggle2(ActionEvent event) { //PASSWORD찾기 => 인증번호가 오지않나요?
		alert.setTitle("도움말");
		alert.setHeaderText("가입하셨던 휴대번호를 제대로 입력하셨는지 확인해보세요.");        	
		alert.show();
	}

	@FXML
	void getMyMail(ActionEvent event) { //PASSWORD찾기 => 이메일로인증 => 인증번호받기
		System.out.println("비밀번호찾기/이메일로인증/인증번호받기 호출");
		String mem_id = find_PWMID.getText(); //메일텍스트 아이디
    	String findMN = find_PWMN.getText(); //메일텍스트 이름
    	String findM = find_PWE.getText(); //메일텍스트 메일
    	String MyMail = resultMyMail.getText(); //메일텍스트 인증번호
    	session = (MemberVO)service.getSession(mem_id); //입력된회원의 정보를가져와서 비교함, 변수에담아둠
    	System.out.println(session +"\n ||입력한 회원의정보||");
    	if(mem_id.trim().equals("")||findMN.trim().equals("")||findM.trim().equals("")){//텍스트필드가 비어있다면 발송을 할수없다.
    		alert.setTitle("비밀번호찾기 실패");
			alert.setHeaderText("회원정보를 정확히 입력해주세요.");        	
			alert.show();
			return;
    	}else if(session==null||!(findM.equals(session.getMem_email()))||!(findMN.equals(session.getMem_name()))){ //입력은했는데 회원의 정보가 다르다면
    		alert.setTitle("비밀번호찾기 실패");
			alert.setHeaderText("입력하신 정보가 일치하지않습니다.");        	
			alert.show();
			return;
    	}else{ // 모든 텍스트필드에 값이 제대로 입력이 되었다면
    		//Alert창 호출
        	Stage stage = new Stage();
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FindPwAlert.fxml"));
        	
        	try {
    			Pane pane = loader.load();
    			Scene scene = new Scene(pane);
    			stage.setScene(scene);
    			stage.show();
    			
    		} catch (IOException e) {
    			e.printStackTrace();
    		}    	
        	System.out.println("====Alert창 호출 끝=====");
        	
        	//이메일전송.
        	FindNaverMail mail = new FindNaverMail();
        	try {
    			String checkNumber = mail.SendEmail(session.getMem_email()); //인증번호요청
    	    	System.out.println(checkNumber + " || 이메일로 요청된 인증번호 6자리의 결과값"); //인증번호를 받아옴
    	    	checkNum = checkNumber; // 받아온 인증번호를 담아둠.
    		} catch (MessagingException e1) {
    			e1.printStackTrace();
    		}
    		
    	}
	}

	@FXML
	void addQToggle3(ActionEvent event) { //PASSWORD찾기 => 인증번호가 오지않나요?
		alert.setTitle("도움말");
		alert.setHeaderText("가입하셨던 이메일을 제대로 입력하셨는지 확인해보세요.");        	
		alert.show();
	}

	@FXML
	void ok_getMyNumber(ActionEvent event){ //비밀번호찾기 ==> 휴대폰으로찾기 ==> OK버튼눌렀을때
		System.out.println("휴대전화인증 후 확인버튼 호출");
		String mem_id = find_PWID.getText(); //아이디
		String findpwn = find_PWN.getText(); //이름
		String findpwp = find_PWP.getText(); //휴대폰번호		
    	String MyNumber = resultMyNumber.getText(); //휴대전화텍스트 인증번호
    	
    	if(mem_id.trim().equals("")||findpwn.trim().equals("")||findpwp.trim().equals("")||MyNumber.trim().equals("")){ //텍스트창이 비어있다면
    		alert.setTitle("경고");
			alert.setHeaderText("회원님의 정보를 올바르게 입력해주세요.");        	
			alert.show();
			return;
    	}else{
    		if(checkNum.equals(MyNumber)){ //인증번호가 같다면 아이디를 찾아다줌
        		alert.setTitle("아이디찾기 성공");
        		alert.setHeaderText("찾으시는 회원님의 패스워드는 "+"[" + session.getMem_pass()+ "]" + " 입니다.");
    			alert.show();
        	}else{ //인증번호가 다르다면 실패
        		alert.setTitle("아이디찾기 실패");
    			alert.setHeaderText("입력하신 정보가 일치하지않습니다.");        	
    			alert.show();
        	return;
        	}
    	}
	}

	@FXML
	void ok_getMyMail(ActionEvent event){ // 비밀번호찾기 ==> 메일로찾기 ==> OK 버튼눌렀을때
		System.out.println("메일로찾기 OK버튼 호출");
		String mem_id = find_PWMID.getText(); //메일텍스트 아이디
    	String findMN = find_PWMN.getText(); //메일텍스트 이름
    	String findM = find_PWE.getText(); //메일텍스트 메일
    	String MyMail = resultMyMail.getText(); //메일텍스트 인증번호
    	if(mem_id.trim().equals("")||findMN.trim().equals("")||findM.trim().equals("")||MyMail.trim().equals("")){ //텍스트창이 비어있다면
    		alert.setTitle("경고");
			alert.setHeaderText("회원님의 정보를 올바르게 입력해주세요.");        	
			alert.show();
			return;
    	}else{
    	if(checkNum.equals(MyMail)){ //인증번호가 같다면 아이디를 찾아다줌
    		alert.setTitle("비밀번호찾기 성공");
			alert.setHeaderText("찾으시는 회원님의 비밀번호는 "+"[" + session.getMem_pass()+ "]" + " 입니다.");        	
			alert.show();
    	}else{ //인증번호가 다르다면 실패
    		alert.setTitle("아이디찾기 실패");
			alert.setHeaderText("입력하신 정보가 일치하지않습니다.");        	
			alert.show();
    	return;
    	}
    	}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		findPw_Pane = targetPane;
		//로딩과 동시에 아코디언의 타이틀을 오픈시킴.
		accord2.setExpandedPane(titleP);
	}

}
