package commandline;

public class HumanUser extends GenericUser {
	
	private static int userID = 0;
	private String name;
	
	public HumanUser(String name) {
		this.name = name; 
	}
	
	public void setUserName(String name) {
		this.name = name;
	}

}
