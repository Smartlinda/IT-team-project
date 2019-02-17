package commandline;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

/*
 * This class houses all the methods required for writing what is going on in the game to a  log file,
 * namely toptrumps.log. There is a separate method for all the information required. These are called in the
 * main for use.
 */

public class writeToLog {
	static final String fileName = "toptrumps.log";

	// Write the initial read in deck to the file.
	public static void writeCompleteDeckToFile(ArrayList<Card> completeDeck) {
		PrintWriter write = null;
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName);
			write = new PrintWriter(fileOut);
		} catch (FileNotFoundException e) {

			System.err.println("No file was found");
		}
		write.print("------------------------------");

		write.print("\nComplete deck: ");
		write.print("\nDescription    Size Speed Range Firepower Cargo");
		for (int i = 0; i < completeDeck.size(); i++) {
			String str = completeDeck.get(i).toString();
			write.print("\n" + str);
		}
		write.close();
	}

	// Write the shuffled deck to the file.
	public static void writeCompleteShuffledDeckToFile(ArrayList<Card> shuffledDeck) {
		PrintWriter write = null;
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName, true);
			write = new PrintWriter(fileOut);
		} catch (FileNotFoundException e) {

			System.err.println("No file was found");
		}
		write.print("\n------------------------------");
		write.print("\nShuffled deck: ");
		for (int i = 0; i < shuffledDeck.size(); i++) {
			String str = shuffledDeck.get(i).toString();
			write.print("\n" + str);
		}
		write.close();
	}

	// Write the contents of a user's deck to file.
	public static void writeUsersDeckContentToFile(ArrayList<Card> personalDeck, GenericUser user) {
		PrintWriter write = null;
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName, true);
			write = new PrintWriter(fileOut);
		} catch (FileNotFoundException e) {

			System.err.println("No file was found");
		}
		write.print("\n------------------------------");
		if (user.getUserID() == 0) {
			write.print("\nHUMAN PLAYER");
		} else {
			write.print("\nAI PLAYER " + user.getUserID());
		}
		write.print("\nCards in deck: ");
		for (int i = 0; i < personalDeck.size(); i++) {
			String str = personalDeck.get(i).toString();
			write.print("\n" + str);
		}
		write.close();
	}

	// Write the contents of the drawstack to file. 
	public static void writeContentsOFCommunalPileToFile(ArrayList<Card> communalPile) {
		PrintWriter write = null;
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName, true);
			write = new PrintWriter(fileOut);
		} catch (FileNotFoundException e) {
			System.err.println("No file was found");
		}
		write.print("\n------------------------------");
		write.print("\nContent of communal pile: ");
		for (int i = 0; i < communalPile.size(); i++) {
			String str = communalPile.get(i).toString();
			write.print("\n" + str);
		}
		write.close();
	}

	// Write the users' top cards to file in each round.
	public static void writeContentsOfCurrentCardsInPlayToFile(Card cardInPlay, GenericUser user) {
		PrintWriter write = null;
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName, true);
			write = new PrintWriter(fileOut);
		} catch (FileNotFoundException e) {

			System.err.println("No file was found");
		}
		write.print("\n------------------------------");
		if (user.getUserID() == 0) {
			write.print("\nHUMAN PLAYER");
		} else {
			write.print("\nAI PLAYER " + user.getUserID());
		}
		write.print("\nContent of user's card: ");
		String str = cardInPlay.toString();
		write.print("\n" + str);
		write.close();
	}

	// Write which category a player selected each round.
	public static void writeCategorySelectedAndValuesToFile(int round, String[] header, int category, Card card, GenericUser user) {
		PrintWriter write = null;
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName, true);
			write = new PrintWriter(fileOut);
		} catch (FileNotFoundException e) {

			System.err.println("No file was found");
		}
		
		write.print("\n\nROUND " + round);
		write.print("\n--------------------------------------------------");
		if (user.getUserID() == 0) {
			write.print("\nHuman ");
		}else {
			write.print("\nAI Player " + user.getUserID() + " ");
		}
		write.print("selected " + header[category + 1] + ": " + card.getAttributeValues()[category]);

		write.close();
	}

	// Write the winner of the game to file.
	public static void writeWinnerToFile(GenericUser user) {
		PrintWriter write = null;
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName, true);
			write = new PrintWriter(fileOut);
		} catch (FileNotFoundException e) {

			System.err.println("No file was found");
		}
		write.print("\n------------------------------");
		if (user.getUserID() == 0) {
			write.println("\nThe winner of the game is: Human Player");
		} else {
			write.println("\nThe winner of the game is: AI Player " + user.getUserID());
		}
		write.close();
	}
}
