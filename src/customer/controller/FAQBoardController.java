package customer.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import customer.dao.FAQBoardDAO;
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
import vo.FaqVO;

public class FAQBoardController implements Initializable{

    @FXML
    private Button btnSearch;

    @FXML
    private TableView<FaqVO> FAQBoard;

    @FXML
    private ComboBox searchCase;

    @FXML
    private TextField txtSearch;

    @FXML
    private Pane FAQBoardPane;

    @FXML
    private ImageView backImage;

    
    
    
    private FAQBoardDAO dao = new FAQBoardDAO();
    
    private FAQBoardController faqBoardController = this;
    
    
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	System.out.println("initialize");
    	
    	setTableView();
    	
    	setComboBox();
    	
    	setTableData(null, null);
    	
    	
    }
    
    
    
    
    private void setTableView() {
    	TableColumn<FaqVO, String> tableCol_faqNum = new TableColumn<>("글번호");
    	TableColumn<FaqVO, String> tableCol_faqSubject = new TableColumn<>("제목");
    	TableColumn<FaqVO, String> tableCol_faqMemid = new TableColumn<>("작성자");
    	TableColumn<FaqVO, String> tableCol_faqDate = new TableColumn<>("작성일자");
    	TableColumn<FaqVO, String> tableCol_faqView = new TableColumn<>("조회수");
    	
    	
    	tableCol_faqNum.setCellValueFactory(new PropertyValueFactory<FaqVO, String>("faqNum"));
    	tableCol_faqSubject.setCellValueFactory(new PropertyValueFactory<FaqVO, String>("faqSubject"));
    	tableCol_faqMemid.setCellValueFactory(new PropertyValueFactory<FaqVO, String>("faqMemid"));
    	tableCol_faqDate.setCellValueFactory(new PropertyValueFactory<FaqVO, String>("faqDate"));
    	tableCol_faqView.setCellValueFactory(new PropertyValueFactory<FaqVO, String>("faqView"));
    	
    	
    	
    	tableCol_faqNum.setMinWidth(100);
    	tableCol_faqSubject.setMinWidth(370);
    	tableCol_faqMemid.setMinWidth(100);
    	tableCol_faqDate.setMinWidth(100);
    	tableCol_faqView.setMinWidth(100);
    	
    	
    	
    	FAQBoard.getColumns().addAll(tableCol_faqNum,
    								 tableCol_faqSubject,
    								 tableCol_faqMemid,
    								 tableCol_faqDate,
    								 tableCol_faqView
    								);
    	
    	
    	
    	FAQBoard.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.isPrimaryButtonDown() && event.getClickCount()==2) {
					
					FaqVO vo = FAQBoard.getSelectionModel().getSelectedItem();
					
					
					try {
						dao.plusView(vo.getFaqNum());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
					
					Stage stage = new Stage();
					
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FAQContent.fxml"));
					
					
					
					try {
						FlowPane pane = (FlowPane)loader.load();
						
						FAQContentController faqContentController = (FAQContentController)loader.getController();
						faqContentController.setParentController(faqBoardController);
						
						faqContentController.setBoardData(vo.getFaqNum());
						
						Scene scene = new Scene(pane, 610, 400);
						
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
    	searchCase.getItems().addAll("제목","작성자");
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
			List<FaqVO> list = dao.selectBoardList(boardTitle, boardWriter);
			ObservableList<FaqVO> boardList;
			boardList = FXCollections.observableArrayList(list);
			
			FAQBoard.setItems(boardList);
			
		} catch (SQLException e) {
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
