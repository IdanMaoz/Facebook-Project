package Model;

import java.sql.Date;

public class Comment {
	
	private int postID;
	private int commentID;
	private Date dateTime;
	private String text;
	private User commenter;
	
	public Comment(int post, int cmntID, Date date, String txt, User user){
		
		this.postID = post;
		this.commentID = cmntID;
		this.dateTime = date;
		this.text = txt;
		this.commenter = user;
		
	}

	public int getPostID() {
		return postID;
	}

	public void setPostID(int postID) {
		this.postID = postID;
	}

	public int getCommentID() {
		return commentID;
	}

	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}


	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getCommenter() {
		return commenter;
	}

	public void setCommenter(User commenter) {
		this.commenter = commenter;
	}

	public String getDetails() {
		String result = "";
		result += commenter.getDetails() + "\n" 
				+ "Posted at: " + dateTime.toString() + "\n";
		return result;
	}
	
	

}
