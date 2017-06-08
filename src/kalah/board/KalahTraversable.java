package kalah.board;

public interface KalahTraversable {
	public KalahTraversalState getNextTraversalState(KalahTraversalState currentTraversalState, int housesPerPlayer, Player currentPlayersTurn);
}
