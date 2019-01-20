package commandline;
import java.io.*;
import java.util.*;

public class ReadDeck {
	
	static List<String> Card = new ArrayList<>();
	
	

	public void read_deck() throws IOException {
		Object[] array = null;
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\ALEXANDRA\\eclipse-workspace\\TeamProject\\StarCitizenDeck.txt"));
		String line;
		//br.next(); // ignore the first line
		while ((line = br.readLine()) != null) {
			String[] splited = line.split("\\s");
			Card.add(splited);


		}
		br.close();


	}

	public static List<String> get_Card() {
		return Card;

	}
	
	


	public static void main(String[] args) throws IOException {
		ReadDeck read_deck = new ReadDeck(); 
		read_deck.read_deck();       	
		System.out.println(get_Card());
		
		
		
		
	}

}