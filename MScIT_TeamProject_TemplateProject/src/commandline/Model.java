package commandline;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/*
 * The Model class is responsible for reading in StarCitizenDeck.txt and storing the Cards in the deck 'cardCon'.
 * ****THE FILE PATH MUST BE CHANGED FOR RUNNING IN THE COMMANDLINE OR FOR MACS****
 */

public class Model {

	// Variables.
	// private String filePath =
	// "H:\\git\\IT-team-project1\\MScIT_TeamProject_TemplateProject\\StarCitizenDeck.txt";
	private String filePath = System.getProperty("user.dir") + "\\StarCitizenDeck.txt";

	protected String[] cardHeader = new String[6];
	private String[] row;
	private String[] subrow;
	private int[] attributeValues;
	protected ArrayList<Card> cardCon = new ArrayList<Card>(); // all cards

	// Constructor.
	public Model() {
	}

	// Getters.
	public String getHeader(int index) {
		return cardHeader[index + 1];
	}

	public int getDeckSize() {
		return cardCon.size();
	}

	public ArrayList<Card> getDeck() {
		return cardCon;
	}

	public String[] getHeader() {
		return cardHeader;
	}

	// Methods.
	public void readContent() {
		try {
			BufferedReader brd = new BufferedReader(new FileReader(filePath));
			String head = brd.readLine(); // Read in the first line.
			cardHeader = head.split(" ");
			while (brd.ready()) {
				String con = brd.readLine(); // Read in one row.
				row = con.split(" ");
				
				// Extract the integers from the row.
				subrow = Arrays.copyOfRange(row, 1, row.length);
				// Convert from String[] to int[].
				attributeValues = Arrays.stream(subrow).mapToInt(Integer::parseInt).toArray();
				
				
				Card card = new Card(row[0], attributeValues, this); // Make a card object.
				cardCon.add(card); // Add to the deck.
			}
			brd.close();
		} catch (IOException e) {
			System.out.println("An exception occurred!");
			e.printStackTrace();
		}
	}
}
