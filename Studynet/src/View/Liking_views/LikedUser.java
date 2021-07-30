package View.Liking_views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Handlers.LikingComment_handler;
import Handlers.LikingPost_handler;
import Model.Comment;
import Model.Post;
import Model.User;
import View.Login;

public class LikedUser extends JPanel{

	private static final long serialVersionUID = 1L;
	
	public JLabel textArea;
	
	public JLabel likeType;
	
	public LikedUser(User user, Post post) {
		
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		setOpaque(false);
		
		textArea = new JLabel(user.getDetails());
		textArea.setOpaque(false);
		textArea.setFont(new Font("Arial", Font.PLAIN, 14));
		add(textArea);
		
		int type = LikingPost_handler.getLikeType(user.getMail(), post.getID());
		likeType = new JLabel("");
		switch (type) {
		case 1:
			likeType.setIcon(new ImageIcon(Login.class.getResource("/Images/possitive.jpg")));
			break;
			
		case 2:
			likeType.setIcon(new ImageIcon(Login.class.getResource("/Images/Angry.jpg")));
			break;
		case 3:
			likeType.setIcon(new ImageIcon(Login.class.getResource("/Images/Funny.jpg")));
			break;
		case 4:
			likeType.setIcon(new ImageIcon(Login.class.getResource("/Images/Lovely.jpg")));
			break;
	
		default:
			break;
		}
		add(Box.createRigidArea(new Dimension(20, 20)));
		add(likeType);
	}
	
	public LikedUser(User user, Comment comment) {
		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		textArea = new JLabel(user.getDetails());
		textArea.setOpaque(false);
		textArea.setFont(new Font("Arial", Font.PLAIN, 14));
		add(textArea);
		int type=LikingComment_handler.getLikeType(user.getMail(), comment.getPostID(), comment.getCommentID());
		likeType = new JLabel("");
		switch (type) {
		case 1:
			likeType.setIcon(new ImageIcon(Login.class.getResource("/Images/possitive.jpg")));
			break;
			
		case 2:
			likeType.setIcon(new ImageIcon(Login.class.getResource("/Images/Angry.jpg")));
			break;
		case 3:
			likeType.setIcon(new ImageIcon(Login.class.getResource("/Images/Funny.jpg")));
			break;
		case 4:
			likeType.setIcon(new ImageIcon(Login.class.getResource("/Images/Lovely.jpg")));
			break;
	
		default:
			break;
		}
		add(Box.createRigidArea(new Dimension(20, 20)));
		add(likeType);
	}
	
	
	

}
