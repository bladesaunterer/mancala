package kalah.board;

public class AClockwiseTraversal implements KalahTraversable {
	public KalahTraversalState getNextTraversalState(KalahTraversalState currentTraversalState, int housesPerPlayer){
		int currentSeedContainer = currentTraversalState.getCurrentSeedContainerBeingSewnForPlayer();
		Player playerSewingSeeds = currentTraversalState.getCurrentPlayerHoldSeedsDistributed();
		
		
		if(currentSeedContainer == housesPerPlayer) {
			currentSeedContainer = 0;
			playerSewingSeeds= (playerSewingSeeds == Player.ONE) ? Player.TWO:Player.ONE;
			
		}else{
			currentSeedContainer++;
		}
		
		return new KalahTraversalState(playerSewingSeeds, currentSeedContainer);
		
	}

}
