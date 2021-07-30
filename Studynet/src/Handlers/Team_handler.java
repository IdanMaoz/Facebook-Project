package Handlers;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import Model.SQConnection;
import Model.Team;
import View.ScreenHelper;
import jdk.nashorn.internal.runtime.RewriteException;

public abstract class Team_handler {
	
	public static void addTeam(String teamName, String creatorName, String creationDate) throws SQLException {
		int teamID = -1;
		if(checkTeam(teamName)) {
			String sql="SET IDENTITY_INSERT dbo.Team ON INSERT INTO dbo.Team(ID,Name) VALUES (?,?)";
			PreparedStatement pr = SQConnection.con.prepareStatement(sql);
			teamID = sumTeams() +1;
			pr.setLong(1, teamID);
			pr.setString(2, teamName);
			pr.executeUpdate();
		}
		
		TeamMembers_handler.addTeamMember(creatorName, teamID, creationDate);
		TeamManager_handler.assignManagerToTeam(creatorName, teamID);
	}
	
	public static boolean checkTeam(String name) throws SQLException {
		if(name.length()>20) {
			System.out.println("~~~team: " + (sumTeams()+1) +" name is too long");
			return false;
		}
		return true;
		
	}
	
	public static int sumTeams() throws SQLException {//for the autoumatical ID
		PreparedStatement pr=SQConnection.con.prepareStatement("select * from dbo.Team");
		int count=0;
 		ResultSet r1=pr.executeQuery();
 		while(r1.next()) {
 			count++;
 		}
 		return count;
	}
	
	public static ArrayList<Team> getJoinedTeams(String Mail) throws SQLException {
		ArrayList<Team> teams = new ArrayList<Team>();
		PreparedStatement pr=SQConnection.con.prepareStatement("select * from dbo.Team where ID in "
				+ "(select ID from dbo.ListFriendsInTeam where Mail=?)");
		pr.setString(1, Mail);
		ResultSet rs=pr.executeQuery();
		while(rs.next()) {
			int ID = rs.getInt(1);
			String name = rs.getString(2);
	
			Team Temp = new Team(ID, name);
			teams.add(Temp);
		}
		return teams;
	}
	
	public static ArrayList<Team> getNotJoinedTeams(String Mail) throws SQLException {
		ArrayList<Team> teams = new ArrayList<Team>();
		PreparedStatement pr=SQConnection.con.prepareStatement("select * from dbo.Team where ID not in "
				+ "(select ID from dbo.ListFriendsInTeam where Mail=?)");
		pr.setString(1, Mail);
		ResultSet rs=pr.executeQuery();
		while(rs.next()) {
			int ID = rs.getInt(1);
			String name = rs.getString(2);
	
			Team Temp = new Team(ID, name);
			teams.add(Temp);
		}
		return teams;
	}
	
	public static ArrayList<Team> getTeams(){
		ArrayList<Team> teams=new ArrayList<Team>();
		PreparedStatement pr;
		try {
			pr = SQConnection.con.prepareStatement("select * from dbo.Team");
			ResultSet rs=pr.executeQuery();
			while(rs.next()) {
				int ID=rs.getInt(1);
				String name=rs.getString(2);
				Team temp=new Team(ID, name);
				teams.add(temp);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teams;
	
	}
	
	


	


}
