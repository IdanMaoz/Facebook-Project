package Model;

public class Team {
	private int IDTeam;
	private String name;
	
	public Team(int iDTeam, String name) {
		IDTeam = iDTeam;
		this.name = name;
	}
	
	public int getIDTeam() {
		return IDTeam;
	}
	public void setIDTeam(int iDTeam) {
		IDTeam = iDTeam;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDetailis() {
		String details = "";
		details += " " + name +"\n";
		return details;
	}
	
	
	

}
