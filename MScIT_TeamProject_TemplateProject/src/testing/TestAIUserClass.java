package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import commandline.AIUser;
import commandline.Card;
import commandline.Model;

/**
 * @author Adriano
 *
 */

public class TestAIUserClass
{
   /**
    * The test shows that the value to be returned is the 4th position
    * of the array, since it is the one that contains the highest value 
    * in the array.
    *
    */

   
   
   @Test
   public void testSelectedCategoryForAIUser()
   {
      AIUser user = new AIUser();
      Model model = new Model();
      int [] array = { 1,2,3,4,5};
      Card card = new Card("One card", array, model);
      int actual = user.selectCategory(card);
      int expected = 4;
     
      assertEquals(expected, actual);
      
   }

}
