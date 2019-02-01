package commandline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DownloadStats {

	int game_id;
	int numberOfRounds;
	String winner;
	int numberOfDraws;

	// proceed with a database connection
	Connection connection = null;
	ResultSet rs0 = null;
	ResultSet rs1 = null;
	ResultSet rs2 = null;
	ResultSet rs3 = null;
	ResultSet rs4 = null;
	
	
	
	
	
	Statement smt = null;

	
	String[] queries = {
			"SELECT COUNT(game_id) FROM games_stats", 
			"SELECT COUNT(winner) FROM games_stats WHERE winner='AI'",
			"SELECT COUNT(winner) FROM games_stats WHERE winner='Human'",
			"SELECT ROUND(AVG(number_of_draws),2) FROM games_stats", 
			"SELECT MAX(number_of_rounds) FROM games_stats", 
			};
	
	
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
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
					"teamproject");

//			connection = DriverManager.getConnection("jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/", "m_18_2416090c",
//					"2416090c");
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
			connection.setAutoCommit(false);
			for (int i = 0; i < queries.length; i++) {
				smt.addBatch(queries[i]);
			}
		} catch (SQLException e) {
			System.out.println("Oups. That's embarrassing. Something went wrong...");
			e.printStackTrace();
		} // End of try-catch

		// query to get all records from table postgres

		try {
				rs0 = smt.executeQuery(queries[0]);
//				rs1 = smt.executeQuery(queries[1]);
				
		} catch (SQLException e) {
			System.out.println("Oups. That's embarrassing. Something went wrong...");
			e.printStackTrace();
		} // End of try-catch
		showTableStats();
	}// End of method

	/*
	 * This method will show the table with the statistics of the player
	 */
	private void showTableStats() {	
				int i = 0;
		try {
			if (rs0.next()) {
				String leftAlignFormat = "| %-11s | %-16s | %-10s | %-15s |%n";

				System.out.format("+-------------+------------------+------------+-----------------+%n");
				System.out.format("|   Game ID   | Number of Rounds |   Winner   | Number of Draws |%n");
				System.out.format("+-------------+------------------+------------+-----------------+%n");

				// Add the values within the table and also add the values to a global value
				do {
					game_id = rs0.getInt(1);
//					numberOfRounds = rs1.getInt(queries[1]);
//					winner = rs2.getString(queries[2]);
//					numberOfDraws = rs3.getInt(queries[3]);

					System.out.format(leftAlignFormat, game_id, numberOfRounds, winner, numberOfDraws);
					System.out.format("+-------------+------------------+------------+-----------------+%n");

				} while (rs0.next() && rs1.next());

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
	public int getGameID() {
		return game_id;
	}

	public void setGameID(int game_id) {
		this.game_id = game_id;
	}

	public int getNumberOfRounds() {
		return numberOfRounds;
	}

	public void setNumberOfRounds(int numberOfRounds) {
		this.numberOfRounds = numberOfRounds;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public int getNumberOfDraws() {
		return numberOfDraws;
	}

	public void setNumberOfDraws(int numberOfDraws) {
		this.numberOfDraws = numberOfDraws;
	}

	/*
	 * GETTERS AND SETTERS - END
	 */
}// End of class