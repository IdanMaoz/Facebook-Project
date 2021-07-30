package View.Team_views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Handlers.TeamManager_handler;
import Model.Team;
import Model.User;
import View.ScreenHelper;

public class AssignMemberPane extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JButton btnAssign;
	
	private JLabel txtArea;
	
	public AssignMemberPane(User user, Team team, nonManagersScreen screen) {
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		txtArea = new JLabel(user.getDetails());
		txtArea.setForeground(Color.BLACK);
		add(txtArea);
		
		btnAssign = new JButton("Assign");
		btnAssign.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				TeamManager_handler.assignManagerToTeam(user.getMail(), team.getIDTeam());
				ScreenHelper.refreshTeamRequests();
				screen.close();
			}
		});
		ScreenHelper.style_btn(btnAssign);
		btnAssign.setForeground(Color.BLACK);
		
		add(btnAssign);
		
		
		
		
		
	}

}
