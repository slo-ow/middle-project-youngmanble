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
 * 메인화면에서 아이디찾기 눌렀을때 실행되는 클래스
 * 
 * 기능 : SMS / EMAIL 로 인증번호 받아서 아이디찾기
 * */
public class FindIdController implements Initializable{
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
	private Pane findId_Pane;
	
    @FXML
    private ToggleButton qtoggle2;

    @FXML
    private TextField find_N;

    @FXML
    private TextField resultMyNumber;

    @FXML
    private TextField find_MN;

    @FXML
    private TextField find_P;

    @FXML
    private ToggleButton qtoggle;

    @FXML
    private TextField find_M;

    @FXML
    private TextField resultMyMail;
    
    //아코디언의 타이틀을 처음부터 오픈시켜준다.
    @FXML
    private Accordion accord;
    @FXML 
    private TitledPane titleT;
    
    @FXML
    void getMyNumber(ActionEvent event) { // 아이디찾기/ 휴대전화로 인증번호받기
    	//입력된 회원의 이름을 가져옴
		String mem_name = find_N.getText();
		//입력된 회원의 번호를 가져옴
		String mem_mbp = find_P.getText();
		//쿼리문실행
		session = (MemberVO)service.getName(mem_name);
		//가져온정보가 일치하다면 아래의 문장 실행 ==> 해당번호로 인증번호발송
		if(mem_name.trim().equals("")||mem_mbp.trim().equals("")){//텍스트필드가비어있다면 발송불가능
			alert.setTitle("아이디찾기 실패");
			alert.setHeaderText("회원정보를 정확히 입력해주세요.");        	
			alert.show();
			return;
		}else if(session==null||!(mem_mbp.equals(session.getMem_mbp()))){ //입력한회원의 핸드폰번호가 일치하지않다면
			alert.setTitle("아이디찾기 실패");
			alert.setHeaderText("입력하신 정보가 일치하지 않습니다.");        	
			alert.show();
			return;
		}else{ //모든입력이 제대로 됐다면 실행함
			String number = mem_mbp;//입력받은 핸드폰번호
			//SMS 객체호출
			SendSMS sms = new SendSMS();
			checkNum = sms.SendSMS(number);
			System.out.println(checkNum);
			
	    	System.out.println("ID찾기/휴대폰/인증번호 호출");    	
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
    void addQToggle(ActionEvent event) { //ID찾기 => 휴대번호인증 => 인증번호가 오지않나요?
    	alert.setTitle("도움말");
		alert.setHeaderText("가입하셨던 휴대번호를 제대로 입력하셨는지 확인해보세요.");        	
		alert.show();
    }

    @FXML
    void getMyMail(ActionEvent event) { //인증번호받기
    	System.out.println("ID찾기/이메일/인증번호 호출");
    	
    	String mem_name = find_MN.getText(); //메일텍스트 이름
    	String findM = find_M.getText(); //메일텍스트 메일
    	String MyMail = resultMyMail.getText(); //메일텍스트 인증번호
    	session = (MemberVO)service.getName(mem_name); //입력된회원의 정보를가져와서 비교함, 변수에담아둠
    	System.out.println(session +"\n ||입력한 회원의정보||");
    	if(mem_name.trim().equals("")||findM.trim().equals("")){//텍스트필드가 비어있다면 발송을 할수없다.
    		alert.setTitle("아이디찾기 실패");
			alert.setHeaderText("회원정보를 정확히 입력해주세요.");        	
			alert.show();
			return;
    	}else if(session==null||!(findM.trim().equals(session.getMem_email()))){ //입력은했는데 회원의 정보가 다르다면
    		alert.setTitle("아이디찾기 실패");
			alert.setHeaderText("입력하신 정보가 일치하지않습니다.");        	
			alert.show();
			return;
    	}else{ // 모든 텍스트필드에 값이 제대로 입력이 되었다면
    		//Alert창 호출
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
    void addQToggle2(ActionEvent event) { //ID찾기 => 이메일인증 => 인증번호가 오지않나요?
    	alert.setTitle("도움말");
		alert.setHeaderText("가입하셨던 이메일을 제대로 입력하셨는지 확인해보세요.");        	
		alert.show();
    }
    
    @FXML
    void getMyId(ActionEvent event){
    	System.out.println("휴대전화인증 후 확인버튼 호출");
    	String findn = find_N.getText();
    	String findp = find_P.getText();
    	String MyNumber = resultMyNumber.getText(); //휴대전화텍스트 인증번호
    	if(findn.trim().equals("")||findp.trim().equals("")||MyNumber.trim().equals("")){ //텍스트창이 비어있다면
    		alert.setTitle("경고");
			alert.setHeaderText("회원님의 정보를 올바르게 입력해주세요.");        	
			alert.show();
			return;
    	}else{
    		if(checkNum.equals(MyNumber)){ //인증번호가 같다면 아이디를 찾아다줌
        		alert.setTitle("아이디찾기 성공");
        		alert.setHeaderText("찾으시는 회원님의 아이디는 "+"[" + session.getMem_id()+ "]" + " 입니다.");
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
    void getMyId2(ActionEvent event){
    	System.out.println("이메일인증 후 확인버튼 호출");
    	String findmn = find_MN.getText();
    	String findm = find_M.getText();
    	String MyMail = resultMyMail.getText(); //메일텍스트 인증번호
    	if(findmn.trim().equals("")||findm.trim().equals("")||MyMail.trim().equals("")){ //텍스트창이 비어있다면
    		alert.setTitle("경고");
			alert.setHeaderText("회원님의 정보를 올바르게 입력해주세요.");        	
			alert.show();
			return;
    	}else{
    	if(checkNum.equals(MyMail)){ //인증번호가 같다면 아이디를 찾아다줌
    		alert.setTitle("아이디찾기 성공");
			alert.setHeaderText("찾으시는 회원님의 아이디는 "+"[" + session.getMem_id()+ "]" + " 입니다.");        	
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
		findId_Pane = targetPane;
		//로딩과 동시에 아코디언의 타이틀을 오픈시킴.
		accord.setExpandedPane(titleT);
	}

}
