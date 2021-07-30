package View.Tagging_views;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.CurrentUser;
import Handlers.FriendsList_handler;
import Model.Post;
import Model.User;
import View.Post_views.AddPost;

public class TaggingScreen extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<User> taggables;
	
	private JPanel contentPane;

	public TaggingScreen(AddPost ap, JButton relative) {
		
		setBackground(Color.BLUE);
		
		contentPane = new JPanel();
		contentPane.setOpaque(false);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

		taggables = FriendsList_handler.FriendsList(CurrentUser.getUsername());
		
		for(User user : taggables) {
			TagPane tp = new TagPane(ap, user, this);
			contentPane.add(tp);
			contentPane.add(Box.createRigidArea(new Dimension(20, 20)));
		}
		
		setContentPane(contentPane);
		setVisible(true);
		
		setBounds(300, 300, 500, 500);
		
	}
	
	public void close() {
		this.dispose();
	}

}
