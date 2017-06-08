package kalah;

import com.qualitascorpus.testsupport.IO;

import kalah.board.Board;
import kalah.board.KalahTraversable;
import kalah.board.Player;
import kalah.util.KalahOutput;
import kalah.util.KalahPlayerInput;

public class GameInstance {
	
	private static final Player FIRST_PLAYER_TURN = Player.ONE;
	private Board board;
	private KalahPlayerInput playerOne, playerTwo;
	private IO io;
	private KalahTraversable boardTraverser;
	
	public GameInstance(IO io,KalahPlayerInput playerOne, KalahPlayerInput playerTwo, Board board, KalahTraversable boardTraverser ) {
		this.io = io;
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		this.board = board;
		this.boardTraverser = boardTraverser;
	}
	
	public void play() {
		
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
				currentPlayer = board.playerTurn(Integer.parseInt(playerInput), currentPlayer, boardTraverser);
			}
				
		}
		
	}

}
