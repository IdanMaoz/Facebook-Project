package Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public abstract class EntryLogger {
	static String logName="Entry_Log.txt";
	static File log;

	public static void createLog() {
		log = new File(logName);
		try {
			if(log.createNewFile()) 
				System.out.println("- LOG file created");
			else
				System.out.println("- LOG file located");

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void addStatement(String statement) {
		try {
			FileWriter writer = new FileWriter(logName, true);
			writer.write("[" + timeStanp() + "] " + "\t" + statement + "\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	
	public static String timeStanp() {
    	long millis=System.currentTimeMillis();  
    	Timestamp ts = new Timestamp(millis);
    	
		return ts.toString();
	}

	public static void clearLog() {
		createLog();
		try {
			FileWriter writer = new FileWriter(logName, false);
			
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
