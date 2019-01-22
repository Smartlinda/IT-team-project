package commandline;
import java.io.*;
import java.util.*;

public class Model_a {

	static List<String> cardName = new ArrayList<>();
	static List<String> attributeValues = new ArrayList<>();
	static String  filePath = "C:\\Users\\ALEXANDRA\\eclipse-workspace\\TeamProject\\StarCitizenDeck.txt";

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
	
	//I changed it to this, have a look at it and test it
//	 public static ArrayList<String> read_attributeValues(String filePath)
//
//	   {
//	      ArrayList<String> attributeValues = new ArrayList<>();
//	      BufferedReader br = null;
//	      try
//	      {
//	         br = new BufferedReader(new FileReader(filePath));
//	         br.readLine();
//	         String line = null;
//	         while ((line = br.readLine()) != null)
//	         {
//	            attributeValues.add(line.substring(line.split(" ")[0].length() + 1,
//	                  line.length()));
//	         }
//	      }
//	      catch (IOException e)
//	      {
//	         e.printStackTrace();
//	      }
//	      finally
//	      {
//	         if (br != null)
//	         {
//	            try
//	            {
//
//	               br.close();
//	            }
//	            catch (IOException e)
//	            {
//	               e.printStackTrace();
//	            }
//	         }
//	      }
//	      return attributeValues;
//	   }
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