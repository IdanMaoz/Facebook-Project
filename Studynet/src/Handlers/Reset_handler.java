package Handlers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.SQConnection;

public abstract class Reset_handler {
	
	public static void resetAllTables() {
		resetLikeToCommentTable();
		resetLikeTofPostTable();
		resetCommentTable();
		resetFriendsListTable();
		resetListFriendsInTeamTable();
		resetManageTable();
		resetManagerTable();
		resetSharingTable();
		resetTaggingTable();
		resetRequestListTable();
		resetRequestListTeamTable();
		resetTeamTable();
		resetPostTable();
		resetUsersTable();
	}
	
	public static void resetLikeToCommentTable(){
		PreparedStatement pr;
		 try {
			pr=SQConnection.con.prepareStatement("Delete from dbo.likeToComment");
			pr.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void resetLikeTofPostTable(){
		PreparedStatement pr;
		try {
			pr=SQConnection.con.prepareStatement("Delete from dbo.likeTofPost");
			pr.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void resetCommentTable(){
		PreparedStatement pr;
		try {
			pr=SQConnection.con.prepareStatement("Delete from dbo.Comment");	
			pr.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void resetFriendsListTable(){
		PreparedStatement pr;
		try {
			pr=SQConnection.con.prepareStatement("Delete from dbo.Friendslist");
			pr.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void resetListFriendsInTeamTable(){
		PreparedStatement pr;
		try {
			pr=SQConnection.con.prepareStatement("Delete from dbo.listFriendsInTeam");	
			pr.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void resetManageTable(){
		PreparedStatement pr;
		try {
			pr=SQConnection.con.prepareStatement("Delete from dbo.Manage");	
			pr.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void resetManagerTable(){
		PreparedStatement pr;
		try {
			pr=SQConnection.con.prepareStatement("Delete from dbo.Manager");
			pr.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void resetSharingTable(){
		PreparedStatement pr;
		try {
			pr=SQConnection.con.prepareStatement("Delete from dbo.Sharing");	
			pr.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void resetTaggingTable(){
		PreparedStatement pr;
		try {
			pr=SQConnection.con.prepareStatement("Delete from dbo.Tagging");
			pr.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void resetRequestListTable(){
		PreparedStatement pr;
		try {
			pr=SQConnection.con.prepareStatement("Delete from dbo.RequestList");	
			pr.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void resetRequestListTeamTable(){
		PreparedStatement pr;
		try {
			pr=SQConnection.con.prepareStatement("Delete from dbo.RequestListTeam");
			pr.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void resetTeamTable(){
		PreparedStatement pr;
		try {
			pr=SQConnection.con.prepareStatement("Delete from dbo.Team");	
			pr.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void resetPostTable(){
		PreparedStatement pr;
		try {
			pr=SQConnection.con.prepareStatement("Delete from dbo.Post");	
			pr.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void resetUsersTable(){
		PreparedStatement pr;
		try {
			pr=SQConnection.con.prepareStatement("Delete from dbo.Users");	
			pr.execute();
			System.out.println("SFDDF");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}