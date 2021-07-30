package Handlers;

import java.awt.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Controller.Main;
import Model.Comment;
import Model.SQConnection;
import Model.User;

public abstract class Comment_handler {	
	
	public static boolean checkComment(int IDPost,int numComment,String text,String Mail) throws SQLException {
		if(text.length()>200) {
			System.out.println("~~~Comment: " + numComment +"from Post: " + IDPost +" text too long");
			return false;
		}
		if(findComment(IDPost, numComment)) {
			System.out.println("~~~Comment: " + numComment +" from Post: " + IDPost +" has already exists");
			return false;
		}
		return true;
	}
	
	public static void addComment(int IDPost,int numComment,String dateComment,String text,String Mail) throws SQLException {
		
		if(checkComment(IDPost, numComment, text, Mail)) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime commentDate = LocalDateTime.parse(dateComment,formatter);
			String sql="Insert INTO dbo.Comment (IDPost,SerialNumComment,DateAndTimeComment,Text,Mail) VALUES (?,?,?,?,?)";
			PreparedStatement pr = SQConnection.con.prepareStatement(sql);
			
			pr.setInt(1, IDPost);
			pr.setInt(2, numComment);
			pr.setObject(3, commentDate);
			pr.setString(4, text);
			pr.setString(5, Mail);
			pr.executeUpdate();
		}
		
	}
	
	public static boolean findComment(int IDPost,int numComment) throws SQLException {
		PreparedStatement pr=SQConnection.con.prepareStatement("select * from dbo.Comment where IDPost=? and SerialNumComment=?");
		pr.setInt(1, IDPost);
		pr.setInt(2, numComment);
		ResultSet r1=pr.executeQuery();
		if(!r1.next())
			return false;
		return true;
	}
	
	public static ArrayList<Comment> findPostComments(int ID) throws SQLException {
		ArrayList<Comment> comments = new ArrayList<Comment>();
		PreparedStatement pr=SQConnection.con.prepareStatement("select * from dbo.Comment where IDPost=?");
		pr.setInt(1, ID);
		ResultSet rs=pr.executeQuery();
		while(rs.next()){
			
			int postID = rs.getInt(1);
			int commentID = rs.getInt(2);
			Date datePosted = rs.getDate(3);
			String text = rs.getString(4);
			User commenter = findCommentWriter(postID, commentID);
			
			Comment temp = new Comment(postID, commentID, datePosted, text, commenter);
			comments.add(temp);
		}
		
		return comments;
	}
	
	public static int sumLikeOfComment(String IDPost,String IDComment) throws SQLException {
		int postID=Integer.parseInt(IDPost);
		int CommentID=Integer.parseInt(IDComment);
		int count=0;
		PreparedStatement pr=SQConnection.con.prepareStatement("select * from dbo.likeToComment where IDPost=? and IDComment=?");
		pr.setInt(1, postID);
		pr.setInt(2, CommentID);
		ResultSet rs=pr.executeQuery();
		while(rs.next()){
			count++;
		}
		return count;
	}
	
	
	public static User findCommentWriter(int IDPost, int nComment) throws SQLException {
 		PreparedStatement pr=SQConnection.con.prepareStatement("select * from dbo.Users where Mail in(select Mail from dbo.Comment where IDPost=? and SerialNumComment=?)");
 		pr.setInt(1,IDPost);
 		pr.setInt(2, nComment);
 		ResultSet r1=pr.executeQuery();
 		
 		User temp = null;
 		
 		while(r1.next()) {
 			String mail = r1.getString(1);

 			temp = Users_handler.findUser(mail);
 		}
 		
 		return temp;
 	}
		

}
