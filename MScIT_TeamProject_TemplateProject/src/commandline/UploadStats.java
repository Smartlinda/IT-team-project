package commandline;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


// Get variables from the main --------------------------
public class UploadStats {
    
//	private int gamesHumanWonFinal;
//	private int gamesAIWonFinal;
	private int roundCounter;
	private int numberOfDraws;
	private String winner;
	protected int i;
	private Connection connection = null;
	private ResultSet result;


public UploadStats(int gamesHumanWonFinal2, int gamesAIWonFinal2, int roundCounter2, int numberOfDraws2, int i2) {

		if (gamesHumanWonFinal2 == 1) {
			winner = "Human";
		} else if (gamesAIWonFinal2 == 1) {
			winner = "AI";
		}
		//this.gamesHumanWonFinal = gamesHumanWonFinal2;
		//this.gamesAIWonFinal = gamesAIWonFinal2;
		this.roundCounter = roundCounter2;
		this.numberOfDraws = numberOfDraws2;
		this.i=i2;
	
	}

public void test_variables() {
	System.out.println("The variables are:");
	System.out.println(winner);
//	System.out.println(gamesHumanWonFinal);
//	System.out.println(gamesAIWonFinal);
	System.out.println(roundCounter-1);
	System.out.println(numberOfDraws);	
	System.out.println(i);	

}



//----------------------

// Connection to the database



public void updateValuesInDB() throws SQLException {
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
		connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/", "postgres","teamproject");
	} catch (SQLException e) {
		System.out.println("Connection failed!");
		e.printStackTrace();
	} // End of try-catch

	// connection to the database is done
	if (connection != null) {
		try {
			int newroundCounter = roundCounter -1 ;
			
			
			System.out.println("Established connection to database.\n");
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO games_stats " +  "VALUES ('"+ i + "', '"+ newroundCounter + "' , '"+ winner +  "', '"+ numberOfDraws + "')");
			connection.setAutoCommit(false);

			statement.executeBatch();
			//result = statement.executeQuery("SELECT * FROM games_stats ;");
			//System.out.println("ID is: " + result.getString( "gameid"));
			connection.commit();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	} else {
		System.out.println("Failed to establish connection!");
	}
	//
	
	// 
}// 
	
//-------------------------------------------------	
	
	
	

}


