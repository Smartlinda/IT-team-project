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
	//----------------------------------------------
//	int game_id = db.getGameID();
//	int numberOfRounds = db.getNumberOfRounds();
//	String winner = db.getWinner();
//	int numberOfDraws = db.getNumberOfDraws();#
	//---------------------------------------------

	// Also HERE add the variables gained from each match
	int gamePlayerWon_GameData=0;
	int gameAIWon_GameData=0;
	int totalRoundsPlayed_GameData=0;
	int totalDraws_GameData=0;
	
	// for method
	private int a=0;
	private int b=0;
	private int c=0;
	private int d=0;
	
	// This variables are when they are the final values to be uploaded
//	int gamesHumanWonFinal;
//	int gamesPlayedFinal;
//	int gamesWonFinal;
//	int gamesAIWonFinal;
//	double averageDrawsFinal;
//	int drawsRecordFinal;
//	int roundsRecordFinal;
//	int totalRoundsFinal;
//	int numberOfDrawsFinal;

	
	String[] queries = {
			"SELECT COUNT(game_id) FROM games_stats", 
			"SELECT COUNT(winner) FROM games_stats WHERE winner='AI'",
			"SELECT COUNT(winner) FROM games_stats WHERE winner='Human'",
			"SELECT ROUND(AVG(number_of_draws),2) FROM games_stats", 
			"SELECT MAX(number_of_rounds) FROM games_stats", 
			};
	
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
				//asd asd
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} // try-catch exception
		} else {
			System.out.println("Failed to establish connection!");
		} // if-else
	}// End of method

	void getValuesFromMain(int a, int b, int c, int d) {
		this.a = gamePlayerWon_GameData;
		this.b = gameAIWon_GameData;
		this.c = totalRoundsPlayed_GameData;
		this.d = totalDraws_GameData;
	}
	
//	private void numberOfGamesPlayed(int dbValue, int gameValue) {
//		gamesPlayedFinal = dbValue + gameValue;
//	}// End of method
//
//	private void numberOfTimesHumanWon(int dbValue, int gameValue) {
//		gamesHumanWonFinal = dbValue + gameValue;
//	}// End of method
//
//	private void numberOfTimesAIWon(int dbValue, int gameValue) {
//		gamesAIWonFinal = dbValue + gameValue;
//	}// End of method
//
//	private void averageNumberOfDraws(double dbValue, double gameValue) {
//		averageDrawsFinal = (dbValue + gameValue) / gamesPlayedFinal;
//	}// End of method
//
//	private void numberOfDrawsInAGame(int dbValue, int gameValue) {
//		numberOfDrawsFinal = dbValue + gameValue;
//	}// End of method
//
//	private void largestNumberOfRoundsPlayed(int dbValue, int gameValue) {
//		if (dbValue <= gameValue) {
//			drawsRecordFinal = gameValue;
//		}
//	}// End of method
//
//	private void numberOfTotalRounds(int dbValue, int gameValue) {
//		roundsRecordFinal = dbValue + gameValue;
//	}// End of method

}// End of class
