package commandline;

import java.util.ArrayList;

/*
 * 
 * 
 */

public class GenericUser {

	// Variables.
	public int userID; 
	public ArrayList<Card> personalDeck = new ArrayList<Card>(); 
	protected int roundsWon = 0; 
	protected int selectedCategory;

	// Constructor.
	GenericUser() {
	}

	// Getters and Setters.
	public ArrayList<Card> getPersonalDeck() {
		return personalDeck;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public int getRoundsWon() {
		return roundsWon;
	}
	
	public int getSelectedCategory() {
		return selectedCategory;
	}
	
	public void setSelectedCategory(int selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

	// Methods.
	public void roundWinner() {
		roundsWon++;
	}
	
	public int selectCategory(Card card) {
		return selectedCategory;
	}

	// Add given card to the bottom of the pile.
	public void addCard(Card card) {
		personalDeck.add(card);
	}

	// Remove the topmost card from the pile. 
	public void removeTopCard(Card card) { 
		personalDeck.remove(0);
	}

	// A toString method.
	public String toString() {
		String str = String.format("Player %2d: %2d rounds won, %2d cards left in deck.", userID, roundsWon, personalDeck.size());
		return str;
	}

}
