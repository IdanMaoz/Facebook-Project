package Handlers;

import java.sql.PreparedStatement;
import java.sql.  ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.SQConnection;
import Model.User;

public abstract class FriendsList_handler {
	
	public static ArrayList<User> notFriendList(String Mail) {
		ArrayList<User> nonFriends = new ArrayList<User>();
		PreparedStatement pr;
		try {
			pr = SQConnection.con.prepareStatement("select * from dbo.Users where Mail!=? and Mail not in "
					+ "(select Mail2 from dbo.friendsList where Mail1=?)");
			
			pr.setString(1, Mail);
			pr.setString(2, Mail);
			ResultSet rs=pr.executeQuery();
			while(rs.next()) {
				String email = rs.getString(1);

		
				User Temp = Users_handler.findUser(email);
				nonFriends.add(Temp);

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return nonFriends;
	}
	
	public static ArrayList<User> FriendsList(String Mail) {
		ArrayList<User> friends = new ArrayList<User>();
		PreparedStatement pr;
		try {
			pr = SQConnection.con.prepareStatement("select * from dbo.Users where Mail!=? and Mail in "
					+ "(select Mail2 from dbo.friendsList where Mail1=?)");
			
			pr.setString(1, Mail);
			pr.setString(2, Mail);
			ResultSet rs=pr.executeQuery();
			while(rs.next()) {
				String email = rs.getString(1);
				User Temp = Users_handler.findUser(email);
				friends.add(Temp);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return friends;
	}
	
}
