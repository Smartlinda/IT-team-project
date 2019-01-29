package commandline;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Model {
	private String filePath = System.getProperty("user.dir") + "\\StarCitizenDeck.txt" ;
	protected String[] cardHeader = new String[6];
	private String[][] cards;
	protected static int numberOfAllCards;
	private int numberOfPlayers;
	private String[] row;
	private String[] subrow;
	private int[] aV;
	protected ArrayList<Card> cardCon = new ArrayList<Card>(); // all cards

	public Model() {
	}

	public void readContent() {
		cardCon = new ArrayList<Card>();
		try {
			BufferedReader brd = new BufferedReader(new FileReader(filePath));
			String head = brd.readLine(); // read in the first line which are the
											// headers
			cardHeader = head.split(" ");
			while (brd.ready()) {
				String con = brd.readLine();// read in one row
				row = con.split(" "); // split it
				subrow = Arrays.copyOfRange(row, 1, row.length);// extract the
																// integer part from
																// the row
				aV = Arrays.stream(subrow).mapToInt(Integer::parseInt).toArray();// convert
																					// from
																					// string[]
																					// to
																					// int[]
				Card card = new Card(row[0], aV, this);// make a card object
				cardCon.add(card); // store into the arraylist that contains all
									// cards
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		numberOfAllCards = cardCon.size();
	}

	public String getHeader(int index) {
		// +1 to avoid description
		return cardHeader[index + 1];
	}

	public int getDeckSize() {
		return cardCon.size();
	}

}
