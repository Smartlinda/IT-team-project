package testing;

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
   public static void setup()
   {
      model = new Model();
      controller = new Controller(model);
      copy = new ArrayList<Integer>();
      controller.getActiveUser().add(0);
      controller.getActiveUser().add(1);
   }

   /**
    * This test will allow to test if the checkRoundWinner method will return a
    * tie, if it returns -1, a draw occurred in the round
    */
   @Test
   public void testCheckRoundWinner()
   {
      int[] array = { 1, 2, 3, 4, 5 };
      Card card = new Card("One card", array, model);
      Card card2 = new Card("another card", array, model);
      GenericUser[] userArray = { new HumanUser("adriano"), new AIUser() };
      controller.setUserArray(userArray);
      controller.getUserArray()[0].getPersonalDeck().add(card);
      controller.getUserArray()[1].getPersonalDeck().add(card2);
      controller.getUserArray()[1].selectCategory(card2);
      System.out.println(controller.getUserArray()[1].getSelectedCategory());     
      
      int actual = controller.checkRoundWinner(1);
      // tests for -1 to see if there is draw
      int expected = -1;

      assertEquals("This test will check if the input matches the output",expected, actual);

   }

   @Test
   public void testExcludeLoser()
   {
      GenericUser[] userArray = { new HumanUser("adriano"), new AIUser() };
      int[] array = { 1, 2, 3, 4, 5 };
      Card card = new Card("One card", array, model);
      controller.setUserArray(userArray);
      controller.getUserArray()[0].getPersonalDeck().add(card);
      controller.excludeLoser();
      int expected = 1;
      int actual = controller.getActiveUser().size();
      assertEquals(expected, actual);
   }

}
