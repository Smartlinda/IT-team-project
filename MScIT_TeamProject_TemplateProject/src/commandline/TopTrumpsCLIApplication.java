package commandline;

import java.util.Scanner;

/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {

	/**
	 * This main method is called by TopTrumps.java when the user specifies that they want to run in
	 * command line mode. The contents of args[0] is whether we should write game logs to a file.
 	 * @param args
	 */
	public static void main(String[] args) {
		
		//not in the loop: this is a one time thing i think 
	//------------------------------------------------------------------------------------------------------	
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome! What's your name?");
		String userName = in.nextLine(); // can be anything, like numbers,symbols we dont care, right?
		HumanUser player = new HumanUser(userName);  //make human user with username
//add the player to the userarray in controller
		
		/*
		 * the underneath could be put into the loop but we need to make a variable that indicates if a game is running or not
		 * for example a player could have finished a game and wants to play another one, and doesnt want to quit so we need to
		 * give the option to play again without terminating the program. or do we terminate? 
		 */
		
		System.out.println(   
				"Hi " + userName + "! \nSelect an option:\nPress 1 to start a game\nPress 2 to see statistics");
		int selection = in.nextInt();  //do it this way because otherwise the scanner does weird shit			
		System.out.print("");																					
		if (selection == 1) { 
			System.out.println("Game starts here.");		//start the game
//			System.out.println("How many AI do you want?");
// add the AI to the userarray in controller			
		} else if (selection == 2) {
			System.out.println("Statistics displayed here");		//display the statistics
		} 
		
		in.close();
//need to catch inputmismatchexceptions for when the user inputs something: when selecting a game or when selecting a statistic
		//---------------------------------------------------------------------------------------------------

		boolean writeGameLogsToFile = false; // Should we write game logs to file?
		if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true; // Command line selection
		
		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application
		
		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {

			
			// ----------------------------------------------------
			// Add your game logic here based on the requirements
			// ----------------------------------------------------
			
			
			
			userWantsToQuit=true; // use this when the user wants to exit the game
			
		}


	}

}
