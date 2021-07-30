package View.Comment_views;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Model.Post;
import View.ScreenHelper;

public class AddCommentPane extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextArea text;
	
	private JPanel buttonPane;
	
	private JButton btnPost;
	private JButton btnClose;
	
	public AddCommentPane(Post post) {
		this.setOpaque(false);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
				
		buttonPane = new JPanel();
		buttonPane.setBackground(Color.BLUE);
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		
		text = new JTextArea();
		text.setBackground(Color.BLUE);
		this.add(text);
		
		btnPost = new JButton("Post");
		ScreenHelper.style_btn(btnPost);
		
		btnClose = new JButton("Close");
		ScreenHelper.style_btn(btnClose);
		
		buttonPane.add(btnPost);
		buttonPane.add(btnClose);
		add(buttonPane);
		
		createEvents();	
	}
	
	public void createEvents() {
		
	}
}















