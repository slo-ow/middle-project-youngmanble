package admin.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import admin.dao.AdminUserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import vo.MemberVO;

public class AdminUserCheckController implements Initializable {

    @FXML
    private TableColumn<MemberVO, String> colName;

    @FXML
    private TableColumn<MemberVO, String> colRegno;

    @FXML
    private TableColumn<MemberVO, String> colGender;

    @FXML
    private Button btnDelete;

    @FXML
    private TableColumn<MemberVO, String> colPhone;

    @FXML
    private TableView<MemberVO> UserTable;

    @FXML
    private TableColumn<MemberVO, Integer> colCount;

    @FXML
    private TextField tfId;

    @FXML
    private TableColumn<MemberVO, String> colId;

    @FXML
    private TableColumn<MemberVO, String> colEmail;
    
    @FXML
    void delete(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("탈퇴");
    	alert.setHeaderText("회원 탈퇴처리 완료.");
    	alert.show();
    	try {
			
			dao.deleteBoard(tfId.getText());
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private AdminUserDAO dao = new AdminUserDAO();
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("initialize");
		setTables();
		setTableData(null,null);
		
	}
	
	private void setTables(){
		colId.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
		colName.setCellValueFactory(new PropertyValueFactory<>("mem_name"));
		colGender.setCellValueFactory(new PropertyValueFactory<>("mem_sexdstn"));
		colRegno.setCellValueFactory(new PropertyValueFactory<>("mem_ihidnum"));
		colPhone.setCellValueFactory(new PropertyValueFactory<>("mem_mbp"));
		colEmail.setCellValueFactory(new PropertyValueFactory<>("mem_email"));
		colCount.setCellValueFactory(new PropertyValueFactory<>("count"));
		
		UserTable.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.isPrimaryButtonDown()&&event.getClickCount()==2){
					MemberVO vo = UserTable.getSelectionModel().getSelectedItem();
					
					tfId.setText(vo.getMem_id());
					
				}
			}
		});
	
	}
	
	public void setTableData(String boardId,String boardName){
		try {
			List<MemberVO> list = dao.selectBoardList(boardId,boardName);
			ObservableList<MemberVO> memList;
			memList = FXCollections.observableArrayList(list);
			
			UserTable.setItems(memList);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
