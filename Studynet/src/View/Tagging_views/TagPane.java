package View.Tagging_views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Controller.CurrentUser;
import Handlers.FriendsList_handler;
import Model.Post;
import Model.User;
import View.ScreenHelper;
import View.Post_views.AddPost;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;

public class TagPane extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JPanel content;
	
	private JLabel pic;
	private JLabel txtArea;
	private JButton btnTag;
	

	public TagPane(AddPost ap, User user, TaggingScreen ts) {
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setBackground(Color.DARK_GRAY);
		//setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		content = new JPanel();
		content.setOpaque(false);
		content.setLayout(new BoxLayout(content, BoxLayout.LINE_AXIS));

		pic = new JLabel(user.getPhoto());
		pic.setForeground(Color.CYAN);
		content.add(Box.createRigidArea(new Dimension(20, 10)));
		content.add(pic);

		txtArea = new JLabel(user.getDetails());
		txtArea.setFont(new Font("Arial", Font.PLAIN, 16));
		txtArea.setForeground(Color.CYAN);
		txtArea.setBackground(Color.DARK_GRAY);
		content.add(Box.createRigidArea(new Dimension(20, 10)));
		content.add(txtArea);
		
		btnTag = new JButton("Tag");
		btnTag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ap.tag(user);
				ts.close();
				ap.hideShare();
			}
		});
		ScreenHelper.style_btn(btnTag);	
		content.add(Box.createRigidArea(new Dimension(20, 10)));
		content.add(btnTag);

		add(content);

		
		
	}

}












