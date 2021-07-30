package Handlers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JOptionPane;

import Controller.CurrentUser;
import Controller.Main;
import Model.Post;
import Model.SQConnection;
import Model.User;

public abstract class Post_handler {
	static Connection con = SQConnection.con;
	
	public static int getMonth(Date date) {
	   Calendar cal = Calendar.getInstance();
	   cal.setTime(date);
       return cal.get(Calendar.MONTH)+1;
	 }
	public static int currentMonth() {
		 Calendar cal = Calendar.getInstance();
	       return cal.get(Calendar.MONTH)+1;
	    }
	
	public static boolean check_Post(String photo,String text,String location,String video,String UserMail) throws SQLException {
		if(text.length()>200) {
			JOptionPane.showMessageDialog(null, "Sorry, post text must be at most 200 symbols");
			return false;
		}
		if(location.length()>50) {
			JOptionPane.showMessageDialog(null, "Sorry, location must be at most 50 numbers long");
		}
		return true;
	}
	
	
	public static void addPost(String photo,String text,String location,String video,String datePost,String UserMail, String tagged){
		int vd = 0;
		int postID = 0;
		try {
			if(!video.contentEquals("")) {
				vd=Integer.parseInt(video);
			}
			else {
				vd=0;
			}
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, "Video should be a number !");
			e1.printStackTrace();
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime postedDate = LocalDateTime.parse(datePost,formatter);
		try {
			postID  = sumPosts()+1;
			if(check_Post(photo, text, location, video, UserMail)) {
				String sql="SET IDENTITY_INSERT dbo.Post ON Insert INTO dbo.Post (ID,Photo,text,location,video,datePosted,UserMail) VALUES (?,?,?,?,?,?,?)";
				PreparedStatement pr = con.prepareStatement(sql);
				
				pr.setInt(1, postID);
				pr.setString(2, photo);
				pr.setString(3, text);
				pr.setString(4, location);
				pr.setInt(5, vd);
				pr.setObject(6, postedDate);
				pr.setString(7, UserMail);
				pr.executeUpdate();
				JOptionPane.showMessageDialog(null, "Your post has been Posted !");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(!tagged.contentEquals("")) {
			Tagging_handler.Tagging(postID, tagged);
		}
		
	}
	
	public static Post findPost(int postID) {
		String sql ="SELECT * from dbo.Post WHERE ID=?";
		Post result = null;
		PreparedStatement pr;
		try {
			pr = SQConnection.con.prepareStatement(sql);
	 		pr.setInt(1,postID);
	 		ResultSet rs=pr.executeQuery();
	 		System.out.println("daadf");
	 		while(rs.next()) {
	 			int ID = rs.getInt(1);
	 			String photo = rs.getString(2);
	 			String text = rs.getString(3);
	 			String location = rs.getString(4);
	 			int video = rs.getInt(5);
	 			Date date = rs.getDate(6);
	 			User writer = Users_handler.findUser(rs.getString(7));
	 			result = new Post(ID, photo, text, location, video, date, writer);
	 		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static int sumPosts() {
		PreparedStatement pr;
		int count=0;
		try {
			pr = con.prepareStatement("select * from dbo.Post");
	 		ResultSet r1=pr.executeQuery();
	 		while(r1.next()) {
	 			count=Integer.parseInt(r1.getString(1));
	 		}
		} catch (SQLException e) {
			e.printStackTrace();
		}


 		return count;
	}
	
	
	public static ArrayList<Post> relatedPosts(String mail) throws SQLException {
		ArrayList<Post> posts = new ArrayList<Post>();
		PreparedStatement pr=SQConnection.con.prepareStatement("select * from dbo.Post where UserMail=? or UserMail in(select Mail1 from dbo.FriendsList where Mail2=?)");
		pr.setString(1, mail);
		pr.setString(2, mail);
		ResultSet rs=pr.executeQuery();
		while(rs.next()) {
			
			int ID = rs.getInt(1);
			String photo = rs.getString(2);
			String text = rs.getString(3);
			String location = rs.getString(4);
			int video = rs.getInt(5);
			Date date = rs.getDate(6);
			User writer = findPostWriter(ID);
			
			Post temp = new Post(ID, photo, text, location, video, date, writer);
			posts.add(temp);
		}
		
		return posts;
	}
	
	
	public static User findPostWriter(int IDPost) throws SQLException {
 		PreparedStatement pr=SQConnection.con.prepareStatement("select * from dbo.Users where Mail in(select UserMail from dbo.Post where ID=?)");
 		pr.setInt(1,IDPost);
 		ResultSet r1=pr.executeQuery();
 		
 		User temp = null;
 		
 		while(r1.next()) {
 			String mail = r1.getString(1);
 			
 			temp = Users_handler.findUser(mail);
 		}
 		
 		return temp;
 	}
	
	public static class sortByDate implements  Comparator<Post>{
		@Override
		 public int compare(Post o1, Post o2) 
         { 
             if( o2.getDate().compareTo(o1.getDate())<0 )
             	return -1;
             else {
             	if ( o2.getDate().compareTo(o1.getDate())>0 )
             		return 1;
             	else
             		return 0;
             }   	
         }
	}
	
	
	
	

}
