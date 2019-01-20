package commandline;
import java.io.*;
import java.util.*;

public class Model_a {

	static List<String> cardName = new ArrayList<>();
	static List<String> attributeValues = new ArrayList<>();

//--- 
	public static void read_cardName() throws IOException {
		Object[] array = null;
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\ALEXANDRA\\eclipse-workspace\\TeamProject\\StarCitizenDeck.txt"));
		br.readLine();
		String line = null;

		while ((line = br.readLine()) != null) {
			String[] splited = line.split("\\s");
			cardName.add(splited[0]);
		}
		br.close(); 
	}
	
	
//----
	
//---
	public static void read_attributeValues() throws IOException {
		Object[] array = null;
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\ALEXANDRA\\eclipse-workspace\\TeamProject\\StarCitizenDeck.txt"));
		br.readLine();
		String line = null;

		while ((line = br.readLine()) != null) {
			String[] splited = line.split(" ");
			attributeValues.add(splited[0]);
		}
		br.close(); 
	}
	
//---	
	
	
	

	public static List<String> get_Card() {
		return cardName;

	}


	public static List<String> get_attributeValues() {
		return attributeValues;

	}



	public static void main(String[] args) throws IOException {
		Model_a  read_deck = new Model_a (); 
		read_deck.read_cardName(); 
		System.out.println(get_Card());
		System.out.println(get_attributeValues());

		


	}

}