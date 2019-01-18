package commandline;

public class Card {
	
	private String cardName;
	private int[] attributeValues;
	
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
