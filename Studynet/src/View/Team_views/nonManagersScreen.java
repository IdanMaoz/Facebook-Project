package View.Team_views;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Handlers.TeamMembers_handler;
import Model.Team;
import Model.User;

public class nonManagersScreen extends JFrame{

	private static final long serialVersionUID = 1L;

	private JScrollPane scroller;
	
	private JPanel contentPane;
	private JPanel headerPane;
	
	private JTextField header;
	
	private ArrayList<User> nonManagers;
	
	public nonManagersScreen(Team team, JButton relative) {
		setLocationRelativeTo(relative);
		setBackground(Color.DARK_GRAY);
		
		scroller = new JScrollPane();
		scroller.setOpaque(false);
		
		contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
		contentPane.setBackground(Color.BLUE);
		contentPane.setOpaque(false);
		
		this.setContentPane(scroller);
		
		headerPane = new JPanel();
		header = new JTextField("Choose members to assign as managers:");
		
		headerPane.add(header);
		contentPane.add(headerPane);
		
		nonManagers = TeamMembers_handler.getNonManagers(team.getIDTeam());
		setBounds(500,500,300,400);
		setVisible(true);
		
		for(User member : nonManagers) {
			AssignMemberPane amp = new AssignMemberPane(member, team, this);
			contentPane.add(amp);
		}
		scroller.setViewportView(contentPane);
		setContentPane(scroller);
		
		
	}
	
	public void close() {
		this.dispose();
	}

}











