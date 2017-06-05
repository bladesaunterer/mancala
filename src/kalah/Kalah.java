package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;

import kalah.util.HumanPlayer;
import kalah.util.KalahIO;
import kalah.util.KalahPlayerInput;

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
		KalahPlayerInput playerOne = new HumanPlayer(); 
		KalahPlayerInput playerTwo = new HumanPlayer();
		Board board = new Board(HOUSES_PER_PLAYER,SEEDS_PER_HOUSE,io);
		
		String playerInput;
		Player currentPlayer = FIRST_PLAYER_TURN;
		
		while(true) {
			if(board.gameOver(currentPlayer)) {
				KalahIO.renderBoard(board, io);
				KalahIO.endGame(board,io);
				KalahIO.displayResults(io, board.getFinalPlayerScore(Player.ONE), board.getFinalPlayerScore(Player.TWO));
				break;
			}
			
			KalahIO.renderBoard(board, io);
			
			playerInput = (currentPlayer == Player.ONE) ? playerOne.getPlayerInput(currentPlayer, io) : playerTwo.getPlayerInput(currentPlayer, io) ;
		
			if(KalahIO.userEndsGame(playerInput)){
				KalahIO.endGame(board,io);
				break;
			} else {
				currentPlayer = board.playerTurn(Integer.parseInt(playerInput), currentPlayer);
			}
				
		}
		
		
	}
}
