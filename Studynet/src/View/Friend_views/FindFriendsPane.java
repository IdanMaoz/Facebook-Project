package View.Friend_views;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import Controller.CurrentUser;
import Handlers.FriendsList_handler;
import Handlers.RequestList_handler;
import Model.User;
import View.ScreenHelper;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class FindFriendsPane extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JButton btnAddFriend;	
	
	public FindFriendsPane(User user) {
		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		JLabel pic = new JLabel(user.getPhoto());
		//pic.setIcon(new ImageIcon(FindFriendsPane.class.getResource("/Images/logoimg(1).jpg")));
		pic.setBounds(1, 1, 50, 50);
		add(pic);
		
		JTextArea txtUserhead = new JTextArea(user.getDetails());
		txtUserhead.setForeground(Color.CYAN);
		txtUserhead.setFont(new Font("Arial", Font.PLAIN, 16));
		txtUserhead.setBounds(pic.getX() + pic.getWidth() + 10, pic.getY(), 350, 50);
		txtUserhead.setOpaque(false);
		txtUserhead.setEditable(false);
		add(txtUserhead);
		
		btnAddFriend = new JButton("Add");
		btnAddFriend.setBounds(txtUserhead.getX() + txtUserhead.getWidth(), pic.getY(), 115, 30);
		ScreenHelper.style_btn(btnAddFriend);
		add(btnAddFriend);
		btnAddFriend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RequestList_handler.addRequest(CurrentUser.getUsername(), user.getMail(), ScreenHelper.getCurrentDate());
				JOptionPane.showMessageDialog(null, "You sent friend request to "+ user.getFirstName()+" "+user.getLastName());
				ScreenHelper.refreshRequestList();
			}
		});
		
		if(RequestList_handler.isInRequestList(CurrentUser.getUsername(), user.getMail())) {
			btnAddFriend.setVisible(false);
			JPanel lblPane = new JPanel();
			lblPane.setOpaque(false);
			JLabel label=new JLabel("Sent");
			txtUserhead.setOpaque(false);
			label.setForeground(Color.cyan);
			lblPane.add(label);
			add(lblPane);
		}
	}


}
