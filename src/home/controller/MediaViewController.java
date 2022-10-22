package home.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.HomePageApp;

public class MediaViewController implements Initializable{
	
    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    private AnchorPane root;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label labelTime;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnPause;

    @FXML
    private Slider sliderVolume;

    @FXML
    private MediaView mediaView;
    
    private boolean endOfMedia;
    
    public static AnchorPane targetPane;
    
    public static MediaPlayer mediaPlayer;
    @Override
   	public void initialize(URL location, ResourceBundle resources) {
    	
    	targetPane = root;
    	
   		// 미디어 객체 생성
   		Media media = new Media(getClass().getResource("/media/game1.mp4").toString());

   		// 미디어 플레이어 생성 및 미디어 뷰에 설정
   		mediaPlayer = new MediaPlayer(media);
   		mediaView.setMediaPlayer(mediaPlayer);

   		// 해당 상태가 되면 실행할 Runnable 설정
   		mediaPlayer.setOnReady(new Runnable() {
   			@Override
   			public void run() {
   				mediaPlayer.play();
   				mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
					@Override
					public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
						double progress = mediaPlayer.getCurrentTime().toSeconds() / mediaPlayer.getTotalDuration().toSeconds();
						progressBar.setProgress(progress);
						progressIndicator.setProgress(progress);
						labelTime.setText(
							(int)mediaPlayer.getCurrentTime().toSeconds()+"/"+
							(int)mediaPlayer.getTotalDuration().toSeconds()+" sec");	
					}
				});

				btnPlay.setDisable(true); 
				btnPause.setDisable(false);
				
				if(mediaPlayer.isAutoPlay()) {
					mediaView.setVisible(false);
				}
   				
   			} // run end
   		});//OnReady end
   		
   		mediaPlayer.setOnPlaying(()->{
			btnPlay.setDisable(true); 
			btnPause.setDisable(false); 
		});
		
		mediaPlayer.setOnPaused(()->{
			btnPlay.setDisable(false); 
			btnPause.setDisable(true); 
		});
		
		mediaPlayer.setOnEndOfMedia(()->{
			progressBar.setProgress(1.0);
			progressIndicator.setProgress(1.0);	
			endOfMedia = true;
			btnPlay.setDisable(false);
			btnPause.setDisable(true);
		});
		
		sliderVolume.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				mediaPlayer.setVolume(sliderVolume.getValue() / 100.0);
			}
		});
		sliderVolume.setValue(50.0);
		

		btnPlay.setOnAction(event->{
			if(endOfMedia) { 
				mediaPlayer.stop();
				mediaPlayer.seek(mediaPlayer.getStartTime());
			}
			mediaPlayer.play();
			endOfMedia = false;
		});
		btnPause.setOnAction(event->mediaPlayer.pause());
		
		
    }//initialize end
    
    public void closeMedia(){
    	mediaPlayer.setVolume(0);
    	HomePageApp hp = new HomePageApp();    	
    	Stage stage = (Stage) hp.mediaStage.getScene().getWindow();
		stage.close();
    }
}
