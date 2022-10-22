package customer.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import home.controller.HomePageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import vo.MemberVO;

public class ChatBoneController implements Initializable{

   @FXML
   private TextArea textAreaChat;

   @FXML
   private Pane chatPane;

   @FXML
   private Label chatLogo;

   @FXML
   private TextField txtInsert;

   @FXML
   private ImageView backImage;


   private EnquireController parentController;

   MemberVO logVO = HomePageController.session;

   Socket socket;

   public void setParentController(EnquireController enquireController) {
      this.parentController = enquireController;
   }


   @FXML
   void callServer(){//관리자 호출하기버튼
      System.out.println("관리자 호출하기 버튼누름.");
      //관리자 호출을 누르면 서버연결을 시도한다.
      //관리자 쪽에는 요청이왔다는 알림창을 띄워준다.
      conectServer();
   }


   @FXML
   void send(ActionEvent event) {
//      System.out.println("1:1 문의메세지 전송버튼클릭!!!");
//      String msg = txtInsert.getText();
//      //textAreaChat.setText(txtInsert.getText() + "\n" + msg);
//      System.out.println(msg+"메시지값 읽어왔는지 확인");
//      ClientSender(msg);
//      txtInsert.clear();//텍스트필드 비워줌
   }

   @FXML
   void exit(ActionEvent event) {
      Stage stage = (Stage) chatLogo.getScene().getWindow();
      stage.close();
   }

   @FXML
   void submitMsg(ActionEvent event){
      System.out.println("1:1 문의메세지 전송!!!");
      String msg = "["+logVO.getMem_id()+"] : "+txtInsert.getText();
      System.out.println(msg+"메시지값 읽어왔는지 확인");
      ClientSender(msg);
      txtInsert.clear();//텍스트필드 비워줌

   }

   @Override
   public void initialize(URL location, ResourceBundle resources) {
      txtInsert.setDisable(true);

   }


   void conectServer(){ //관리자에게 문의하는 채팅서버 활성화
      Thread thread = new Thread(){
         @Override 
         public void run() {
            DataOutputStream out;
            try{
               //String serverIp = "192.168.207.71";
               //String serverIp = "118.42.46.19";
               //소켓을 생성하여 연결을 요청한다.
               socket = new Socket();
//               socket.connect(new InetSocketAddress("118.42.46.19", 7777));
               socket.connect(new InetSocketAddress("192.168.207.16", 7777));
               System.out.println("서버에 연결되었습니다.");
               textAreaChat.setText("관리자 호출 요청중...\n관리자 호출 요청완료.\n관리자와 대화가 시작되었습니다.");
               out = new DataOutputStream(socket.getOutputStream());
               out.writeUTF("▶"+logVO.getMem_id()+"◀");
               txtInsert.setDisable(false);
               //Thread receiver = new Thread(new ClientReceiver(socket));
               //sender.start();
               //receiver.start();   
            } catch (Exception ce) {
               textAreaChat.setText("지금은 1:1 문의시간이 아닙니다.\n문의시간 09:00 ~ 18:00  공휴일 휴무");
               //               Alert alert = new Alert(AlertType.ERROR);
               //               alert.setTitle("알림");
               //               alert.setHeaderText("지금은 1:1 문의시간이 아닙니다.\n 문의시간 09:00 ~ 18:00 공휴일 휴무");
               //               alert.setContentText("급한용무가 있으신분들은 관리자 호출을 요청하시기바랍니다.");
               //               alert.show();
               ce.printStackTrace();               
               return;
            }
            ClientReceiver(); //Receiver호출
         }
      };
      thread.start();
   }

   //메세지전송
   void ClientSender(String msg){
      Thread thread = new Thread(){
         @Override
         public void run() {
            
            DataOutputStream out;
            System.out.println(msg + "메세지로 넘겨받은값 확인");
            //String name = logVO.getMem_id();      
            try{
               //textAreaChat.setText(textAreaChat.getText() + "\n" + msg); // 출력1
               out = new DataOutputStream(socket.getOutputStream());
               out.writeUTF(msg);               
               out.flush();
            }catch(Exception e) { } //Exception

         }

      };
      thread.start();
   }

   //ClientReceiver
   void ClientReceiver(){
      while(true){
         try{
            DataInputStream in;
            in = new DataInputStream(socket.getInputStream());
            while(in!=null){
               try{
                  String rr = in.readUTF();
                  textAreaChat.setText(textAreaChat.getText() +"\n" + rr); // 출력2
                  System.out.println(rr + "\t 서버쪽으로 보낸메세지");
                  //textAreaChat.setText(textAreaChat.getText() +"\n" + in.readUTF() + "\n 정답을알려줘~"); //관리자가 보낸메세지 읽어서 뿌리기
                  System.out.println(rr + "서버에서 날아온 메세지");
                  if(rr.equals("exit")){ //서버에서 보낸 exit문자열과 같다면 소켓을닫아준다.
                     System.out.println(rr+" || 종료메세지 확인 클라이언트 소켓 종료.");
                     socket.close();
                     System.out.println(socket);
                     textAreaChat.setText("관리자님께서 1:1 문의를 종료하셨습니다.");
                     txtInsert.setDisable(true);
                  }
               }catch(IOException e){
                  //
               }
            }
         }catch(IOException e) {} //Exception   
      }

}
/*   void ClientReceiver(){
      Thread thread = new Thread(){
         @Override
         public void run() {
            DataInputStream in;
            try{
               in = new DataInputStream(socket.getInputStream());
               while(in!=null){
                  try{
                     System.out.println(in.readUTF() + "\t 서버쪽으로 보낸메세지");
                     //textAreaChat.setText(textAreaChat.getText() +"\n" + in.readUTF() + "\n 정답을알려줘~"); //관리자가 보낸메세지 읽어서 뿌리기
                     textAreaChat.setText(textAreaChat.getText() +"\n" + in.readUTF() + "\n 정답을알려줘~"); //관리자가 보낸메세지 읽어서 뿌리기
                  }catch(IOException e){
                     //
                  }
               }
            }catch(IOException e) {} //Exception   
         }
      };
      thread.start();
   } 2
 */
}//메인
