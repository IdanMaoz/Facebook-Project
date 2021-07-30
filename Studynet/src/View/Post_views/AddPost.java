package View.Post_views;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Controller.CurrentUser;
import Handlers.Post_handler;
import Handlers.Tagging_handler;
import Model.User;
import View.ScreenHelper;
import View.Tagging_views.TaggingScreen;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AddPost extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JPanel btnPane;
	
	private JButton btnPost;
	private JButton btnClose;
	private JButton btnTag;
	
	private String tagged="";
	
	private JTextArea textArea;
	private JLabel txtHeader;
	
	private JTextField photo_txt;
	private JTextField location_txt;
	private JTextField video_txt;
	private JLabel videoHeader;
	private JLabel photoHeader;
	private JLabel locationHeader;
	
	private AddPost theClass;
	
	public AddPost() {
		setOpaque(false);
		setBackground(Color.BLUE);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		btnPane = new JPanel();
		btnPane.setOpaque(false);
		
		JPanel txtHeaderPane = new JPanel();
		txtHeaderPane.setOpaque(false);
		
		txtHeader = new JLabel();
		txtHeader.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		txtHeader.setOpaque(false);
		txtHeader.setText("Write your post here");
		txtHeader.setBackground(Color.BLUE);
		txtHeader.setForeground(Color.CYAN);
		txtHeaderPane.add(txtHeader);
		
		add(txtHeaderPane);
		
		textArea = new JTextArea();
		textArea.setBackground(Color.WHITE);
		textArea.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		textArea.setLineWrap(true);
		
		add(textArea);
		
		add(Box.createRigidArea(new Dimension(20, 20)));
		
		JPanel picHeader = new JPanel();
		picHeader.setOpaque(false);
		picHeader.setLayout(new BoxLayout(picHeader, BoxLayout.LINE_AXIS));
		
		photoHeader = new JLabel();
		photoHeader.setText("Photo:");
		photoHeader.setOpaque(false);
		photoHeader.setForeground(Color.CYAN);
		photoHeader.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		photoHeader.setBackground(Color.BLUE);
		picHeader.add(photoHeader);
		add(picHeader);
		
		photo_txt = new JTextField();
		photo_txt.setBackground(Color.WHITE);
		photo_txt.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		picHeader.add(Box.createRigidArea(new Dimension(20, 20)));
		picHeader.add(photo_txt);
		add(picHeader);
		
		add(Box.createRigidArea(new Dimension(20, 20)));
		
		JPanel vidHeader = new JPanel();
		vidHeader.setOpaque(false);
		vidHeader.setLayout(new BoxLayout(vidHeader, BoxLayout.LINE_AXIS));
		
		videoHeader = new JLabel();
		videoHeader.setText("Video:");
		videoHeader.setOpaque(false);
		videoHeader.setForeground(Color.CYAN);
		videoHeader.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		videoHeader.setBackground(Color.BLUE);
		vidHeader.add(videoHeader);
		
		video_txt = new JTextField();
		video_txt.setBackground(Color.WHITE);
		video_txt.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		vidHeader.add(Box.createRigidArea(new Dimension(20, 20)));
		vidHeader.add(video_txt);

		add(vidHeader);
		
		add(Box.createRigidArea(new Dimension(20, 20)));
		
		JPanel locHeader = new JPanel();
		locHeader.setOpaque(false);
		locHeader.setLayout(new BoxLayout(locHeader, BoxLayout.LINE_AXIS));
		
		locationHeader = new JLabel();
		locationHeader.setText("Location:");
		locationHeader.setOpaque(false);
		locationHeader.setHorizontalAlignment(SwingConstants.CENTER);
		locationHeader.setForeground(Color.CYAN);
		locationHeader.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		locationHeader.setBackground(Color.BLUE);
		locHeader.add(locationHeader);

		location_txt = new JTextField();
		location_txt.setBackground(Color.WHITE);
		location_txt.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		locHeader.add(location_txt);
		add(locHeader);		
		
		btnPane.setLayout(new BoxLayout(btnPane, BoxLayout.LINE_AXIS));
		
		btnPost = new JButton("Post");
		
		btnClose = new JButton("Close");

		btnTag = new JButton("Tag");

		ScreenHelper.style_btn(btnPost);
		ScreenHelper.style_btn(btnTag);
		ScreenHelper.style_btn(btnClose);
		
		btnPane.add(btnPost);
		btnPane.add(btnTag);
		btnPane.add(btnClose);

		add(btnPane);
		
		createEvents();		
		
		theClass = this;
	}
	
	public void createEvents() {
		
		btnPost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = textArea.getText();
				String photo = photo_txt.getText();
				String vid = video_txt.getText();
				String date = ScreenHelper.getCurrentDateTime();
				String location = location_txt.getText();
				Post_handler.addPost(photo, text, location, vid, date, CurrentUser.getUsername(), tagged);
				ScreenHelper.refreshPosts();
			}
		});
		
		btnTag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaggingScreen tag = new TaggingScreen(getThis() ,btnTag);
				
			}
		});
		
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}
	
	
	public AddPost getThis() {
		return theClass;
	}
	
	public void hideShare() {
		this.btnTag.setVisible(false);
	}
	
	public void tag(User user) {
		String text = this.textArea.getText();
		if(user.getNickName().equalsIgnoreCase(""))
			text += " ["+ user.getFirstName() + " " + user.getLastName()+" was tagged]";
		else
			text += " ["+ user.getNickName()+ " was tagged]";
		
		textArea.setText(text);
		
		this.tagged = user.getMail();
	}

}
