package View.Team_views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Controller.CurrentUser;
import Handlers.TeamManager_handler;
import Model.Team;
import Model.User;
import View.ScreenHelper;

public class MyTeamsPane extends JPanel {
private static final long serialVersionUID = 1L;
	
	private JTextArea txtArea;
	
	private JButton btnAssign;
	
	public MyTeamsPane(Team team) {
		
		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		txtArea = new JTextArea(team.getDetailis());
		txtArea.setOpaque(false);
		txtArea.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		txtArea.setForeground(Color.CYAN);
		
		ArrayList<User> arr = TeamManager_handler.findTeamManagers(team.getIDTeam());
		String managers = "";
		for(User user : arr) {
			managers += user.getDetails() + "\n";
		}
		
		txtArea.setText(txtArea.getText() + managers);
		
		add(txtArea);
		if(TeamManager_handler.isManagerOf(CurrentUser.getUsername(), team.getIDTeam())) {
			btnAssign = new JButton("Assign");
			ScreenHelper.style_btn(btnAssign);
			
			btnAssign.addActionListener(new ActionListener() {		
				@Override
				public void actionPerformed(ActionEvent e) {
					nonManagersScreen nms = new nonManagersScreen(team,btnAssign);
				}
			});
			
			
			add(btnAssign);
		}
	}
}
