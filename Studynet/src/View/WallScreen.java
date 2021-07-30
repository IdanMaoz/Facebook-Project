package View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import Controller.Backable;
import Controller.FrameLoader;
import Handlers.FriendsList_handler;
import Handlers.Manager_handler;
import Handlers.Post_handler;
import Handlers.Post_handler.sortByDate;
import Handlers.RequestListTeam_handler;
import Handlers.RequestList_handler;
import Handlers.Tagging_handler;
import Handlers.TeamManager_handler;
import Handlers.TeamMembers_handler;
import Handlers.Team_handler;
import Handlers.Users_handler;
import Controller.CurrentUser;
import Model.Post;
import Model.Team;
import Model.User;
import View.Friend_views.FindFriendsPane;
import View.Friend_views.FriendRequestsPane;
import View.Friend_views.MyFriendsPane;
import View.Post_views.AddPost;
import View.Post_views.PostPane;
import View.Tagging_views.TagNotificationPane;
import View.Team_views.AddToTeamRequestPane;
import View.Team_views.TeamCreateionScreen;
import View.Team_views.JoinTeamPane;
import View.Team_views.ManagerRegistrationScreen;
import View.Team_views.MyTeamsPane;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JViewport;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;


import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;

public class WallScreen extends JFrame implements Backable{
	
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JPanel view;
	
	private JButton btnFriendRequests;
	private JButton btnNotifications;
	private JButton btnAddPost;
	private JButton btnGroups;
	private JButton btnFriends;
	private JButton btnJoinGroups;
	private JButton btnFindFriends;
	private JButton btnLogOut;
	private JButton btnCreateGroup;
	private JButton btnPosts;
	private JButton btnBecomeAManager;
	private JButton btnTeamRequests;
	private JButton btnUpdateInfo;
	
	private JScrollPane scroller;
	
	//private HashMap<Integer, PostPane> postPanes;
	
	public WallScreen() {
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
		resetView();
		
		scroller = new JScrollPane(view);
		scroller.setBounds(179, 51, 580, 500);
		scroller.setOpaque(false);
		scroller.getViewport().setOpaque(false);
		
		if(!Manager_handler.isManager(CurrentUser.getUsername())) {
			btnBecomeAManager = new JButton("Become a Manager");
			btnBecomeAManager.setBounds(10, 350, 150, 30);
			ScreenHelper.style_btn(btnBecomeAManager);
			btnBecomeAManager.setFont(new Font("Calibri Light", Font.BOLD, 14));
			contentPane.add(btnBecomeAManager);
		}
		else {
			btnTeamRequests = new JButton("Team Requests");
			btnTeamRequests.setBounds(10, 350, 150, 30);
			ScreenHelper.style_btn(btnTeamRequests);
			btnTeamRequests.setFont(new Font("Calibri Light", Font.BOLD, 18));
			contentPane.add(btnTeamRequests);
		}
		
		btnUpdateInfo = new JButton("Update Info");
		btnUpdateInfo.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		btnUpdateInfo.setBounds(7, 470, 150, 30);
		ScreenHelper.style_btn(btnUpdateInfo);
		contentPane.add(btnUpdateInfo);
			
		
		btnPosts = new JButton("Posts");
		btnPosts.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		btnPosts.setBounds(7, 110, 150, 30);
		ScreenHelper.style_btn(btnPosts);
		
		contentPane.add(btnPosts);
		contentPane.add(scroller);
		
		btnCreateGroup = new JButton("Make Group");
		btnCreateGroup.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		btnCreateGroup.setBounds(7, 310, 150, 30);
		ScreenHelper.style_btn(btnCreateGroup);
		contentPane.add(btnCreateGroup);
		
		btnLogOut = new JButton("Log Out");
		btnLogOut.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		btnLogOut.setBounds(7, 510, 150, 30);
		ScreenHelper.style_btn(btnLogOut);
		contentPane.add(btnLogOut);
		
		btnFriendRequests = new JButton("Friend Requests");
		btnFriendRequests.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		btnFriendRequests.setBounds(520, 10, 200, 30);
		ScreenHelper.style_btn(btnFriendRequests);
		contentPane.add(btnFriendRequests);
		
		btnNotifications = new JButton("Notifications");
		btnNotifications.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		btnNotifications.setBounds(303, 10, 200, 30);
		ScreenHelper.style_btn(btnNotifications);
		contentPane.add(btnNotifications);
		
		JTextArea txtrWelcome = new JTextArea();
		txtrWelcome.setForeground(Color.CYAN);
		txtrWelcome.setFont(new Font("Calibri Light", Font.PLAIN, 24));
		txtrWelcome.setText("Hello");
		txtrWelcome.setBounds(70, 15, 55, 23);
		txtrWelcome.setOpaque(false);
		contentPane.add(txtrWelcome);
		
		JTextArea txtUsername = new JTextArea();
		txtUsername.setForeground(Color.CYAN);
		txtUsername.setFont(new Font("Calibri Light", Font.PLAIN, 24));
		txtUsername.setText(Users_handler.getFullName());
		txtUsername.setOpaque(false);
		txtUsername.setBounds(136, 15, 160, 23);
		
		contentPane.add(txtUsername);
		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(SignUpScreen.class.getResource("/Images/logoimg.jpg")));
		logo.setBounds(10, 10, 50, 50);
		contentPane.add(logo);
		
		btnAddPost = new JButton("Add Post");
		btnAddPost.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		btnAddPost.setBounds(7, 70, 150, 30);
		ScreenHelper.style_btn(btnAddPost);
		contentPane.add(btnAddPost);
		
		btnGroups = new JButton("Groups");
		btnGroups.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		btnGroups.setBounds(7, 150, 150, 30);
		ScreenHelper.style_btn(btnGroups);
		contentPane.add(btnGroups);
		
		btnFriends = new JButton("Friends");
		btnFriends.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		btnFriends.setBounds(7, 190, 150, 30);
		ScreenHelper.style_btn(btnFriends);
		contentPane.add(btnFriends);
		
		btnFindFriends = new JButton("Find Friends");
		btnFindFriends.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		btnFindFriends.setBounds(7, 230, 150, 30);
		ScreenHelper.style_btn(btnFindFriends);
		contentPane.add(btnFindFriends);
		
		btnJoinGroups = new JButton("Join Groups");
		btnJoinGroups.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		btnJoinGroups.setBounds(7, 270, 150, 30);
		ScreenHelper.style_btn(btnJoinGroups);
		contentPane.add(btnJoinGroups);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(SignUpScreen.class.getResource("/Images/log_in_bg.jpg")));
		background.setBounds(0, 0, 800, 600);
		contentPane.add(background);
		
		ScreenHelper.setWall(this);
	}

	public void initComponents() {
		initFrame();
		
	}
	
	public void createEvents() {
		
		btnUpdateInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameLoader.addFrame(new SignUpScreen());
			}
		});
		
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				ScreenHelper.logout();
			}
		});
		
		btnPosts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				change_view("Posts");
			}
		});
		
		btnGroups.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				change_view("Groups");
			}
		});
		
		btnFriends.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				change_view("My Friends");
			}
		});
		
		btnFindFriends.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				change_view("Find Friends");
			}
		});
		
		btnAddPost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				change_view("Add Post");
				
				System.out.println(TeamMembers_handler.getNonManagers(1));
			}
		});
		
		btnFriendRequests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				change_view("Friend Requests");
			}
		});
		
		btnJoinGroups.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				change_view("Join Groups");
				
			}
		});
		
		if(Manager_handler.isManager(CurrentUser.getUsername())) {
			btnTeamRequests.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					change_view("Add to Team Request");
					
				}
			});
		}
		
		btnCreateGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Manager_handler.isManager(CurrentUser.getUsername()))
						change_view("Make Group");
				else
					JOptionPane.showMessageDialog(null, "You have to be a Manager to use this function");
				
						
			}
		});
		
		btnNotifications.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				change_view("Notifications");
				
			}
		});
		
		
		
		if(!Manager_handler.isManager(CurrentUser.getUsername())) {
			btnBecomeAManager.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ManagerRegistrationScreen manager = new ManagerRegistrationScreen();
				}
			});
		}
	}
	
	public void resetView() {
		view = new JPanel();
		view.setOpaque(false);
		view.setLayout(new BoxLayout(view, BoxLayout.PAGE_AXIS));
		
	}
	
	public void change_view(String to) {
		resetView();
		
		switch (to) {
		case "Posts": 
			fillPosts();
			break;
			
		case "Find Friends":
			fillFindFriends();
			break;
			
		case "Add Post":
			createPost();
			break;
		
		case "My Friends":
			fillMyFriends();
			break;
			
		case "Groups":
			fillGroups();
			break;
		
		case "Make Group":
			createGroup();
			break;
		
		case "Join Groups":
			fillFindGroups();
			break;
			
		case "Friend Requests":
			fillFriendRequests();
			break;
			
		case "Add to Team Request":
			fillAddingToTeamRequest();
			break;
			
		case "Notifications":
			viewNotifications();
			break;
		
		
		default:
			break;
		}
		
		scroller.setViewportView(view);
		
	}

	public void fillPosts() {
		try {
			ArrayList<Post> posts = Post_handler.relatedPosts(CurrentUser.getUsername());
			Collections.sort(posts,new sortByDate());
			for(Post p : posts) {
				PostPane pane = new PostPane(p);
				view.add(pane);
				view.add(Box.createRigidArea(new Dimension(20, 20)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void goToPost(int postID) {
		resetView();
		PostPane pane=null;
		try {
			pane = new PostPane(Post_handler.findPost(postID));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		view.add(pane);
		scroller.setViewportView(view);
	}
	
	
	public void createPost() {
		AddPost post = new AddPost();
		view.add(post);
	}
	
	
	public void fillFindGroups() {
		try {
			ArrayList<Team> teams = Team_handler.getNotJoinedTeams(CurrentUser.getUsername());
			for(Team team:teams) {
				JoinTeamPane pane=new JoinTeamPane(team);
				view.add(pane);
				view.add(Box.createRigidArea(new Dimension(20, 20)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void createGroup() {
		System.out.println("You can make a new Group");
		TeamCreateionScreen groupScreen = new TeamCreateionScreen();
	}
	
	public void fillMyFriends() {
		ArrayList<User> friends;
		friends = FriendsList_handler.FriendsList(CurrentUser.getUsername());
		for(User friend : friends) {
			MyFriendsPane friendPane = new MyFriendsPane(friend);
			view.add(friendPane);
			view.add(Box.createRigidArea(new Dimension(20, 20)));
		}
	}
	
	public void fillFindFriends() {
		ArrayList<User> friends;
		friends = FriendsList_handler.notFriendList(CurrentUser.getUsername());
		System.out.println(friends.size());
		for(User friend : friends) {
			FindFriendsPane friendPane = new FindFriendsPane(friend);
			view.add(friendPane);
			view.add(Box.createRigidArea(new Dimension(20, 20)));
		}
	}
	
	public void fillFriendRequests() {
		ArrayList<User> senders = RequestList_handler.requestSentMe(CurrentUser.getUsername());
		for(User us : senders) {
			FriendRequestsPane frp = new FriendRequestsPane(us);
			view.add(frp);
			view.add(Box.createRigidArea(new Dimension(20, 20)));
		}

		
	}
	
	public void fillGroups() {
		ArrayList<Team> teams;
		try {
			teams = Team_handler.getJoinedTeams(CurrentUser.getUsername());
			System.out.println(teams.size());
			for(Team team:teams) {
				MyTeamsPane teamPane=new MyTeamsPane(team);
				view.add(teamPane);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void fillAddingToTeamRequest() {
		ArrayList<User> senders = RequestListTeam_handler.requestSentMe(CurrentUser.getUsername());
		ArrayList<Team> teams=Team_handler.getTeams();
		System.out.println(senders+"sss");
		for(User us : senders) {
			for(Team tm : teams) {
				if(RequestListTeam_handler.isInRequest(us.getMail(), tm.getIDTeam(),CurrentUser.getUsername())) {
					AddToTeamRequestPane adding=new AddToTeamRequestPane(us, tm);
					view.add(adding);
				}
			}
		}
	}
	
	public void viewNotifications() {
		ArrayList<Integer> taggedIn = Tagging_handler.taggedIn(CurrentUser.getUsername());
		if(!taggedIn.isEmpty()) {
			for(int num : taggedIn) {
				TagNotificationPane tnp = new TagNotificationPane(Post_handler.findPost(num));
				view.add(tnp);
				view.add(Box.createRigidArea(new Dimension(20, 20)));
			}
		}
		else {
			JPanel msgPane = new JPanel();
			msgPane.setOpaque(false);
			
			JLabel lbl = new JLabel("You have no notifications yet");
			lbl.setForeground(Color.cyan);
			msgPane.add(lbl);
			view.add(msgPane);
					
		}
			
	}
	public void hideBtn() {
		if(Manager_handler.isManager(CurrentUser.getUsername()))
			btnBecomeAManager.setVisible(false);
	}
	
	@Override
	public void init() {
		WallScreen reset = new WallScreen();
		this.dispose();
	}

	@Override
	public void vanish() {
		this.dispose();
		
	}

	@Override
	public void disappear() {
		this.setVisible(false);
	}
}
