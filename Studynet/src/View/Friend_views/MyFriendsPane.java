package View.Friend_views;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Model.User;

import java.awt.Color;
import java.awt.Font;

public class MyFriendsPane extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JLabel txtArea;
	
	public MyFriendsPane(User user) {
		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		txtArea = new JLabel(user.getDetails());
		txtArea.setFont(new Font("Arial", Font.PLAIN, 16));
		txtArea.setForeground(Color.CYAN);
		add(txtArea);
		
	}
	
	

}
