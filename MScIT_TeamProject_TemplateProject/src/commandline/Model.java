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

public class Model
{
	// private String filePath;
	private static String filePath = "C:\\Users\\ALEXANDRA\\eclipse-workspace\\TeamProject\\StarCitizenDeck.txt";
	private String[] cardHeader;
	private String[][] cards;
	protected static int numberOfAllCards;
	private static Model a;
	
	static ArrayList<String> attributeValues = new ArrayList<>();
	static ArrayList<Integer> numbers = new ArrayList<Integer>();



	static List<String> cardName = new ArrayList<>();


	public Model
	(String filePath)
	{
		Model.filePath = filePath;
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
						line.length()) );
				
				}
//            System.out.println(newList);
//			 List<Integer> newList = new ArrayList<Integer>(attributeValues.size()) ;
//	            for (String myInt : attributeValues) 
//	            { 
//	              newList.add(Integer.valueOf(myInt)); 
//	            }
//	            System.out.println(newList);


			
			
			
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

		a = new Model(filePath);
		Model.getCard_Name(filePath);
		Model.readCardAttributes(filePath);
		
		
		
		System.out.println(attributeValues);
		
		System.out.println(cardName);
		//System.out.println(numbers);
		




	}

}