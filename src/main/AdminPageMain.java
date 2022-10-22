package main;

import java.io.IOException;

import org.apache.log4j.helpers.Loader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminPageMain extends Application {

	@Override
	public void start(Stage Stage) throws Exception {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Pane root = FXMLLoader.load(loader.getResource("fxml/AdminPage.fxml"));

		//Stage.setResizable(false);

		Scene scene = new Scene(root);
		Stage.setScene(scene);
		Stage.show();
		
	}
	public static void main(String[] args) {
		launch();
	}

}
