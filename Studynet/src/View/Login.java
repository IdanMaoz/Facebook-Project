package View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextPane;
import Controller.Backable;
import Controller.FrameLoader;
import Controller.Main;
import Handlers.Users_handler;
import Controller.CurrentUser;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;

public class Login extends JFrame implements Backable{
	
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField username;
	private JTextField password;
	
	private JButton signUpBtn;
	private JButton login_btn;
	
	public Login() {
		initComponents();
		createEvents();
		this.setVisible(true);
	}
	
	public void initFrame() {
		setBounds(0, 0, ScreenHelper.width(), ScreenHelper.height());
		contentPane = new JPanel();
		contentPane.setBounds(0, 0, ScreenHelper.width(), ScreenHelper.height());
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

	}
	
	public void initComponents() {
		initFrame();
		
		signUpBtn = new JButton("Sign Up");
		signUpBtn.setBounds(385, 335, 115, 30);
		ScreenHelper.style_btn(signUpBtn);
		contentPane.add(signUpBtn);
		
		JTextPane txtpnWelcomeToStudynet = new JTextPane();
		txtpnWelcomeToStudynet.setForeground(Color.CYAN);
		txtpnWelcomeToStudynet.setOpaque(false);
		txtpnWelcomeToStudynet.setEditable(false);
		txtpnWelcomeToStudynet.setFont(new Font("Calibri Light", Font.PLAIN, 72));
		txtpnWelcomeToStudynet.setText("Welcome to Studynet");
		txtpnWelcomeToStudynet.setBounds((ScreenHelper.width-700)/2, (ScreenHelper.height-100)/4, 700, 100);
		contentPane.add(txtpnWelcomeToStudynet);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(Color.CYAN);
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 18));
		lblPassword.setBounds(197, 295, 128, 28);
		contentPane.add(lblPassword);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setForeground(Color.CYAN);
		lblUsername.setFont(new Font("Arial", Font.PLAIN, 18));
		lblUsername.setLabelFor(username);
		lblUsername.setBounds(197, 257, 128, 28);
		contentPane.add(lblUsername);
		
		
		login_btn = new JButton("Login");
		login_btn.setBounds(266, 335, 115, 30);
		ScreenHelper.style_btn(login_btn);
		contentPane.add(login_btn);
		

		password = new JTextField();
		password.setFont(new Font("Arial", Font.PLAIN, 24));
		password.setColumns(10);
		password.setBounds(335, 295, 216, 27);
		contentPane.add(password);
		
		username = new JTextField();
		username.setFont(new Font("Arial", Font.PLAIN, 24));
		username.setBounds(335, 257, 216, 27);
		contentPane.add(username);
		username.setColumns(10);
		

		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(Login.class.getResource("/Images/logoimg.jpg")));
		logo.setBounds(10, 10, 50, 50);
		contentPane.add(logo);
		
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(Login.class.getResource("/Images/log_in_bg.jpg")));
		background.setBounds(0, 0, 784, 561);
		contentPane.add(background);
		
	}
	
	public void createEvents() {
		signUpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameLoader.addFrame(new SignUpScreen());
				setVisible(false);
			}
		});
		
		login_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				String mail = username.getText();		
				String pass = password.getText();
				if(Users_handler.checkPassword(mail, pass)) {
					System.out.println("Logged in as: " + mail);
					CurrentUser.setUser(mail);
					FrameLoader.addFrame(new WallScreen());
				}
				else {
					JOptionPane.showMessageDialog(null, "User Name or Password are incorrect");
				}
				//FrameLoader.addFrame(new WallScreen());
			}
		});
	}

	public void easyLogin() {
		this.username.setText("peter@gmail.com");
		this.password.setText("123456Aa");
	}
	
	@Override
	public void init() {
		Login reset = new Login();
		this.dispose();
	}
	
	public void vanish() {
		this.dispose();
	}
	
	public void disappear() {
		this.setVisible(false);
	}

}
