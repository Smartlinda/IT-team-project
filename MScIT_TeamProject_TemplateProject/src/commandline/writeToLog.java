package commandline;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class writeToLog
{
   String fileName = "toptrumps.log";

   // working, needs to be tested with the program
   public void writeCompleteDeckToFile(ArrayList<String> completeDeck)
   {
      PrintWriter write = null;
      try
      {
         FileOutputStream fileOut = new FileOutputStream(fileName);
         write = new PrintWriter(fileOut);
      }
      catch (FileNotFoundException e)
      {

         System.err.println("No file was found");
      }
      // write the complete deck to file, initial deck
      write.print("---------");

      write.print("\nComplete deck: ");
      for (int i = 0; i < completeDeck.size(); i++)
      {
         String str = completeDeck.get(i).toString();
         write.print("\n" + str);
      }
      write.close();
   }

   // working, needs to be tested with the program
   public void writeCompleteShuffledDeckToFile(ArrayList<String> shuffledDeck)
   {
      PrintWriter write = null;
      try
      {
         FileOutputStream fileOut = new FileOutputStream(fileName, true);
         write = new PrintWriter(fileOut);
      }
      catch (FileNotFoundException e)
      {

         System.err.println("No file was found");
      }
      // write the complete deck to file, initial deck
      write.print("\n---------");
      write.print("\nShuffled deck: ");
      for (int i = 0; i < shuffledDeck.size(); i++)
      {
         String str = shuffledDeck.get(i).toString();
         write.print("\n" + str);
      }
      write.close();
   }

   // not working this method must write to the file the content
   // of the deck for each user once the deck is allocated to each user
   // it must indicate for each of them if the user is an AI or a human
   // Will probably take four arguments, that are the distributed decks
   // and "listen" to the type of use, a method something like, is user human   
   //Might also be used to get the content of each deck aft
   public static void writeUsersDeckContentToFile(
         ArrayList<String> shuffledDeck)
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
      write.print("\n---------");
      write.print("\nShuffled deck: ");// needs to be changed
      for (int i = 0; i < shuffledDeck.size(); i++)
      {
         String str = shuffledDeck.get(i).toString();
         write.print("\n" + str);
      }
      write.close();
   }

   // working, needs to be tested with the program
   // the content of the communal pile has to be written to file when cards are
   // added or removed
   public static void writeContentsOFCommunalPileToFile(
         ArrayList<String> communalPile)
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
      write.print("\n---------");
      write.print("\nContent of communal pile: ");
      for (int i = 0; i < communalPile.size(); i++)
      {
         String str = communalPile.get(i).toString();
         write.print("\n" + str);
      }
      write.close();
   }
   //not ready
   //Should take four inputs from each user, meaning they are the cards from the top of the deck
   public static void writeContentsOFCurrentCardsInPlayToFile(ArrayList<String> communalPile)
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
      write.print("\n---------");
      write.print("\nContent of communal pile: ");
      for (int i = 0; i < communalPile.size(); i++)
      {
         String str = communalPile.get(i).toString();
         write.print("\n" + str);
      }
      write.close();
   }
   //not ready
   //only used when a user or computer selects category
   public static void writeCategorySelectedAndValuesToFile(
         ArrayList<String> communalPile)
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
      write.print("\n---------");
      write.print("\nContent of communal pile: ");
      for (int i = 0; i < communalPile.size(); i++)
      {
         String str = communalPile.get(i).toString();
         write.print("\n" + str);
      }
      write.close();
   }
   //not working
   //should take the content of the deck of each user
   //should take 4 arguments
   public static void writeContentOfEachDeckAfterARoundToFile(
         ArrayList<String> shuffledDeck)
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
      write.print("\n---------");
      write.print("\nShuffled deck: ");// needs to be changed
      for (int i = 0; i < shuffledDeck.size(); i++)
      {
         String str = shuffledDeck.get(i).toString();
         write.print("\n" + str);
      }
      write.close();
   }
   // tested and working, but needs to be tested with input from the game winner
   public static void writeWinnerToFile(String gameWinner)
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
      write.println("\n---------");
      write.println("The winner of the game is: " + gameWinner);
      write.close();
   }

   // test area
   public static void main(String[] args)
   {
      String filePath = "C:\\Users\\Adriano\\eclipse-workspace\\MScIT_TeamProject_TemplateProject\\StarCitizenDeck.txt";
      // ArrayList<String> titles = readCards(filePath);
      // System.out.println(titles.get(0));

      ArrayList<String> arr = new ArrayList<>();
      arr.add("75");
      arr.add("something more");
      arr.add("something else");
      ArrayList<String> shuffledDeck = new ArrayList<>();
      shuffledDeck.add("the shiffle");
      shuffledDeck.add("Mashisle");
      //
      // writeCompleteDeckToFile(arr);
      // writeCompleteShuffledDeckToFile(shuffledDeck);
      writeWinnerToFile(filePath);
   }
}
