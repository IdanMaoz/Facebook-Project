package View.Liking_views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import Controller.CurrentUser;
import Handlers.LikingComment_handler;
import Handlers.LikingPost_handler;
import View.Login;
import View.ScreenHelper;

public class LikingPane extends JFrame {
private static final long serialVersionUID = 1L;
	
	private JPanel buttonPane;
	private JPanel likePane;
	private JPanel lblPane;
	private JPanel cancelPane;
	
	private JButton btnPossitive;
	private JButton btnAngry;
	private JButton btnFunny;
	private JButton btnLikedIt;
	private JButton btnCancelLike;
	
	private JLabel lbl;
	
	private int postID = -1;
	private int commentID = -1;
	
	private JButton relativeTo;
	
	
	public LikingPane(int postID, JButton btn) throws SQLException {
		this.postID = postID;
		this.relativeTo = btn;
		initComponents();
		
	}
	
	public LikingPane(int postID, int commentID, JButton btn) throws SQLException {
		this.postID = postID;
		this.commentID = commentID;
		this.relativeTo = btn;
		initComponents();
	}
	
	public void initComponents() throws SQLException {
		setLocationRelativeTo(relativeTo);
		setBackground(Color.BLUE);
		
		likePane = new JPanel();
		likePane.setLayout(new BoxLayout(likePane, BoxLayout.PAGE_AXIS));
		likePane.setOpaque(false);
		this.setContentPane(likePane);
		
		lblPane = new JPanel();
		lblPane.setOpaque(false);
		lbl = new JLabel("Choose your Like");
		lblPane.add(lbl);
		likePane.add(lblPane);
		
		buttonPane = new JPanel();
		buttonPane.setOpaque(false);
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		likePane.add(buttonPane);
		
		System.out.println("woooo");
		btnPossitive = new JButton("");
		btnAngry = new JButton("");
		btnFunny = new JButton("");
		btnLikedIt = new JButton("");
		btnCancelLike = new JButton("Cancel Like");

		btnPossitive.setBorder(null);
		btnAngry.setBorder(null);		
		btnFunny.setBorder(null);
		btnLikedIt.setBorder(null);
		btnCancelLike.setBorder(null);
		
		ScreenHelper.style_btn(btnPossitive);
		ScreenHelper.style_btn(btnAngry);
		ScreenHelper.style_btn(btnFunny);
		ScreenHelper.style_btn(btnLikedIt);
		ScreenHelper.style_btn(btnCancelLike);
		
		
		btnPossitive.setIcon(new ImageIcon(Login.class.getResource("/Images/possitive.jpg")));
		btnAngry.setIcon(new ImageIcon(Login.class.getResource("/Images/Angry.jpg")));
		
		btnFunny.setIcon(new ImageIcon(Login.class.getResource("/Images/Funny.jpg")));
		
		btnLikedIt.setIcon(new ImageIcon(Login.class.getResource("/Images/Lovely.jpg")));
		btnCancelLike.setForeground(Color.BLACK);
		
		buttonPane.add(Box.createRigidArea(new Dimension(10, 8)));		
		buttonPane.add(btnPossitive);
		buttonPane.add(Box.createRigidArea(new Dimension(15, 8)));
		buttonPane.add(btnAngry);
		buttonPane.add(Box.createRigidArea(new Dimension(15, 8)));
		buttonPane.add(btnFunny);
		buttonPane.add(Box.createRigidArea(new Dimension(15, 8)));
		buttonPane.add(btnLikedIt);
		
		cancelPane = new JPanel();
		cancelPane.setOpaque(false);
		cancelPane.add(btnCancelLike);
		add(cancelPane);
		
		this.setVisible(true);
		
		setSize(250, 160);
		
		createEvents();
	}
	
	public void createEvents() {
		btnPossitive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int type = 1;
				try {
					if(commentID == -1) {
						if(LikingPost_handler.alreadyMakeLikeToPost(postID, CurrentUser.getUsername())) {
							LikingPost_handler.deleteLikeToPost(postID, CurrentUser.getUsername());
						}
						LikingPost_handler.addLikingPost(type, postID, CurrentUser.getUsername());
					}
					else {
						if(LikingComment_handler.alreadyMakeLikeToComment(postID, commentID, CurrentUser.getUsername())) {
							LikingComment_handler.deleteLikeToComment(postID, commentID, CurrentUser.getUsername());
						}
						LikingComment_handler.addLikingComment(type, postID, commentID, CurrentUser.getUsername());
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				close();
			}
		});
		
		btnAngry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int type = 2;
				try {
					if(commentID == -1) {
						if(LikingPost_handler.alreadyMakeLikeToPost(postID, CurrentUser.getUsername())) {
							LikingPost_handler.deleteLikeToPost(postID, CurrentUser.getUsername());
						}
						LikingPost_handler.addLikingPost(type, postID, CurrentUser.getUsername());
					}
					else {
						if(LikingComment_handler.alreadyMakeLikeToComment(postID, commentID, CurrentUser.getUsername())) {
							LikingComment_handler.deleteLikeToComment(postID, commentID, CurrentUser.getUsername());
						}
						LikingComment_handler.addLikingComment(type, postID, commentID, CurrentUser.getUsername());
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				close();

			}
		});
		
		btnFunny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int type = 3;
				try {
					if(commentID == -1) {
						if(LikingPost_handler.alreadyMakeLikeToPost(postID, CurrentUser.getUsername())) {
							LikingPost_handler.deleteLikeToPost(postID, CurrentUser.getUsername());
						}
						LikingPost_handler.addLikingPost(type, postID, CurrentUser.getUsername());
					}
					else {
						if(LikingComment_handler.alreadyMakeLikeToComment(postID, commentID, CurrentUser.getUsername())) {
							LikingComment_handler.deleteLikeToComment(postID, commentID, CurrentUser.getUsername());
						}
						LikingComment_handler.addLikingComment(type, postID, commentID, CurrentUser.getUsername());
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				close();

			}
		});
		
		btnLikedIt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int type = 4;
				try {
					if(commentID == -1) {
						if(LikingPost_handler.alreadyMakeLikeToPost(postID, CurrentUser.getUsername())) {
							LikingPost_handler.deleteLikeToPost(postID, CurrentUser.getUsername());
						}
						LikingPost_handler.addLikingPost(type, postID, CurrentUser.getUsername());
					}
					else {
						if(LikingComment_handler.alreadyMakeLikeToComment(postID, commentID, CurrentUser.getUsername())) {
							LikingComment_handler.deleteLikeToComment(postID, commentID, CurrentUser.getUsername());
						}
						LikingComment_handler.addLikingComment(type, postID, commentID, CurrentUser.getUsername());
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				close();
			}
		});
		
		btnCancelLike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int type = 5;
				try {
					if(commentID == -1)
						LikingPost_handler.deleteLikeToPost(postID, CurrentUser.getUsername());
					else
						LikingComment_handler.deleteLikeToComment(postID, commentID, CurrentUser.getUsername());
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				close();
			}
		});
	}
	
	public void close() {
		ScreenHelper.refreshPosts();
		this.dispose();
	}
	


}
