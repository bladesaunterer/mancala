package kalah.board;

import kalah.components.PlayerHold;


public interface KalahVariantGameLogic {
	public Player playerTurn(PlayerHold playerOneHold, PlayerHold playerTwoHold, int houseSelection, Player currentPlayer, KalahTraversable boardTraverser);
}
