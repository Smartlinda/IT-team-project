package commandline;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UploadStats {
	DownloadStats db = new DownloadStats();
	Connection connection = null;

	// Get the values already on the database
	int gamesPlayed = db.getGamesPlayed();
	int gamesWon = db.getGamesWon();
	int gamesAIWon = db.getGamesAIWon();
	double averageDraws = db.getAverageDraws();
	int drawsRecord = db.getDrawsRecord();
	int roundsRecord = db.getRoundsRecord();
	int totalRounds = db.getTotalRounds();

	// Also HERE add the variables gained from each match

	// This variables are when they are the final values to be uploaded
	int gamesHumanWonFinal;
	int gamesPlayedFinal;
	int gamesWonFinal;
	int gamesAIWonFinal;
	double averageDrawsFinal;
	int drawsRecordFinal;
	int roundsRecordFinal;
	int totalRoundsFinal;
	int numberOfDrawsFinal;
	
	// Create an String array list and add the queries inside
	String query1 = "UPDATE games_stats SET games_played = " + gamesPlayedFinal;
	String query2 = "UPDATE games_stats SET games_won = " + gamesWonFinal;
	String query3 = "UPDATE games_stats SET games_ai_won = " + gamesAIWonFinal;
	String query4 = "UPDATE games_stats SET avg_draws = " + averageDrawsFinal;
	String query5 = "UPDATE games_stats SET draws_record = " + drawsRecordFinal;
	String query6 = "UPDATE games_stats SET rounds_record = " + roundsRecordFinal;
	String query7 = "UPDATE games_stats SET total_rounds = " + totalRoundsFinal;

	String[] queries = { query1, query2, query3, query4, query5, query6, query7 };

	public UploadStats() {
		/*
		 * Here we add the method to start!
		 */
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
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
					"teamproject");
		} catch (SQLException e) {
			System.out.println("Connection failed!");
			e.printStackTrace();
		} // End of try-catch

		// connection to the database is done
		if (connection != null) {
			try {
				System.out.println("Established connection to database.\n");
				Statement statement = connection.createStatement();
				connection.setAutoCommit(false);
				for (int i = 0; i < queries.length; i++) {
					statement.addBatch(queries[i]);
				}
				statement.executeBatch();
				connection.commit();
				// do not forget to close the connection to the database
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} // try-catch exception
		} else {
			System.out.println("Failed to establish connection!");
		} // if-else
	}// End of method

	private void numberOfGamesPlayed(int dbValue, int gameValue) {
		gamesPlayedFinal = dbValue + gameValue;
	}// End of method

	private void numberOfTimesHumanWon(int dbValue, int gameValue) {
		gamesHumanWonFinal = dbValue + gameValue;
	}// End of method

	private void numberOfTimesAIWon(int dbValue, int gameValue) {
		gamesAIWonFinal = dbValue + gameValue;
	}// End of method

	private void averageNumberOfDraws(double dbValue, double gameValue) {
		averageDrawsFinal = (dbValue + gameValue) / gamesPlayedFinal;
	}// End of method

	private void numberOfDrawsInAGame(int dbValue, int gameValue) {
		numberOfDrawsFinal = dbValue + gameValue;
	}// End of method

	private void largestNumberOfRoundsPlayed(int dbValue, int gameValue) {
		if (dbValue <= gameValue) {
			drawsRecordFinal = gameValue;
		}
	}// End of method

	private void numberOfTotalRounds(int dbValue, int gameValue) {
		roundsRecordFinal = dbValue + gameValue;
	}// End of method
}// End of class
