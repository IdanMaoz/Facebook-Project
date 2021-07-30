package View.Liking_views;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


import Handlers.LikingComment_handler;
import Handlers.LikingPost_handler;
import Model.Post;
import Model.User;
import Model.Comment;
public class UsersWhoLikedFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private JScrollPane scroller;
	private JPanel contentPane;
	
	private ArrayList<User> users;
	
	public UsersWhoLikedFrame(Post p, JButton relative) {
		setLocationRelativeTo(relative);
		scroller = new JScrollPane();
		
		
		contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
		
		users = LikingPost_handler.findPostLikers(p.getID());
		int count = 0;
		for(User user : users) {
			count++;
			LikedUser lu = new LikedUser(user, p);
			contentPane.add(lu);
		}
		if(count < 2)
			setSize(350, 80);
		else
			setSize(350, 60*count);
		
		scroller.setViewportView(contentPane);
		add(scroller);
	}
	
	public UsersWhoLikedFrame(Comment  cmt, JButton relative) {
		setLocationRelativeTo(relative);
		
		scroller = new JScrollPane();
		
		contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
		users=LikingComment_handler.findCommentLikers(cmt.getPostID(), cmt.getCommentID());
		int count = 0;
		for(User user : users) {
			count++;
			LikedUser lu = new LikedUser(user, cmt);
			contentPane.add(lu);
		}
		if(count < 2)
			setSize(350, 80);
		else
			setSize(350, 60*count);
		
		scroller.setViewportView(contentPane);
		add(scroller);
	}

}
