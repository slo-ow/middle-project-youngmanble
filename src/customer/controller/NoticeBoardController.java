package customer.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import customer.dao.NoticeDAO;
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
import vo.NoticeVO;

public class NoticeBoardController implements Initializable {

    @FXML
    private Button btnSearch;

    @FXML
    private ComboBox searchCase;

    @FXML
    private TextField txtSearch;

    @FXML
    private TableView<NoticeVO> noticeBoard;

    @FXML
    private Pane noticeBoardPane;

    @FXML
    private ImageView backImage;

    
    
    
    private NoticeDAO dao = new NoticeDAO();
    
    private NoticeBoardController noticeBoardController = this;
    
    
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	System.out.println("initialize");
    	
    	setTableView();
    	
    	setComboBox();
    	
    	setTableData(null, null);
    	
    	
    }
    
    
    
    
    
    private void setTableView() {
    	TableColumn<NoticeVO, String> tableCol_ntcNum = new TableColumn<>("글번호");
    	TableColumn<NoticeVO, String> tableCol_ntcSubject = new TableColumn<>("제목");
    	TableColumn<NoticeVO, String> tableCol_ntcMemid = new TableColumn<>("작성자");
    	TableColumn<NoticeVO, String> tableCol_ntcDate = new TableColumn<>("작성일자");
    	TableColumn<NoticeVO, String> tableCol_ntcView = new TableColumn<>("조회수");
    	
    	
    	
    	tableCol_ntcNum.setCellValueFactory(new PropertyValueFactory<NoticeVO, String>("ntcNum"));
    	tableCol_ntcSubject.setCellValueFactory(new PropertyValueFactory<NoticeVO, String>("ntcSubject"));
    	tableCol_ntcMemid.setCellValueFactory(new PropertyValueFactory<NoticeVO, String>("ntcMemid"));
    	tableCol_ntcDate.setCellValueFactory(new PropertyValueFactory<NoticeVO, String>("ntcDate"));
    	tableCol_ntcView.setCellValueFactory(new PropertyValueFactory<NoticeVO, String>("ntcView"));
    	
    	
    	tableCol_ntcNum.setMinWidth(100);
    	tableCol_ntcSubject.setMinWidth(370);
    	tableCol_ntcMemid.setMinWidth(100);
    	tableCol_ntcDate.setMinWidth(100);
    	tableCol_ntcView.setMinWidth(100);
    	
    	
    	noticeBoard.getColumns().addAll(tableCol_ntcNum,
    									tableCol_ntcSubject,
    									tableCol_ntcMemid,
    									tableCol_ntcDate,
    									tableCol_ntcView
    	);
    	
    	
    	
    	noticeBoard.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.isPrimaryButtonDown() && event.getClickCount()==2) {
					
					NoticeVO vo = noticeBoard.getSelectionModel().getSelectedItem();
					
					
					try {
						dao.plusView(vo.getNtcNum());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
					Stage stage = new Stage();
					
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/NoticeContent.fxml"));
					
					try {
						FlowPane pane = (FlowPane)loader.load();
						
						NoticeContentController noticeContentController = (NoticeContentController)loader.getController();
						noticeContentController.setParentController(noticeBoardController);
						
						noticeContentController.setBoardData(vo.getNtcNum());
						
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
			List<NoticeVO> list = dao.selectBoardList(boardTitle, boardWriter);
			ObservableList<NoticeVO> boardList;
			boardList = FXCollections.observableArrayList(list);
			
			noticeBoard.setItems(boardList);
			
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
