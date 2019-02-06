import commandline.TopTrumpsCLIApplication;
import online.TopTrumpsOnlineApplication;

public class TopTrumps {

	/** This is the main class for the TopTrumps Application */
	public static void main(String[] args) {
		
		System.err.print(" _________  ________  ________        _________  ________  ___  ___  _____ ______   ________  ________      \n" + 
				"|\\___   ___\\\\   __  \\|\\   __  \\      |\\___   ___\\\\   __  \\|\\  \\|\\  \\|\\   _ \\  _   \\|\\   __  \\|\\   ____\\     \n" + 
				"\\|___ \\  \\_\\ \\  \\|\\  \\ \\  \\|\\  \\     \\|___ \\  \\_\\ \\  \\|\\  \\ \\  \\\\\\  \\ \\  \\\\\\__\\ \\  \\ \\  \\|\\  \\ \\  \\___|_    \n" + 
				"     \\ \\  \\ \\ \\  \\\\\\  \\ \\   ____\\         \\ \\  \\ \\ \\   _  _\\ \\  \\\\\\  \\ \\  \\\\|__| \\  \\ \\   ____\\ \\_____  \\   \n" + 
				"      \\ \\  \\ \\ \\  \\\\\\  \\ \\  \\___|          \\ \\  \\ \\ \\  \\\\  \\\\ \\  \\\\\\  \\ \\  \\    \\ \\  \\ \\  \\___|\\|____|\\  \\  \n" + 
				"       \\ \\__\\ \\ \\_______\\ \\__\\              \\ \\__\\ \\ \\__\\\\ _\\\\ \\_______\\ \\__\\    \\ \\__\\ \\__\\     ____\\_\\  \\ \n" + 
				"        \\|__|  \\|_______|\\|__|               \\|__|  \\|__|\\|__|\\|_______|\\|__|     \\|__|\\|__|    |\\_________\\\n" + 
				"                                                                                                \\|_________|\n" + 
				"                                                                                                            \n" + 
				"                                                                                                            \n");
		
		// command line switches
		boolean onlineMode = false;
		boolean commandLineMode = false;
		boolean printTestLog = false;
		
		// check the command line for what switches are active
		for (String arg : args) {
			
			if (arg.equalsIgnoreCase("-t")) printTestLog=true;
			if (arg.equalsIgnoreCase("-c")) commandLineMode=true;
			if (arg.equalsIgnoreCase("-o")) onlineMode=true;
			
		}
		
		// We cannot run online and command line mode simultaneously
		if (onlineMode && commandLineMode) {
			System.out.println("ERROR: Both online and command line mode selected, select one or the other!");
			System.exit(0);
		}
		
		// Start the appropriate application
		if (onlineMode) {
			// Start the online application
			String[] commandArgs = {"server", "TopTrumps.json"};
			TopTrumpsOnlineApplication.main(commandArgs);
		} else if (commandLineMode) {
			// Start the command line application
			String[] commandArgs = {String.valueOf(printTestLog)};
			TopTrumpsCLIApplication.main(commandArgs);
		}
	}
}