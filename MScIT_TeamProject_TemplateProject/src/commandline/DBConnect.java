package commandline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {

	// build constructor
	public DBConnect() {

	}

	private void showPlayerStatus() {
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

		// trying for a database connection
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
					"teamproject");
		} catch (SQLException e) {
			System.out.println("Oups! Something went wrong! Fail to connect!");
			e.printStackTrace();
			return;
		}

		// if connection has been established then proceed with the actions
		if (connection != null) {
			try {
				System.out.println("Established connection to database.\n");
				Statement smt = connection.createStatement();
				// query to display all records from table postgre
				String q = "SELECT * FROM games_stats";
				ResultSet rs = smt.executeQuery(q);

				if (rs.next()) {
					String leftAlignFormat = "| %-12s | %-9s | %-12s | %-13s | %-12s | %-13s | %-12s |%n";

					System.out.format(
							"+--------------+-----------+--------------+---------------+--------------+---------------+--------------+%n");
					System.out.format(
							"| Games Played | Games won | Games AI Won | Average Draws | Draws Record | Rounds Record | Total Rounds |%n");
					System.out.format(
							"+--------------+-----------+--------------+---------------+--------------+---------------+--------------+%n");

					do {
						System.out.format(leftAlignFormat, rs.getString(1), rs.getString(2), rs.getString(3),
								rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
					} while (rs.next());
					System.out.format(
							"+--------------+-----------+--------------+---------------+--------------+---------------+--------------+%n");
				} else {
					System.out.println("Record Not Found...");
				}
				connection.close();

			} catch (SQLException e) {
				e.printStackTrace();
			} // try-catch exception
		} else {
			System.out.println("Oups! Something went wrong! Is your machine connected to the Internet?");
		} // if-else
	}// end of method
}// end of class