package commandline;

import java.util.Scanner;

/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {

	/**
	 * This main method is called by TopTrumps.java when the user specifies that
	 * they want to run in command line mode. The contents of args[0] is whether we
	 * should write game logs to a file.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Controller controller = new Controller();
		
		Scanner in = new Scanner(System.in);
		System.out.print("Welcome! What's your name? ");
		String userName = in.nextLine(); 
		HumanUser player = new HumanUser(userName); // make human user with username

		System.out.println(
				"Hi " + userName + "! \nSelect an option:\nPress 1 to start a game\nPress 2 to see statistics");
		System.out.print("Enter your selection here: ");
		int selection = in.nextInt(); 
		System.out.print("");
		if (selection == 1) {
			System.out.print("Choose the number of opponents (1-4): ");
			selection = in.nextInt(); // need to check if input is between 1 and 4 (exception catching)
			controller.userArray = new GenericUser[selection + 1]; // make the array to be the size of aiplayers+1
			controller.userArray[player.userID] = player; //add the player to the userarray in controller
			for (int i = 0; i < selection; i++) {
				AIUser ai = new AIUser();
				controller.userArray[ai.userID] = ai;
				
			}
			System.out.print("");
			
			System.out.println("Game starts here.");
			
		} else if (selection == 2) {
			System.out.println("Statistics displayed here");
			// display the statistics
		}

//need to catch inputmismatchexceptions for when the user inputs something: when selecting a game or when selecting a statistic
		// ---------------------------------------------------------------------------------------------------

		boolean writeGameLogsToFile = false; // Should we write game logs to file?
		// ------uncomment this----------if (args[0].equalsIgnoreCase("true"))
		// writeGameLogsToFile=true; // Command line selection

		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application

		int roundCounter = 1;
		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {
			
			System.out.println("Round " + roundCounter);
			

			roundCounter++;
			// if () {
			//	 userWantsToQuit=true; // use this when the user wants to exit the game
			// }
		}

		in.close();

	}

}
