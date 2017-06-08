package kalah.board.interfaces;

import kalah.board.KalahTraversalState;
import kalah.board.Player;

public interface KalahTraversable {
	public KalahTraversalState getNextTraversalState(KalahTraversalState currentTraversalState, int housesPerPlayer, Player currentPlayersTurn);
}
