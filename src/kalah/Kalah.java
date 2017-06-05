package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;

import kalah.util.HumanPlayer;
import kalah.util.KalahOutput;
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
		this.play(io, new HumanPlayer(Player.ONE,io), new HumanPlayer(Player.TWO, io));
	
	}
	
	private void play(IO io, KalahPlayerInput playerOne, KalahPlayerInput playerTwo ) {
		Board board = new Board(HOUSES_PER_PLAYER,SEEDS_PER_HOUSE,io);
		KalahOutput output = new KalahOutput(board, io);
		
		String playerInput;
		Player currentPlayer = FIRST_PLAYER_TURN;
		
		while(true) {
			if(board.gameOver(currentPlayer)) {
				output.renderBoard();
				output.endGame();
				output.displayResults(board.getFinalPlayerScore(Player.ONE), board.getFinalPlayerScore(Player.TWO));
				break;
			}
			
			output.renderBoard();
			
			playerInput = (currentPlayer == Player.ONE) ? playerOne.getPlayerInput() : playerTwo.getPlayerInput() ;
		
			if(output.userEndsGame(playerInput)){
				output.endGame();
				break;
			} else {
				currentPlayer = board.playerTurn(Integer.parseInt(playerInput), currentPlayer);
			}
				
		}
		
	}
}
