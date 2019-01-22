package commandline;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Model_Adriano
{
	// private String filePath;
	private static String filePath = "C:\\Users\\ALEXANDRA\\eclipse-workspace\\TeamProject\\StarCitizenDeck.txt";
	private String[] cardHeader;
	private String[][] cards;
	protected static int numberOfAllCards;
	private int numberOfPlayers;
	private static Model_Adriano a;
	static ArrayList<String> cardValues = new ArrayList<String>();
	static ArrayList<String> attributeValues = new ArrayList<>();
	static List<String> list = new ArrayList<String>();
	static List<String> cardName = new ArrayList<>();


	static ArrayList<String> name = new ArrayList<>();




	public Model_Adriano
	(String filePath)
	{
		Model_Adriano.filePath = filePath;
	}
	//working, 0th position will only contain the word description, you might want to avoid it
	//   gets the header for the meaning of the values that are collected
	public String[] getCardHeaders(String filePath)
	{

		FileInputStream s;
		String[] headers = null;
		try
		{
			s = new FileInputStream(filePath);
			Scanner rf = new Scanner(s);

			String firstline = rf.nextLine();
			firstline.trim();
			headers = firstline.split(" ");

			rf.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		return headers;
	}

	// minimum of 1, maximum of 4, not tested 
	public void createNumberOfAIPlayers(int numbberOfPlayers)
	{
		this.numberOfPlayers = numbberOfPlayers;
	}

	//working fine
	//collects cards to an arraylist   
	public static ArrayList<String> getCards(String filePath)
	{
		BufferedReader input = null;
		try
		{
			input = new BufferedReader(new FileReader(filePath));
			input.readLine();
			String nm;
			while ((nm = input.readLine()) != null)
			{
				cardValues.add(nm);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (input != null)
			{
				try
				{
					input.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}

			}
		}
		numberOfAllCards = cardValues.size();
		return cardValues;
	}

	//working fine
	//reads the values of the attributes in the cards
	public static ArrayList<String> readCardAttributes(String filePath)

	{
		BufferedReader br = null;
		try
		{
			br = new BufferedReader(new FileReader(filePath));
			br.readLine();
			String line = null;
			while ((line = br.readLine()) != null)
			{
				attributeValues.add(line.substring(line.split(" ")[0].length() + 1,
						line.length()));
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (br != null)
			{
				try
				{

					br.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return attributeValues;
	}


	public static ArrayList<String> getCard_Name(String filePath) throws IOException{


		//Object[] array = null;
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		br.readLine();
		String line = null;

		while ((line = br.readLine()) != null) {
			String[] splited = line.split("\\s");
			cardName.add(splited[0]);
		}
		br.close();
		return (ArrayList<String>) cardName;


	}






	//test area
	public static void main(String[] args) throws IOException {

		a = new Model_Adriano(filePath);
		Model_Adriano.getCards(filePath);
		Model_Adriano.readCardAttributes(filePath);
		
		ArrayList<String> titles = getCards(filePath);
		System.out.println(titles.get(1));
		
		ArrayList<String> titles_a = readCardAttributes(filePath);
		System.out.println(titles_a.get(0));
		
		ArrayList<String> titles_b = getCard_Name(filePath);
		System.out.println(titles_b);




	}

}