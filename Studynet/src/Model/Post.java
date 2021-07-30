package Model;

import java.sql.Date;

public class Post {
	
	private int ID;
	private String text;
	private String photo;
	private int video;
	private String location;
	private Date date;
	private User writer;
	private int commentNum = 0;
	


	public Post(int ID, String photo, String text, String location, int video, Date date, User writer) {
		this.ID = ID;
		this.text = text;
		this.photo = photo;
		this.video = video;
		this.location = location;
		this.date = date;
		this.writer = writer;
	}
	
	
	public String getDetailis() {
		String details = "";
		details += writer.getDetails() +"\n" 
				+ " Posted at: " + date.toString() + "\n";
		return details;
	}


	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}


	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getVideo() {
		return video;
	}

	public void setVideo(int video) {
		this.video = video;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getWriter() {
		return writer;
	}

	public void setWriter(User writer) {
		this.writer = writer;
	}


	public int getCommentNum() {
		return commentNum;
	}


	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	
	public void increaseCommentNum() {
		this.commentNum += 1;
	}
	
}
