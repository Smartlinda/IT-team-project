package online.dwResources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import online.configuration.TopTrumpsJSONConfiguration;

import commandline.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.APPLICATION_JSON) // This resource returns JSON content
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as input
/**
 * This is a Dropwizard Resource that specifies what to provide when a user
 * requests a particular URL. In this case, the URLs are associated to the
 * different REST API methods that you will need to expose the game commands to
 * the Web page.
 * 
 * Below are provided some sample methods that illustrate how to create REST API
 * methods in Dropwizard. You will need to replace these with methods that allow
 * a TopTrumps game to be controled from a Web page.
 */
public class TopTrumpsRESTAPI {

	private Model model = new Model();
	private DownloadStats db = new DownloadStats();
	private Controller ctrl = new Controller(model);
	private HumanUser player;
	private AIUser[] AIUserArray;
	private AIUser jeff = new AIUser();
	
	private int roundNumber = 0;  
	private int drawStackSize = 0;  // HOW MANY CARDS LEFT IN COMMON PILE
	private int cardsLeft;  // HOW MANY CARDS ARE LEFT IN EACH DECK
	private int AIPlayers = 0;
	private int roundWinner = -1;  // ACTIVE PLAYER
	private int currentCategory = -1; // WHAT IS SELECTED
	
	private int gameWinner = -1;
	
	private String Number;
	//private String Index;

	private String[] playerStates = { "active", "notActive" };
	private String[] gameStates = { "prestart", "gameStarted", "choosingCategory", "showResults", "roundEndsWithWinner",
			"roundEndsInDraw", "endGame", "gameQuit" };
	private String[] current = { playerStates[0], gameStates[0]};
	/**
	 * A Jackson Object writer. It allows us to turn Java objects into JSON strings
	 * easily.
	 */
	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();

	/**
	 * Contructor method for the REST API. This is called first. It provides a
	 * TopTrumpsJSONConfiguration from which you can get the location of the deck
	 * file and the number of AI players.
	 * 
	 * @param conf
	 */
	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) {
		conf.getDeckFile();
		conf.getNumAIPlayers();
	}
	

	@GET
	@Path("statistics/download_stats")
	public Number[] downloadStats() {
		Number[] gotStats = new Number[db.getQuery().length];
		gotStats = db.getQuery();
		return gotStats;
	}

	@GET
	@Path("game/preStart")
	public String preStart() {
		roundNumber = 0;
		model = new Model();
		ctrl = new Controller(model);
		model.readContent();
		ctrl.shuffling();
		ArrayList<Card> deck = ctrl.getShuffledStack();
		return "The deck has been shuffled.";
	}

	@GET
	@Path("game/setupGame")
	public String setupGame(@QueryParam("Number") String Number) {
		this.Number = Number;
		
		current[1] = gameStates[0];
		jeff.setNextID(1);

		player = new HumanUser("You");
		AIPlayers = Integer.parseInt(Number);
		AIUserArray = new AIUser[AIPlayers];

		for (int AI = 0; AI < AIPlayers; AI++) {
			AIUser ai = new AIUser();
			AIUserArray[AI] = ai;
		}

		for (int user = 0; user < AIPlayers + 1; user++) {
			ctrl.getActiveUser().add(user);
		}

		ctrl.setUserArray(new GenericUser[AIPlayers + 1]);
		ctrl.addHuman(player);
		ctrl.addAI(AIUserArray);
		ctrl.distributeCards();

		Random rand = new Random();
		roundWinner = rand.nextInt(ctrl.getUserArray().length);

		for(int i=0; i < ctrl.getUserArray().length; i++) {
			System.out.println(ctrl.getUserArray()[i]);
		}
		
		current[1] = gameStates[1];

		return "The game has been started.";
	}
	
	@GET
	@Path("game/getCardsLeft")
	public String getCardsLeft() {
		int[] howManyLeft = new int[ctrl.getUserArray().length];
		for(int user = 0; user < howManyLeft.length; user++ ) {
			for(int i = 0; i < ctrl.getActiveUser().size(); i++ ) {
				if(ctrl.getUserArray()[user].getUserID() == ctrl.getActiveUser().get(i)) {
					howManyLeft[user] = ctrl.getUserArray()[user].getPersonalDeck().size();
					break;
				} else {
					howManyLeft[user] = 0;
				}
			}
		}
		String returned = "hi";
		try {
			returned = oWriter.writeValueAsString(howManyLeft);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return returned;
	}

	@GET
	@Path("game/getTopCard")
	public String getTopCard() {  // NEED TO CHECK FOR OTHER PLAYERS ALSO
		String card = "You are out of the game...";
		if (current[0].equals(playerStates[0])) {
			try {
				card = oWriter.writeValueAsString(ctrl.getUserArray()[0].getPersonalDeck().get(0));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return card;
	}

	@GET
	@Path("game/getDrawPile")
	public String getDrawPile() {
		String contents = Integer.toString(ctrl.getDrawStack().size());
		return contents;
	}
	
	@GET
	@Path("game/getRoundCounter")
	public String getRoundCounter() {
	 roundNumber++;
	 String counter = Integer.toString(roundNumber);
	 return counter;
	}
	
	@GET
	@Path("game/selectCategory")
	public String selectCategory(@QueryParam("Index") String Index) {
		//this.Index = Index;
		currentCategory = Integer.parseInt(Index);
		
		ctrl.getUserArray()[roundWinner].setSelectedCategory(currentCategory);
		
		if (roundWinner == 0) {
			return "You selected " + model.getHeader(currentCategory);
		} else {
			return "Player " + ctrl.getUserArray()[roundWinner].getUserID() + " selected " + model.getHeader(currentCategory);
		}
	}
}

//	@GET
//	@Path("/helloJSONList")
//	/**
//	 * Here is an example of a simple REST get request that returns a String.
//	 * We also illustrate here how we can convert Java objects to JSON strings.
//	 * @return - List of words as JSON
//	 * @throws IOException
//	 */
//	public String helloJSONList() throws IOException {
//		
//		List<String> listOfWords = new ArrayList<String>();
//		listOfWords.add("Hello");
//		listOfWords.add("World!");
//		
//		// We can turn arbatory Java objects directly into JSON strings using
//		// Jackson seralization, assuming that the Java objects are not too complex.
//		String listAsJSONString = oWriter.writeValueAsString(listOfWords);
//		
//		return listAsJSONString;
//	}

//	@GET
//	@Path("/helloWord")
//	/**
//	 * Here is an example of how to read parameters provided in an HTML Get request.
//	 * @param Word - A word
//	 * @return - A String
//	 * @throws IOException
//	 */
//	public String helloWord(@QueryParam("Word") String Word) throws IOException {
//		return "Hello "+Word;
//	}
