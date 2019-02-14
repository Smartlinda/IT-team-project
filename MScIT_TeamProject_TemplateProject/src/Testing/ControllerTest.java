package Testing;

import static org.junit.Assert.*;

import org.junit.Test;

import commandline.AIUser;
import commandline.Card;
import commandline.Controller;
import commandline.GenericUser;
import commandline.HumanUser;
import commandline.Model;

public class ControllerTest
{

   @Test
   public void testCheckRoundWinner()
   {

      Model model = new Model();
      Controller controller = new Controller(model);
      int[] array = { 1, 2, 3, 4, 5 };
      Card card = new Card("One card", array, model);
      Card card2 = new Card("another card", array, model);
      GenericUser[] userArray = { new AIUser(), new HumanUser("adriano") };

      controller.setUserArray(userArray);
      controller.getUserArray()[0].addCard(card);
      controller.getUserArray()[1].addCard(card2);
      controller.getActiveUser().add(0);
      controller.getActiveUser().add(1);

      controller.getUserArray()[0].selectCategory(card);
      int actual = controller.checkRoundWinner(0);
      int expected = 0;

      assertEquals(expected, actual);

   }

   @Test
   public void testExcludeLoser()
   {

   }

}
