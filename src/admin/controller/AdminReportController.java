package admin.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import admin.dao.AdminReportDAO;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import vo.ReportVO;

public class AdminReportController implements Initializable {
	
	@FXML
	private Button btnBlackList;
	
    @FXML
    private TextField tfReportId;

    @FXML
    private Button btnAdd;

    @FXML
    private TableColumn<ReportVO, String> colReportTitle;

    @FXML
    private TableView<ReportVO> ReportTable;

    @FXML
    private TableColumn<ReportVO, String> colSuspId;

    @FXML
    private TableColumn<ReportVO, String> colVIcId;

    @FXML
    private TableColumn<ReportVO, String> colDate;
    
    @FXML
    private TableColumn<ReportVO, String> colProst;
    
    @FXML
    private TableColumn<ReportVO, String> colNum;

    @FXML
    private Button btnClear;
    
    
    @FXML
    void add(ActionEvent event) {
    	String repoId = tfReportId.getText();
    	try {
			dao.updateBoard(repoId);
			System.out.println("잘 수행되었습니다");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void clear(ActionEvent event) {
    	ReportVO vo = ReportTable.getSelectionModel().getSelectedItem();
    	System.out.println(tfReportId.getText());
    	try {
			dao.deleteBoard(tfReportId.getText());
			ReportTable.getItems().remove(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void black(ActionEvent event) {
    	ReportVO vo = ReportTable.getSelectionModel().getSelectedItem();
    	try {
			dao.insertBlack(tfReportId.getText());
			dao.deleteBoard(tfReportId.getText());
			ReportTable.getItems().remove(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    private AdminReportDAO dao = new AdminReportDAO();
    
    private AdminReportController adminReportController = this;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("initialize");
		setTables();
		setTableData(null, null);
		
	}

	private void setTables() {
		colVIcId.setCellValueFactory(new PropertyValueFactory<>("rpt_trgter_id"));
		colReportTitle.setCellValueFactory(new PropertyValueFactory<>("rpt_title"));
		colSuspId.setCellValueFactory(new PropertyValueFactory<>("rpt_mem_id"));
		colDate.setCellValueFactory(new PropertyValueFactory<>("rpt_resist_day"));
		colProst.setCellValueFactory(new PropertyValueFactory<>("rpt_prost"));
		colNum.setCellValueFactory(new PropertyValueFactory<>("rpt_num"));
		
		ReportTable.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.isPrimaryButtonDown()&&event.getClickCount()==2){
					ReportVO vo = ReportTable.getSelectionModel().getSelectedItem();
					
					System.out.println(vo);
					tfReportId.setText(vo.getRpt_num());
					
					Stage stage = new Stage();
				
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AdminReportCn.fxml"));
					
					try {
						Pane pane = loader.load();
						
						AdminReportCnController adminReportCnController = (AdminReportCnController)loader.getController();
						
						adminReportCnController.setParent(adminReportController);
						//System.out.println(vo.getRpt_trgter_id());
						adminReportCnController.setBoardData(vo.getRpt_num());
						
						Scene scene = new Scene(pane,571,600);
						
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
	
	public void setTableData(String repoId,String reportTitle){
		
		List<ReportVO> list;
		try {
			list = dao.selectBoardList(repoId, reportTitle);
			ObservableList<ReportVO> repoList;
			repoList = FXCollections.observableArrayList(list);
			
			ReportTable.setItems(repoList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	

}
