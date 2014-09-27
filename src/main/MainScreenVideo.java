package main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import utilities.WAVPlayer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
//import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainScreenVideo {
	
	private static StackPane mvPane = new StackPane();
	private static int SCREEN_WIDTH;
	private static int SCREEN_HEIGHT;
	
	private static void initFxLater(JFXPanel panel)
	{
		StackPane root = new StackPane();
		Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
		
		File file = new File("libs/animbg.mp4");
		
		Media media = new Media(file.toURI().toString());
		
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		
		mediaPlayer.setAutoPlay(true);
		
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		
		MediaView mediaView = new MediaView(mediaPlayer);
		
		((StackPane)scene.getRoot()).getChildren().add(mediaView);
		/*
		HBox hB = new HBox();
		
		
		Button exitButton = new Button("Quit");
		
		bP.getChildren().add(exitButton);
		
		
		
		((StackPane)scene.getRoot()).getChildren().add(bP);
		
		*/
		panel.setScene(scene);
		
	}
	
	private static JFrame initSwingLater()
	{
		JFrame mainFrame = new JFrame("Java-ET");
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setUndecorated(true);
		SCREEN_WIDTH = mainFrame.getWidth();
		SCREEN_HEIGHT = mainFrame.getHeight();
		
		final JFXPanel fxPanel = new JFXPanel();
		
		mainFrame.add(fxPanel);

		mainFrame.setComponentZOrder(fxPanel, 0);
		
		Platform.runLater(new Runnable() {
			public void run()
			{
				initFxLater(fxPanel);
			}
		});
		mainFrame.setVisible(true);
		
		return mainFrame;
	}
	
	public static void main(String[] args)
	{
		initSwingLater();
		
		WAVPlayer mainMusic = new WAVPlayer("libs/menu_server.wav");
		mainMusic.loopPlay();
	}
}

class TransparentJPanel extends JPanel
{
	public TransparentJPanel()
	{
		this.setBackground(new Color(0, 0, 0, 0));
		this.setOpaque(false);
	}
}