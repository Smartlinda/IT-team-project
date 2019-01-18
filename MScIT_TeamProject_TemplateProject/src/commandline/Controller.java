package commandline;
import java.util.ArrayList;
import java.util.List;

public class Controller {
	protected HumanUser hu;
	protected AIUser ai1;
	protected ArrayList<Card> cardCon; //card content which includes name and values
	protected Card[][] personalDeck;
	
	public Controller() {
		
	}
	
	
	public void distributeCards(int n) {  //n as number of total players
		personalDeck = new Card[numberOfAllCards/n+1][n];
		int k=0;
		for (int j=0;j<numberOfAllCards/n;j++) {
			for (int i=0;i<n;i++) {
				personalDeck[j][i] = cardCon.get(i+n*k);
				k++;
			}
		}
		for (int i=0;i<numberOfAllCards%n;i++) {
			personalDeck[numberOfAllCards/n][i] = cardCon.get(i+n*(numberOfAllCards/n+1));
		}
	}
	
	public changeOwnership(int i) { //i is the winner, the compile deck will regarded as a winner as well
		int winner = checkRoundWinner();
		for(int j=0;j<#totalPlayer-1;j++) {
			personalDeck[personalDeck.size()+j][winner] = personalDeck[]
		}
		for (all i in personal[personalDeck.size()])
	}
	
	public int checkRoundWinner() {
		int max=-10;
		List maxList;
		for (int i=0;i<#allPlayer;i++) {
			if (persoanlDeck[0][i].getributeValues[selectCategory()] > max) {
				max = personalDeck[0][i];
				maxList.add(i);
			}
		}
		return (int)maxList.get(maxList.size()-1);
	}
	
}

