package Handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.SQConnection;
import Model.User;

public abstract class LikingPost_handler extends Liking_handler {
	
	static Connection con = SQConnection.con;
	
	public static void addLikingPost(int like,int IDPost,String Mail) throws SQLException {
		String sql="SET IDENTITY_INSERT dbo.likeTofPost ON Insert INTO dbo.likeTofPost (ID,IDLike,IDPost,Mail) VALUES (?,?,?,?)";
		PreparedStatement pr = con.prepareStatement(sql);
		pr.setInt(1, sumLikeInPosts()+1);
		pr.setInt(2, like);
		pr.setInt(3, IDPost);
		pr.setString(4, Mail);
		pr.executeUpdate();
			
		
	}
	
	public static int sumLikeInPosts() throws SQLException {
		int ID=0;
		PreparedStatement pr=con.prepareStatement("select * from dbo.likeTofPost");
 		ResultSet r1=pr.executeQuery();
 		while(r1.next()) {
 			ID= r1.getInt(1);
 		}
 		return ID;
	}
	
	public static int sumLikeOfPost(int IDPost) throws SQLException {
		int count=0;
		PreparedStatement pr=SQConnection.con.prepareStatement("select * from dbo.likeTofPost where IDPost=?");
		pr.setInt(1, IDPost);
		ResultSet rs=pr.executeQuery();
		while(rs.next()){
			count++;
		}
		return count;
	}
	
	public static boolean alreadyMakeLikeToPost(int IDPost,String Mail) throws SQLException {
		PreparedStatement pr=SQConnection.con.prepareStatement("select * from dbo.likeTofPost where IDPost=? and Mail=?");
		pr.setInt(1, IDPost);
		pr.setString(2, Mail);
		ResultSet rs=pr.executeQuery();
		if(rs.next())
			return true;
		else
			return false;
	}
	
	public static boolean alreadyMakeLikeToPost(int IDLike,int IDPost,String Mail) throws SQLException {
		PreparedStatement pr=SQConnection.con.prepareStatement("select * from dbo.likeTofPost where IDLike=? and IDPost=? and Mail=? ");
		pr.setInt(1, IDLike);
		pr.setInt(2, IDPost);
		pr.setString(3, Mail);
		ResultSet rs=pr.executeQuery();
		if(rs.next())
			return true;
		else
			return false;
	}
	
	public static void deleteLikeToPost(int IDPost, String mail) throws SQLException {
		PreparedStatement pr=SQConnection.con.prepareStatement("Delete from dbo.likeTofPost where IDPost=? and Mail=?");
		pr.setInt(1, IDPost);
		pr.setString(2, mail);
		pr.executeUpdate();
	}
		
	public static ArrayList<User> findPostLikers(int IDPost) {
		ArrayList<User> users=new ArrayList<User>();
 		User temp = null;

 		PreparedStatement pr;
		try {
			pr = SQConnection.con.prepareStatement("SELECT Mail from dbo.LiketofPost where IDPost=?");
	 		pr.setInt(1,IDPost);
	 		ResultSet r1=pr.executeQuery();
	 		
	 		while(r1.next()) {
	 			String mail = r1.getString(1);
	 			users.add(Users_handler.findUser(mail));
	 		}

		} catch (SQLException e) {
			e.printStackTrace();
		}

 		return users;
 	}
	
	public static int getLikeType(String user, int postID) {
		String sql = "Select IDLike FROM LikeTofPost WHERE Mail = ? AND IDPost=?";
		int result = 0;
		try {
			PreparedStatement pr = SQConnection.con.prepareStatement(sql);
			pr.setString(1,user);
			pr.setInt(2, postID);
			
			ResultSet rs = pr.executeQuery();
			
			while(rs.next())
				result = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
		

}
