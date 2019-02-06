package commandline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DownloadStats {

	private int game_id;
	private int numberOfRounds;
	private String winner;
	private int numberOfDraws;
	

	Connection connection = null;

	Statement smt = null;

	String[] queries = { "SELECT COUNT(game_id) FROM games_stats",
			"SELECT COUNT(winner) FROM games_stats WHERE winner='AI'",
			"SELECT COUNT(winner) FROM games_stats WHERE winner='Human'",
			"SELECT ROUND(AVG(number_of_draws),2) FROM games_stats", "SELECT MAX(number_of_rounds) FROM games_stats", };

	ResultSet rs0 = null;
	Number[] query = new Number[queries.length];

	/*
	 * Constructor
	 */
	public DownloadStats() {
		loadJDBC();
	}// End of Constructor

	/*
	 * This method will load the JDBC jar file
	 */
	void loadJDBC() {
		// Try and load JDBC
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			System.out.println("Could not load class " + "org.postgresql.Drive");
			e.printStackTrace();
		} // try-catch exception
		System.out.println("PostgreSQL JDBC Driver found!");
		initializeConnection();
	}// End of method

	/*
	 * This method will initialize the connection to the server
	 */
	void initializeConnection() {
		// trying for a database connection
		try {
			// Connect to the server with username and passwd
//			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
//					"teamproject");

			// change to this when we running the game from the labs
			connection = DriverManager.getConnection("jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/", "m_18_2416090c",
					"2416090c");
			System.out.println("Connection Established.");
			getTableStats();
		} catch (SQLException e) {
			System.out.println("Oups! Something went wrong! Fail to connect!");
			e.printStackTrace();
		} // End of try-catch
	}// End of method

	/*
	 * This method will find and get the table from the DB
	 */
	private void getTableStats() {
		try {
			smt = connection.createStatement();

			for (int i = 0; i < queries.length; i++) {
				rs0 = smt.executeQuery(queries[i]);
				rs0.next();
				query[i] = ((Number) rs0.getObject(1)).intValue();
			}

		} catch (SQLException e) {
			System.out.println("Oups. That's embarrassing. Something went wrong...");
			e.printStackTrace();
		}

		showTableStats();
	}// End of method

	/*
	 * This method will show the table with the statistics of the player
	 */
	private void showTableStats() {
		int i = 0;
		String leftAlignFormat = "| %-24s | %-12s | %-19s | %-19s | %-36s |%n";

		System.out.format(
				"+--------------------------+--------------+---------------------+---------------------+--------------------------------------+%n");
		System.out.format(
				"|   Games Played Overall   | AI Times Won |   Human Times Won   | AVG Number of Draws | Highest Amount of Rounds in One Game |%n");
		System.out.format(
				"+--------------------------+--------------+---------------------+---------------------+--------------------------------------+%n");

		System.out.format(leftAlignFormat, query[0], query[1], query[2], query[3], query[4]);
		System.out.format(
				"+--------------------------+--------------+---------------------+---------------------+--------------------------------------+%n");
	}// End of method

}// End of class