package View.Comment_views;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Controller.CurrentUser;
import Handlers.LikingComment_handler;
import Handlers.LikingPost_handler;
import Model.Comment;
import View.Login;
import View.ScreenHelper;
import View.Liking_views.LikingPane;
import View.Liking_views.UsersWhoLikedFrame;

import java.awt.Font;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class CommentPane extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JTextArea txtrContent;
	private JLabel likeInfo;
	
	private JButton btnLike;
	private JButton btnViewLikes;
	
	private JPanel textComponent;
	private JPanel likeInfoPane;
	private JPanel upperPane;
	
	private UsersWhoLikedFrame uwl;
	
	public CommentPane(Comment comment) throws SQLException {
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setOpaque(false);
		
		upperPane = new JPanel();
		upperPane.setOpaque(false);
		upperPane.setLayout(new BoxLayout(upperPane, BoxLayout.LINE_AXIS));
		
		this.add(upperPane);
		
		textComponent = new JPanel();
		textComponent.setOpaque(false);
		textComponent.setLayout(new BoxLayout(textComponent, BoxLayout.PAGE_AXIS));

		
		JLabel pic = new JLabel(comment.getCommenter().getPhoto());
		upperPane.add(pic);
		
		JTextArea txtUsername = new JTextArea(comment.getDetails());
		txtUsername.setBackground(Color.DARK_GRAY);
		txtUsername.setForeground(Color.CYAN);
		txtUsername.setFont(new Font("Arial", Font.PLAIN, 10));
		textComponent.add(txtUsername);
		
		txtrContent = new JTextArea(comment.getText());
		txtrContent.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		txtrContent.setBackground(Color.DARK_GRAY);
		txtrContent.setForeground(Color.CYAN);
		txtrContent.setLineWrap(true);
		
		textComponent.add(txtrContent);
		
		upperPane.add(textComponent);	
		
		int likes = LikingComment_handler.sumLikeOfPost(comment.getPostID(), comment.getCommentID());
		if (likes > 0) {
			
			JPanel lowerPane = new JPanel();
			lowerPane.setLayout(new BoxLayout(lowerPane, BoxLayout.LINE_AXIS));
			lowerPane.setOpaque(false);
			
			likeInfoPane = new JPanel();
			likeInfoPane.setOpaque(false);
			//likeInfoPane.setLayout(new BoxLayout(likeInfoPane, BoxLayout.LINE_AXIS));
			likeInfoPane.setBorder(null);
			
			likeInfo = new JLabel(" " + likes + " people like this comment");
			likeInfo.setOpaque(false);
			likeInfo.setForeground(Color.CYAN);
			
			likeInfoPane.add(likeInfo);
			
			lowerPane.add(likeInfoPane);
			
			
			btnViewLikes = new JButton("View");
			uwl = null;
			btnViewLikes.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					uwl = new UsersWhoLikedFrame(comment, btnViewLikes);
					uwl.setVisible(true);
				}
			});
			btnViewLikes.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseExited(MouseEvent e) {
					if(uwl != null)
						uwl.dispose();
				}
			});
			ScreenHelper.style_btn(btnViewLikes);
			lowerPane.add(btnViewLikes);

			add(lowerPane);
		}
		

		
		btnLike = new JButton("");
		btnLike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					LikingPane likePane = new LikingPane(comment.getPostID(), comment.getCommentID(), btnLike);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnLike.setFont(new Font("Arial", Font.BOLD, 14));
		btnLike.setBounds(461, 35, 79, 30);
		this.setBounds(1, 1, txtrContent.getWidth() + btnLike.getWidth(), pic.getHeight() + txtrContent.getHeight());
		ScreenHelper.style_btn(btnLike);
		if(LikingComment_handler.alreadyMakeLikeToComment(1,comment.getPostID(),comment.getCommentID(),CurrentUser.getUsername())) {
			btnLike.setIcon(new ImageIcon(Login.class.getResource("/Images/possitive.jpg")));
		}
		else if(LikingComment_handler.alreadyMakeLikeToComment(2,comment.getPostID(),comment.getCommentID(),CurrentUser.getUsername())) {
			btnLike.setIcon(new ImageIcon(Login.class.getResource("/Images/Angry.jpg")));
		}
		else if(LikingComment_handler.alreadyMakeLikeToComment(3,comment.getPostID(),comment.getCommentID(),CurrentUser.getUsername())) {
			btnLike.setIcon(new ImageIcon(Login.class.getResource("/Images/Funny.jpg")));
		}
		else if(LikingComment_handler.alreadyMakeLikeToComment(4,comment.getPostID(),comment.getCommentID(),CurrentUser.getUsername())) {
			btnLike.setIcon(new ImageIcon(Login.class.getResource("/Images/Lovely.jpg")));
		}
		else {
			btnLike.setText("Like");
			btnLike.setForeground(Color.white);
		}
		upperPane.add(btnLike);
		
	}
	
	

	
}
