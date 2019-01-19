package commandline;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Model
{
   private String filePath;
   private String[] cardHeader;
   private String[][] cards;
   private int numberOfAllCards;
   private int numberOfPlayers;

   public Model(String filePath)
   {
      this.filePath = filePath;
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
   public ArrayList<String> getCards(String filePath)
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
   public static void writeToFile(String sequence)
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

  
   //test area
   public static void main(String[] args)
   {
      String filePath = "C:\\Users\\Adriano\\eclipse-workspace\\MScIT_TeamProject_TemplateProject\\StarCitizenDeck.txt";
      // ArrayList<String> titles = readCards(filePath);
      // System.out.println(titles.get(0));
      String[] name = getCardHeader(filePath);
      System.out.println(name[0]);
      writeToFile("Hello, world");
   }

}
