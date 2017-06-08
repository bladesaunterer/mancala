package kalah.board;

import kalah.board.interfaces.KalahTraversable;

public class AClockwiseTraversal implements KalahTraversable {
	public KalahTraversalState getNextTraversalState(KalahTraversalState currentTraversalState, int housesPerPlayer, Player currentPlayersTurn){
		int currentSeedContainer = currentTraversalState.getCurrentSeedContainerBeingSownForPlayer();
		Player playerSowingSeeds = currentTraversalState.getCurrentPlayerHoldSeedsDistributed();
		
		
		if(playerSowingSeeds != currentPlayersTurn && currentSeedContainer==housesPerPlayer){
			currentSeedContainer = 1;
			playerSowingSeeds = currentPlayersTurn;
		} else if(currentSeedContainer == housesPerPlayer+1) {
			currentSeedContainer = 1;
			playerSowingSeeds= (playerSowingSeeds == Player.ONE) ? Player.TWO:Player.ONE;
			
		}else{
			currentSeedContainer++;
		}
		
		
		
		return new KalahTraversalState(playerSowingSeeds, currentSeedContainer);
		
	}

}
