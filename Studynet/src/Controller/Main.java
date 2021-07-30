package Controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import Handlers.Comment_handler;
import Handlers.LikingPost_handler;
import Handlers.Manage_handler;
import Handlers.Post_handler;
import Handlers.RequestListTeam_handler;
import Handlers.Reset_handler;
import Handlers.Users_handler;
import Model.EntryLogger;
import Model.SQConnection;
import View.Login;
import View.ScreenHelper;
import View.SignUpScreen;


public class Main {
	public static void main(String[] args) throws SQLException  {

		startConnection();	
		startGUI();
//		resetData();
//		loadMockData();
	}
			
	public static void resetData() {
		EntryLogger.clearLog();
		Reset_handler.resetAllTables();
	}
	
		
	public static void loadMockData() {
		resetData();
		try {
			Users_handler.loadMockData();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void startConnection() {
		try {
			SQConnection.setConnection();
			SQConnection.buildConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
			
	public static void startGUI() {
		FrameLoader.reset();
		FrameLoader.addFrame(new Login());		
	}
	
}
