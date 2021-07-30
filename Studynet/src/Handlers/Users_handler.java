package Handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import javax.swing.JOptionPane;

import Controller.Main;
import Model.EntryLogger;
import Model.SQConnection;
import Model.User;
import Controller.CurrentUser;


public abstract class Users_handler {
	
	static Connection con = SQConnection.con;
	static String userDetails;
	static String path = "src//Resources//MOCK_DATA.csv";
	static Boolean singleEntry = true;

	
 	public static User findUser(String userMail) {
 		User temp = null;
 		PreparedStatement pr;
		try {
			pr = SQConnection.con.prepareStatement("select * from dbo.Users where Mail=?");
			pr.setString(1,userMail);
	 		ResultSet r1=pr.executeQuery();
	 		
	 		while(r1.next()) {
	 			String mail = r1.getString(1);
	 			String fName = r1.getString(3);
	 			String lName = r1.getString(4);
	 			String nickName = r1.getString(8);
	 			String photo = r1.getString(9);
	 			
	 			temp = new User(mail, fName, lName, nickName, photo);
	 		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
 		return temp;
 	}
	
	public static void loadMockData() throws IOException {
		singleEntry = false;
		Path file = Paths.get(path);
		System.out.println("- Reading CSV file...");
		BufferedReader br = Files.newBufferedReader(file);
		System.out.println("   File read");

		String linetext = null;
		EntryLogger.addStatement(multiInsertHeader());
		while ((linetext = br.readLine()) != null) 
		{
			String [] data = linetext.split(",");
			String mail = data[0];
			String password = data [1];
			String fName = data[2];
			String lName = data[3];
			String dob = data[4];
			String joined = data[5];
			String gender = data[6];
			String nickName = data[7];
			String photo = data[8];
			String phone = data [9];
			
			if(gender.contentEquals("Male") || gender.contentEquals("male"))
				gender = "M";
			if(gender.contentEquals("female") || gender.contentEquals("Female"))
				gender = "F";
			if(gender.contentEquals("other") || gender.contentEquals("Other"))
				gender="O";
			setSingleEntry(false);

			addUser(mail, password, fName, lName, dob, joined, gender, nickName, photo, phone);
			setSingleEntry(true);
		}	
	}

	public static boolean check_constraints(String mail, String password, Date dob, String gender, Long phone,String nikcName)
	{
		if (mail.length() > 20){
			JOptionPane.showMessageDialog(null, "E-Mail too long");
			return false;
		}
			
		if (Year.now().getValue() - getYear(dob) < 18) {
			JOptionPane.showMessageDialog(null, "Sorry, you must be 18 or older to register");
			return false;
		}
		
		if(!gender.contentEquals("M") && !gender.contentEquals("F") && !gender.contentEquals("O")) {
			JOptionPane.showMessageDialog(null, "Please choose a gender");
			return false;
		}
		
		if(String.valueOf(phone).length() > 10)	{
			JOptionPane.showMessageDialog(null, "Phone number must be at most 10 digits");
			return false;
		}
		
		
		if (password.length() != 8) {
			JOptionPane.showMessageDialog(null, "Password must be 8 alpha-numeric characters");
			return false;
		}
		if(nikcName.length() > 20) {
			JOptionPane.showMessageDialog(null, "Nickname must be at most 20 characters long");
			return false;
			
		}
		return true;
	}
 	
 	public static void addUser(String mail, String password, String fName, String lName, String dob, String joined, String gender, String nickName, String photo, String phoneNum) 	{
 		if((mail.contentEquals("") || password.contentEquals("") || fName.contentEquals("") || lName.contentEquals("") || dob.contentEquals("") || joined.contentEquals("") || gender.contentEquals("") || phoneNum.contentEquals(""))) {
 			JOptionPane.showMessageDialog(null, "Please fill in all of the necessary fields (marked with *)");
 			return;
 		}
 			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
 			Date birthDate = null;
 			Date joinDate = Date.valueOf(joined);
 			long phone = Long.parseLong(phoneNum);
 	    	try{
 	    		birthDate = Date.valueOf(dob);
 	    	}
 	    	catch (Exception e) {
 	    		JOptionPane.showMessageDialog(null, "Invalid Date !");
 	    		return;
 	    	}
		
 			if(gender.contentEquals("male") || gender.contentEquals("Male"))
 				gender = "M";
 			else if(gender.contentEquals("female") || gender.contentEquals("Female"))
 				gender = "F";
 			else if(gender.contentEquals("other") || gender.contentEquals("Other"))
 				gender = "O";
		
		
 			if( check_constraints(mail, password, birthDate ,gender, phone,nickName)) 
 			{
 				if(Users_handler.findUser(mail)!=null) {
 					updateUser(mail, password, fName, lName, dob, joined, gender, nickName, photo, phoneNum);
 				}
 				else {
 				String sql = "INSERT INTO dbo.Users (Mail,Password,FirstName,LastName,BirthDate,JoinDate,Gender,NickName,Photo,Phone) VALUES (?,?,?,?,?,?,?,?,?,?)";
 				PreparedStatement pr;
 				try {
 					pr = con.prepareStatement(sql);
 					pr.setString(1, mail);	
 					pr.setString(2, password);
 					pr.setString(3, fName);
 					pr.setString(4, lName);
 					pr.setDate(5, birthDate);
 					pr.setObject(6, joinDate);
 					pr.setString(7, gender);
 					pr.setString(8, nickName);
 					pr.setString(9, photo);
					pr.setObject(10, phoneNum);	
				
					pr.executeUpdate();
					userDetails = userDetails(mail, password,fName,lName,birthDate.toString(), joinDate.toString(),gender,nickName,photo,phoneNum);
					EntryLogger.addStatement(insertLog());
					if(singleEntry==true)
					JOptionPane.showMessageDialog(null, "New user registered: \n" + findUser(mail).getDetails());
 				} catch (SQLException e) {
 					JOptionPane.showMessageDialog(null, "One or more of provided fields is invalid");
 				}
				
 		}
 			}
	}
 	
 	public static boolean checkPassword(String mail, String password){
 		try {
	 		PreparedStatement pr;
			pr = con.prepareStatement("SELECT Password FROM dbo.Users WHERE Mail=?");
	 		pr.setString(1,mail);
	 		ResultSet rs=pr.executeQuery();

	 		while(rs.next()) {
	 			if(rs.getString(1).contentEquals(password)) {
	 				System.out.println("checking password");
	 				return true;
	 			}
	 			else 
	 				return false;
	 		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
 	}
 	
 	public static String getFullName() {
 		String fullName="";
 		PreparedStatement pr;
		try {
			pr = con.prepareStatement("SELECT FirstName, LastName FROM dbo.Users WHERE Mail=?");
	 		pr.setString(1,CurrentUser.getUsername());
	 		ResultSet rs=pr.executeQuery();
	 		while(rs.next()) {
	 			fullName +=rs.getString(1) + " " + rs.getString(2);
	 		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
 		return fullName;

 	}

 	
 	public static void setSingleEntry(boolean single) {
 		singleEntry = single;
 	}
 	
	public static void printUsers() {
		String sql = "SELECT * FROM dbo.Users";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			int row = 1;
			System.out.println("- Printing Users table:");
			
			System.out.println("\t" + "\t" + "E-Mail "+ "   " + "\t" + " Password" + "  " + "Firstname"+ "   " + "Lastname"+ "   " + " Birthdate"+ "    " + "Joined"+ "\t" + "   " + " Sex"+ "   " + "Nickname"+ "\t" + "Photo"+ "\t" + "Phone");
			
			
			while(rs.next()) {
				System.out.println("------------------------------------------------------------------------------------------------------------------------------");
			    System.out.println(
			    		"Row " + row + ":" + "\t" + "\t" + 
			    		rs.getString(1) + "   " +
			            rs.getString(2) + "   " +
			            rs.getString(3) + "   " +
			            rs.getString(4) + "   " +
			            rs.getDate(5)   + "   " +
			            rs.getDate(6)   + "   " +
			            rs.getString(7) + "    " +
			            rs.getString(8) + "    " + "\t" +
			            rs.getString(9) + "    " + "\t" +
			            rs.getInt(10));
			    row++;
			    
			    
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public static Date currentDate() {
    	long millis=System.currentTimeMillis();  
    	Date date=new java.sql.Date(millis);
    	return date;
    }

    public static void clearTable() {
    	String sql = "DELETE FROM dbo.Users";  	
		try {
			Statement stmt = con.createStatement();			
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
    }
    	
    public static String insertLog() {
    	String action = "";
    	if(singleEntry) {
    		action += "INSERTED user:" 	+ "\n";
    		action += userHeader() 		+ "\n";
    	}
   		action += "\t\t\t\t" + userDetails;
   		return action;
    }
    
    public static String multiInsertHeader() {
    	String action = "INSERTED users:" + "\n";
    	action += userHeader() + "\n";
    	return action;
    }
    
    public static String getPsssword(String mail) {
    	String pass="";
    	try {
			PreparedStatement pr=SQConnection.con.prepareStatement("select Password from dbo.Users where Mail=?");
			pr.setString(1, mail);
			ResultSet rs=pr.executeQuery();
			while(rs.next()) {
				 pass=rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return pass;	
    }
    
    public static String getFirstName(String mail) {
    	String pass="";
    	try {
			PreparedStatement pr=SQConnection.con.prepareStatement("select FirstName from dbo.Users where Mail=?");
			pr.setString(1, mail);
			ResultSet rs=pr.executeQuery();
			while(rs.next()) {
				 pass=rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return pass;	
    }
    
    public static String getLastName(String mail) {
    	String pass="";
    	try {
			PreparedStatement pr=SQConnection.con.prepareStatement("select LastName from dbo.Users where Mail=?");
			pr.setString(1, mail);
			ResultSet rs=pr.executeQuery();
			while(rs.next()) {
				 pass=rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return pass;	
    }
    
    public static String getPhone(String mail) {
    	String pass="";
    	try {
			PreparedStatement pr=SQConnection.con.prepareStatement("select Phone from dbo.Users where Mail=?");
			pr.setString(1, mail);
			ResultSet rs=pr.executeQuery();
			while(rs.next()) {
				 pass=rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return pass;	
    }
    
    public static String getPhoto(String mail) {
    	String pass="";
    	try {
			PreparedStatement pr=SQConnection.con.prepareStatement("select Photo from dbo.Users where Mail=?");
			pr.setString(1, mail);
			ResultSet rs=pr.executeQuery();
			while(rs.next()) {
				 pass=rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return pass;	
    }
    
    public static String getNickName(String mail) {
    	String pass="";
    	try {
			PreparedStatement pr=SQConnection.con.prepareStatement("select NickName from dbo.Users where Mail=?");
			pr.setString(1, mail);
			ResultSet rs=pr.executeQuery();
			while(rs.next()) {
				 pass=rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return pass;	
    }
    
    public static String getDay(String mail) {
    	String pass="";
    	try {
			PreparedStatement pr=SQConnection.con.prepareStatement("select BirthDate from dbo.Users where Mail=?");
			pr.setString(1, mail);
			ResultSet rs=pr.executeQuery();
			while(rs.next()) {
				LocalDate localDate=Date.valueOf(rs.getString(1)).toLocalDate();
				pass=""+localDate.getDayOfMonth();	 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return pass;	
    }
    
    public static String getMonth(String mail) {
    	String pass="";
    	try {
			PreparedStatement pr=SQConnection.con.prepareStatement("select BirthDate from dbo.Users where Mail=?");
			pr.setString(1, mail);
			ResultSet rs=pr.executeQuery();
			while(rs.next()) {
				LocalDate localDate=Date.valueOf(rs.getString(1)).toLocalDate();
				pass=""+localDate.getMonthValue();	 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return pass;	
    }
    
    public static String getYear(String mail) {
    	String pass="";
    	try {
			PreparedStatement pr=SQConnection.con.prepareStatement("select BirthDate from dbo.Users where Mail=?");
			pr.setString(1, mail);
			ResultSet rs=pr.executeQuery();
			while(rs.next()) {
				LocalDate localDate=Date.valueOf(rs.getString(1)).toLocalDate();
				pass=""+localDate.getYear();	 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return pass;	
    }
    
    public static String getGender(String mail) {
    	String pass="";
    	try {
			PreparedStatement pr=SQConnection.con.prepareStatement("select Gender from dbo.Users where Mail=?");
			pr.setString(1, mail);
			ResultSet rs=pr.executeQuery();
			while(rs.next()) {
				pass=rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return pass;	
    }
    
    public static void updateUser(String mail, String password, String fName, String lName, String dob, String joined, String gender, String nickName, String photo, String phoneNum) {
    	Date birthDate = null;
    	Long phone=Long.parseLong(phoneNum);
    	try{
    		birthDate = Date.valueOf(dob);
    	}
    	catch (Exception e) {
    		JOptionPane.showMessageDialog(null, "Invalid Date !");
    		return;
    	}
    	try {
			PreparedStatement pr=SQConnection.con.prepareStatement("select * from Users where Mail=?");
			pr.setString(1, mail);
			ResultSet rs=pr.executeQuery();
			if(rs.next()) {
				String statement=rs.getString(1)+" Update:";
				String sql1="Update dbo.Users set Password=?,FirstName=?,LastName=?,BirthDate=?,Gender=?,NickName=?,Photo=?,Phone=? where Mail=?";
				PreparedStatement pr1;
				try {
					pr1= SQConnection.con.prepareStatement(sql1);
					pr1.setString(1, password);
					pr1.setString(2, fName);
					pr1.setString(3, lName);
					pr1.setDate(4, birthDate);
					pr1.setString(5, gender);
					pr1.setString(6, nickName);
					pr1.setString(7, photo);
					pr1.setLong(8, phone);
					pr1.setString(9, mail);
					pr1.execute();
					} catch (SQLException e) {
						e.printStackTrace();
				}
				if(rs.getString(2).contentEquals(password) && rs.getString(3).contains(fName) && rs.getString(4).contains(lName) && 
						rs.getDate(5).equals(birthDate) && rs.getString(7).contains(gender) && rs.getString(8).contentEquals(nickName) &&
						rs.getString(9).contentEquals(photo) && rs.getString(10).contains(phoneNum)) {
					statement="No Updatee";
				}
				else {
					if(!rs.getString(2).contentEquals(password)) {
						statement=statement+" Password "+rs.getString(2)+" changeTo "+password+",";
					}
					if(!rs.getString(3).contentEquals(fName)) {
						statement=statement+" FirstName "+rs.getString(3)+" changeTo "+fName+",";
					}
					if(!rs.getString(4).contentEquals(lName)) {
						statement=statement+" LastName "+rs.getString(4)+" changeTo "+lName+",";
					}
					if(!rs.getDate(5).equals(birthDate)) {
						statement=statement+" DateofBirth "+rs.getDate(5)+" changeTo "+birthDate+",";
					}
					if(!rs.getString(7).contentEquals(gender)) {
						statement=statement+" Gender "+rs.getString(7)+" changeTo "+gender+",";
					}
					if(!rs.getString(8).contentEquals(nickName)) {
						statement=statement+" NickName "+rs.getString(8)+" changeTo "+nickName+",";
					}
					if(!rs.getString(9).contentEquals(photo)) {
						statement=statement+" Photo "+rs.getString(9)+" changeTo "+photo+",";
					}
					if(!(rs.getString(10).contentEquals(phoneNum))) {
						statement=statement+" Phone "+rs.getString(10)+" changeTo "+phoneNum+",";
					}
				}
				statement=statement.substring(0, statement.length()-1);
				EntryLogger.addStatement(statement);
				JOptionPane.showMessageDialog(null, " Changes Saved !");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
    }
    
    
    public static String userHeader() {
    	String header = "";
    	header += "\t\t\t\t";
    	header += "E-Mail";
    	header += " \t\t\t";
    	header += "Password";
    	header += " \t";
    	header += "Firstname";
    	header += " \t";
    	header += "Lastname";
    	header += " \t";
    	header += "Birthdate";
    	header += " \t";
    	header += "JoinDate";
    	header += " \t";
    	header += "Gender";
    	header += " \t";
    	header += "Nickname";
    	header += " \t\t";
    	header += "Photo";
    	header += " \t\t";
    	header += "Phone";
    	header += "\n";
    	header +="\t\t\t\t-------------------------------------------------------------------------------------------------------------------------------------------------------------------"; 
    			
    	return header;
    }
    
    public static String userDetails(String mail, String password, String fName, String lName, String dob, String joined, String gender, String nick, String photo, String phone) {
    	String details = "";
    	details += mail;
    	if(mail.length() < 15)
    		details += "\t";
    	details += " " + "\t" + "|";
    	details += password;
    	details += " " + "\t" + "|";
    	details += fName;
    	if(fName.length() < 7)
    		details += "\t";
    	details += " " + "\t" + "|";
    	details += lName;
    	if(lName.length() < 7)
    		details += "\t";
    	details += " " + "\t" + "|";
    	details += dob;
    	details += " " + "\t" + "|";
    	details += joined;
    	details += " " + "\t" + "| ";
    	details += gender;
    	details += " " + "\t" + "|";
    	details += nick;
    	details += " " + "\t" + "|";
    	details += photo;
    	details += " " + "\t" + "|";
    	details += phone;
   
    	return details;
    }
}