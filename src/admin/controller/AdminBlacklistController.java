package admin.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javax.mail.MessagingException;

import admin.dao.AdminBlackDAO;
import admin.mail.Sendmail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import vo.blacklistVO;

public class AdminBlacklistController implements Initializable{

    @FXML
    private TableColumn<blacklistVO, String> colReason;

    @FXML
    private TableView<blacklistVO> blackTable;

    @FXML
    private TableColumn<blacklistVO, String> colCount;

    @FXML
    private TableColumn<blacklistVO, String> colCode;

    @FXML
    private TableColumn<blacklistVO, String> colId;

    @FXML
    private Button btnClear;

    @FXML
    private TableColumn<blacklistVO, String> colRegi;

    @FXML
    private TableColumn<blacklistVO, String> colMail;

    @FXML
    private Button btnSend;
    
    @FXML
    private TextField tfCode;
    
    Alert alert;

    @FXML
    void clear(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("리스트삭제");
    	alert.setHeaderText("블랙리스트 삭제완료.");
    	alert.show();
    	try {
			dao.deleteBoard(tfCode.getText());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	setTableData(null, null);
    }
    
    @FXML
    void send(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("경고장");
    	alert.setHeaderText("경고장 발송완료.");
    	alert.show();
    	
    	Sendmail send = new Sendmail();
    	send.semail = tfCode.getText();
    	try {
			send.Sendmaill();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
    }
    
    private AdminBlackDAO dao = new AdminBlackDAO();
    
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("init!!");
		setTableView();
		setTableData(null,null);
		
	}
    
    private void setTableView(){
    	colCode.setCellValueFactory(new PropertyValueFactory<blacklistVO,String>("blk_code"));
    	colId.setCellValueFactory(new PropertyValueFactory<blacklistVO,String>("blk_mem_id"));
    	colRegi.setCellValueFactory(new PropertyValueFactory<blacklistVO,String>("blk_resist_day"));
    	colCount.setCellValueFactory(new PropertyValueFactory<blacklistVO,String>("blk_wrn_num"));
    	colReason.setCellValueFactory(new PropertyValueFactory<blacklistVO,String>("blk_res_regi"));
    	colMail.setCellValueFactory(new PropertyValueFactory<blacklistVO,String>("blk_email"));
    	
    	blackTable.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.isPrimaryButtonDown()&&event.getClickCount()==2){
					blacklistVO vo = blackTable.getSelectionModel().getSelectedItem();
				
					tfCode.setText(vo.getBlk_mem_id());
				}
			}
		});
    	
    	
    	
    }
    
    public void setTableData(String code,String blackid){
    	try {
			List<blacklistVO> list = dao.selectBoardList(code, blackid);
			ObservableList<blacklistVO> blackList;
			blackList = FXCollections.observableArrayList(list);
			
			blackTable.setItems(blackList);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    

}
