package commandline;
import java.io.BufferedReader;
import java.io.FileInputStream;
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
//working, 0th position will only contain the word description, you might want to avoid it
   public static String[] getCardHeader(String filePath)
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
   public static  ArrayList<String> getCards(String filePath)
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
   public static ArrayList<String> read_attributeValues(String filePath) throws IOException {
      
      ArrayList<String> attributeValues = new ArrayList<>();
      Object[] array = null;
      BufferedReader br = new BufferedReader(new FileReader("filePath"));
      br.readLine();
      String line = null;

      while ((line = br.readLine()) != null) {
         String[] splited = line.split(" ");
         attributeValues.add(splited[0]);
      }
      br.close(); 
      return attributeValues;
   }
   //test area
   public static void main(String[] args)
   {
      String filePath = "C:\\Users\\Adriano\\eclipse-workspace\\MScIT_TeamProject_TemplateProject\\StarCitizenDeck.txt";
       ArrayList<String> titles = getCards(filePath);
       System.out.println(titles.get(0));
      String[] name = getCardHeader(filePath);
      System.out.println(name[0]);
      
      
      
   }

}
