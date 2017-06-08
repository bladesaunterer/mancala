package kalah.board;

import kalah.board.interfaces.KalahTraversable;

public class AClockwiseTraversal implements KalahTraversable {
	public KalahTraversalState getNextTraversalState(KalahTraversalState currentTraversalState, int housesPerPlayer, Player currentPlayersTurn){
		int currentSeedContainer = currentTraversalState.getCurrentSeedContainerBeingSewnForPlayer();
		Player playerSewingSeeds = currentTraversalState.getCurrentPlayerHoldSeedsDistributed();
		
		
		if(playerSewingSeeds != currentPlayersTurn && currentSeedContainer==housesPerPlayer){
			currentSeedContainer = 1;
			playerSewingSeeds = currentPlayersTurn;
		} else if(currentSeedContainer == housesPerPlayer+1) {
			currentSeedContainer = 1;
			playerSewingSeeds= (playerSewingSeeds == Player.ONE) ? Player.TWO:Player.ONE;
			
		}else{
			currentSeedContainer++;
		}
		
		
		
		return new KalahTraversalState(playerSewingSeeds, currentSeedContainer);
		
	}

}
