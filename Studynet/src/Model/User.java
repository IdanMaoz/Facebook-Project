package Model;

public class User {
	
	private String mail;
	private String firstName;
	private String lastName;
	private String nickName;
	private String photo;
	
	public User(String mail, String firstName, String lastName, String nickName, String photo) {
		this.mail = mail;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nickName = nickName;
		this.photo = photo;
	}
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getDetails() {
		String details = "";
		details += " " + firstName + " "
				+ lastName + " " 
				+ "(" + mail + ")";
		return details;
	}
	
	public String toString() {
		return getDetails();
	}

}
