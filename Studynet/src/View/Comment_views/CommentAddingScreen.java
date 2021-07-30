package View.Comment_views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Controller.CurrentUser;
import Handlers.Comment_handler;
import Model.Post;
import View.ScreenHelper;

public class CommentAddingScreen extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTextArea text;
	
	private JPanel commentPane;
	private JPanel buttonPane;
	private JPanel lblPane;
	private JPanel textPane;
	
	private JLabel lbl;
	
	private JScrollPane scroller;
	
	private JButton btnPost;
	private JButton btnClose;
	
	private Post pst;
	
	public CommentAddingScreen(Post post, JButton relative) {
		
		setLocationRelativeTo(relative);
		
		this.pst = post;
		commentPane = new JPanel();
		commentPane.setBackground(Color.BLUE);
		commentPane.setLayout(new BoxLayout(commentPane, BoxLayout.PAGE_AXIS));
		
		lblPane = new JPanel();
		lblPane.setOpaque(false);
		
		lbl = new JLabel("Write your comment here:");
		lbl.setForeground(Color.BLACK);
		lblPane.add(lbl);
		commentPane.add(lblPane);
		
		
		
		buttonPane = new JPanel();
		buttonPane.setOpaque(false);
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		
		textPane = new JPanel();
		textPane.setOpaque(false);
		textPane.setLayout(new BoxLayout(textPane, BoxLayout.LINE_AXIS));
		textPane.add(Box.createRigidArea(new Dimension(10, 20)));
		
		text = new JTextArea();
		text.setBackground(Color.WHITE);
		textPane.add(text);
		
		textPane.add(Box.createRigidArea(new Dimension(10, 20)));
		
		commentPane.add(textPane);
		
		btnPost = new JButton("Post");
		ScreenHelper.style_btn(btnPost);
		btnPost.setForeground(Color.BLACK);
		
		btnClose = new JButton("Close");
		ScreenHelper.style_btn(btnClose);
		btnClose.setForeground(Color.BLACK);
		
		buttonPane.add(btnPost);
		buttonPane.add(btnClose);
		commentPane.add(buttonPane);
		
		scroller = new JScrollPane(commentPane);
		
		setSize(300, 150);
		this.setContentPane(scroller);
		this.setVisible(true);
		
		createEvents();
		
	}
	
	public void createEvents() {
		
		btnPost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Comment_handler.addComment(pst.getID(), pst.getCommentNum()+1, ScreenHelper.getCurrentDateTime(), text.getText(), CurrentUser.getUsername());
					pst.increaseCommentNum();
					ScreenHelper.refreshPosts();
					close();
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		
	}
	
	public void close() {
		this.dispose();
	}


}
