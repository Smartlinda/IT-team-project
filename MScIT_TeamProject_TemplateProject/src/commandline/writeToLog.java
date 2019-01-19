package commandline;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class writeToLog
{
   //working, needs to be tested with the program
   public void writeCompleteDeckToFile(ArrayList<String> completeDeck)
   {
      PrintWriter write = null;
      String fileName = "toptrumps.log";
      try
      {
         FileOutputStream fileOut = new FileOutputStream(fileName);
         write = new PrintWriter(fileOut);
      }
      catch (FileNotFoundException e)
      {
         
         System.err.println("No file was found");
      }
      //write the complete deck to file, initial deck
      write.print("---------");
     
      write.print("\nComplete deck: ");
      for(int i = 0; i< completeDeck.size();i++) {
         String str = completeDeck.get(i).toString();
         write.print("\n"+str);
      }
      write.close();    
   }
   //working, needs to be tested with the program
   public void writeCompleteShuffledDeckToFile(ArrayList<String> shuffledDeck)
   {
      PrintWriter write = null;
      String fileName = "toptrumps.log";
      try
      {
         FileOutputStream fileOut = new FileOutputStream(fileName,true);
         write = new PrintWriter(fileOut);
      }
      catch (FileNotFoundException e)
      {
         
         System.err.println("No file was found");
      }
      //write the complete deck to file, initial deck
      write.print("\n---------");
      write.print("\nShuffled deck: ");
      for(int i = 0; i< shuffledDeck.size();i++) {
         String str = shuffledDeck.get(i).toString();
         write.print("\n"+str);
      }
      write.close();    
   }
}
