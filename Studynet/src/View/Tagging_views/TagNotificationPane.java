package View.Tagging_views;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.jgoodies.forms.layout.CellConstraints.Alignment;

import Model.Post;
import View.ScreenHelper;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

public class TagNotificationPane extends JPanel{
	

	private static final long serialVersionUID = 1L;
	private JTextArea text;
	private JButton btnToPost;
	private Post post;
	
	private JPanel organizer;
	
	public TagNotificationPane(Post post) {
		this.post = post;
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setOpaque(false);
		
		organizer = new JPanel();
		organizer.setOpaque(false);
		organizer.setLayout(new BoxLayout(organizer, BoxLayout.LINE_AXIS));
		
		text = new JTextArea(notificationText());
		text.setOpaque(false);
		text.setFont(new Font("Arial", Font.PLAIN, 16));
		text.setForeground(Color.CYAN);
		text.setEditable(false);
		organizer.add(text);

		
		btnToPost = new JButton("View");
		btnToPost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenHelper.toPost(post.getID());
			}
		});
		ScreenHelper.style_btn(btnToPost);
		organizer.add(btnToPost);
		
		add(organizer);
	}
	
	
	
	public String notificationText() {
		String result = "";
		result += post.getWriter().getDetails();		
		result += "mentioned you in a post";
		return result;
	}
}
