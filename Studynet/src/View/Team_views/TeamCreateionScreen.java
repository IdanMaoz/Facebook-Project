package View.Team_views;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import Controller.CurrentUser;
import Handlers.Team_handler;
import View.ScreenHelper;

public class TeamCreateionScreen extends JFrame{

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JPanel btnPane;
	
	private JTextField header;
	
	private JTextField groupName;
	
	private JButton btnCreate;
	
	public TeamCreateionScreen() {
		
		
		contentPane = new JPanel();
		btnPane = new JPanel();
		
		setContentPane(contentPane);
		setBounds(800, 300, 300, 150);
		setBackground(Color.BLUE);
		
		contentPane.setOpaque(false);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
		
		btnPane.setOpaque(false);
		
		header = new JTextField(" Enter group name here:");
		header.setFont(new Font("Arial", Font.PLAIN, 18));
		header.setForeground(Color.white);
		header.setOpaque(false);
		header.setEditable(false);
		
		contentPane.add(header);
		
		groupName = new JTextField();
		groupName.setHorizontalAlignment(SwingConstants.CENTER);
		groupName.setFont(new Font("Calibri Light", Font.BOLD, 24));
		
		contentPane.add(groupName);
		
		btnCreate = new JButton("Create");
		btnCreate.setFont(new Font("Candara Light", Font.PLAIN, 16));
		ScreenHelper.style_btn(btnCreate);
		btnPane.add(btnCreate);
		
		contentPane.add(btnPane);
	
		setVisible(true);
		
		createEvents();
	}
	
	public void createEvents() {
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Team_handler.addTeam(groupName.getText(), CurrentUser.getUsername(), ScreenHelper.getCurrentDateTime());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				close();
			}
		});
		
	}
	
	public void close() {
		this.dispose();
	}

}







