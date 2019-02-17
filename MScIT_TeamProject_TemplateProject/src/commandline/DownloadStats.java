package commandline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * This class creates a connection to the database and retrieves all the required statistics from it,
 * then displays the numbers in a pretty table. 
 * ****THE LOGIN IS FOR m_18_2416090c SERVER, IT CONNECTS TO THAT ONE SPECIFICALLY
 */

public class DownloadStats {

	// Variables.

	// Variables.
	private Connection connection = null;
	private Statement smt = null;
	private ResultSet rs = null;

	private String[] queries = { "SELECT COUNT(game_id) FROM games_stats",
			"SELECT COUNT(winner) FROM games_stats WHERE winner='AI'",
			"SELECT COUNT(winner) FROM games_stats WHERE winner='Human'",
			"SELECT ROUND(AVG(number_of_draws),2) FROM games_stats", "SELECT MAX(number_of_rounds) FROM games_stats", };

	private Number[] results = new Number[queries.length]; // An array to store results gotten from the ResultSet

	// Constructor.
	public DownloadStats() {
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


	// Start a connection.
	private void initializeConnection() {
		try {

			// Change connection details according to desired configuration.
//			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
//					"Cttbsr48P");
			connection = DriverManager.getConnection("jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/", "m_18_2416090c",
					"2416090c");

			System.out.println("Connection Established.");
			getTableStats();
		} catch (SQLException e) {
			System.out.println("Oups! Something went wrong! Fail to connect!");
			e.printStackTrace();
		}
	}

	// Retrieve statistics from the database.
	private void getTableStats() {
		if (connection != null) {
			try {
				smt = connection.createStatement();
				for (int i = 0; i < queries.length; i++) {
					rs = smt.executeQuery(queries[i]); // Execute all queries in 'query'.
					rs.next();
					results[i] = ((Number) rs.getObject(1)).intValue(); // Store results in 'results[]'.
				}
				connection.close();
			} catch (SQLException e) {
				System.out.println("Oups. That's embarrassing. Something went wrong...");
				e.printStackTrace();
			}
			showTableStats(); // Print statistics.
		}
	}

	// Format and display the required statistics.
		private void showTableStats() {
			String leftAlignFormat = "| %-24s | %-12s | %-19s | %-23s | %-36s |%n";

			System.out.println(
					"+--------------------------+--------------+---------------------+-------------------------+--------------------------------------+");
			System.out.println(
					"|   Games Played Overall   | AI Times Won |   Human Times Won   | Average Number of Draws | Highest Number of Rounds in One Game |");
			System.out.println(
					"+--------------------------+--------------+---------------------+-------------------------+--------------------------------------+");
			System.out.format(leftAlignFormat, results[0], results[1], results[2], results[3], results[4]);
			System.out.println(
					"+--------------------------+--------------+---------------------+-------------------------+--------------------------------------+");
		}
	
	public Number[] getQuery() {
		return results;
	}

	public void setQuery(Number[] query) {
		this.results = query;
	}

}// End of class