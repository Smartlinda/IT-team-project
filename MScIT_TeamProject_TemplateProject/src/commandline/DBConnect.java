package commandline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {

	int gamesPlayed;
	int gamesWon;
	int gamesAIWon;
	double averageDraws;
	int drawsRecord;
	int roundsRecord;
	int totalRounds;

	// proceed with a database connection
	Connection connection = null;
	ResultSet rs = null;
	Statement smt = null;

	/*
	 * Constructor
	 */
	public DBConnect() {
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
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
					"teamproject");
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
		} catch (SQLException e) {
			System.out.println("Oups. That's embarasing. Something went wrong...");
			e.printStackTrace();
		} // End of try-catch

		// query to get all records from table postgres
		String q = "SELECT * FROM games_stats";

		try {
			rs = smt.executeQuery(q);
		} catch (SQLException e) {
			System.out.println("Oups. That's embarasing. Something went wrong...");
			e.printStackTrace();
		} // End of try-catch
		showTableStats();
	}// End of method

	/*
	 * This method will show the table with the statistics of the player
	 */
	private void showTableStats() {
		try {
			if (rs.next()) {
				String leftAlignFormat = "| %-12s | %-9s | %-12s | %-13s | %-12s | %-13s | %-12s |%n";

				System.out.format(
						"+--------------+-----------+--------------+---------------+--------------+---------------+--------------+%n");
				System.out.format(
						"| Games Played | Games won | Games AI Won | Average Draws | Draws Record | Rounds Record | Total Rounds |%n");
				System.out.format(
						"+--------------+-----------+--------------+---------------+--------------+---------------+--------------+%n");

				// Add the values within the table and also add the values to a global value
				do {
					gamesPlayed = rs.getInt(1);
					gamesWon = rs.getInt(2);
					gamesAIWon = rs.getInt(3);
					averageDraws = rs.getDouble(4);
					drawsRecord = rs.getInt(5);
					roundsRecord = rs.getInt(6);
					totalRounds = rs.getInt(7);

					System.out.format(leftAlignFormat, gamesPlayed, gamesWon, gamesAIWon, averageDraws, drawsRecord,
							roundsRecord, totalRounds);
				} while (rs.next());
				System.out.format(
						"+--------------+-----------+--------------+---------------+--------------+---------------+--------------+%n");
			} else {
				System.out.println("Record Not Found...");
			} // End of if else
		} catch (SQLException e) {
			System.out.println("Oups! Something went wrong!");
			e.printStackTrace();
		} // End of try-catch
	}// End of method

	/*
	 * GETTERS AND SETTERS - START
	 */
	public int getGamesPlayed() {
		return gamesPlayed;
	}

	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}

	public int getGamesWon() {
		return gamesWon;
	}

	public void setGamesWon(int gamesWon) {
		this.gamesWon = gamesWon;
	}

	public int getGamesAIWon() {
		return gamesAIWon;
	}

	public void setGamesAIWon(int gamesAIWon) {
		this.gamesAIWon = gamesAIWon;
	}

	public double getAverageDraws() {
		return averageDraws;
	}

	public void setAverageDraws(double averageDraws) {
		this.averageDraws = averageDraws;
	}

	public int getDrawsRecord() {
		return drawsRecord;
	}

	public void setDrawsRecord(int drawsRecord) {
		this.drawsRecord = drawsRecord;
	}

	public int getRoundsRecord() {
		return roundsRecord;
	}

	public void setRoundsRecord(int roundsRecord) {
		this.roundsRecord = roundsRecord;
	}

	public int getTotalRounds() {
		return totalRounds;
	}

	public void setTotalRounds(int totalRounds) {
		this.totalRounds = totalRounds;
	}
	/*
	 * GETTERS AND SETTERS - END
	 */
}// end of class