package commandline;

import java.util.ArrayList;

public class GenericUser{
	
	private int userID;
	private int howManyCardsLeft;
	private int numberOfWinsForUser = 0;
	private Controller controller = new Controller();
	
	GenericUser(){
	}
	
	public void selectCategory() {
	}
	
	public void calculateNumberOfWins() {  // If this player is the winner of the round, add one to the number of wins. 
		if (controller.checkRoundWinner() == userID) {
			numberOfWinsForUser++;
		}
	}
	
}

//add personal decks 
//add methods 