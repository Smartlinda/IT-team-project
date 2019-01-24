package commandline;

import java.util.*;


//things to solve
//-----------------------------------------
// - add players to the userArray (in their constructor or what?)
// - testing if the methods work or not (i will do that (reka))

public class Controller {
	private HumanUser humanUser; // only one human player
	// private AIUser[] AIUsers; // maybe its stupid to have a separate array for
	// ai, so all users in 1 array (userArray)
	private int numberOfUsers;
	private int numberOfActivePlayers;
	protected GenericUser[] userArray;// = new GenericUser[5]; // 5 is the maximum number of players

	private ArrayList<Card> drawStack; // an arraylist to store the cards in play/in the middle when its a draw
	private ArrayList<Card> shuffledStack; // an arraylist to store the shuffled cards
	private Integer[] shuffledArray;
	protected Model model = new Model();
	

	public Controller(Model model) {
		this.model = model;
	}

	

	public void someoneLost() { // to get rid of a player with no cards
		numberOfActivePlayers--; // maybe not needed, we will see
	}

	public void distributeCards() {

		// a loop for the start of the game
		for (int j = 0; j < model.numberOfAllCards; j++) {
			userArray[j % userArray.length].addCard(model.cardCon.get(shuffledArray[j])); // add the shuffled card to the
																				// specific player
		}


	}

	public void changeOwnership(int i) { // i is the winner, the compile deck will regarded as a winner as well
		// int winner = checkRoundWinner(); do this in main
		if (i != -1) { // if there is a winner this round
			for (int j = 0; j < userArray.length; j++) {
				userArray[i].addCard(userArray[j].personalDeck.get(0)); // add top card to winner's deck from
																		// everyones decks
				userArray[j].personalDeck.remove(0); // remove card from own deck

			}
			if (!drawStack.isEmpty()) { // if there is something in the draw stack
				int drawStackSize = drawStack.size();  // need this because if we used drawStack.size in the loop, 
													//it would change every iteration and it would not be correct
				for (int k = 0; k < drawStackSize; k++) {
					userArray[i].addCard(drawStack.get(0)); // add those cards to the winner
					drawStack.remove(0);
				}
			}
		} else { // if there isn't a winner this round
			for (int j = 0; j < userArray.length; j++) {
				drawStack.add(userArray[j].personalDeck.get(0)); // add the top cards to the draw stack
				userArray[j].personalDeck.remove(0);
			}
		}
	}

	public int checkRoundWinner() { // check the winner of the round
		int max = -10; // arbitrary negative value
		List<Integer> maxList = new ArrayList<Integer>();
		for (int i = 0; i < userArray.length; i++) {
			if (userArray[i].personalDeck.get(0).attributeValues[userArray[i].selectedCategory] > max) {
				max = userArray[i].personalDeck.get(0).attributeValues[userArray[i].selectedCategory];
				maxList.add(i);
			} else if (userArray[i].personalDeck.get(0).attributeValues[userArray[i].selectedCategory] == max) {
				return -1; // if there is a draw, return -1, cos theres no winner.
			}
		}
		return (int) maxList.get(maxList.size() - 1); // return the winner
	}

	public Integer[] shuffling() { // creates a set of shuffled (non-repeating) numbers
		model.readContent();
		Random randNum = new Random(); // from 1 to #totalcards, for example if #totalcards is 5, then
		Set<Integer> shuffledDeck = new LinkedHashSet<Integer>(); // it will be sth like [2,5,1,4,3] instead of
																	// [1,2,3,4,5]
		while (shuffledDeck.size() < model.numberOfAllCards) { // maybe need to change to [0,1,2,3,4]
			Integer next = randNum.nextInt(model.numberOfAllCards); // add a random number to the set until there are
																	// enough
			shuffledDeck.add(next);
		
		}
		shuffledArray = shuffledDeck.toArray(new Integer[shuffledDeck.size()]);
		shuffledStack = new ArrayList<Card>();
		for (int j = 0; j < shuffledArray.length; j++) {
			
		   shuffledStack.add(model.cardCon.get(shuffledArray[j]));
		}
		return shuffledArray; // return the set of shuffled numbers
	}

}
