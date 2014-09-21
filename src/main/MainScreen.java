package main;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Dialog.ModalityType;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import utilities.WAVPlayer;

public class MainScreen extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final String BACKGROUND_IMAGE = "libs/background.jpg";
	private static final String FRAME_TITLE = "Java-ET";
	
	private Image mainImage = null;
	private Image scaledImage = null ;
	
	public MainScreen(int width, int height)
	{
		this.setBackground(Color.lightGray);
		this.setPreferredSize(new Dimension(width, height));
		this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		
		JDialog browser = new JDialog((JFrame) this.getParent(), FRAME_TITLE);
		
		browser.setModalityType(ModalityType.APPLICATION_MODAL);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		
		browser.setSize(toolkit.getScreenSize());
		
		try {
			
			mainImage = ImageIO.read(new File(BACKGROUND_IMAGE));
			
			mainImage = mainImage.getScaledInstance(this.getPreferredSize().width, 
					this.getPreferredSize().height, 
					Image.SCALE_REPLICATE);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e)
			{
				scaledImage = mainImage.getScaledInstance(
						MainScreen.this.getWidth(),
						MainScreen.this.getHeight(),
						Image.SCALE_REPLICATE
						);
				MainScreen.this.repaint();
				super.componentResized(e);
				
			}
		});
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if(scaledImage == null)
			g2d.drawImage(mainImage, 0, 0, this);
		else {
			g2d.drawImage(scaledImage, 0, 0, this);
		}
		
		g2d.dispose();
	}
	
	public static void createMainScreen()
	{
		JFrame mainFrame = new JFrame(FRAME_TITLE);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().add(new MainScreen(1920, 1080));
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainFrame.setUndecorated(true);
		mainFrame.pack();
		mainFrame.setVisible(true);
		
		JButton exitButton = new JButton("Quit");
		
		exitButton.setBounds(100,100,100,25);
		mainFrame.add(exitButton);
		
		ActionListener exitButtonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.dispose();
				System.exit(0);
			}
		};
		
		exitButton.addActionListener(exitButtonListener);
		
		
		WAVPlayer mainScreenPlayer = new WAVPlayer("libs/menu_server.wav");
		mainScreenPlayer.loopPlay();
	}
	
	public static void main(String[] args)
	{
		MainScreen.createMainScreen();
	}
}
