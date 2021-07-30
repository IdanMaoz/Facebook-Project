package View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JLabel;

import Controller.CurrentUser;
import Controller.FrameLoader;

public abstract class ScreenHelper {
	
	static int height = 600;
	static int width = 800;
	static int logo_dim = 50;
	static WallScreen wallScreen;
	
	public static void toPost(int postID) {
		wallScreen.goToPost(postID);
	}
	
	public static void updateButtons() {
		wallScreen.hideBtn();
	}
	
	public static void setWall(WallScreen wall) {
		wallScreen = wall;
	}
	
	public static void refreshPosts() {
		wallScreen.change_view("Posts");
	}
	
	public static void refreshFriendRequests() {
		wallScreen.change_view("Friend Requests");
	}
	public static void refreshAddTeamRequest() {
		wallScreen.change_view("Add to Team Request");
	}
	public static void refreshJoinGroups() {
		wallScreen.change_view("Join Groups");
	}
	public static void refreshRequestList() {
		wallScreen.change_view("Find Friends");
	}
	public static void refreshAddPost() {
		wallScreen.change_view("Add Post");
	}
	
	public static void refreshTeamRequests() {
		wallScreen.change_view("Groups");
	}

	public static int height() {
		return height;
	}
	
	public static int width() {
		return width;
	}
	
	public static int l_dim() {
		return logo_dim;
	}
	
	public static void pos_logo(JLabel lbl) {
		lbl.setBounds(10, 10, 50, 50);
	}
	
	public static void style_btn(JButton btn) {
		btn.setFont(new Font("Arial", Font.BOLD, 18));
		btn.setForeground(Color.CYAN);
		btn.setOpaque(false);
		btn.setContentAreaFilled(false);
		btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	
	public static void logout() {
		CurrentUser.reset();
		FrameLoader.back();
	}
	
	public static void wall() {
		FrameLoader.addFrame(new WallScreen());
	}

	
	public static String getCurrentDateTime() {
		String dateTime = "";
		dateTime = LocalDateTime.now().withNano(0).toString();
		
		return dateTime.replace("T", " "); 
	}
	
	public static Date getCurrentDate() {
		long millis=System.currentTimeMillis();  
		Date date=new Date(millis); 
		return date;
	}
	
}
