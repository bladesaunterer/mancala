package kalah.util;

import java.util.List;

import com.qualitascorpus.testsupport.IO;
import kalah.KalahBoardInfoRetriever;
import kalah.Player;
import kalah.components.SeedContainer;

public class KalahIO {
	public static final String QUIT_STRING = "q";
	private static final String USER_PROMPT = "Player P%d's turn - Specify house number or '" + QUIT_STRING + "' to quit: ";
	private static final String END_GAME_MESSAGE = "Game over";
	private static final String TRY_AGAIN_MESSAGE = "House is empty. Move again.";
	
	
	public static void renderBoard(KalahBoardInfoRetriever board, IO io) {
		List<SeedContainer> playerOne = board.getSeedContainers(Player.ONE);
		List<SeedContainer> playerTwo = board.getSeedContainers(Player.TWO);
		
		int numOfHouses = board.getNumberOfHouses();
		String outerBoundary = "+----"+ new String(new char[numOfHouses]).replace("\0", "+-------") + "+----+";
		String playerDivider = "|    |"+ new String(new char[numOfHouses-1]).replace("\0", "-------+") + "-------|    |";
		
		String upperBoard = "| P2 ";
		String lowerBoard = String.format("| %2d ", playerTwo.get(playerTwo.size()-1).getNumOfSeeds());
		String scoreFormat = "|%2d[%2d] ";
		
		for(int i=0;i<numOfHouses;i++){
			upperBoard +=String.format(scoreFormat,numOfHouses-i, playerTwo.get(numOfHouses-i-1).getNumOfSeeds());
			lowerBoard +=String.format(scoreFormat,i+1, playerOne.get(i).getNumOfSeeds());
		}
		
		upperBoard += String.format("| %2d |", playerOne.get(playerOne.size()-1).getNumOfSeeds());
		lowerBoard += "| P1 |";
	
		io.println(outerBoundary);
		io.println(upperBoard);
		io.println(playerDivider);
		io.println(lowerBoard);
		io.println(outerBoundary);
	}
	
	public static String getPlayerInput(Player player, IO io){
		return io.readFromKeyboard(String.format(USER_PROMPT, player.numericValue()));
	}

	public static void endGame(KalahBoardInfoRetriever board, IO io) {
		io.println(END_GAME_MESSAGE);
		renderBoard(board, io);
	}
	
	
	public static boolean userEndsGame(String userInput){
		return userInput.equals(QUIT_STRING);	
	}
	
	public static void pleaseTryAgain(IO io) {
		io.println(TRY_AGAIN_MESSAGE);
	}
	
	public static void displayResults(IO io, int playerOneScore, int playerTwoScore) {
		io.println(String.format("\tplayer 1:%d", playerOneScore));
		io.println(String.format("\tplayer 2:%d", playerTwoScore));
		if(playerOneScore > playerTwoScore){
			io.println("Player 1 wins!");
		} else if(playerOneScore < playerTwoScore){
			io.println("Player 2 wins!");
		}else{
			io.println("A tie!");
		}
	}
	
	

}
