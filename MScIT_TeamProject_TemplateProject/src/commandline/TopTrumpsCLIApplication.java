package commandline;

import java.util.Random;
import java.util.Scanner;
import commandline.DBConnect;

//need to
//take players with 0 cards out of the game somehow
//display the winner and winning cards
//at the end of the game display stats

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

		Model model = new Model();
		model.readContent();

		Controller controller = new Controller(model);

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

			do {

				System.out.print("Choose the number of opponents (1-4): ");

				selection = in.nextInt(); // need to check if input is between 1 and 4 (exception catching)

				try {
					if (1 <= selection && selection <= 4) {

						controller.userArray = new GenericUser[selection + 1]; // make the array to be the size of
																				// aiplayers+1
						controller.userArray[player.userID] = player; // add the player to the userarray in controller
						for (int i = 0; i < selection; i++) {
							AIUser ai = new AIUser();
							controller.userArray[ai.userID] = ai;
						}
						controller.shuffling(); // shuffle the card
						controller.distributeCards(); // distribute the cards to each player

						System.out.print("");

						System.out.println("Game starts here.");

					} else {
						throw new Exception();
					}

				} catch (Exception e) {
					System.out.println("Please choose a number between 1-4");
				}
			} while (1 > selection || selection > 4);

			// --------------------------------

		} else if (selection == 2) {
			System.out.println("Statistics displayed here");
			/*
			 * Use this if Player selects to see the statistics
			 */
			DBConnect db = new DBConnect();
			System.exit(0);
			// display the statistics
		}

//need to catch inputmismatchexceptions for when the user inputs something: when selecting a game or when selecting a statistic
		// ---------------------------------------------------------------------------------------------------

		boolean writeGameLogsToFile = false; // Should we write game logs to file?
		/*
		 * UNCOMMENT THIS LATER if (args[0].equalsIgnoreCase("true"))
		 * writeGameLogsToFile=true; // Command line selection
		 */
		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application

		int roundCounter = 1;
		Random randNum = new Random();
		int winner = randNum.nextInt(controller.userArray.length);
		int previousWinner = -2;
		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {

			if (winner == -1) {
				winner = previousWinner;
			}

			System.out.println();
			System.out.println("Round " + roundCounter);
			System.out.println("Round " + roundCounter + ": Players have drawn their cards");

			System.out.println("You drew '" + controller.userArray[0].personalDeck.get(0).getCardName() + "':");

			for (int i = 0; i < controller.userArray[0].personalDeck.get(0).getAttributeValues().length; i++) {
				System.out.println(model.getHeader(i) + ": "
						+ controller.userArray[0].personalDeck.get(0).getAttributeValues()[i]);
			}
			int numberOfleftCard = controller.userArray[0].personalDeck.size();

			System.out.println("There are " + numberOfleftCard + " cards in your deck.");

			if (winner == 0) {
				System.out.println("It is your turn to select a category, the categories are: ");
				for (int i = 0; i < controller.userArray[0].personalDeck.get(0).getAttributeValues().length; i++) {
					System.out.println(i + 1 + ": " + model.getHeader(i));
				}
				System.out.print("Enter the number for your attribute: ");
				selection = in.nextInt();
				controller.userArray[0].selectedCategory = selection - 1; // because the indices are not 1-5 its 0-4
				System.out.println("You selected " + model.cardHeader[selection] + ".");

			} else {
				System.out.println("Player " + controller.userArray[winner].userID + "'s turn to select a category. ");

				controller.userArray[winner].selectCategory(controller.userArray[winner].personalDeck.get(0));

				System.out.println("Player " + controller.userArray[winner].userID + " selected "
						+ model.cardHeader[controller.userArray[winner].selectedCategory] + ".");

			}

			// need to make a thing to show the winner and winning card

			previousWinner = winner; // if there is a draw
			winner = controller.checkRoundWinner();
			controller.changeOwnership(winner);
			roundCounter++;

			for (int j = 0; j < controller.userArray.length; j++) {
				if (controller.userArray[j].personalDeck.size() == 40) { // if someone has all the cards they win
					userWantsToQuit = true;
					if (j == 0) {
						System.out.println("Congrats, you win!");
					} else {
						System.out.println("The winner is: Player " + j + ".");
					}
					System.exit(0);
				}
			}
		}

		in.close();

	}

}
