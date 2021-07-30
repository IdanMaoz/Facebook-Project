package View.Post_views;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import Controller.CurrentUser;
import Handlers.Comment_handler;
import Handlers.LikingPost_handler;
import Handlers.Sharing_handler;
import Model.Comment;
import Model.Post;
import View.Login;
import View.ScreenHelper;
import View.Comment_views.CommentAddingScreen;
import View.Comment_views.CommentPane;
import View.Liking_views.LikingPane;
import View.Liking_views.UsersWhoLikedFrame;

import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;


public class PostPane extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JPanel buttonPane;
	private JPanel postHead;
	private JPanel likeInfoPane;
	
	private JButton btnLike;
	private JButton btnComment;
	private JButton btnShare;
	private JButton btnViewLikes;
	
	private JTextArea postHeader;
	private JTextArea text;
	private JTextField photo;
	private JTextField video;
	private JTextField location;
	private JTextField likeInfo;
	
	private JLabel pic;
	
	private UsersWhoLikedFrame uwl;

	
	private Post pst;

	public PostPane(Post post) throws SQLException {
		setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		pst = post;
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setOpaque(false);
		
		postHead = new JPanel();
		postHead.setLayout(new BoxLayout(postHead, BoxLayout.LINE_AXIS));
		postHead.setOpaque(false);
		pic = new JLabel(post.getPhoto());
		postHead.add(pic);
		
		postHeader = new JTextArea(post.getDetailis());
		postHeader.setFont(new Font("Arial", Font.BOLD, 10));
		postHeader.setForeground(Color.CYAN);
		postHeader.setBackground(Color.DARK_GRAY);
		postHeader.setEditable(false);
		postHead.add(postHeader);
		
		this.add(postHead);
		
		JPanel content = new JPanel();
		text = new JTextArea(post.getText());
		text.setBackground(Color.DARK_GRAY);
		text.setForeground(Color.CYAN);
		text.setEditable(false);
		text.setLineWrap(true);
		text.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		
		photo = new JTextField(post.getPhoto());
		photo.setBackground(Color.DARK_GRAY);
		photo.setForeground(Color.CYAN);
		photo.setEditable(false);
		photo.setFont(new Font("Arial", Font.PLAIN, 16));
		
		video = new JTextField(post.getVideo());
		video.setBackground(Color.DARK_GRAY);
		video.setForeground(Color.CYAN);
		video.setEditable(false);
		video.setFont(new Font("Arial", Font.PLAIN, 16));
		
		location = new JTextField(post.getLocation());
		location.setBackground(Color.DARK_GRAY);
		location.setForeground(Color.CYAN);
		location.setEditable(false);
		location.setFont(new Font("Arial", Font.PLAIN, 16));

		text.setBounds(content.getX(), content.getY(), content.getWidth(), text.getHeight());

		content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
		content.add(text);
		if(!photo.getText().contentEquals(""))
			content.add(photo);
		if(!video.getText().contentEquals(""))
			content.add(video);
		if(!location.getText().contentEquals(""))
			content.add(location);

		this.add(content);
		
		int likes = LikingPost_handler.sumLikeOfPost(pst.getID());
		if (likes > 0) {
			likeInfoPane = new JPanel();
			likeInfoPane.setOpaque(false);
			likeInfoPane.setLayout(new BoxLayout(likeInfoPane, BoxLayout.LINE_AXIS));
			likeInfoPane.setBorder(null);
			
			likeInfo = new JTextField(" " + likes + " people like this post");
			likeInfo.setOpaque(false);
			likeInfo.setForeground(Color.CYAN);
			likeInfoPane.add(likeInfo);
			
			
			
			btnViewLikes = new JButton("View");
			uwl = null;
			btnViewLikes.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					System.out.println(pst);
					uwl = new UsersWhoLikedFrame(pst, btnViewLikes);
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
			likeInfoPane.add(btnViewLikes);
			add(likeInfoPane);
		}
		
		
		
		buttonPane = new JPanel();
		buttonPane.setOpaque(false);
		
		btnLike = new JButton("");
		ScreenHelper.style_btn(btnLike);
		
		if(LikingPost_handler.alreadyMakeLikeToPost(1,pst.getID(),CurrentUser.getUsername())) {
			btnLike.setIcon(new ImageIcon(Login.class.getResource("/Images/possitive.jpg")));
		}
		else if(LikingPost_handler.alreadyMakeLikeToPost(2,pst.getID(),CurrentUser.getUsername())) {
			btnLike.setIcon(new ImageIcon(Login.class.getResource("/Images/Angry.jpg")));
		}
		else if(LikingPost_handler.alreadyMakeLikeToPost(3,pst.getID(),CurrentUser.getUsername())) {
			btnLike.setIcon(new ImageIcon(Login.class.getResource("/Images/Funny.jpg")));
		}
		else if(LikingPost_handler.alreadyMakeLikeToPost(4,pst.getID(),CurrentUser.getUsername())) {
			btnLike.setIcon(new ImageIcon(Login.class.getResource("/Images/Lovely.jpg")));
		}
		else {
			btnLike.setText("Like");
			btnLike.setForeground(Color.white);
		}
		buttonPane.add(btnLike);
		
		btnComment = new JButton("Comment");
		btnComment.setBounds(btnLike.getX() + btnLike.getWidth() + 10, btnLike.getY(), 130, 30);
		ScreenHelper.style_btn(btnComment);
		btnComment.setForeground(Color.white);
		buttonPane.add(btnComment);
		
		if(!Sharing_handler.alreadySharedPost(CurrentUser.getUsername(), post.getID())) {
			btnShare = new JButton("Share");
			btnShare.setBounds(btnComment.getX() + btnComment.getWidth() + 10, btnComment.getY(), 115, 30);
			ScreenHelper.style_btn(btnShare);
			btnShare.setForeground(Color.white);
			buttonPane.add(btnShare);
		}

		this.add(buttonPane);

		
		ArrayList<Comment> comments = new ArrayList<Comment>();
		try {
			comments = Comment_handler.findPostComments(post.getID());
			for(Comment cmnt : comments) {
				CommentPane cmntPane = new CommentPane(cmnt);
				add(cmntPane);
				add(Box.createRigidArea(new Dimension(20, 5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		post.setCommentNum(comments.size());
		createEvents();

	}
	
	public void createEvents() {
		btnComment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CommentAddingScreen newComment = new CommentAddingScreen(pst, btnComment);
			}
		});
		
		btnLike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					LikingPane likePane = new LikingPane(pst.getID(), btnLike);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		if(!Sharing_handler.alreadySharedPost(CurrentUser.getUsername(), pst.getID())) {
			btnShare.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SharePostScreen sps = new SharePostScreen(pst, btnShare);
				}
			});
		}
	}
	
	public Post getPost() {
		return this.pst;
	}

}
