package admin.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import admin.dao.FaqDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import vo.FaqVO;

public class AdminFaqController implements Initializable {

    @FXML
    private TableColumn<FaqVO, String> colFaqDate;

    @FXML
    private TableColumn<FaqVO, String> colFaqView;

    @FXML
    private TableView<FaqVO> FaqTable;

    @FXML
    private TableColumn<FaqVO, String> colFaqWriter;

    @FXML
    private Button btnFaqWrite;

    @FXML
    private TableColumn<FaqVO, String> colFaqNum;

    @FXML
    private TableColumn<FaqVO, String> colFaqTitle;
    

    @FXML
    private Pane FaqPane;
    
  
    private FaqDAO dao = new FaqDAO();
    
    private AdminFaqController adminFaqController = this;    
    
  

    @FXML
    void gotoFaqForm(ActionEvent event) {
    	FaqPane.getChildren().clear();
    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try {
			Pane root = FXMLLoader.load(loader.getResource("fxml/AdminFaqForm.fxml"));
			FaqPane.getChildren().add(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("initialize");
		setTables();
		setTableData(null,null);
	
	}
	private void setTables(){
		colFaqNum.setCellValueFactory(new PropertyValueFactory<>("faqNum"));
		colFaqTitle.setCellValueFactory(new PropertyValueFactory<>("faqSubject"));
		colFaqWriter.setCellValueFactory(new PropertyValueFactory<>("faqMemid"));
		colFaqDate.setCellValueFactory(new PropertyValueFactory<>("faqDate"));
		colFaqView.setCellValueFactory(new PropertyValueFactory<>("faqView"));
		
		FaqTable.setOnMousePressed(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				//오른쪽 버튼을 누르거나 왼쪽 마우스를 누를때 실행된다.
				if(event.isPrimaryButtonDown()&&event.getClickCount()==2){
					//????
					FaqVO vo = FaqTable.getSelectionModel().getSelectedItem();
					
					Stage stage = new Stage();
					
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AdminFaqContent.fxml"));
					
					try {
						Pane pane = loader.load();
						
						AdminFaqContentController adminfaqContentController
													= (AdminFaqContentController)loader.getController();
						adminfaqContentController.setParent(adminFaqController);
						adminfaqContentController.setBoardData(vo.getFaqNum());
						
						Scene scene = new Scene(pane,800,618);
						
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
	
	public void setTableData(String faqTitle,String faqWriter){
		try {
			List<FaqVO> list = dao.selectBoardList(faqTitle, faqWriter);
			ObservableList<FaqVO> faqList;
			faqList = FXCollections.observableArrayList(list);
			
			FaqTable.setItems(faqList);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void p_delete(ActionEvent event){
		FaqVO vo = FaqTable.getSelectionModel().getSelectedItem();
		
		try {
			dao.deleteBoard(vo.getFaqNum());
			FaqTable.getItems().remove(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
	
}
