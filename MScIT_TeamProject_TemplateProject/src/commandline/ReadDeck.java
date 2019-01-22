package commandline;
import java.io.*;
import java.util.*;

public class ReadDeck {
	
	static List<String> Card = new ArrayList<>();
   private int numberOfPlayers;
	
	

	public void read_deck() throws IOException {
		Object[] array = null;
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\ALEXANDRA\\eclipse-workspace\\TeamProject\\StarCitizenDeck.txt"));
		String line;
		//br.next(); // ignore the first line
		while ((line = br.readLine()) != null) {
			String[] splited = line.split("\\s");
			Card.toArray(splited); // Changed this from Card.add(splited) to Card.toArray(splited) - Adamos


		}
		br.close();


	}

	public static List<String> get_Card() {
		return Card;

	}
	
	//not working properly yet
   public static String[] getCardHeader(String filePath)
   {

      String[] header = null;
      FileReader s;
      try
      {
         s = new FileReader(filePath);
         Scanner rf = new Scanner(s);
         while (rf.hasNext())
         {
            String line = rf.next();
            header = line.split(" ");
         }
         rf.close();
      }
      catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }

      return header;
   }

   // minimum of 1, maximum of 4, not tested 
   public void createNumberOfAIPlayers(int numbberOfPlayers)
   {
      this.numberOfPlayers = numbberOfPlayers;
   }
   
//working fine
   public static ArrayList<String> getCards(String filePath)
   {
      ArrayList<String> cardValues = new ArrayList<String>();
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
      return cardValues;
   }
   //Method to create writeGameLogsToFile
   //Not sure if it should just be one method that takes all arguments and writes to file
   //Since it can be messy
   public void writeToFile(String sequence)
   {
      PrintWriter write = null;
      String fileName = "toptrumps.log";
      try
      {
         FileOutputStream fileOut = new FileOutputStream(fileName, true);
         write = new PrintWriter(fileOut);
      }
      catch (FileNotFoundException e)
      {
         
         System.err.println("No file was found");
      }
      
      write.println(sequence);
      write.close();    
   }


	public static void main(String[] args) throws IOException {
		ReadDeck read_deck = new ReadDeck(); 
		read_deck.read_deck();       	
		System.out.println(get_Card());
		
		
		
		
	}

}