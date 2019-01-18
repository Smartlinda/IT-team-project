package commandline;

import java.util.ArrayList;

public class GenericUser {

	protected int userID; // Users have an ID from 0 to 4.
	protected int howManyCardsLeft; // How many cards do the players have left?
	protected int numberOfWinsForUser = 0; // How many wins?
	protected ArrayList<Card> personalDeck; // Collect all cards that are in my hand
	private Controller controller = new Controller();
	protected int selectedCategory;   // returns the index of the selected category

	GenericUser() { // Constructor
	}

	public int selectCategory() { // Not sure if this is the best way to make a method like this
		return selectedCategory;
	}

	public void calculateNumberOfWins() { // If this player is the winner of the round, add one to the number of wins.
		if (controller.checkRoundWinner() == userID) {
			numberOfWinsForUser++;
		}
	}

	public void addCard(Card card) { // add the given card to the bottom of the pile
		personalDeck.add(card);
	}

	public void removeTopCard(Card card) { // remove the card from the top of the pile
		personalDeck.remove(0);
	}

}

//add personal decks 
//add methods 