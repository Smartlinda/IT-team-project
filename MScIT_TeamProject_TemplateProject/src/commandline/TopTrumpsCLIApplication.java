package commandline;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import commandline.DownloadStats;

/** TO RUN ON COMMANDLINE ON **WINDOWS**; mac users sorry........................
 * go to IT-team-project\MScIT_TeamProject_TemplateProject\src
 * >javac commandline\*.java
 * >java commandline/TopTrumpsCLIApplication
 */

/** THINGS TO DO <--------------------------------------------
 * have the game looping so that at the end you can play again - does not terminate
 * display all game statistics at the very end (have the stats for *CURRENT* game already)
 * connect to the yacata database pls thx
 * optional - add setters and getters so that the code is not so long (reka requests this ok ok)
 */

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
		boolean writeGameLogsToFile = false; // Should we write game logs to file?

		// -------------UNCOMMENT AFTER MAKING JAR FILE----if (args[0].equalsIgnoreCase("true")) {
		writeGameLogsToFile = true;
		// ------UNCOMMENT--------} // Command line selection

		Model model = new Model();
		model.readContent();
		if (writeGameLogsToFile) {
			writeToLog.writeCompleteDeckToFile(model.cardCon);
		}

		Controller controller = new Controller(model);

		System.out.println(" _______            _______                             ");
		System.out.println("|__   __|          |__   __|                            ");
		System.out.println("   | | ___  _ __      | |_ __ _   _ _ __ ___  _ __  ___ ");
		System.out.println("   | |/ _ \\| '_ \\     | | '__| | | | '_ ` _ \\| '_ \\/ __|");
		System.out.println("   | | (_) | |_) |    | | |  | |_| | | | | | | |_) \\__ \\");
		System.out.println("   |_|\\___/| .__/     |_|_|   \\__,_|_| |_| |_| .__/|___/");
		System.out.println("           | |                               | |        ");
		System.out.println("           |_|                               |_|        ");
		System.out.println();

		Scanner in = new Scanner(System.in);
		System.out.print("Welcome! What's your name? ");
		String userName = in.nextLine();
		HumanUser player = new HumanUser(userName); // make human user with username

		System.out.println(
				"Hi " + userName + "! \nSelect an option:\nPress 1 to start a game\nPress 2 to see statistics");

		System.out.print("Enter your selection here: ");
		int selection = -1; // assign to a random number, so it's a global var not local

		while (true) {
			try {
				selection = in.nextInt(); // need to check if input is between 1 and 4 (exception catching)
				if (selection == 1 || selection == 2) {
					break;
				}
				throw (new InputMismatchException());
			} catch (InputMismatchException e) {
				in.nextLine();
				System.err.println("That's not a valid choice, try again.");
			}
		}

		System.out.print("");
		if (selection == 1) {

			System.out.print("Choose the number of opponents (1-4): ");

			while (true) {
				try {
					selection = in.nextInt(); // need to check if input is between 1 and 4 (exception catching)
					if (selection <= 4 && selection >= 1) {
						break;
					}
					throw (new InputMismatchException());
				} catch (InputMismatchException e) { // is inputmismatchexception appropriate?
					in.nextLine();
					System.err.println("That's not a valid choice, try again.");
				}
			}

			controller.userArray = new GenericUser[selection + 1]; // make the array to be the size of
																	// aiplayers+1

			controller.userArray[player.userID] = player; // add the player to the userarray in controller

			for (int i = 0; i < selection; i++) {
				AIUser ai = new AIUser();
				controller.userArray[ai.userID] = ai;
			}

			for (int i = 0; i < controller.userArray.length; i++) {
				// activeUser contains the ID for users in play
				controller.activeUser.add(i);
			}
			controller.shuffling(); // shuffle the cards
			if (writeGameLogsToFile) {
				writeToLog.writeCompleteShuffledDeckToFile(controller.shuffledStack);
			}

			controller.distributeCards(); // distribute the cards to each player
			if (writeGameLogsToFile) {
				for (int j = 0; j < controller.userArray.length; j++) {
					writeToLog.writeUsersDeckContentToFile(controller.userArray[j].personalDeck,
							controller.userArray[j]);
				}
			}

			System.out.print("");

			System.out.println("Game starts here.");

		} else if (selection == 2) {
			/*
			 * Use this if Player selects to see the statistics
			 */
			DownloadStats db = new DownloadStats();
			System.exit(0);
			// display the statistics
		}

		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application

		int roundCounter = 1; // start on round 1
		Random randNum = new Random();
		int winner = randNum.nextInt(controller.userArray.length); // first winner chosen randomly
		int previousWinner = -2; // random number to say who was the previous winner

		while (!userWantsToQuit) { // Loop until the user wants to exit the game

			if (winner == -1) { // if there was a draw, the previous winner chooses the category again
				winner = previousWinner;
			}

			System.out.println();
			System.out.println("Round " + roundCounter);
			System.out.println("Round " + roundCounter + ": Players have drawn their cards");

			if (controller.activeUser.contains(0)) { // if human still in play
				System.out.println("You drew '" + controller.getTopCard(0).getCardName() + "':");

				for (int i = 0; i < controller.getTopCard(0).getAttributeValues().length; i++) {
					System.out.println(
							"   > " + model.getHeader(i) + ": " + controller.getTopCard(0).getAttributeValues()[i]);
				}
				int numberOfleftCard = controller.userArray[0].personalDeck.size();

				System.out.println("There are " + numberOfleftCard + " cards in your deck.");
			}
			if (winner == 0) {
				System.out.println("It is your turn to select a category, the categories are: ");
				for (int i = 0; i < controller.getTopCard(0).getAttributeValues().length; i++) {
					System.out.println("   " + (i + 1) + ": " + model.getHeader(i));
				}
				System.out.print("Enter the number for your attribute: ");

				while (true) {
					try {
						selection = in.nextInt(); // need to check if input is between 1 and 4 (exception catching)
						if (selection <= 5 && selection >= 1) {
							break;
						}
						throw (new InputMismatchException());
					} catch (InputMismatchException e) { // is inputmismatchexception appropriate?
						in.nextLine();
						System.err.println("That's not a valid category, try again.");
					}
				}

				controller.userArray[0].selectedCategory = selection - 1; // because the indices are not 1-5, it's 0-4
				System.out.println("You selected " + model.cardHeader[selection] + ".");

			} else {
				System.out.println("Player " + controller.userArray[winner].userID + "'s turn to select a category. ");

				controller.userArray[winner].selectCategory(controller.getTopCard(winner));

				System.out.println("Player " + controller.userArray[winner].userID + " selected "
						+ model.cardHeader[controller.userArray[winner].selectedCategory + 1] + ".");

			}

			if (writeGameLogsToFile) {
				writeToLog.writeCategorySelectedAndValuesToFile(roundCounter, model.cardHeader,
						controller.userArray[winner].selectedCategory, controller.getTopCard(winner),
						controller.userArray[winner]);
			}
			
			previousWinner = winner; // if there is a draw
			winner = controller.checkRoundWinner(previousWinner);

			if (writeGameLogsToFile) {
				for (int j : controller.activeUser) {
					writeToLog.writeContentsOfCurrentCardsInPlayToFile(controller.getTopCard(j),
							controller.userArray[j]);
				}
			}

			if (winner == -1) {

				int winnerInDraw = (int) controller.maxList.get(controller.maxList.size() - 1); // even though it's a
																								// draw, display one of
																								// the winning cards
				System.out.println("Round " + roundCounter + ": This round was a draw.");
				System.out.println("The winning card was '" + controller.getTopCard(winnerInDraw).getCardName() + "':");
				for (int i = 0; i < controller.getTopCard(winnerInDraw).getAttributeValues().length; i++) {
					System.out.println(
							model.getHeader(i) + ": " + controller.getTopCard(winnerInDraw).getAttributeValues()[i]);
				}
			} else {
				if (winner == 0) {
					System.out.println("Round " + roundCounter + ": You won this round");
				} else {
					System.out.println("Round " + roundCounter + ": Player " + winner + " won this round");
				}
				System.out.println("The winning card was '" + controller.getTopCard(winner).getCardName() + "':");
				for (int i = 0; i < controller.getTopCard(winner).getAttributeValues().length; i++) {
					System.out
							.println(model.getHeader(i) + ": " + controller.getTopCard(winner).getAttributeValues()[i]);

				}
			}

			boolean writeDrawStackToFileQuestionMark = false; // only write drawstack to file if its size changed
			if (!controller.drawStack.isEmpty() || winner == -1) {
				writeDrawStackToFileQuestionMark = true;
			} else {
				writeDrawStackToFileQuestionMark = false;
			}
			controller.changeOwnership(winner); // add losers' cards to winner deck

			if (writeGameLogsToFile) {
				for (int j = 0; j < controller.userArray.length; j++) {
					writeToLog.writeUsersDeckContentToFile(controller.userArray[j].personalDeck,
							controller.userArray[j]);
				}
			}

			if (writeGameLogsToFile && writeDrawStackToFileQuestionMark) {
				writeToLog.writeContentsOFCommunalPileToFile(controller.drawStack);
			}

			System.out.println("Common pile now has " + controller.drawStack.size() + " cards");
			controller.excludeLoser(); // if someone has no cards left, get rid of them
			roundCounter++;

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.err.println("You woke up the thread!");
			}

			for (int j = 0; j < controller.userArray.length; j++) {
				if (controller.userArray[j].personalDeck.size() == 40) { // if someone has all the cards they win
					userWantsToQuit = true;

					System.out.println();

					if (writeGameLogsToFile) {
						writeToLog.writeWinnerToFile(controller.userArray[j]);
					}

					if (j == 0) {
						System.out.println("Congrats " + userName + ", you win!");
					} else {
						System.out.println("The winner is: AI Player " + j + ".");
					}
					System.out.println("Scores:");
					for (int l = 0; l < controller.userArray.length; l++) {
						if (l == 0) {
							System.out.println("    " + userName + ": " + controller.userArray[l].numberOfWinsForUser);
						} else {
							System.out
									.println("    AI Player " + l + ": " + controller.userArray[l].numberOfWinsForUser);
						}
					}
					System.exit(0);
				}
			}
		}

		in.close();

	}

}