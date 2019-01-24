package commandline;

import java.util.Random;
import java.util.Scanner;

/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {

	/**
	 * This main method is called by TopTrumps.java when the user specifies that
	 * they want to run in command line mode. The contents of args[0] is whether we
	 * should write game logs to a file.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Controller controller = new Controller();
		Model model = new Model();
		model.readContent();
		

		Scanner in = new Scanner(System.in);
		System.out.print("Welcome! What's your name? ");
		String userName = in.nextLine();
		HumanUser player = new HumanUser(userName); // make human user with username

		System.out.println(
				"Hi " + userName + "! \nSelect an option:\nPress 1 to start a game\nPress 2 to see statistics");
		System.out.print("Enter your selection here: ");
		int selection = in.nextInt();
		System.out.print("");
		if (selection == 1) {
			System.out.print("Choose the number of opponents (1-4): ");
			selection = in.nextInt(); // need to check if input is between 1 and 4 (exception catching)
			controller.userArray = new GenericUser[selection + 1]; // make the array to be the size of aiplayers+1
			controller.userArray[player.userID] = player; // add the player to the userarray in controller
			for (int i = 0; i < selection; i++) {
				AIUser ai = new AIUser();
				controller.userArray[ai.userID] = ai;
			}
			controller.shuffling(); // shuffle the card
			controller.distributeCards(); // distribute the cards to each player

			System.out.print("");
			System.out.println("Game starts here.");

		} else if (selection == 2) {
			System.out.println("Statistics displayed here");
			// display the statistics
		}

//need to catch inputmismatchexceptions for when the user inputs something: when selecting a game or when selecting a statistic
		// ---------------------------------------------------------------------------------------------------

		boolean writeGameLogsToFile = false; // Should we write game logs to file?
		// ------uncomment this----------if (args[0].equalsIgnoreCase("true"))
		// writeGameLogsToFile=true; // Command line selection

		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application

		int roundCounter = 1;
		Random randNum = new Random();
		int winner = randNum
		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {

			System.out.println("Round " + roundCounter);
			System.out.println("Round " + roundCounter + ": Players have drawn their cards");
			System.out.println("You drew '" + controller.userArray[0].personalDeck.get(0).getCardName() + "':");
			for (int i = 0; i < controller.userArray[0].personalDeck.get(0).getAttributeValues().length; i++) {
				System.out.println(model.getHeader(i) + ": " + controller.userArray[0].personalDeck.get(0).getAttributeValues()[i]);
			}
			int numberOfleftCard = controller.userArray[0].personalDeck.size()-1;
			System.out.println("There are " + numberOfleftCard + " cards in your deck.");
			
			if (controller.checkRoundWinner() == 0) {
				System.out.println("It is your turn to select a category, the categories are: ");
				for (int i = 0; i < controller.userArray[0].personalDeck.get(0).getAttributeValues().length; i++) {
					System.out.println(i+1+ ": " + model.getHeader(i));
				}
				System.out.print("Enter the number for your attribute: ");
				selection = in.nextInt();
				controller.userArray[0].selectedCategory = selection;
			} 
			
			
			roundCounter++;
			// if () {
			// userWantsToQuit=true; // use this when the user wants to exit the game
			// }
		}

		in.close();

	}

}
