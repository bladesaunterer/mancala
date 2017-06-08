package kalah.board;

public class AClockwiseTraversal implements KalahTraversable {
	public KalahTraversalState getNextTraversalState(KalahTraversalState currentTraversalState, int housesPerPlayer){
		Player currentPlayersTurn = currentTraversalState.getCurrentPlayerHoldSeedsDistributed();
		Player otherPlayer = (currentPlayersTurn == Player.ONE) ? Player.TWO : Player.ONE;
		int currentSeedContainer = currentTraversalState.getCurrentSeedContainerBeingSewnForPlayer();
		Player playerSewingSeeds = currentTraversalState.getCurrentPlayerHoldSeedsDistributed();
		
		
		if(currentSeedContainer == housesPerPlayer) {
			currentSeedContainer = 0;
			playerSewingSeeds= (playerSewingSeeds == Player.ONE) ? Player.TWO:Player.ONE;
			
		}else{
			currentSeedContainer++;
		}
		
//		if(playerSewingSeeds == otherPlayer && currentSeedContainer==housesPerPlayer){
//			currentSeedContainer = 0;
//			playerSewingSeeds = currentPlayersTurn;
//		}
		
		return new KalahTraversalState(playerSewingSeeds, currentSeedContainer);
		
	}

}
