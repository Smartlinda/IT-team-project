package commandline;

/*
 * The Card class has two attributes: its cardName (i.e. velociraptor) and attributeValues ({10,0,5,7,2}).
 * The model is an input in the constructor to ensure that only one model is used throughout the program. 
 * There are no setters as the values need not be changed, as they are all provided in the .txt file.
 * Cards are either stored in an array or arraylist in our program, but are distinct objects.
 */

public class Card {

	// Variables.
	protected String cardName;
	protected int[] attributeValues;
	Model model;

	// Constructor.
	public Card(String cardName, int[] attributeValues, Model model) {
		this.model = model;
		this.cardName = cardName;
		this.attributeValues = attributeValues;
	}

	// Getters.
	public String getCardName() {
		return cardName;
	}

	public int[] getAttributeValues() {
		return attributeValues;
	}

	// A toString method.
	public String toString() {
		String str = String.format("%-13s: %2d %2d %2d %2d %2d", cardName, attributeValues[0], attributeValues[1],
				attributeValues[2], attributeValues[3], attributeValues[4]);
		return str;
	}

}
