package Testing;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import commandline.AIUser;
import commandline.Card;
import commandline.Controller;
import commandline.GenericUser;
import commandline.HumanUser;
import commandline.Model;

public class ControllerTest
{
   static Model model;
   static Controller controller;
   static ArrayList<Integer> activeUser;
   static ArrayList<Integer> copy;
   
   @BeforeClass
   public static void setup() {
      
      model = new Model();
      controller = new Controller(model);

      activeUser = new ArrayList<Integer>();
      activeUser.add(1);
      activeUser.add(2);
   }
   /**
    * This test will allow to test if the checkRoundWinner method
    * will return a tie, if it returns -1, a draw occurred in the round
    * 
    *
    */
   @Test
   public void testCheckRoundWinner()
   {

      
      int[] array = { 1, 2, 3, 4, 5 };
      Card card = new Card("One card", array, model);
      Card card2 = new Card("another card", array, model);
      GenericUser[] userArray = { new AIUser(), new HumanUser("adriano") };

     
      int actual = controller.checkRoundWinner(0);
      //tests for -1 to see if there is draw
      int expected = -1;

      assertEquals("This test will check if the input matches the output",expected, actual);

   }

   @SuppressWarnings("unchecked")
   @Test
   public void testExcludeLoser()
   {          
      GenericUser[] userArray = { new AIUser(), new HumanUser("adriano") };
      copy = new ArrayList<Integer>();
      copy = (ArrayList<Integer>) activeUser.clone();
      for (int user : activeUser)
      {
         if (userArray[user].getPersonalDeck().size() == 0)
         {

         }
      }
      
   }

}
