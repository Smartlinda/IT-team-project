package commandline;

public class AIUser extends GenericUser {
	
	private static int userIDCounter = 1;
	private int userID;
	private int howManyCardsLeft;
	private int numberOfWinsForUser = 0;
	Controller controller = new Controller();
	
	public AIUser(int userID) {
		this.userIDc = userID;
	}
	
	public int selectCategory(){ //The category with the highest number will be selected for AI players
		int max = -1;			// Save the maximum value
		int index = -1;			// Save the index of the max value
		for(int i = 0; i < <numberOfCategories>; i++) {
			if (cardAttributes[i] > max) { // Calculate the maximum attribute of the card
				max = cardAttributes[i];
				index = i;
			}
		}
		return index;   // Return the index of the highest value
	}
	
}

//Need to make classes properly
//Adjust controller