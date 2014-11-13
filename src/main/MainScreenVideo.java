package main;

import java.io.File;

import javax.swing.JFrame;

import utilities.WAVPlayer;
import javafx.application.Application;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.*;

public class MainScreenVideo extends Application 
{	
	private static int SCREEN_WIDTH;
	private static int SCREEN_HEIGHT;
	WAVPlayer mainMusic = new WAVPlayer("libs/menu_server.wav");
	WAVPlayer selectSound = new WAVPlayer("libs/select.wav");
	
	public static void main(String[] args)
	{
		JFrame dummyFrame = new JFrame();
		
		dummyFrame.setUndecorated(true);
		dummyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dummyFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		dummyFrame.setVisible(true);
		
		SCREEN_WIDTH = dummyFrame.getWidth();
		SCREEN_HEIGHT = dummyFrame.getHeight();
		
		dummyFrame.dispose();
		
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		StackPane root = new StackPane();
		Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
		
		File file = new File("libs/animbg.mp4");
		
		Media media = new Media(file.toURI().toString());
		
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		
		mediaPlayer.setAutoPlay(true);
		
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		
		MediaView mediaView = new MediaView(mediaPlayer);
		
		((StackPane)scene.getRoot()).getChildren().add(mediaView);
		
		
		/* START */
		
		/* MENU BOX START */
		
		TilePane tP = new TilePane(Orientation.VERTICAL);
		tP.setHgap(100);
		tP.setVgap(0);
		
		Label lb = new Label("MENU");
		lb.setStyle("-fx-text-alignment:right;-fx-pref-width:280;-fx-text-fill:white;-fx-font-weight:bold;-fx-font-size:20;-fx-padding: 10;-fx-background-color:rgba(0, 0, 0, 0.5)");
		
		Label lb2 = new Label("New Game \u00BB");
		lb2.setStyle("-fx-pref-width:280;-fx-text-fill:white;-fx-font-size:13;-fx-padding:10;-fx-background-color:rgba(0, 0, 0, 0.5);-fx-text-alignment:center;");
		
		lb2.setCursor(Cursor.HAND);
		
		lb2.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				lb2.setStyle("-fx-pref-width:280;-fx-text-fill:white;-fx-font-size:13;-fx-padding:10;-fx-background-color:rgba(0, 0, 0, 0.9);-fx-text-alignment:center;");
				selectSound.playOnce();
			}
		});

		lb2.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				lb2.setStyle("-fx-pref-width:280;-fx-text-fill:white;-fx-font-size:13;-fx-padding:10;-fx-background-color:rgba(0, 0, 0, 0.5);-fx-text-alignment:center;");
			}
		});
		
		Label lb3 = new Label("Find Game \u00BB");
		lb3.setStyle("-fx-pref-width:280;-fx-text-fill:white;-fx-font-size:13;-fx-padding:10;-fx-background-color:rgba(0, 0, 0, 0.5);-fx-text-alignment:center;");
		
		lb3.setCursor(Cursor.HAND);
		
		lb3.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				lb3.setStyle("-fx-pref-width:280;-fx-text-fill:white;-fx-font-size:13;-fx-padding:10;-fx-background-color:rgba(0, 0, 0, 0.9);-fx-text-alignment:center;");
				selectSound.playOnce();
			}
		});

		lb3.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				lb3.setStyle("-fx-pref-width:280;-fx-text-fill:white;-fx-font-size:13;-fx-padding:10;-fx-background-color:rgba(0, 0, 0, 0.5);-fx-text-alignment:center;");
			}
		});
		
		Label lb4 = new Label("Options \u00BB");
		lb4.setStyle("-fx-pref-width:280;-fx-text-fill:white;-fx-font-size:13;-fx-padding:10;-fx-background-color:rgba(0, 0, 0, 0.5);-fx-text-alignment:center;");
		
		lb4.setCursor(Cursor.HAND);
		
		lb4.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				lb4.setStyle("-fx-pref-width:280;-fx-text-fill:white;-fx-font-size:13;-fx-padding:10;-fx-background-color:rgba(0, 0, 0, 0.9);-fx-text-alignment:center;");
				selectSound.playOnce();
			}
		});

		lb4.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				lb4.setStyle("-fx-pref-width:280;-fx-text-fill:white;-fx-font-size:13;-fx-padding:10;-fx-background-color:rgba(0, 0, 0, 0.5);-fx-text-alignment:center;");
			}
		});
		
		
		lb2.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0)
			{
				dialog("LOL");
			}
		});
		
		tP.getChildren().addAll(lb, lb2, lb3, lb4);
		
		
		/* MENU BOX END */ 
		
		ImageView label = new ImageView(new Image("http://java-et.org/images/javaet_scaled_500_opacity.png"));

		StackPane leftMenuContainer = new StackPane();
		
		StackPane.setAlignment(leftMenuContainer, Pos.TOP_LEFT);
		
		leftMenuContainer.setMaxHeight(SCREEN_HEIGHT);
		
		leftMenuContainer.setMaxWidth(SCREEN_WIDTH * 0.35);
		
		leftMenuContainer.setStyle("-fx-background-color:rgba(255, 255, 255, 0);-fx-padding:"+(SCREEN_HEIGHT*0.20)+";");

		leftMenuContainer.getChildren().addAll(tP);
		
		StackPane.setAlignment(tP, Pos.TOP_CENTER);
		
		StackPane overlay = new StackPane();
		overlay.setMaxHeight(SCREEN_HEIGHT);
		overlay.setMaxWidth(SCREEN_WIDTH);
		overlay.setStyle("-fx-background-color:rgba(255, 255, 255, 0.02)");
		
		
		StackPane layout = new StackPane();
		layout.getChildren().addAll(mediaView, label, overlay, leftMenuContainer);
		
		stage.setScene(new Scene(layout));
		
		stage.setMaximized(true);
		stage.initStyle(StageStyle.UNDECORATED);
		
		stage.show();
		
		mainMusic.loopPlay();
		
		/* END */
		
		
	}
	
	@SuppressWarnings("deprecation")
	public void dialog(String msg)
	{
		Stage dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.setScene(new Scene(VBoxBuilder.create().
		    children(new Text("Hi"), new Button("Ok.")).
		    alignment(Pos.CENTER).padding(new Insets(5)).build()));
		dialogStage.show();
	}
	
	public void stop(Stage stage)
	{
		mainMusic.stopLoop();
	}
}
