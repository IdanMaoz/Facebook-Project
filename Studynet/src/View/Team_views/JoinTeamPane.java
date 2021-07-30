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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Controller.CurrentUser;
import Handlers.Manage_handler;
import Handlers.Manager_handler;
import Handlers.RequestListTeam_handler;
import Handlers.TeamManager_handler;
import Handlers.TeamMembers_handler;
import Model.Manage;
import Model.Team;
import Model.User;
import View.ScreenHelper;

public class JoinTeamPane extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JButton btnAddTeam;
	
	public JoinTeamPane(Team team) {
		
		//setBackground(Color.BLUE);
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		setOpaque(false);
		
		JTextArea txtTeamHead=new JTextArea(team.getDetailis());
		//txtTeamHead.setBackground(Color.BLUE);
		txtTeamHead.setOpaque(false);
		txtTeamHead.setForeground(Color.cyan);
		txtTeamHead.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		txtTeamHead.setEditable(false);
		
		ArrayList<User> managers = TeamManager_handler.findTeamManagers(team.getIDTeam());
		String manager_details = "";
		for(User user : managers) {
			manager_details += user.getDetails();			
		}
		
		txtTeamHead.setText(txtTeamHead.getText() + manager_details);
		
		add(txtTeamHead);
		
		
		btnAddTeam=new JButton("Join");
		btnAddTeam.setBounds(txtTeamHead.getX(),txtTeamHead.getY(), 115, 30);
		ScreenHelper.style_btn(btnAddTeam);
		btnAddTeam.setForeground(Color.cyan);
		
		add(btnAddTeam);
		btnAddTeam.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//RequestListTeam_handler.addRequestTeam(team.getIDTeam(),Manager_handler.findTeamManager(team.getIDTeam()) , ScreenHelper.getCurrentDate(), CurrentUser.getUsername());
				try {
					ArrayList<String> mails=Manage_handler.manageInTeam(team.getIDTeam());
					for(String mail:mails) {
						RequestListTeam_handler.addRequestTeam(team.getIDTeam(),mail, ScreenHelper.getCurrentDate(), CurrentUser.getUsername());
						JOptionPane.showMessageDialog(null, "You sent request to join "+ team.getName());
					}
					ScreenHelper.refreshJoinGroups();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		if(RequestListTeam_handler.isInRequestList(CurrentUser.getUsername(), team.getIDTeam())) {
			btnAddTeam.setVisible(false);
			JLabel label=new JLabel("Sent");
			label.setBounds(txtTeamHead.getX(), txtTeamHead.getY(), 115, 30);
			txtTeamHead.setOpaque(false);
			label.setForeground(Color.cyan);
			add(label);
		}
	}

}
