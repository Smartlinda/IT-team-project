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
		boolean writeGameLogsToFile = false; // Should we write game logs to file?
		boolean userWantsToQuit = false;
		boolean gameEnd = false;
		boolean didHumanWin = false;
		int numberOfDraws;

		if (args[0].equalsIgnoreCase("true")) {
			writeGameLogsToFile = true;
		} // Command line selection

		System.err.print(
				" _________  ________  ________        _________  ________  ___  ___  _____ ______   ________  ________      \n"
						+ "|\\___   ___\\\\   __  \\|\\   __  \\      |\\___   ___\\\\   __  \\|\\  \\|\\  \\|\\   _ \\  _   \\|\\   __  \\|\\   ____\\     \n"
						+ "\\|___ \\  \\_\\ \\  \\|\\  \\ \\  \\|\\  \\     \\|___ \\  \\_\\ \\  \\|\\  \\ \\  \\\\\\  \\ \\  \\\\\\__\\ \\  \\ \\  \\|\\  \\ \\  \\___|_    \n"
						+ "     \\ \\  \\ \\ \\  \\\\\\  \\ \\   ____\\         \\ \\  \\ \\ \\   _  _\\ \\  \\\\\\  \\ \\  \\\\|__| \\  \\ \\   ____\\ \\_____  \\   \n"
						+ "      \\ \\  \\ \\ \\  \\\\\\  \\ \\  \\___|          \\ \\  \\ \\ \\  \\\\  \\\\ \\  \\\\\\  \\ \\  \\    \\ \\  \\ \\  \\___|\\|____|\\  \\  \n"
						+ "       \\ \\__\\ \\ \\_______\\ \\__\\              \\ \\__\\ \\ \\__\\\\ _\\\\ \\_______\\ \\__\\    \\ \\__\\ \\__\\     ____\\_\\  \\ \n"
						+ "        \\|__|  \\|_______|\\|__|               \\|__|  \\|__|\\|__|\\|_______|\\|__|     \\|__|\\|__|    |\\_________\\\n"
						+ "                                                                                                \\|_________|\n"
						+ "                                                                                                            \n"
						+ "                                                                                                            \n");

		Scanner in = new Scanner(System.in);

		// User inputs a sensible username.
		System.out.print("Welcome! What's your name? ");
		String userName;

		while (true) {
			try {
				userName = in.nextLine();
				if (userName.equals("") || (userName.length() > 20)) {
					throw (new Exception());
				}
				break;
			} catch (Exception e) {
				System.err.println("A sensible name please!");
			}
		}

		// While the program is running.
		while (!userWantsToQuit) {

			// Reset the game.
			HumanUser player = new HumanUser(userName); // Make a new human user with given username.
			numberOfDraws = 0;
			didHumanWin = false;
			gameEnd = false;
			Model model = new Model();
			model.readContent();

			Controller controller = new Controller(model);

			// Selection screen.
			int selection = -1;
			System.out.println("Hi " + userName + "!");

			while (selection != 1 && selection != 3) {
				System.out.println(
						"\nSelect an option:\nPress 1 to start a game\nPress 2 to see statistics\nPress 3 to quit the game");

				System.out.print("Enter your selection here: ");

				// Checking for a sensible input.
				while (true) {
					try {
						selection = in.nextInt();
						if (selection == 1 || selection == 2 || selection == 3) {
							break;
						}
						throw (new Exception());
					} catch (Exception e) {
						in.nextLine();
						System.err.println("That's not a valid choice, try again.");
					}
				}

				System.out.print("");

				// Set up the game.
				if (selection == 1) {

					System.out.print("Choose the number of opponents (1-4): ");

					// Checking for a sensible input.
					while (true) {
						try {
							selection = in.nextInt();
							if (selection <= 4 && selection >= 1) {
								break;
							}
							throw (new Exception());
						} catch (Exception e) {
							in.nextLine();
							System.err.println("That's not a valid choice, try again.");
						}
					}

					controller.setUserArray(new GenericUser[selection + 1]);
					controller.getUserArray()[player.getUserID()] = player; // Add the human to the userArray.

					for (int i = 0; i < selection; i++) {
						AIUser ai = new AIUser();
						controller.getUserArray()[ai.userID] = ai; // Add the AI to the userArray.
					}

					for (int i = 0; i < controller.userArray.length; i++) {
						// activeUser contains the ID for users in play
						controller.getActiveUser().add(i);
					}

					// Write the deck to file.
					if (writeGameLogsToFile) {
						writeToLog.writeCompleteDeckToFile(model.getDeck());
					}

					// Shuffle the cards and write the deck to file.
					controller.shuffling();
					if (writeGameLogsToFile) {
						writeToLog.writeCompleteShuffledDeckToFile(controller.getShuffledStack());
					}

					// Distribute the cards to each player, then write to log.
					controller.distributeCards();
					if (writeGameLogsToFile) {
						for (int user = 0; user < controller.userArray.length; user++) {
							writeToLog.writeUsersDeckContentToFile(controller.getUserArray()[user].personalDeck,
									controller.getUserArray()[user]);
						}
					}

					System.out.print("");

					System.out.println("Game starts here.");
					break;

					// Show statistics.
				} else if (selection == 2) {

					DownloadStats db = new DownloadStats();

					// Quit the game.
				} else if (selection == 3) {
					System.exit(0);
				}
			}

			// Start on round one and choose a random person to start.
			int roundCounter = 1;
			Random randNum = new Random();
			int winner = randNum.nextInt(controller.getUserArray().length);
			int previousWinner = -2; // Arbitrary small number.

			// Loop until the game finished
			while (!gameEnd) {

				// If there was a draw, the previous winner chooses the category again.
				if (winner == -1) {
					winner = previousWinner;
				}

				System.out.println();
				System.out.println("Round " + roundCounter);
				System.out.println("Round " + roundCounter + ": Players have drawn their cards");

				// If human still in play.
				if (controller.getActiveUser().contains(0)) {
					System.out.println("You drew '" + controller.getTopCard(0).getCardName() + "':");

					for (int i = 0; i < controller.getTopCard(0).getAttributeValues().length; i++) {
						System.out.println(
								"   > " + model.getHeader(i) + ": " + controller.getTopCard(0).getAttributeValues()[i]);
					}
					System.out.println(
							"There are " + controller.userArray[0].personalDeck.size() + " cards in your deck.");
				}

				// If human won the round.
				if (winner == 0) {
					System.out.println("It is your turn to select a category, the categories are: ");
					for (int i = 0; i < controller.getTopCard(0).getAttributeValues().length; i++) {
						System.out.println("   " + (i + 1) + ": " + model.getHeader(i));
					}
					System.out.print("Enter the number for your attribute: ");

					// Sensible input checking.
					while (true) {
						try {
							selection = in.nextInt();
							if (selection <= 5 && selection >= 1) {
								break;
							}
							throw (new Exception());
						} catch (Exception e) {
							in.nextLine();
							System.err.println("That's not a valid category, try again.");
						}
					}

					// Change indices from 1-5 to 0-4 to match our arrays.
					controller.getUserArray()[0].selectedCategory = selection - 1;
					System.out.println("You selected " + model.getHeader(selection - 1) + ".");

					// If the AI won the round.
				} else {
					System.out.println("Player " + controller.getUserArray()[winner].getUserID()
							+ "'s turn to select a category. ");

					controller.getUserArray()[winner].selectCategory(controller.getTopCard(winner));

					System.out.println("Player " + controller.getUserArray()[winner].userID + " selected "
							+ model.getHeader(controller.getUserArray()[winner].selectedCategory) + ".");
				}

				// Write selected category to file.
				if (writeGameLogsToFile) {
					writeToLog.writeCategorySelectedAndValuesToFile(roundCounter, model.cardHeader,
							controller.getUserArray()[winner].selectedCategory, controller.getTopCard(winner),
							controller.getUserArray()[winner]);
				}

				// In case there is a draw, winner of previous round is saved in a variable.
				previousWinner = winner;
				winner = controller.checkRoundWinner(previousWinner);

				// Write cards in personal decks to file.
				if (writeGameLogsToFile) {
					for (int user : controller.activeUser) {
						writeToLog.writeContentsOfCurrentCardsInPlayToFile(controller.getTopCard(user),
								controller.userArray[user]);
					}
				}

				// If there was a draw.
				if (winner == -1) {
					numberOfDraws++;

					int winnerInDraw = (int) controller.maxList.get(controller.maxList.size() - 1);

					// Display a winning card even though it was a draw, as Richard did.
					System.out.println("Round " + roundCounter + ": This round was a draw.");
					System.out.println(
							"The winning card was '" + controller.getTopCard(winnerInDraw).getCardName() + "':");
					for (int i = 0; i < controller.getTopCard(winnerInDraw).getAttributeValues().length; i++) {
						System.out.println(model.getHeader(i) + ": "
								+ controller.getTopCard(winnerInDraw).getAttributeValues()[i]);
					}

					// If someone won the round.
				} else {
					if (winner == 0) {
						System.out.println("Round " + roundCounter + ": You won this round");
					} else {
						System.out.println("Round " + roundCounter + ": Player " + winner + " won this round");
					}
					System.out.println("The winning card was '" + controller.getTopCard(winner).getCardName() + "':");
					for (int i = 0; i < controller.getTopCard(winner).getAttributeValues().length; i++) {
						System.out.println(
								model.getHeader(i) + ": " + controller.getTopCard(winner).getAttributeValues()[i]);
					}
				}

				// Only write drawstack to file if its size has changed.
				boolean writeDrawStackToFileQuestionMark = false;
				if (!controller.drawStack.isEmpty() || winner == -1) {
					writeDrawStackToFileQuestionMark = true;
				} else {
					writeDrawStackToFileQuestionMark = false;
				}

				// Add losers' cards to winner's deck.
				controller.changeOwnership(winner);

				// Write the deck contents to file as well as the contents of the drawstack (if
				// it has changed).
				if (writeGameLogsToFile) {
					for (int j = 0; j < controller.userArray.length; j++) {
						writeToLog.writeUsersDeckContentToFile(controller.userArray[j].personalDeck,
								controller.userArray[j]);
					}
				}

				if (writeGameLogsToFile && writeDrawStackToFileQuestionMark) {
					writeToLog.writeContentsOFCommunalPileToFile(controller.drawStack);
				}

				// If a player lost all their cards, they are out of the game.
				System.out.println("Common pile now has " + controller.drawStack.size() + " cards");
				controller.excludeLoser();
				roundCounter++;

				sleepThread();

				// Check if the game has ended (someone has all the cards).
				for (int user = 0; user < controller.getUserArray().length; user++) {
					if (controller.getUserArray()[user].personalDeck.size() == 40) {
						gameEnd = true;

						System.out.println();

						// Write the winner to file.
						if (writeGameLogsToFile) {
							writeToLog.writeWinnerToFile(controller.userArray[user]);
						}

						if (user == 0) { // if user won
							System.out.println("Congrats " + userName + ", you win!");
							didHumanWin = true;
						} else { // if AI won
							System.out.println("The winner is: AI Player " + user + ".");
							didHumanWin = false;
						}
						System.out.println("Scores:");
						for (int l = 0; l < controller.getUserArray().length; l++) {
							if (l == 0) {
								System.out.println("    " + userName + ": " + controller.getUserArray()[l].roundsWon);
							} else {
								System.out
										.println("    AI Player " + l + ": " + controller.getUserArray()[l].roundsWon);

							}
						}
					}
				}
			}
			// Reset the AIUser's nextID.
			AIUser.nextID = 1;

			UploadStats upload = new UploadStats(didHumanWin, roundCounter, numberOfDraws);
		}
		in.close();

	}

	// Make the thread sleep so that the player sees all the rounds.
	public static void sleepThread() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			System.err.println("You woke up the thread!");
		}
	}
}