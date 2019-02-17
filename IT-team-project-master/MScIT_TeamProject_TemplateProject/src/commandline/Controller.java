package commandline;

import java.util.*;

/*
 * The controller is responsible for making changes to the model. Here we shuffle and distribute cards, 
 * change card ownership after every round, and make sure that only those players that have remaining cards
 * are still in the game.
 */

public class Controller {

	// Variables.
	public GenericUser[] userArray;
	protected ArrayList<Integer> activeUser = new ArrayList<Integer>(); // Only active users stored in this.
	private ArrayList<Integer> copy = new ArrayList<Integer>();
	protected List<Integer> maxList = new ArrayList<Integer>();
	protected ArrayList<Card> drawStack = new ArrayList<Card>(); // Cards in drawstack stored here.
	protected ArrayList<Card> shuffledStack = new ArrayList<Card>(); // Storing the shuffled cards.
	private Integer[] shuffledArray;
	protected Model model = new Model();

	// Constructor.
	public Controller(Model model) { // All classes must refer to the same model.
		this.model = model;
	}

	// Setters and Getters.
	public GenericUser[] getUserArray() {
		return userArray;
	}

	public ArrayList<Integer> getActiveUser() {
		return activeUser;
	}

	public List<Integer> getMaxList() {
		return maxList;
	}

	public ArrayList<Card> getDrawStack() {
		return drawStack;
	}

	public ArrayList<Card> getShuffledStack() {
		return shuffledStack;
	}

	public Integer[] getShuffledArray() {
		return shuffledArray;
	}
	
	public void setUserArray(GenericUser[] userArray) {
		this.userArray = userArray;
	}

	public void addHuman(HumanUser human) {
		userArray[0] = human;
	}
	
	public void addAI(AIUser[] AIUserArray) {
		for (int i=0; i<AIUserArray.length; i++) {
			userArray[i+1]=AIUserArray[i];
		}
	}

	// Methods.
	/*
	 * Distribute the shuffled cards to the players' decks fairly.
	 * 
	 * For example with 3 players and 8 cards if the shuffled deck is
	 * {3,5,6,1,2,8,7,4}: p1:{3,1,7} p2:{5,2,4} p3:{6,8}.
	 */
	public void distributeCards() {
		for (int j = 0; j < model.getDeckSize(); j++) {
			userArray[j % userArray.length].addCard(model.getDeck().get(shuffledArray[j]));
		}
	}

	// After each round, give cards to the winner or to drawstack.
	public void changeOwnership(int winner) {
		if (winner != -1) { // If there is a winner this round
			for (int user : activeUser) {
				// Add all top cards to winner's deck.
				userArray[winner].addCard(getTopCard(user));
				// Remove card from own deck.
				userArray[user].personalDeck.remove(0);
			}
			if (!drawStack.isEmpty()) { // If there is something in the drawstack.
				int drawStackSize = drawStack.size();
				for (int k = 0; k < drawStackSize; k++) {
					userArray[winner].addCard(drawStack.get(0)); // Add those cards to the winner's deck.
					drawStack.remove(0);
				}
			}
			userArray[winner].roundWinner(); // Add a win for the user
		} else { // If there isn't a winner this round.
			for (int user : activeUser) {
				drawStack.add(getTopCard(user)); // Add the top cards to the draw stack.
				userArray[user].getPersonalDeck().remove(0);
			}
		}
	}

	// Compare category values to obtain a round winner.
	public int checkRoundWinner(int prevWinner) {
		int max = -10; // Arbitrary negative value.
		for (int user : activeUser) {
			// If it is the biggest value of them all.
			if (getTopCard(user).getAttributeValues()[userArray[prevWinner].getSelectedCategory()] > max) {
				max = getTopCard(user).getAttributeValues()[userArray[prevWinner].getSelectedCategory()];
				maxList.add(user);
				// If there is a tie.
			} else if (getTopCard(user).getAttributeValues()[userArray[prevWinner].getSelectedCategory()] == max) {
				max = getTopCard(user).getAttributeValues()[userArray[prevWinner].getSelectedCategory()];
				maxList.add(user);
				return -1; // If there is a draw, return -1.
			}
		}
		return (int) maxList.get(maxList.size() - 1); // Return the winner.
	}

	/*
	 * Creates a set of shuffled (non-repeating) numbers. For example, if
	 * #totalcards is 5, then the shuffled array may look something like [2,5,1,4,3]
	 * instead of [1,2,3,4,5]. This only shuffles the numbers, not the cards. We
	 * 'shuffle' the cards by referring to the corresponding indices in the shuffled
	 * array.
	 */
	public Integer[] shuffling() {
		Random randNum = new Random();
		Set<Integer> shuffledRandomNumbers = new LinkedHashSet<Integer>();
		while (shuffledRandomNumbers.size() < model.getDeckSize()) { // Generate random numbers, not in order.
			Integer next = randNum.nextInt(model.getDeckSize());
			shuffledRandomNumbers.add(next);
		}
		shuffledArray = shuffledRandomNumbers.toArray(new Integer[shuffledRandomNumbers.size()]); // Create an array for
																									// easier access.
		for (int j = 0; j < shuffledArray.length; j++) {
			shuffledStack.add(model.getDeck().get(shuffledArray[j]));
		}
		return shuffledArray; // Return the array of shuffled numbers.
	}

	// When a player loses, remove him from the game.
	public void excludeLoser() {
		copy = (ArrayList<Integer>) activeUser.clone(); // Create a copy for avoiding conflicts in the loop.
		for (int user : activeUser) {
			if (userArray[user].getPersonalDeck().size() == 0) {
				for (int i = 0; i < activeUser.size(); i++) {
					if (copy.get(i) == user) {
						copy.remove(i);
						break;
					}
				}
			}
		}
		activeUser = (ArrayList<Integer>) copy.clone(); // Set the original equal to the copy again.
	}

	// Get the player's top card. Shortening code.
	public Card getTopCard(int player) {
		Card topCard = userArray[player].getPersonalDeck().get(0);
		return topCard;
	}

}
