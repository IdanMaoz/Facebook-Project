package Handlers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.SQConnection;
import Model.User;

public abstract class Manage_handler {
	
	public static ArrayList<String> manageInTeam(int IDTeam) {
		ArrayList<String> userMails=new ArrayList<String>();
		ResultSet rs;
		try {
			PreparedStatement pr=SQConnection.con.prepareStatement("select Mail from dbo.Manage where ID=?");
			pr.setInt(1, IDTeam);
			rs = pr.executeQuery();
			while(rs.next())
				userMails.add(rs.getString(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userMails;
		
	}

}
