package Testing;
import org.junit.Test;
import commandline.Card;
import commandline.Controller;
import commandline.Model;


public class ControllerTest
{

   @Test
   public void testCheckRoundWinner() {
     
     Model model = new Model();
     Controller controller = new Controller(model);
     int[] array = {1,2,3,4,5};
     Card card = new Card("One card", array, model);
     
     
   }
   
   @Test
   public void testExcludeLoser()
   {
      
   }
   

}
