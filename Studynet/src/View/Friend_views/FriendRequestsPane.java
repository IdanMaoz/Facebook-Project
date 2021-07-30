package View.Friend_views;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Controller.CurrentUser;
import Handlers.RequestList_handler;
import Model.User;
import View.ScreenHelper;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class FriendRequestsPane extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JPanel txtPane;
	private JPanel btnPane;
	
	private JLabel userDetails;
	
	private JButton btnAccept;
	private JButton btnDecline;
	
	public FriendRequestsPane(User user) {
		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		txtPane = new JPanel();
		txtPane.setOpaque(false);
		
		userDetails = new JLabel(user.getDetails());
		userDetails.setFont(new Font("Arial", Font.PLAIN, 14));
		userDetails.setForeground(Color.CYAN);
		
		txtPane.add(userDetails);
		
		btnPane = new JPanel();
		btnPane.setOpaque(false);
		
		btnAccept = new JButton("Accept");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RequestList_handler.confirmRequset(user.getMail(), CurrentUser.getUsername());
				RequestList_handler.deleteRequset(CurrentUser.getUsername(), user.getMail());
				ScreenHelper.refreshFriendRequests();
			}
		});
		ScreenHelper.style_btn(btnAccept);
		btnPane.add(btnAccept);
		
		
		btnDecline = new JButton("Decline");
		btnDecline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RequestList_handler.deleteRequset(CurrentUser.getUsername(), user.getMail());
				ScreenHelper.refreshFriendRequests();
			}
		});
		ScreenHelper.style_btn(btnDecline);
		btnPane.add(btnDecline);
		
		add(txtPane);
		add(btnPane);
				
	}
	
	

}
