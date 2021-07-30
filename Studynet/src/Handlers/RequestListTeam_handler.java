	package Handlers;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.SQConnection;
import Model.User;

public abstract class RequestListTeam_handler {
	
	public static void addRequestTeam(int IDTeam,String managerMail,Date dateRequest,String sendMail) throws SQLException {
		String sql="Insert INTO dbo.RequestListTeam (IDTeam,managerMail,DateRequest,Confirm,userMail) VALUES (?,?,?,?,?)";
		PreparedStatement pr = SQConnection.con.prepareStatement(sql);
		pr.setInt(1, IDTeam);
		pr.setString(2,managerMail);
		pr.setDate(3, dateRequest);
		pr.setString(4, "N");
		pr.setString(5, sendMail);
		pr.executeUpdate();
	}
	
	public static void confirmRequsetTeam(int IDTeam,String sendMail) {
		String sql="Update dbo.RequestListTeam set Confirm='Y' where IDTeam=? and userMail=?";
		PreparedStatement pr;
		try {
			pr = SQConnection.con.prepareStatement(sql);
			pr.setInt(1, IDTeam);;
			pr.setString(2, sendMail);
			pr.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void deleteRequsetTeam(int IDTeam,String SendMail)  {
		String sql="Delete from dbo.RequestListTeam where IDTeam=? and userMail=?";
		PreparedStatement pr;
		try {
			pr = SQConnection.con.prepareStatement(sql);
			pr.setInt(1, IDTeam);
			pr.setString(2, SendMail);
			pr.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static ArrayList<User> requestSentMe(String Mail) {
		ArrayList<User> senders = new ArrayList<User>();
		PreparedStatement pr;
		try {
			pr = SQConnection.con.prepareStatement("select Distinct userMail from dbo.RequestListTeam where managerMail=? and Confirm='N'");
			pr.setString(1, Mail);
			ResultSet rs=pr.executeQuery();
			while(rs.next()) {
				User temp = Users_handler.findUser(rs.getString(1));
				senders.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return senders;
	}
	
	public static boolean isInRequest(String mail,int ID,String managerMail) {
		boolean result=false;
		ResultSet rs;
		try {
			PreparedStatement pr=SQConnection.con.prepareStatement("select * from dbo.RequestListTeam where Confirm='N'and IDTeam=? and userMail=? and managerMail=?");
			pr.setInt(1, ID);
			pr.setString(2, mail);
			pr.setString(3, managerMail);
			rs = pr.executeQuery();
			while(rs.next())
				result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static boolean isInRequestList(String mail,int ID) {
		boolean result=false;
		ResultSet rs;
		try {
			PreparedStatement pr=SQConnection.con.prepareStatement("select * from dbo.RequestListTeam where Confirm='N'and IDTeam=? and userMail=?");
			pr.setInt(1, ID);
			pr.setString(2, mail);
			rs = pr.executeQuery();
			while(rs.next())
				result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	

}
