package commandline;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * This class creates a connection to the database and adds the statistics of the current game
 * as a new row, with a new gameID (i.e. if there are 15 games in the database currently, the new ID
 * will be 16). 
 * ****THE LOGIN DETAILS MUST BE CHANGED FOR YOUR SPECIFIC DATABASE****
 */

public class UploadStats {

	// Variables.
	private int current;     // Current gameID.
	private int roundCounter;
	private int numberOfDraws;
	private String winner;
	
	private Connection connection = null;
	private ResultSet gameIDRetrieved = null;

	private String gameIDQuery = "SELECT COUNT(*) FROM games_stats";

	// Constructor.
	public UploadStats(boolean didHumanWin, int roundCounter, int numberOfDraws) {

		if (didHumanWin == true) {
			winner = "Human";
		} else if (didHumanWin == false) {
			winner = "AI";
		}

		this.roundCounter = roundCounter - 1;
		this.numberOfDraws = numberOfDraws;
		loadJDBC();
	}

	// Methods.
	// Load the database driver.
	public void loadJDBC() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			System.out.println("Could not load class " + "org.postgresql.Drive");
			e.printStackTrace();
		}
		System.out.println("PostgreSQL JDBC Driver found!");
		initializeConnection();
	}

	// Proceed with a database connection.
	public void initializeConnection() {
		try {
			// Change connection details according to desired configuration.
//			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
//					"Cttbsr48P");
			connection = DriverManager.getConnection("jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/", "m_18_2416090c",
					"2416090c");

			System.out.println("Connection Established.");
		} catch (SQLException e) {
			System.out.println("Connection failed!");
			e.printStackTrace();
		}
		updateTableStats();
	}

	// Update the values in the database.
	private void updateTableStats() {
		if (connection != null) {
			try {
				// Get the current gameID in order to know what the new one will need to be.
				Statement getGameID = connection.createStatement();
				gameIDRetrieved = getGameID.executeQuery(gameIDQuery);
				gameIDRetrieved.next();
				current = gameIDRetrieved.getInt(1);

				// Execute insertion query.
				Statement statement = connection.createStatement();
				statement.executeUpdate("INSERT INTO games_stats " + "VALUES ('" + (current + 1) + "', '" + roundCounter
						+ "' , '" + winner + "', '" + numberOfDraws + "')");
				connection.close();
			} catch (SQLException e) {
				System.out.println("Oups. That's embarrassing. Something went wrong...");
				e.printStackTrace();
			}
		} else {
			System.out.println("Failed to establish connection!");
		}
	}
}
