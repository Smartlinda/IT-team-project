package commandline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {

	String gamesPlayed;
	String gamesWon;
	String gamesAIWon;
	String averageDraws;
	String drawsRecord;
	String roundsRecord;
	String totalRounds;

	// proceed with a database connection
	Connection connection = null;
	ResultSet rs = null;
	Statement smt = null;

	// build constructor
	public DBConnect() {
		loadJDBC();
	}

	void initializeConnection() {
		// trying for a database connection
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
					"teamproject");
			System.out.println("Connection Established.");
			showStats();
		} catch (SQLException e) {
			System.out.println("Oups! Something went wrong! Fail to connect!");
			e.printStackTrace();
		}

	}// end of method

	void loadJDBC() {
		// TODO Auto-generated method stub
		// Load JDBC
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			System.out.println("Could not load class " + "org.postgresql.Drive");
			e.printStackTrace();
		} // try-catch exception
		System.out.println("PostgreSQL JDBC Driver found!");
		initializeConnection();
	}

	private void showStats() {

		try {
			smt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// query to display all records from table postgres
		String q = "SELECT * FROM games_stats";

		try {
			rs = smt.executeQuery(q);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// here if we uncomment this one we will get the table with the stats
//		showTheStatsTable();

	}
	
	/*
	 * This method will show the table with the statistics of the player
	 */
	private void showTheStatsTable() {
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
					gamesPlayed = rs.getString(1);
					gamesWon = rs.getString(2);
					gamesAIWon = rs.getString(3);
					averageDraws = rs.getString(4);
					drawsRecord = rs.getString(5);
					roundsRecord = rs.getString(6);
					totalRounds = rs.getString(7);

					System.out.format(leftAlignFormat, gamesPlayed, gamesWon, gamesAIWon, averageDraws, drawsRecord,
							roundsRecord, totalRounds);
				} while (rs.next());
				System.out.format(
						"+--------------+-----------+--------------+---------------+--------------+---------------+--------------+%n");
			} else {
				System.out.println("Record Not Found...");
			}
		} catch (SQLException e) {
			System.out.println("Oups! Something went wrong!");
			e.printStackTrace();
		}

	}

	/*
	 * GETTERS AND SETTERS - START
	 */

	public String getGamesPlayed() {
		return gamesPlayed;
	}

	public void setGamesPlayed(String gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}

	public String getGamesWon() {
		return gamesWon;
	}

	public void setGamesWon(String gamesWon) {
		this.gamesWon = gamesWon;
	}

	public String getGamesAIWon() {
		return gamesAIWon;
	}

	public void setGamesAIWon(String gamesAIWon) {
		this.gamesAIWon = gamesAIWon;
	}

	public String getAverageDraws() {
		return averageDraws;
	}
ссс.ссссссссс
	public void setAverageDraws(String с
	}

	public String getDrawsRecord() {
		return drawsRecord;
	}

	public void setDrawsRecord(String drawsRecord) {
		this.drawsRecord = drawsRecord;
	}

	public String getRoundsRecord() {
		return roundsRecord;
	}

	public void setRoundsRecord(String roundsRecord) {
		this.roundsRecord = roundsRecord;
	}

	public String getTotalRounds() {
		return totalRounds;
	}

	public void setTotalRounds(String totalRounds) {
		this.totalRounds = totalRounds;
	}

	/*
	 * GETTERS AND SETTERS - END
	 */
}// end of class