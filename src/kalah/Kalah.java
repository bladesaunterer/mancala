package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;

import kalah.util.KalahIO;

/**
 * This class is the starting point for the Modifiability Assignment.
 */
public class Kalah {
	private static final int HOUSES_PER_PLAYER = 6;
	private static final int SEEDS_PER_HOUSE = 4;
	private static final Player FIRST_PLAYER_TURN = Player.ONE;
	
	
	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}
	
	public void play(IO io) {
		Board board = new Board(HOUSES_PER_PLAYER,SEEDS_PER_HOUSE,io);
		
		boolean continuePlaying = true;
		boolean gameCompleted = false;
		String userInput;
		Player currentPlayer = FIRST_PLAYER_TURN;
		
		while(continuePlaying) {
			if(board.gameOver(currentPlayer)) {
				gameCompleted = true;
				KalahIO.renderBoard(board, io);
				KalahIO.endGame(board,io);
				break;
			}
			KalahIO.renderBoard(board, io);
			if(!gameCompleted){
				userInput = KalahIO.getPlayerInput(currentPlayer, io);
			
				if(KalahIO.userEndsGame(userInput)){
					continuePlaying = false;
					KalahIO.endGame(board,io);
				} else {
					currentPlayer = board.playerTurn(Integer.parseInt(userInput), currentPlayer);
				}
			}
				
		}
		
		if(gameCompleted){
			KalahIO.displayResults(io, board.getFinalPlayerScore(Player.ONE), board.getFinalPlayerScore(Player.TWO));
		}
		
	}
}
