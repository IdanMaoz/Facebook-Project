package Handlers;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.SQConnection;
import Model.User;

public abstract class RequestList_handler {

	public static void addRequest(String sender, String receiver, Date date2){
		String sql="Insert INTO dbo.RequestList (RequestedMail,Mail,DateRequest,Confirm) VALUES (?,?,?,?)";
		PreparedStatement pr;
		try {
			pr = SQConnection.con.prepareStatement(sql);
			pr.setString(1, sender);
			pr.setString(2, receiver);
			pr.setDate(3, date2);
			pr.setString(4, "N");
			pr.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void confirmRequset(String ReqMail,String sendMail){
		String sql="Update dbo.RequestList set Confirm='Y' where RequestedMail=? and Mail=?";
		PreparedStatement pr;
		try {
			pr = SQConnection.con.prepareStatement(sql);
			pr.setString(1, ReqMail);
			pr.setString(2, sendMail);
			pr.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteRequset(String ReqMail,String SendMail){
		String sql="Delete from dbo.RequestList where RequestedMail=? and Mail=?";
		PreparedStatement pr;
		try {
			pr = SQConnection.con.prepareStatement(sql);
			pr.setString(1, ReqMail);
			pr.setString(2, SendMail);
			pr.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static ArrayList<User> requestSentMe(String Mail) {
		ArrayList<User> senders = new ArrayList<User>();
		PreparedStatement pr;
		try {
			pr = SQConnection.con.prepareStatement("select RequestedMail from dbo.RequestList where Mail=? and Confirm='N'");
			pr.setString(1, Mail);
			ResultSet rs=pr.executeQuery();
			while(rs.next()) {
				User temp = Users_handler.findUser(rs.getString(1));
				senders.add(temp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return senders;
	}
	
	public static boolean isInRequestList(String Reqmail, String sendMail) {
		boolean result=false;
		ResultSet rs;
		try {
			PreparedStatement pr=SQConnection.con.prepareStatement("select * from dbo.RequestList where Confirm='N'and RequestedMail=? and Mail=?");
			pr.setString(1, Reqmail);
			pr.setString(2, sendMail);
			rs = pr.executeQuery();
			while(rs.next())
				result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	


}
