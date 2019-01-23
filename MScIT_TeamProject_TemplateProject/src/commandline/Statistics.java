package commandline;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Statistics {

	int gamesPlayed;
	
	DBConnect db = new DBConnect();

	public Statistics() {

	}// End of constructor

	private void updateValuesInDB() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			System.out.println("Could not load class " + "org.postgresql.Drive");
			e.printStackTrace();
		} // try-catch exception
		// the driver is loaded...
		System.out.println("PostgreSQL JDBC Driver found!");
		// proceed with a database connection
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "teamproject");
		} catch (SQLException e) {
			System.out.println("Connection failed!");
			e.printStackTrace();
		}
		
		// connection to the database is done
		if (connection != null) {
		try {
			System.out.println("Established connection to database.\n");
			Statement statement = connection.createStatement();
			String sql = "UPDATE games_stats SET games_played = " + gamesPlayed;
			ResultSet rs = statement.executeQuery(sql);
			
			// do not forget to close the connection to the database
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} // try-catch exception
		} else {
			System.out.println("Failed to establish connection!");
		} // if-else

	}// End of method

	private void numberOfGamesPlayed(int a, int b) {
		int totalGamesPlayed = 0;
		/*
		 * ADD CODE HERE
		 */
		totalGamesPlayed = gamesPlayed;
	}

	private int numberOfTimesAIWon() {
		int totalTimesAIWon = 0;
		/*
		 * ADD CODE HERE
		 */
		return totalTimesAIWon;
	}

	private int numberOfTimesHumanWon() {
		int totalPlayerWins = 0;
		/*
		 * ADD CODE HERE
		 */
		return totalPlayerWins;
	}

	private double averageNumberOfDraws() {
		double avgDraws = 0.0;
		/*
		 * ADD CODE HERE
		 */
		return Math.round(avgDraws);
	}

	private int largestNumberOfRoundsPlayed() {
		int maxRoundsPlayed = 0;
		/*
		 * ADD CODE HERE
		 */
		return maxRoundsPlayed;
	}

	private int numberOfDrawsInAGame() {
		int drawsInGame = 0;
		/*
		 * ADD CODE HERE
		 */
		return drawsInGame;
	}

	private int numberOfRoundsPlayedInAGame() {
		int maxRoundsInGame = 0;
		/*
		 * ADD CODE HERE
		 */
		return maxRoundsInGame;
	}
}
