package customer.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import customer.dao.FreeBoardDAO;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import vo.FreeBoardVO;

public class FreeBoardController implements Initializable {

    @FXML
    private Button btnSearch;

    @FXML
    private Pane freeBoardPane;

    @FXML
    private ComboBox searchCase;

    @FXML
    private TextField txtSearch;

    @FXML
    private TableView<FreeBoardVO> freeboard;

    @FXML
    private ImageView backImage;
    
    
    
    
    private FreeBoardDAO dao = new FreeBoardDAO();
    
    private FreeBoardController freeBoardController = this;
    
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	System.out.println("initialize");
    	
    	setTableView();
    	
    	setComboBox();
    	
    	setTableData(null, null);
    	
    	
    }
    
    
    
    
    
    private void setTableView() {
    	TableColumn<FreeBoardVO, String> tableCol_fbNum = new TableColumn<>("글번호");
    	TableColumn<FreeBoardVO, String> tableCol_fbSubject = new TableColumn<>("제목");
    	TableColumn<FreeBoardVO, String> tableCol_fbMemid = new TableColumn<>("작성자");
    	TableColumn<FreeBoardVO, String> tableCol_fbDate = new TableColumn<>("작성일자");
    	TableColumn<FreeBoardVO, String> tableCol_fbView = new TableColumn<>("조회수");
    	
    	
    	tableCol_fbNum.setCellValueFactory(new PropertyValueFactory<FreeBoardVO, String>("fbNum"));
    	tableCol_fbSubject.setCellValueFactory(new PropertyValueFactory<FreeBoardVO, String>("fbSubject"));
    	tableCol_fbMemid.setCellValueFactory(new PropertyValueFactory<FreeBoardVO, String>("fbMemid"));
    	tableCol_fbDate.setCellValueFactory(new PropertyValueFactory<FreeBoardVO, String>("fbDate"));
    	tableCol_fbView.setCellValueFactory(new PropertyValueFactory<FreeBoardVO, String>("fbView"));
    	
    	
    	tableCol_fbNum.setMinWidth(100);
    	tableCol_fbSubject.setMinWidth(370);
    	tableCol_fbMemid.setMinWidth(100);
    	tableCol_fbDate.setMinWidth(100);
    	tableCol_fbView.setMinWidth(100);
    	
    	freeboard.getColumns().addAll(tableCol_fbNum,
    								  tableCol_fbSubject,
    								  tableCol_fbMemid,
    								  tableCol_fbDate,
    								  tableCol_fbView
    								 );	
    	
    	
    	
    	
    	freeboard.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.isPrimaryButtonDown() && event.getClickCount()==2) {
					
					FreeBoardVO vo = freeboard.getSelectionModel().getSelectedItem();
					
					
					try {
						dao.plusView(vo.getFbNum());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					Stage stage = new Stage();
					
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FreeContent.fxml"));
					
					try {
						FlowPane pane = (FlowPane)loader.load();
						
						FreeContentController freeContentController = (FreeContentController)loader.getController();
						freeContentController.setParentController(freeBoardController);
						
						freeContentController.setBoardData(vo.getFbNum());
						
						Scene scene = new Scene(pane, 610, 340); 
						
						stage.setScene(scene);
						stage.initModality(Modality.APPLICATION_MODAL);
						
						stage.show();
						
				
						
						
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
				
			}
    		
		});
    	
    	
    }
    
    
    
    private void setComboBox() {
    	searchCase.getItems().addAll("제목", "작성자");
    	searchCase.setValue("제목");
    }
    
    
    
    
    
    
    @FXML
    void search(ActionEvent event) {
    	System.out.println("click_search");
    	
    	String boardTitle = null;
    	String boardWriter = null;
    	
    	if(searchCase.getValue().toString().equals("제목")) {
    		boardTitle = txtSearch.getText();
    	} else if(searchCase.getValue().toString().equals("작성자")) {
    		boardWriter = txtSearch.getText();
    	}
    	
    	
    	setTableData(boardTitle, boardWriter);
    }
    
    
    
    
    
    
    public void setTableData(String boardTitle, String boardWriter) {
    	try {
			List<FreeBoardVO> list = dao.selectBoardList(boardTitle, boardWriter);
			ObservableList<FreeBoardVO> boardList;
			boardList = FXCollections.observableArrayList(list);
			
			freeboard.setItems(boardList);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
    			
    }
    
    
    
    
    
    @FXML
    public void click_delete(ActionEvent event) {
    	FreeBoardVO vo = freeboard.getSelectionModel().getSelectedItem();
    	
    	try {
			dao.deleteBoard(vo.getFbNum());
			freeboard.getItems().remove(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    
    
    
    
    @FXML
    void writeFree(ActionEvent event) {
    	Stage stage = new Stage();
    	
    	FlowPane pane = null;
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FreeContent.fxml"));
    	
    	try {
			pane = (FlowPane)loader.load();
			
			FreeContentController freeContentController = (FreeContentController)loader.getController();
			freeContentController.setParentController(freeBoardController);
			
			Scene scene = new Scene(pane, 610, 340);
			stage.setScene(scene);
			
			stage.initModality(Modality.APPLICATION_MODAL);
			
			stage.show();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    
    
    
    
    
    

    @FXML
    void insertSearch(ActionEvent event) {

    }

    @FXML
    void viewSearchCase(ActionEvent event) {

    }



	
	
	
	
	
	

}
