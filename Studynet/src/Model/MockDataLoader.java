package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import Controller.Main;
public class MockDataLoader {
	
	private BufferedReader br;
	private String path;
	
	public MockDataLoader() throws IOException {
		this.path = "src//Resources//MOCK_DATA.csv";
		readFile();
		loadData();
		
		
	}
	
	public void readFile() throws IOException {
		Path file = Paths.get(path);
		System.out.println("- Reading CSV file...");
		br = Files.newBufferedReader(file);
		System.out.println("   File read");

	}
	
	public void loadData() {
		String sql = "INSERT INTO dbo.Users (Mail,Password,FirstName,LastName,BirthDate,JoinDate,Gender,NickName,Photo,Phone) VALUES (?,?,?,?,?,?,?,?,?,?)";
		try (PreparedStatement pr = SQConnection.con.prepareStatement(sql))
		{
			
			int count = 0;
			String linetext = null;
			while ((linetext = br.readLine()) != null) 
			{
				String [] data = linetext.split(",");
				String mail = data[0];
				String password = data [1];
				String fName = data[2];
				String lName = data[3];
				String birthDate = data[4];
				String joinDateS = data[5];
				String gender = data[6];
				String nickName = data[7];
				String photo = data[8];
				String phoneS = data [9];
				
				Date dob = Date.valueOf(birthDate);
				Date joinDate = Date.valueOf(joinDateS);
				double phone = Double.parseDouble(phoneS);
				
				
				pr.setString(1, mail);
				pr.setString(2, password);
				pr.setString(3, fName);
				pr.setString(4, lName);
				pr.setDate(5, dob);
				pr.setDate(6, joinDate);
				pr.setString(7, String.valueOf(gender));
				pr.setString(8, nickName);
				pr.setString(9, photo);
				pr.setDouble(10, phone);
				
				
					
				
		        int row = pr.executeUpdate();
		        // rows affected
		        System.out.println(row); //1
	
			}
			    } catch (SQLException e) {
			        System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
	}
	
}
