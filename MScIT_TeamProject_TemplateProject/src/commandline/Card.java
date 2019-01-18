package commandline;

public class Card {
	
	String cardName;
	int[] attributeValues;
	
	public Card(String n, int[] v) {
		cardName = n;
		attributeValues = v;
	}
	
	public String getCardName() {
		return cardName;
	}
	
	public int[] getAttributeValues() {
		return attributeValues;
		// something
	}

}
