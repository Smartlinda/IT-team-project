package commandline;

public class HumanUser extends GenericUser {

	protected static int userID = 0; // Human player is always ID 0, why not
	private String name; // Assume human has a name

	public HumanUser(String name) { // Constructor
		this.name = name;
	}

//	public int humanSelectCategory(Card card) { // We probably don't need this, but I just put it in
//		return selectedCategory; // delete if unnecessary (player selecting his own category)
//	}

	public void setUserName(String name) { // Set the name
		this.name = name;
	}

	public String getUserName() { // Get the name
		return name;
	}

}
