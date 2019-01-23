package commandline;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Statistics {
	DBConnect db = new DBConnect();

	int gamesPlayed = db.getGamesPlayed();
	int gamesWon = db.getGamesWon();
	int gamesAIWon = db.getGamesAIWon();
	double averageDraws = db.getAverageDraws();
	int drawsRecord = db.getDrawsRecord();
	int roundsRecord = db.getRoundsRecord();
	int totalRounds = db.getTotalRounds();

	// Also add the variables gained from each match

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
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
					"teamproject");
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

	private void numberOfGamesPlayed(int dbValue, int gameValue) {
		dbValue = dbValue + gameValue;
	}

	private void numberOfTimesAIWon(int dbValue, int gameValue) {
		dbValue = dbValue + gameValue;
	}

	private void numberOfTimesHumanWon(int dbValue, int gameValue) {
		dbValue = dbValue + gameValue;
	}

	private void averageNumberOfDraws(double dbValue, double gameValue) {
		dbValue = (dbValue + gameValue) / 2;
	}

	private void largestNumberOfRoundsPlayed(int dbValue, int gameValue) {
		if (dbValue <= gameValue) {
			dbValue = gameValue;
		}
	}

	private void numberOfDrawsInAGame(int dbValue, int gameValue) {
//		dbValue = dbValue + gameValue;
	}

	private void numberOfRoundsPlayedInAGame(int dbValue, int gameValue) {
//		dbValue = dbValue + gameValue;
	}
}
