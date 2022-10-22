package admin.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.helpers.Loader;

import admin.dao.AdminNoticeDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import vo.NoticeVO;

public class AdminNoticeController implements Initializable {
	
    @FXML
    private Pane notiPane;
	
    @FXML
    private TableColumn<NoticeVO, String> colWriter;

    @FXML
    private TableColumn<NoticeVO, String> colNotiNum;

    @FXML
    private TableColumn<NoticeVO, String> colNotiTitle;

    @FXML
    private Button btnWrite;

    @FXML
    private TableColumn<NoticeVO, String> colDate;

    @FXML
    private TableColumn<NoticeVO, String> colViewNum;

    @FXML
    private TableView<NoticeVO> table;
    

    @FXML
    void gotoNoticeForm(ActionEvent event) {
    	notiPane.getChildren().clear();
    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
    	
    	try {
			Pane root = FXMLLoader.load(loader.getResource("fxml/AdminNoticeForm.fxml"));
			notiPane.getChildren().add(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    

    private AdminNoticeDAO dao = new AdminNoticeDAO();
    
    private AdminNoticeController adminNoticeController = this;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("notice");
		setTables();
		setTableData(null, null);
	}
	public void setTables(){
		colNotiNum.setCellValueFactory(new PropertyValueFactory<>("ntcNum"));
		colNotiTitle.setCellValueFactory(new PropertyValueFactory<>("ntcSubject"));
		colWriter.setCellValueFactory(new PropertyValueFactory<>("ntcMemid"));
		colDate.setCellValueFactory(new PropertyValueFactory<>("ntcDate"));
		colViewNum.setCellValueFactory(new PropertyValueFactory<>("ntcView"));
		
		table.setOnMousePressed(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent event) {
				if(event.isPrimaryButtonDown()&&event.getClickCount()==2){
					
					NoticeVO vo = table.getSelectionModel().getSelectedItem();
					
					Stage stage = new Stage();
					
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AdminNoticeContent.fxml"));
					
					try {
						Pane pane = loader.load();
						
						AdminNoticeContentController adminNoticeContentController
											= (AdminNoticeContentController)loader.getController();
						adminNoticeContentController.setParent(adminNoticeController);
						adminNoticeContentController.setBoardData(vo.getNtcNum());
						
						Scene scene = new Scene(pane);
						
						stage.setScene(scene);
						stage.initModality(Modality.APPLICATION_MODAL);
						
						stage.show();
						
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
	
	public void setTableData(String boardTitle,String boardWriter){
		try {
			List<NoticeVO> list = dao.selectBoardList(boardTitle, boardWriter);
			ObservableList<NoticeVO> notiList;
			notiList = FXCollections.observableArrayList(list);
			
			table.setItems(notiList);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	

	}
	public void delete(ActionEvent event) {
		NoticeVO vo = table.getSelectionModel().getSelectedItem();
		
		try {
			dao.deleteBoard(vo.getNtcNum());
			table.getItems().remove(vo);
			
			Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Noti Dialog");
	    	alert.setHeaderText("공지사항이 삭제되었습니다.");
	    	
	    	alert.show();
	    	
	    	
	    	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
