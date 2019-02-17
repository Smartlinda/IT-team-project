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
import javax.ws.rs.PathParam;
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
 * 
 * 
 * 
 */
public class TopTrumpsRESTAPI {

	private Model model = new Model();
	private DownloadStats db = new DownloadStats();
	private Controller ctrl = new Controller(model);
	private HumanUser player;
	private AIUser[] AIUserArray;
	private AIUser jeff = new AIUser();  //

	private int roundNumber = 0;  // i think this is all we need.
	private int numberOfDraws = 0;
	private int drawStackSize = 0; // HOW MANY CARDS LEFT IN COMMON PILE
	private int cardsLeft; // HOW MANY CARDS ARE LEFT IN EACH DECK
	private int AIPlayers = 0;
	private int roundWinner = -1; // ACTIVE PLAYER
	private int currentCategory = -1; // WHAT IS SELECTED

	private int gameWinner = -1;

	private String Number;
	// private String Index;

	private String[] playerStates = { "active", "notActive" };
	private String[] gameStates = { "prestart", "gameStarted", "choosingCategory", "showResults", "roundEndsWithWinner",
			"roundEndsInDraw", "endGame", "gameQuit" };
	private String[] current = { playerStates[0], gameStates[0] };
	/**
	 * A Jackson Object writer. It allows us to turn Java objects into JSON strings
	 * easily.
	 * @throws IOException 
	 */
	
	

//	
	
	
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

	public TopTrumpsRESTAPI() {
		// TODO Auto-generated constructor stub
	}

	@GET //don't touch, it works
	@Path("statistics/download_stats")
	public Number[] downloadStats() {
		Number[] gotStats = new Number[db.getQuery().length];
		gotStats = db.getQuery();
		return gotStats;
	}

	@GET // reset everything when /game is opened
	@Path("game/preStart")
	public String preStart() {
		roundWinner = -1;
		gameWinner = -1;
		drawStackSize = 0;
		numberOfDraws = 0;
		AIPlayers = 0;
		roundNumber = 0;
		model = new Model();
		ctrl = new Controller(model);
		model.readContent();
		ctrl.shuffling();
		// ArrayList<Card> deck = ctrl.getShuffledStack();
		return "The deck has been shuffled.";
	}
	
	

	@GET // set up the game at the start, return the random first round winner
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

		for (int i = 0; i < ctrl.getUserArray().length; i++) {
			System.out.println(ctrl.getUserArray()[i]);
		}

		current[1] = gameStates[1];

		return Integer.toString(roundWinner);
	}

	

	
	
	
	
	
	
				

	
	
	
	
	@GET	// give the round counter to the website
	@Path("/getRoundCounter")
	
	public String getRoundCounter() throws IOException  {
		//roundNumber++;
		
        int roundNumber = 1;
		String counter = oWriter.writeValueAsString(roundNumber);
		
		return counter;
	}
	
	


	

	@GET // return array with number of cards left for every player
	@Path("game/getCardsLeft")
	public String getCardsLeft() {
		current[1] = gameStates[2];
		int[] howManyLeft = new int[ctrl.getUserArray().length];
		for (int user = 0; user < howManyLeft.length; user++) {
			for (int i = 0; i < ctrl.getActiveUser().size(); i++) {
				if (ctrl.getUserArray()[user].getUserID() == ctrl.getActiveUser().get(i)) {
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

	@GET	// give the top cards to the website, according to the 'number' parameter in the url
	@Path("game/getTopCards")
	public String getTopCards(@QueryParam("Number") String Number) { // NEED TO CHECK FOR OTHER PLAYERS ALSO
		this.Number = Number;
		String card = "lost";
		Card[] topCards = new Card[ctrl.getActiveUser().size()];
		for (int i = 0; i < ctrl.getActiveUser().size(); i++) {
			topCards[i] = ctrl.getUserArray()[ctrl.getActiveUser().get(i)].getPersonalDeck().get(0);
		}

		try {
			card = oWriter.writeValueAsString(topCards);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println(card);
		return card;
	}

	@GET	// give the number of cards in the draw pile to the website
	@Path("game/getDrawPile")
	public String getDrawPile() {
		String contents = Integer.toString(ctrl.getDrawStack().size());
		return contents;
	}


	@GET // NOT CORRECT 
	@Path("/humanSelectCategory")
	//public String humanSelectCategory(@QueryParam("Index") String Index) throws IOException {
		public String humanSelectCategory() throws IOException {
//		// this.Index = Index;  
//		currentCategory = Integer.parseInt(Index);
//      
//		ctrl.getUserArray()[roundWinner].setSelectedCategory(currentCategory);
//
//		return "You selected " + model.getHeader(currentCategory);
//		
//		
//		
//		System.out.println("It is your turn to select a category, the categories are: ");
//		for (int i = 0; i < controller.getTopCard(0).getAttributeValues().length; i++) {
//			System.out.println("   " + (i + 1) + ": " + model.getHeader(i));
//		}
		String choose = "Please choose a category";
		String testchose = oWriter.writeValueAsString(choose );
		
		return testchose;
		
		
	}
	
	
	
	
	

	@GET	// if its an ai's turn, ai selects a category.
	@Path("game/AISelectCategory")
	public String AISelectCategory() {
		ctrl.getUserArray()[roundWinner].selectCategory(ctrl.getUserArray()[roundWinner].getPersonalDeck().get(0));
		currentCategory = ctrl.getUserArray()[roundWinner].getSelectedCategory();

		return "AI selected " + model.getHeader(currentCategory);
	}

	@GET	// distribute the cards between all the players (dealing out), returns status strings
	@Path("game/distributeCards")
	public String distributeCards() {
		if (ctrl.checkRoundWinner(roundWinner) != -1) {
			roundWinner = ctrl.checkRoundWinner(roundWinner); // get the new round winner
			return "Cards were given to the winner, player " + roundWinner;
		}
		ctrl.changeOwnership(roundWinner);
		ctrl.excludeLoser();
		return "The round was a draw, cards go to the common pile.";
	}

	@GET 	// send the game winner to the website
	@Path("game/checkForWinner")
	public String checkForWinner() throws IOException {
		if (ctrl.getActiveUser().size() == 1) {
			gameWinner = ctrl.getActiveUser().get(0);
		} else {
			gameWinner = -1;
		}
		
		String winner = oWriter.writeValueAsString(gameWinner );
		return winner;
	}

	@GET   // sends the state of the player (active or not) to the website
	@Path("game/getPlayerState")
	public String getPlayerState() throws IOException {
		
    String activePlayerHuman = "you! it's your turn";
    int activePlayerAI = 1;
    String active0 = oWriter.writeValueAsString(activePlayerHuman);
    String active1 = oWriter.writeValueAsString(activePlayerAI);
		
		if (current[0].equals("active")) {
			return active0;
		} else {
			return active1;
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
