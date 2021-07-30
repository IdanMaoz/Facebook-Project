package Handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Controller.CurrentUser;
import Controller.Main;
import Model.SQConnection;
import View.ScreenHelper;

public abstract class Tagging_handler {
	
	public static void Tagging(int IDPost, String taggedMail) {
		String sql="Insert INTO dbo.Tagging (Mail,IDPost,TagMail) VALUES (?,?,?)";	
		PreparedStatement pr;
		try {
			pr = SQConnection.con.prepareStatement(sql);
			pr.setString(1, CurrentUser.getUsername());
			pr.setInt(2, IDPost);
			pr.setString(3, taggedMail);
			pr.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static ArrayList<Integer> taggedIn(String mail) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		String sql="SELECT IDPost FROM dbo.Tagging WHERE TagMail=?";
		try {
			PreparedStatement pr=SQConnection.con.prepareStatement(sql);
			pr.setString(1, mail);
			ResultSet rs = pr.executeQuery();
			
			while(rs.next()) {
				int postID = rs.getInt(1);
				arr.add(postID);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return arr;
	}
	
}
