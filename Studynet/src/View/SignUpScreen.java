package View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;

import Controller.Backable;
import Controller.CurrentUser;
import Controller.FrameLoader;
import Controller.Main;
import Handlers.Users_handler;
import Model.EntryLogger;

import javax.swing.JRadioButton;
import javax.swing.JButton;

public class SignUpScreen extends JFrame implements Backable{
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField mail;
	private JTextField password;
	private JTextField day;
	private JTextField month;
	private JTextField year;
	private JTextField phone;
	private JTextField photo;
	private JTextField nickname;
	private JTextField firstName;
	private JTextField lastName;
	
	private JButton btnBack;
	private JButton btnSubmit;
	
	private JRadioButton rdbtnFemale;
	private JRadioButton rdbtnMale;
	private JRadioButton rdbtnOther;
	private ButtonGroup rdbGender;
	
	
	
	public SignUpScreen() {
		initComponents();
		createEvents();
		this.setVisible(true);
	}

	public void initComponents() {
		initFrame();
	}
	
	public void initFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 800, 600);
		contentPane = new JPanel();
		contentPane.setBounds(0, 0, ScreenHelper.width(), ScreenHelper.height());
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(402, 372, 115, 30);
		ScreenHelper.style_btn(btnBack);
		contentPane.add(btnBack);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(281, 372, 115, 30);
		ScreenHelper.style_btn(btnSubmit);
		contentPane.add(btnSubmit);
		
		JLabel lblLastName_1 = new JLabel("Last name:*");
		lblLastName_1.setForeground(Color.CYAN);
		lblLastName_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblLastName_1.setBounds(67, 190, 105, 27);
		contentPane.add(lblLastName_1);
		
		JLabel lblFName_1 = new JLabel("First name:*");
		lblFName_1.setForeground(Color.CYAN);
		lblFName_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblFName_1.setBounds(67, 140, 105, 27);
		contentPane.add(lblFName_1);
		
		lastName = new JTextField();
		lastName.setFont(new Font("Arial", Font.PLAIN, 16));
		lastName.setColumns(10);
		lastName.setBounds(173, 190, 180, 30);
		contentPane.add(lastName);
		
		firstName = new JTextField();
		firstName.setFont(new Font("Arial", Font.PLAIN, 16));
		firstName.setBounds(173, 140, 180, 30);
		contentPane.add(firstName);
		
		rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setFont(new Font("Arial", Font.PLAIN, 16));
		rdbtnFemale.setForeground(Color.CYAN);
		rdbtnFemale.setBounds(421, 335, 85, 20);
		rdbtnFemale.setOpaque(false);
		contentPane.add(rdbtnFemale);
		
		rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setForeground(Color.CYAN);
		rdbtnMale.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnMale.setFont(new Font("Arial", Font.PLAIN, 16));
		rdbtnMale.setBounds(327, 335, 85, 20);
		rdbtnMale.setOpaque(false);
		contentPane.add(rdbtnMale);
		
		rdbtnOther = new JRadioButton("Other");
		rdbtnOther.setForeground(Color.CYAN);
		rdbtnOther.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnOther.setFont(new Font("Arial", Font.PLAIN, 16));
		rdbtnOther.setBounds(515, 335, 85, 20);
		rdbtnOther.setOpaque(false);
		contentPane.add(rdbtnOther);
		
		rdbGender = new ButtonGroup();
		rdbGender.add(rdbtnMale);
		rdbGender.add(rdbtnFemale);
		rdbGender.add(rdbtnOther);
		
		JLabel lblGender = new JLabel("Gender:*");
		lblGender.setForeground(Color.CYAN);
		lblGender.setFont(new Font("Arial", Font.PLAIN, 16));
		lblGender.setBounds(250, 332, 105, 27);
		contentPane.add(lblGender);
		
		JLabel lblNickname = new JLabel("Nickname:");
		lblNickname.setForeground(Color.CYAN);
		lblNickname.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNickname.setBounds(387, 290, 105, 27);
		contentPane.add(lblNickname);
		
		photo = new JTextField();
		photo.setFont(new Font("Arial", Font.PLAIN, 16));
		photo.setColumns(10);
		photo.setBounds(493, 240, 180, 30);
		contentPane.add(photo);
		
		JLabel lblPhoto = new JLabel("Photo:");
		lblPhoto.setForeground(Color.CYAN);
		lblPhoto.setFont(new Font("Arial", Font.PLAIN, 16));
		lblPhoto.setBounds(387, 240, 105, 27);
		contentPane.add(lblPhoto);
		
		JLabel lblPhone = new JLabel("Phone:*");
		lblPhone.setForeground(Color.CYAN);
		lblPhone.setFont(new Font("Arial", Font.PLAIN, 16));
		lblPhone.setBounds(387, 190, 105, 27);
		contentPane.add(lblPhone);
		
		phone = new JTextField();
		phone.setFont(new Font("Arial", Font.PLAIN, 16));
		phone.setColumns(10);
		phone.setBounds(493, 190, 180, 30);
		contentPane.add(phone);
		
		JLabel lblMonth = new JLabel("Month");
		lblMonth.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonth.setForeground(Color.CYAN);
		lblMonth.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		lblMonth.setBounds(553, 125, 50, 14);
		contentPane.add(lblMonth);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setHorizontalAlignment(SwingConstants.CENTER);
		lblYear.setForeground(Color.CYAN);
		lblYear.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		lblYear.setBounds(613, 125, 60, 14);
		contentPane.add(lblYear);
		
		JLabel lblNewLabel = new JLabel("Day");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		lblNewLabel.setForeground(Color.CYAN);
		lblNewLabel.setBounds(493, 125, 46, 14);
		contentPane.add(lblNewLabel);
		
		year = new JTextField();
		year.setHorizontalAlignment(SwingConstants.CENTER);
		year.setFont(new Font("Arial", Font.PLAIN, 16));
		year.setColumns(10);
		year.setBounds(613, 140, 60, 30);
		contentPane.add(year);
		
		month = new JTextField();
		month.setHorizontalAlignment(SwingConstants.CENTER);
		month.setFont(new Font("Arial", Font.PLAIN, 16));
		month.setColumns(10);
		month.setBounds(553, 140, 50, 30);
		contentPane.add(month);
		
		day = new JTextField();
		day.setHorizontalAlignment(SwingConstants.CENTER);
		day.setFont(new Font("Arial", Font.PLAIN, 16));
		day.setBounds(493, 140, 50, 30);
		contentPane.add(day);
		day.setColumns(10);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth:*");
		lblDateOfBirth.setForeground(Color.CYAN);
		lblDateOfBirth.setFont(new Font("Arial", Font.PLAIN, 16));
		lblDateOfBirth.setBounds(387, 140, 105, 27);
		contentPane.add(lblDateOfBirth);
		
		JLabel lblLastName = new JLabel("Password:*");
		lblLastName.setForeground(Color.CYAN);
		lblLastName.setFont(new Font("Arial", Font.PLAIN, 16));
		lblLastName.setBounds(67, 290, 105, 27);
		contentPane.add(lblLastName);
		
		password = new JTextField();
		password.setFont(new Font("Arial", Font.PLAIN, 16));
		password.setColumns(10);
		password.setBounds(173, 290, 180, 30);
		contentPane.add(password);
		
		JLabel lblFName = new JLabel("E-Mail:*");
		lblFName.setForeground(Color.CYAN);
		lblFName.setFont(new Font("Arial", Font.PLAIN, 16));
		lblFName.setBounds(67, 240, 105, 27);
		contentPane.add(lblFName);
		
		mail = new JTextField();
		mail.setFont(new Font("Arial", Font.PLAIN, 16));
		mail.setBounds(173, 240, 180, 30);
		contentPane.add(mail);
		mail.setColumns(10);
		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(SignUpScreen.class.getResource("/Images/logoimg.jpg")));
		logo.setBounds(10, 10, 50, 50);
		contentPane.add(logo);
		
		JTextPane txtpnSignUp = new JTextPane();
		txtpnSignUp.setForeground(Color.CYAN);
		txtpnSignUp.setFont(new Font("Calibri Light", Font.PLAIN, 72));
		txtpnSignUp.setText("Sign Up");
		txtpnSignUp.setBounds(271, 10, 243, 94);
		txtpnSignUp.setOpaque(false);
		txtpnSignUp.setEditable(false);
		contentPane.add(txtpnSignUp);
		
		nickname = new JTextField();
		nickname.setFont(new Font("Arial", Font.PLAIN, 16));
		nickname.setColumns(10);
		nickname.setBounds(493, 290, 180, 30);
		contentPane.add(nickname);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(SignUpScreen.class.getResource("/Images/log_in_bg.jpg")));
		background.setBounds(0, 0, 800, 600);
		contentPane.add(background);
		System.out.println(CurrentUser.getUsername());
		if(CurrentUser.getUsername()!="") {
			mail.setText(CurrentUser.getUsername());
			mail.setEditable(false);
			password.setText(Users_handler.getPsssword(CurrentUser.getUsername()));
			firstName.setText(Users_handler.getFirstName(CurrentUser.getUsername()));
			lastName.setText(Users_handler.getLastName(CurrentUser.getUsername()));
			phone.setText(Users_handler.getPhone(CurrentUser.getUsername()));
			photo.setText(Users_handler.getPhoto(CurrentUser.getUsername()));
			nickname.setText(Users_handler.getNickName(CurrentUser.getUsername()));
			day.setText(Users_handler.getDay(CurrentUser.getUsername()));
			month.setText(Users_handler.getMonth(CurrentUser.getUsername()));
			year.setText(Users_handler.getYear(CurrentUser.getUsername()));
			if(Users_handler.getGender(CurrentUser.getUsername()).contentEquals("M")) {
				rdbtnMale.setSelected(true);
			}
			else if(Users_handler.getGender(CurrentUser.getUsername()).contentEquals("F")) {
				rdbtnFemale.setSelected(true);
			}
			else
				rdbtnOther.setSelected(true);
		}
	}
	

	public void createEvents() {
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameLoader.back();
			}
		});
		
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!CurrentUser.getUsername().contentEquals("")) {
					Users_handler.updateUser(mail.getText(), password.getText(), firstName.getText(), lastName.getText(), getDate(), getJoined(), getGender(), nickname.getText(), photo.getText(), phone.getText());
				}
				else {
					Users_handler.addUser(mail.getText(), password.getText(), firstName.getText(), lastName.getText(), getDate(), getJoined(), getGender(), nickname.getText(), photo.getText(), phone.getText());
				}
			}
		});
	}
	
	
	
	public String getJoined() {
		 long millis=System.currentTimeMillis();  
		 Date date = new Date(millis);
		 return date.toString();
	}
	
	public String getDate() {
		String date = "";
		String y = year.getText();
		String m = month.getText();
		String d = day.getText();
		date = y + "-" + m + "-" + d;
		System.out.println(date);
		return date;
		
	}
	
	public String getGender() {
		String gender="";
		if(rdbtnMale.isSelected())
			gender = "M";
		if(rdbtnFemale.isSelected())
			gender = "F";
		if(rdbtnOther.isSelected())
			gender="O";
		return gender;
	}

	@Override
	public void init() {
		SignUpScreen reset = new SignUpScreen();
		this.dispose();
		
	}
	
	public void vanish() {
		dispose();
	}

	@Override
	public void disappear() {
		this.setVisible(false);
		
	}
}
