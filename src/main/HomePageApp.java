package main;

import java.io.IOException;

import org.apache.log4j.helpers.Loader;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class HomePageApp extends Application {

	double x;
	double y;
	public static Stage mediaStage;
	
	@Override
	public void start(Stage Stage) throws Exception {
//		ClassLoader loader = Thread.currentThread().getContextClassLoader();
//		Pane root = FXMLLoader.load(loader.getResource("fxml/HomePage.fxml"));

		Stage.setResizable(false);
		FXMLLoader fxml = new FXMLLoader(getClass().getResource("/fxml/HomePage.fxml"));
		Pane root = fxml.load(); 
		Scene scene = new Scene(root);
		Stage.setScene(scene);
		Stage.setX(x+200);
		Stage.setY(x+100);
		Stage.show();
		
		mediaStage();
		
		
	}
	
	public void mediaStage(){
		System.out.println(x);
		System.out.println(y);
		try {
			mediaStage = new Stage(StageStyle.UTILITY);
			//twoStage.initModality(Modality.APPLICATION_MODAL);
			
			FXMLLoader fxml = new FXMLLoader(getClass().getResource("/fxml/MediaView.fxml"));
			AnchorPane pane = (AnchorPane) fxml.load();
			Scene scene = new Scene(pane);
			
			mediaStage.setScene(scene);
			mediaStage.setX(x+1240);
			mediaStage.setY(y+100);
			mediaStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch();
	}

}

