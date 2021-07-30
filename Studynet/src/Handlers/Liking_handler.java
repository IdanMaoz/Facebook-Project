package Handlers;

import java.sql.Connection;

public abstract class Liking_handler {
	 //static Connection con;
	 
	public static  int findLikeNum(String like) {
		if(like=="Positive") {
			return 1;
		}
		else if(like=="Angry") {
			return 2;
		}
		else if(like=="Funny") {
			return 3;
		}
		else if(like=="Lovely") {
			return 4;
		}
		else {
			return 0;
		}
	}
	
	public static boolean checkLike(int like) {
		if(like!=1 && like!=2 && like !=3 && like!=4) {
			System.out.println("~~~we dont have this like");
			return false;
		}
		return true;
	}

}
