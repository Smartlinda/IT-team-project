package commandline;

public class Card {

	protected String cardName; // The name for a card, e.g. velociraptor
	protected int[] attributeValues; // The number values for each category for this card
	public Model model = new Model();

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
	
	//does not want to return the header strings for some reason (only this class is messed up)
	public String toString() {
		StringBuilder returned = new StringBuilder();
		returned.append("'"+cardName + "'\n");
		for (int i = 0; i < attributeValues.length;i++) {
			returned.append(model.cardHeader[i+1] + ": " + attributeValues[i]+ "\n");
		}
		return returned.toString();
	}
}
