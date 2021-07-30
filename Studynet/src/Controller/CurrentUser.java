package Controller;

public abstract class CurrentUser {
	static String user="";
	
	public static void setUser(String mail) {
		user = mail;
	}
	
	public static String getUsername() {
		return user;
	}
	
	public static void reset() {
		user="";
	}
}
