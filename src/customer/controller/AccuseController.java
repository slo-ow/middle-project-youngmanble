package customer.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import customer.dao.AccuseDAO;
import home.controller.HomePageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import vo.MemberVO;
import vo.ReportVO;

public class AccuseController implements Initializable{

    @FXML
    private TextArea txtContents;

    @FXML
    private Pane accusePane;

    @FXML
    private TextField txtName;

    @FXML
    private CheckBox useInfo;

    @FXML
    private ImageView backImage;

    @FXML
    private TextField txtTitle;

    
    public static Pane targetPane;
    
    
    
    
    private AccuseDAO dao = new AccuseDAO();
    
    
    
    
    
    @FXML
    void useInfo(ActionEvent event) {

    }

    
    
    
    
    
    
    
    @FXML
    void apply(ActionEvent event) {
    	ReportVO vo = new ReportVO();
    	
    	MemberVO logVO = HomePageController.session;
    	
    	System.out.println(logVO.getMem_id());
    	
    	vo.setRpt_mem_id(txtName.getText());
    	vo.setRpt_title(txtTitle.getText());
    	vo.setRpt_cn(txtContents.getText());
    	vo.setRpt_trgter_id(logVO.getMem_id());
    	
    	try {
			dao.insertMember(vo);
			
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("신고 접수");
			alert.setHeaderText("신고 접수 되었습니다.");
			
			alert.show();
			
		} catch (SQLException e) {
			
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("신고 접수 오류");
			alert.setHeaderText("기존에 없는 아이디입니다.");
			
			alert.show();
			
			e.printStackTrace();
		}
    	
    	
    	
    	
    	targetPane.getChildren().clear();
    	
    	
    }

    
    
    
    
    
    
    @FXML
    void cancel(ActionEvent event) {
    	targetPane.getChildren().clear();
    }

    
    
    
    
    
    
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		targetPane = accusePane;
	}

}
