package main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class ETConsole {
	private JFrame window = new JFrame("Java-ET Console");
	private JPanel masterPanel = new JPanel();
	private JPanel childPanel = new JPanel();
	private JTextArea textArea = new JTextArea("", 20, 48);
	private JPanel panel2 = new JPanel();
	private JTextField inputField = new JTextField(48);
	private JScrollPane scrollPane = new JScrollPane(textArea);
	
	public ETConsole() {		
		window.setIconImage(ClientCore.getIcon().getImage());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(560, 490);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		
		childPanel.setLayout(new BoxLayout(childPanel, BoxLayout.PAGE_AXIS));
		
		textArea.setEditable(false);
		textArea.setBackground(new Color(204, 204, 204));
		
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		childPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		childPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		childPanel.add(scrollPane);
		
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
		panel2.add(inputField);
		panel2.add(Box.createRigidArea(new Dimension(0, 5)));
		panel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		masterPanel.add(childPanel);
		masterPanel.add(panel2);
		
		window.add(masterPanel);
		window.setVisible(true);
	}
	
	public void print(Object text) {
		textArea.append((String) text);
		textArea.setCaretPosition(textArea.getText().length() - 1);
	}
	
	public void println(Object text) {
		textArea.append(text + "\n");
		textArea.setCaretPosition(textArea.getText().length() - 1);
	}
	
}
