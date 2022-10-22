package admin.controller;

import java.sql.SQLException;

import admin.dao.AdminReportDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import vo.ReportVO;

public class AdminReportCnController {

    @FXML
    private Pane cnPane;

    @FXML
    private Button btnBack;

    @FXML
    private TextField tfTitle;

    @FXML
    private Label laMemId;

    @FXML
    private Label laReId;
    
    @FXML
    private Label laNum;

    @FXML
    private TextArea taCn;
    
    @FXML
    void back(ActionEvent event) {
    	Stage stage = (Stage) laMemId.getScene().getWindow();
    	stage.close();
    }
    
    private AdminReportDAO dao = new AdminReportDAO();
    private AdminReportController parent;

	public void setParent(AdminReportController adminReportController) {
		this.parent = adminReportController;
	}

	public void setBoardData(String repoNum) {
		try {
			System.out.println(repoNum);
			ReportVO vo = dao.selectBoard(repoNum);
			System.out.println(vo);
			laNum.setText(vo.getRpt_num());
			laReId.setText(vo.getRpt_trgter_id());
			laMemId.setText(vo.getRpt_mem_id());
			tfTitle.setText(vo.getRpt_title());
			taCn.setText(vo.getRpt_cn());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
