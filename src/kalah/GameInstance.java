package kalah;

import com.qualitascorpus.testsupport.IO;

import kalah.util.KalahOutput;
import kalah.util.KalahPlayerInput;

public class GameInstance {
	private static final int HOUSES_PER_PLAYER = 6;
	private static final Player FIRST_PLAYER_TURN = Player.ONE;
	private int seedsPerHouse;
	private KalahPlayerInput playerOne, playerTwo;
	private IO io;
	
	public GameInstance(IO io,KalahPlayerInput playerOne, KalahPlayerInput playerTwo, int seedsPerHouse ) {
		this.io = io;
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		this.seedsPerHouse = seedsPerHouse;
	}
	
	public void play() {
		Board board = new Board(HOUSES_PER_PLAYER,seedsPerHouse,io);
		KalahOutput outputRenderer = new KalahOutput(board, io);
		
		String playerInput;
		Player currentPlayer = FIRST_PLAYER_TURN;
		
		while(true) {
			if(board.gameOver(currentPlayer)) {
				outputRenderer.renderBoard();
				outputRenderer.endGame();
				outputRenderer.displayResults();
				break;
			}
			
			outputRenderer.renderBoard();
			
			playerInput = (currentPlayer == Player.ONE) ? playerOne.getPlayerInput() : playerTwo.getPlayerInput() ;
		
			if(outputRenderer.userEndsGame(playerInput)){
				outputRenderer.endGame();
				break;
			} else {
				currentPlayer = board.playerTurn(Integer.parseInt(playerInput), currentPlayer);
			}
				
		}
		
	}

}
