package Handlers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.SQConnection;
import Model.User;
import View.ScreenHelper;

public abstract class TeamManager_handler {

	public static void assignManagerToTeam(String mail, int teamID) {
		String sql="Insert INTO dbo.Manage (Mail,ID,StartDate) VALUES (?,?,?)";
		PreparedStatement pr;
		try {
			pr = SQConnection.con.prepareStatement(sql);
			pr.setString(1, mail);
			pr.setInt(2, teamID);
			pr.setObject(3, ScreenHelper.getCurrentDateTime());
			pr.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static boolean isManagerOf(String mail, long teamID) {
		String sql = "SELECT * FROM dbo.Manage WHERE Mail=? AND ID=?";
		PreparedStatement pr;
		boolean result=false;
		try {
			pr = SQConnection.con.prepareStatement(sql);
			pr.setString(1, mail);
			pr.setObject(2, teamID);
			ResultSet rs = pr.executeQuery();
			
			while(rs.next())
				result=true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	
	
	public static ArrayList<User> findTeamManagers(int teamID) {
		ArrayList<User> managers = new ArrayList<User>();
		PreparedStatement pr;
		try {
			pr = SQConnection.con.prepareStatement("select * from dbo.Manage where ID=?");
			pr.setInt(1, teamID);
			
			ResultSet rs=pr.executeQuery();
			
			while(rs.next()) {
				User temp = Users_handler.findUser(rs.getString(1));
				managers.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return managers;
	}
	
}
