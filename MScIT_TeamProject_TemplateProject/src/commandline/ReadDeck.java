package commandline;
import java.io.*;
import java.util.*;

public class ReadDeck {
	static List<String> Description = new ArrayList<>();
	static List<String> Size = new ArrayList<>();
	static List<String> Speed  = new ArrayList<>();
	static List<String> Range = new ArrayList<>();
	static List<String> Firepower = new ArrayList<>();
	static List<String> Cargo = new ArrayList<>();
	static List<String> Card = new ArrayList<>();
	
	

	public void read_deck() throws IOException {
		Object[] array = null;
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\ALEXANDRA\\eclipse-workspace\\TeamProject\\StarCitizenDeck.txt"));
		String line;
		//br.next(); // ignore the first line
		while ((line = br.readLine()) != null) {
			String[] splited = line.split("\\s");
			Card.add(splited);
//			Description.add(splited[0]); 
//			Size.add(splited[1]); 
//			Speed.add(splited[2]); 
//			Range.add(splited[3]); 
//			Firepower.add(splited[4]); 
//			Cargo.add(splited[5]); 

		}
		br.close();


	}

	public static List<String> get_Description() {
		return Description;

	}
	
	public static List<String> get_Size() {
		return Size;

	}
	
	public static List<String> get_Speed() {
		return Speed;

	}
	
	public static List<String> get_Range() {
		return Range;

	}
	
	public static List<String> get_Firepower() {
		return Firepower;

	}

	public static List<String> get_Cargo() {
		return Cargo;

	}
	
	


	public static void main(String[] args) throws IOException {
		ReadDeck read_deck = new ReadDeck(); 
		read_deck.read_deck();       	
		System.out.println(get_Description());
		System.out.println(get_Size());
		System.out.println(get_Speed());
		System.out.println(get_Range());
		System.out.println(get_Firepower());
		System.out.println(get_Cargo());

		
		
		
	}

}