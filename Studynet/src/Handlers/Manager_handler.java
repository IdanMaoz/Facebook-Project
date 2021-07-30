package Handlers;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Model.EntryLogger;
import Model.SQConnection;
import View.ScreenHelper;


public abstract class Manager_handler {
	
	public static void addManager(String mail, String ID, String description) 
	{
 		long identification=0;
		String sql = "INSERT INTO dbo.Manager (Mail,IDManager,Description) VALUES (?,?,?)";
		PreparedStatement pr;
		try {
			if(!ID.contentEquals("")) {
				identification = Long.parseLong(ID);
			}
			pr = SQConnection.con.prepareStatement(sql);
			pr.setString(1, mail);	
			pr.setObject(2, identification);
			pr.setString(3, description);
		    pr.executeUpdate();
		    JOptionPane.showMessageDialog(null, mail + " Now registered as a Manager !");
		    EntryLogger.addStatement("New Manager Registered: " + mail);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "invalid id!");
		}
		
		ScreenHelper.updateButtons();
	}
	
	
	public static boolean isManager(String user) {
		boolean result = false;
		ResultSet rs;
		try {
			PreparedStatement pr=SQConnection.con.prepareStatement("select * from dbo.Manager where Mail=?");
			pr.setString(1, user);
			rs = pr.executeQuery();
			while(rs.next())
				result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	}

}
