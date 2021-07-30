package Handlers;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Model.SQConnection;
import Model.User;

public abstract class TeamMembers_handler {
	
	public static void addTeamMember(String Mail,long IDTeam,String DateAdd) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime addDate = LocalDateTime.parse(DateAdd,formatter);
		String sql="INSERT INTO dbo.ListFriendsInTeam (Mail,ID,DateAdding) VALUES (?,?,?)";
		PreparedStatement pr;
		try {
			pr = SQConnection.con.prepareStatement(sql);
			pr.setString(1, Mail);
			pr.setObject(2, IDTeam);
			pr.setObject(3, DateAdd);
			pr.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	public static ArrayList<User> getNonManagers(long IDTeam){
		String sql = "SELECT Mail From dbo.ListFriendsInTeam WHERE ID=? and Mail in (select Mail from dbo.Manager) EXCEPT SELECT Mail From dbo.Manage WHERE ID=?";
		ArrayList<User> members = new ArrayList<User>();
		PreparedStatement pr;
		try {
			pr = SQConnection.con.prepareStatement(sql);
			pr.setObject(1, IDTeam);
			pr.setObject(2, IDTeam);
			ResultSet rs=pr.executeQuery();
			
			while(rs.next()) {
				User temp = Users_handler.findUser(rs.getString(1));
				members.add(temp);
			}
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return members;
		
	}
	
	

}
