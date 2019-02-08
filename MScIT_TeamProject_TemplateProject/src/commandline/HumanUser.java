package commandline;

/* HumanUser is an extension of GenericUser, but it only differs from a GenericUser 
 * by having a userID = 0 (always), as well as having a name (username) to make the gameplay
 * more personal. 
 */

public class HumanUser extends GenericUser {

	// Variables.
	protected static int userID = 0;
	private String name;

	// Constructor.
	public HumanUser(String name) {
		this.name = name;
	}

	// Setters and Getters.
	public void setUserName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return name;
	}

}
