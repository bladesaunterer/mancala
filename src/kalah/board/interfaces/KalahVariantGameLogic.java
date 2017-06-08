package kalah.board.interfaces;

import kalah.board.Player;
import kalah.components.PlayerHold;


public interface KalahVariantGameLogic {
	public Player playerTurn(PlayerHold playerOneHold, PlayerHold playerTwoHold, int houseSelection, Player currentPlayer, KalahTraversable boardTraverser);
}
