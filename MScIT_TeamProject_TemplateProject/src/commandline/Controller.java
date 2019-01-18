package commandline;

import java.util.*;

public class Controller {
	private HumanUser humanUser; // only one human player
	private AIUser[] AIUsers; // need to populate this array with AI (user selects the number, 1-4)
	private int numberOfUsers = AIUsers.length + 1;
	private GenericUser[] userArray = new GenericUser[numberOfUsers];

	private ArrayList<Card> cardCon; // card content which includes name and values
	// private Card[][] personalDeck; // change to everyone having their own deck
	private ArrayList<Card> drawStack; // an arraylist to store the cards in play/in the middle when its a draw
	private Integer[] shuffledArray;

	public Controller() {
	}

	public void distributeCards() { // dont need n because we have numberOfUsers

		// need a number of all cards (numberOfAllCards) - maybe from the model?

		// a loop for the start of the game
		for (int j = 0; j < numberOfAllCards; j++) {
			userArray[j % numberOfUsers].addCard(cardCon.get(shuffledArray[j])); // add the shuffled card to the
																					// specific player
		} // for any questions message reka ok ok

//		personalDeck = new Card[numberOfAllCards / n + 1][n];     			// idk if this is still needed or not
//		int k = 0;
//		for (int j = 0; j < numberOfAllCards / n; j++) {
//			for (int i = 0; i < n; i++) {
//				personalDeck[j][i] = cardCon.get(i + n * k);
//				k++;
//			}
//		}
//		for (int i = 0; i < numberOfAllCards % n; i++) {
//			personalDeck[numberOfAllCards / n][i] = cardCon.get(i + n * (numberOfAllCards / n + 1));
//		}
	}

	public void changeOwnership(int i) { 						//i is the winner, the compile deck will regarded as a winner as well
		int winner = checkRoundWinner();
		for(int j=0;j<numberOfUsers;j++) {
				userArray[winner].addCard(userArray[j].personalDeck.get(0));	// add top card to winner's deck from everyones decks
				userArray[j].personalDeck.remove(0); 	// remove card from own deck
		}
		//for (all i in personal[personalDeck.size()])	  //idk what you started here	
	}

	
	// for checkroundwinner need a new variable saying if there was a winner or if there was a draw. if there was a winner go to changeownership ^^^^^ if 
	// there is no winner add cards to the draw pile. maybe make a new method for that?? maybe could merge changeownership and addcardstomiddle?? up to you
	
	public void addCardsToMiddle() {
		for(int j=0;j<numberOfUsers;j++) {
			drawStack.add(userArray[j].personalDeck.get(0));
			userArray[j].personalDeck.remove(0);
		}
	}
	
	public int checkRoundWinner() {    // i dont understand what you did here so i wont touch it
		int max = -10;
		List maxList;
		for (int i = 0; i < numberOfUsers; i++) {
			if (personalDeck[0][i].getributeValues[selectCategory()] > max) {
				max = personalDeck[0][i];
				maxList.add(i);
			}
		}
		return (int) maxList.get(maxList.size() - 1);
	}

	// i copied this from stackoverflow lol
	public Integer[] shuffling() { 		// creates a set of shuffled (non-repeating) numbers
		Random randNum = new Random(); // from 1 to #totalcards, for example if #totalcards is 5, then
		Set<Integer> shuffledDeck = new LinkedHashSet<Integer>(); // it will be sth like [2,5,1,4,3] instead of
																	// [1,2,3,4,5]
		while (shuffledDeck.size() < numberOfAllCards) { // maybe need to change to [0,1,2,3,4]
			Integer next = randNum.nextInt(numberOfAllCards) + 1; // add a random number to the set until there are
																	// enough
			shuffledDeck.add(next);
		}
		shuffledArray = shuffledDeck.toArray(new Integer[shuffledDeck.size()]);
		return shuffledArray; // return the set of shuffled numbers
	}

}
