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
	
	//does not want to return the header strings for some reason (only this class is messed up)
	public String toString() {
//		StringBuilder returned = new StringBuilder();
//		returned.append("'"+cardName + "'\n");
//		for (int i = 0; i < attributeValues.length;i++) {
//			returned.append(model.cardHeader[i+1] + ": " + attributeValues[i]+ "\n");
//		}
//		String str = String.format("'%s': \n %s: %d\n%s: %d\\n%s: %d\\n%s: %d\\n%s: %d\\n", 
//				cardName,model.cardHeader[1],attributeValues[0],model.cardHeader[2],attributeValues[1],model.cardHeader[3],
//				attributeValues[2],model.cardHeader[4],attributeValues[3],model.cardHeader[5],attributeValues[4]);
		String str = String.format("'%s': \n %s: %d", 
				cardName,model.cardHeader[1],attributeValues[0],model.cardHeader[2],attributeValues[1],model.cardHeader[3],
				attributeValues[2],model.cardHeader[4],attributeValues[3],model.cardHeader[5],attributeValues[4]);
		
		return str.toString();
	}
}
