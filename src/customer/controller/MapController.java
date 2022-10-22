package customer.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class MapController {

    @FXML
    private Label laMap;

    @FXML
    private WebView wb;
    
    @FXML
    void gomap(MouseEvent event) {
    	//laMap.setDisable(true);    	
    	laMap.setVisible(false);
    	String loca = getClass().getResource("../../map/Youngmap.html").toString();
    	WebEngine engine = wb.getEngine();
		engine.load(loca);
    }
    
    
}
