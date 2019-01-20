package commandline;

public class AIUser extends GenericUser {

	private static int nextID = 1;
	private int numberOfWinsForUser = 0;

	Controller controller = new Controller();

	public AIUser() {
		this.userID = nextID;
		nextID++;
	}

	public int selectCategory(Card card) { // The category with the highest number will be selected for AI players
		int max = -1; // Save the maximum value
		for (int i = 0; i < card.getAttributeValues().length; i++) {
			if (card.getAttributeValues()[i] > max) { // Calculate the maximum attribute of the card
				max = card.getAttributeValues()[i];
				selectedCategory = i;
			}
		}
		return selectedCategory; // Return the index of the highest value
	}

}

//Need to make classes properly
//Adjust controller