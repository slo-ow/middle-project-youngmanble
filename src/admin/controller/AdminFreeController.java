package admin.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import admin.dao.AdminFreeDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import vo.FreeBoardVO;

public class AdminFreeController implements Initializable {

    @FXML
    private Button btnFind;

    @FXML
    private TableColumn<FreeBoardVO, String> colUser;

    @FXML
    private TableColumn<FreeBoardVO, String> colNum;

    @FXML
    private TableColumn<FreeBoardVO, String> colFreeTitle;

    @FXML
    private TableColumn<FreeBoardVO, String> colCount;

    @FXML
    private TableView<FreeBoardVO> FreeTable;

    @FXML
    private TableColumn<FreeBoardVO, String> colDate;

    @FXML
    private TextField tfFind;

    @FXML
    private ComboBox serchCobox;

    @FXML
    private Button btnClear;
    
  
    @FXML
    void Find(ActionEvent event) {
    	
    	String boardTitle = null;
    	String boardWriter = null;
    	
    	if(serchCobox.getValue().toString().equals("글제목")){
    		boardTitle = tfFind.getText();
    	}else if(serchCobox.getValue().toString().equals("작성자")){
    		boardWriter = tfFind.getText();
    	}
    	
    	setTableData(boardTitle,boardWriter);
    }

    @FXML
    void clear(ActionEvent event) {
    	setTableData(null,null);
    }

    private AdminFreeDAO dao = new AdminFreeDAO();
    
    private  AdminFreeController adminFreeController = this;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setTableView();
		setComboBox();
		setTableData(null,null);
	}
	
	private void setTableView(){
		colNum.setCellValueFactory(new PropertyValueFactory<>("fbNum"));
		colFreeTitle.setCellValueFactory(new PropertyValueFactory<>("fbSubject"));
		colUser.setCellValueFactory(new PropertyValueFactory<>("fbMemid"));
		colDate.setCellValueFactory(new PropertyValueFactory<>("fbDate"));
		colCount.setCellValueFactory(new PropertyValueFactory<>("fbView"));
		
		FreeTable.setOnMousePressed(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				if(event.isPrimaryButtonDown()&&event.getClickCount()==2){
					
					FreeBoardVO vo = FreeTable.getSelectionModel().getSelectedItem();
					
					Stage stage = new Stage();
					
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AdminFreeContent.fxml"));
					
					try {
						Pane pane = (Pane)loader.load();
						
						AdminFreeContentController adminFreeContentController = (AdminFreeContentController)loader.getController();
						adminFreeContentController.setParentController(adminFreeController);
						
						adminFreeContentController.setBoardData(vo.getFbNum());
						
						Scene scene = new Scene(pane,618,368);
						
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
	
	public void setTableData(String boardTitle, String boardWriter) {
		try {
			List<FreeBoardVO> list = dao.selectBoardList(boardTitle, boardWriter);
			ObservableList<FreeBoardVO> boardList;
			boardList = FXCollections.observableArrayList(list);
			
			FreeTable.setItems(boardList);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
	}
	    
	private void setComboBox() {
		serchCobox.getItems().addAll("글제목","작성자");
		serchCobox.setValue("글제목");
	}
	    
	
	
	
	
	

}
