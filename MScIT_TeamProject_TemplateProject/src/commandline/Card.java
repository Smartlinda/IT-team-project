package commandline;

public class Card {

	protected String cardName; // The name for a card, e.g. velociraptor
	protected int[] attributeValues; // The number values for each category for this card

	public Card(String cardName, int[] attributeValues) { // Create a card with given name and attributes
		this.cardName = cardName;
		this.attributeValues = attributeValues;
	}

	public String getCardName() { // Return the card name
		return cardName;
	}

	public int[] getAttributeValues() { // Return the values of the attributes
		return attributeValues;
	}
}
