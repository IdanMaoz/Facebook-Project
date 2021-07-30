package Handlers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.SQConnection;
import View.ScreenHelper;

public abstract class Sharing_handler {
	
	public static void sharePost(String mail, int IDPost, String text) {
		String sql="Insert INTO dbo.Sharing (Mail,IDPost,DateSharing,Text) VALUES (?,?,?,?)";
		PreparedStatement pr;
		try {
			pr = SQConnection.con.prepareStatement(sql);
			pr.setString(1, mail);
			pr.setInt(2, IDPost);
			pr.setDate(3, ScreenHelper.getCurrentDate());
			pr.setString(4, text);
			pr.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean alreadySharedPost(String mail,int IDPost) {
		String sql="select * from dbo.Sharing where Mail=? and IDPost=?";
		boolean result=false;
		try {
			PreparedStatement pr=SQConnection.con.prepareStatement(sql);
			pr.setString(1, mail);
			pr.setInt(2, IDPost);
			ResultSet rs=pr.executeQuery();
			if(rs.next()) {
				result=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return result;
	}
	
}
