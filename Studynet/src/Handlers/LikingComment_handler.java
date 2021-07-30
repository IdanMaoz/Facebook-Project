package Handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.SQConnection;
import Model.User;

public abstract class LikingComment_handler extends Liking_handler{
	
	static Connection con = SQConnection.con;
		
		
		public static void addLikingComment(int like,int IDPost,int IDComment,String Mail) throws SQLException {
			String sql="SET IDENTITY_INSERT dbo.likeToComment ON Insert INTO dbo.likeToComment (ID,IDLike,IDPost,IDComment,Mail) VALUES (?,?,?,?,?)";
			PreparedStatement pr = con.prepareStatement(sql);
			pr.setInt(1, sumLikeInComments()+1); 
			pr.setInt(2, like);
			pr.setInt(3, IDPost);
			pr.setInt(4, IDComment);
			pr.setString(5, Mail);
			pr.executeUpdate();
				

		}
		
		public static int sumLikeInComments() throws SQLException {//for the autoumatical ID
			int ID=0;
			PreparedStatement pr=con.prepareStatement("select * from dbo.likeToComment");
			int count;
	 		ResultSet r1=pr.executeQuery();
	 		while(r1.next()) {
	 			ID=r1.getInt(1);
	 		}
	 		return ID;
		}
		
		public static boolean alreadyMakeLikeToComment(int IDPost, int IDComment, String Mail) throws SQLException {
			PreparedStatement pr=SQConnection.con.prepareStatement("select * from dbo.likeToComment where IDPost=? and IDComment=? and Mail=?");
			pr.setInt(1, IDPost);
			pr.setInt(2, IDComment);
			pr.setString(3, Mail);
			ResultSet rs=pr.executeQuery();
			if(rs.next())
				return true;
			else
				return false;
		}
		
		public static int sumLikeOfPost(int IDPost,int IDComment) throws SQLException {
			int count=0;
			PreparedStatement pr=SQConnection.con.prepareStatement("select * from dbo.likeToComment where IDPost=? and IDComment=?");
			pr.setInt(1, IDPost);
			pr.setInt(2, IDComment);
			ResultSet rs=pr.executeQuery();
			while(rs.next()){
				count++;
			}
			return count;
		}
		
		public static boolean alreadyMakeLikeToComment(int IDLike,int IDPost, int IDComment,String Mail) throws SQLException {
			PreparedStatement pr=SQConnection.con.prepareStatement("select * from dbo.likeToComment where IDLike=? and IDPost=? and IDComment=? and Mail=? ");
			pr.setInt(1, IDLike);
			pr.setInt(2, IDPost);
			pr.setInt(3, IDComment);
			pr.setString(4, Mail);
			ResultSet rs=pr.executeQuery();
			if(rs.next())
				return true;
			else
				return false;
		}
		
		public static void deleteLikeToComment(int IDPost, int IDComment,String Mail) throws SQLException {
			PreparedStatement pr=SQConnection.con.prepareStatement("Delete from dbo.likeToComment where IDPost=? and IDComment=? and Mail=?");
			pr.setInt(1, IDPost);
			pr.setInt(2, IDComment);
			pr.setString(3, Mail);
			pr.executeUpdate();
		}
		
		public static ArrayList<User> findCommentLikers(int IDPost,int IDComment ) {
			ArrayList<User> users=new ArrayList<User>();
	 		User temp = null;

	 		PreparedStatement pr;
			try {
				pr = SQConnection.con.prepareStatement("SELECT Mail from dbo.LikeToComment where IDPost=? and IDComment=?");
		 		pr.setInt(1,IDPost);
		 		pr.setInt(2, IDComment);
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
		
		public static int getLikeType(String user, int postID, int CommentID) {
			String sql = "Select IDLike FROM LikeToComment WHERE Mail = ? AND IDPost=? AND IDComment=?";
			int result = 0;
			try {
				PreparedStatement pr = SQConnection.con.prepareStatement(sql);
				pr.setString(1,user);
				pr.setInt(2, postID);
				pr.setInt(3, CommentID);
				
				ResultSet rs = pr.executeQuery();
				
				while(rs.next())
					result = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return result;
		}
		
			

	}



