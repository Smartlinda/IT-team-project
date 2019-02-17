package commandline;

/*
 * AIUser is an extension of Generic User, with only two differences:
 * the 'nextID' variable, which ensures that no two users have the same userID by incrementing it; 
 * and the selectCategory method, which lets the AIUser select the category with the
 * highest value on their current card. Getters and setters are all inherited from the GenericUser class,
 * as well as the toString method.
 */

public class AIUser extends GenericUser
{

   // Variables.
   protected static int nextID = 1;

   // Constructor.
   public AIUser()
   {
      this.userID = nextID;
      nextID++;
   }

   // Setters and getters.
   public int getNextID()
   {
      return nextID;
   }

   public void setNextID(int nextID)
   {
      this.nextID = nextID;
   }

   // Methods.
   public int selectCategory(Card card)
   {
      int max = -1;
      for (int i = 0; i < card.getAttributeValues().length; i++)
      {
         if (card.getAttributeValues()[i] > max)
         { // Calculate the maximum attribute of the card
            max = card.getAttributeValues()[i];
            selectedCategory = i;
         }
      }
      return selectedCategory; // Return the index of the highest value
   }

  
}
