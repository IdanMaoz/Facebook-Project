package View.Team_views;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Controller.CurrentUser;
import Handlers.Manager_handler;
import View.ScreenHelper;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ManagerRegistrationScreen extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JScrollPane scroller;
	
	private JPanel content;
	private JPanel btnPane;
	
	private JLabel header;
	private JLabel IDHeader;
	private JLabel descriptionHeader;
	
	private JTextField IDField;

	private JTextArea description;
	
	private JButton btnSubmit;
	
	public ManagerRegistrationScreen() {
		scroller = new JScrollPane();
		
		content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
		
		JPanel headerPane = new JPanel();
		headerPane.setOpaque(false);
		header = new JLabel("Please fill in this form:");
		header.setForeground(Color.white);
		headerPane.add(header);
		content.add(headerPane);
		content.setBackground(Color.BLUE);
		
		JPanel IDHeaderPane = new JPanel();
		IDHeaderPane.setOpaque(false);
		IDHeader = new JLabel("Please fill in your ID");
		IDHeader.setForeground(Color.white);
		
		IDHeaderPane.add(IDHeader);
		content.add(IDHeaderPane);
		
		JPanel IDPane = new JPanel();
		IDPane.setOpaque(false);
		IDPane.setLayout(new BoxLayout(IDPane, BoxLayout.LINE_AXIS));
		
		IDPane.add(Box.createRigidArea(new Dimension(100, 20)));
		
		IDField = new JTextField();
		IDPane.add(IDField);
		
		IDPane.add(Box.createRigidArea(new Dimension(100, 20)));
		
		content.add(IDPane);
		
		JPanel descPane = new JPanel();
		descPane.setOpaque(false);
		
		descriptionHeader = new JLabel("Please fill in a short description of yourself:");
		descriptionHeader.setForeground(Color.white);
		descPane.add(descriptionHeader);
		content.add(descPane);
		
		JPanel descriptionPane = new JPanel();
		descriptionPane.setOpaque(false);
		descriptionPane.setLayout(new BoxLayout(descriptionPane, BoxLayout.LINE_AXIS));
		
		descriptionPane.add(Box.createRigidArea(new Dimension(100, 20)));
		
		description = new JTextArea();
		descriptionPane.add(description);
		
		descriptionPane.add(Box.createRigidArea(new Dimension(100, 20)));
		
		content.add(descriptionPane);
		
		btnPane = new JPanel();
		btnPane.setOpaque(false);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				Manager_handler.addManager(CurrentUser.getUsername(), IDField.getText(), description.getText());
				close();
			}
		});
		ScreenHelper.style_btn(btnSubmit);
		btnPane.setForeground(Color.BLACK);
		btnPane.add(btnSubmit);
		content.add(btnPane);
		
		
		scroller.setViewportView(content);
		this.setContentPane(scroller);
		
		setVisible(true);
		setBounds(500, 300, 500, 300);
		
	}
	
	public void close() {
		this.dispose();
	}
	

}










