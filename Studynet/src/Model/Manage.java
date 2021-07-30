package Model;

import java.sql.Date;

public class Manage {
	private  String Mail;
	private int IDTeam;
	private Date datePosted;
	
	public Manage(String mail, int iDTeam, Date datePosted) {
		Mail = mail;
		IDTeam = iDTeam;
		this.datePosted = datePosted;
	}
	public String getMail() {
		return Mail;
	}
	public void setMail(String mail) {
		Mail = mail;
	}
	public int getIDTeam() {
		return IDTeam;
	}
	public void setIDTeam(int iDTeam) {
		IDTeam = iDTeam;
	}
	public Date getDatePosted() {
		return datePosted;
	}
	public void setDatePosted(Date datePosted) {
		this.datePosted = datePosted;
	}
	
	
	
	

}
