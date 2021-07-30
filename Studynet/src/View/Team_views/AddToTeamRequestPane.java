package View.Team_views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Controller.CurrentUser;
import Controller.FrameLoader;
import Handlers.RequestListTeam_handler;
import Handlers.RequestList_handler;
import Handlers.TeamMembers_handler;
import Model.Team;
import Model.User;
import View.ScreenHelper;

import java.awt.Color;
import java.awt.Font;

public class AddToTeamRequestPane extends JPanel {
private static final long serialVersionUID = 1L;
	
	private JPanel text;
	private JPanel buttonPane;

	private JLabel userDetails;
	
	private JButton btnAccept;
	private JButton btnDecline;
	
	public AddToTeamRequestPane(User user,Team team) {
		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		text = new JPanel();
		text.setOpaque(false);
		
		userDetails = new JLabel(user.getDetails()+" wants join "+team.getDetailis());
		userDetails.setFont(new Font("Arial", Font.PLAIN, 14));
		userDetails.setOpaque(false);
		userDetails.setForeground(Color.CYAN);
		
		text.add(userDetails);
		add(text);
		
		buttonPane = new JPanel();
		buttonPane.setOpaque(false);
		
		btnAccept = new JButton("Accept");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RequestListTeam_handler.confirmRequsetTeam(team.getIDTeam(),user.getMail());
				RequestListTeam_handler.deleteRequsetTeam(team.getIDTeam(), user.getMail());
				TeamMembers_handler.addTeamMember(user.getMail(), team.getIDTeam(), ScreenHelper.getCurrentDateTime());
				ScreenHelper.refreshAddTeamRequest();
			}
		});
		ScreenHelper.style_btn(btnAccept);
		buttonPane.add(btnAccept);
		
		
		btnDecline = new JButton("Decline");
		btnDecline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RequestListTeam_handler.deleteRequsetTeam(team.getIDTeam(), user.getMail());
				ScreenHelper.refreshAddTeamRequest();
			}
		});
		ScreenHelper.style_btn(btnDecline);
		buttonPane.add(btnDecline);
		
		add(buttonPane);
				
	}
	
	


}
