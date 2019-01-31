package commandline;

public class Card {

	protected String cardName; // The name for a card, e.g. velociraptor
	protected int[] attributeValues; // The number values for each category for this card
	Model model;

	public Card(String cardName, int[] attributeValues, Model model) { // Create a card with given name and attributes
		this.model = model;
		this.cardName = cardName;
		this.attributeValues = attributeValues;
	}

	public String getCardName() { // Return the card name
		return cardName;
	}

	public int[] getAttributeValues() { // Return the values of the attributes
		return attributeValues;
	}
	

	public String toString() {
		String str = String.format("%-13s: %2d %2d %2d %2d %2d", 
				cardName,attributeValues[0],attributeValues[1],
				attributeValues[2],attributeValues[3],attributeValues[4]);
		
		return str.toString();
	}
}
