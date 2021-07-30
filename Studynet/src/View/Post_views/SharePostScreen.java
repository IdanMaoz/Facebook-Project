package View.Post_views;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Controller.CurrentUser;
import Handlers.Post_handler;
import Handlers.Sharing_handler;
import Model.Post;
import View.ScreenHelper;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SharePostScreen extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private Post post;
	
	private JScrollPane scroller;
	
	private JPanel contentPane;
	private JPanel btnPane;
	
	private JTextArea textArea;
	
	private JButton btnShare;
	
	private JLabel header;
	
	private String repostHeader="";
	
	public SharePostScreen(Post pst, JButton relative) {
		post = pst;
		
		scroller = new JScrollPane();
		add(scroller);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLUE);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
		
		btnPane = new JPanel();
		btnPane.setOpaque(false);
		
		JPanel headerPane = new JPanel();
		headerPane.setOpaque(false);
		header = new JLabel("Write your addition to the post: \n");
		String addition = "A post by: \n";
		addition += pst.getDetailis();
		header.setText(header.getText() + addition);
		headerPane.add(header);
		contentPane.add(headerPane);
		
		JPanel textPane = new JPanel();
		textPane.setOpaque(false);
		textPane.setLayout(new BoxLayout(textPane, BoxLayout.LINE_AXIS));
		
		textPane.add(Box.createRigidArea(new Dimension(10, 20)));
		
		textArea = new JTextArea();
		textPane.add(textArea);
		
		textPane.add(Box.createRigidArea(new Dimension(10, 20)));
		
		contentPane.add(textPane);
		
		btnShare = new JButton("Share");
		btnShare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sharing_handler.sharePost(CurrentUser.getUsername(), post.getID(), textArea.getText());
				Post_handler.addPost(post.getPhoto(), repostedText(), post.getLocation(), string_video(), ScreenHelper.getCurrentDateTime(), CurrentUser.getUsername(), "");
				close();

			}
		});
		ScreenHelper.style_btn(btnShare);
		btnShare.setForeground(Color.BLACK);
		btnPane.add(btnShare);
		
		contentPane.add(btnPane);
		
		scroller.setViewportView(contentPane);
		
		setContentPane(scroller);
		setVisible(true);
		setLocationRelativeTo(relative);
		setSize(700, 250);
		
	}
	
	public void close() {
		ScreenHelper.refreshPosts();
		this.dispose();
	}

	public String generateRepostHeader() {
		repostHeader = "This is a shared post of: " + post.getDetailis() + "\n";
		return repostHeader;
	}
	
	public String repostedText() {
		return generateRepostHeader() + "'" + post.getText() + "'" + "\n" + textArea.getText();
	}
	
	public String string_video() {
		int result = post.getVideo();
		String res = Integer.toString(result);
		return res;
				
				
				
	}
	
}
