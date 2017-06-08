package kalah.board;

import com.qualitascorpus.testsupport.IO;

import kalah.components.PlayerHold;
import kalah.util.KalahOutput;

public class AClockwiseStandardKalahVariant extends KalahVariantGameLogic {

	public AClockwiseStandardKalahVariant(int housesPerPlayer, IO io) {
		super(housesPerPlayer, io);
	}

	@Override
	public Player playerTurn(PlayerHold playerOneHold, PlayerHold playerTwoHold, int houseSelection,
			Player currentPlayer) {
		boolean captureOccurs = false;

		if(currentPlayer == Player.ONE){
			int seeds = playerOneHold.removeSeedsFromHouse(houseSelection);
			if(seeds == 0){
				KalahOutput.pleaseTryAgain(io);
				return currentPlayer;
			}
			
			
			//Determines if last seed lands in player store
			if((houseSelection + seeds - 1) == housesPerPlayer) {
				currentPlayer = Player.ONE;
			}else{
				currentPlayer = Player.TWO;
			}
			
		
			
			int currentSeedContainer = houseSelection;
			PlayerHold playerSewingSeeds = playerOneHold;
			
			//distribute seeds
			while(seeds>0){
				if(playerSewingSeeds == playerTwoHold && currentSeedContainer==housesPerPlayer){
					currentSeedContainer = 0;
					playerSewingSeeds = playerOneHold;
				}

				playerSewingSeeds.incrementSeedsInHouse(currentSeedContainer+1);
				
				// TODO checking if capture occurs, triggered by fact that last seed is being sewn - factor this out
				// should be dependent on opposite house and if it is a valid thing to steal from capture rule remains consistent
				if(seeds == 1
						&& currentSeedContainer != housesPerPlayer
						&& playerSewingSeeds == playerOneHold
						&& playerSewingSeeds.getNumOfSeedsInHouse(currentSeedContainer+1) == 1
						&& playerTwoHold.getNumOfSeedsInHouse(housesPerPlayer - currentSeedContainer) > 0) {
					captureOccurs = true;
				}
				
				//determine where seeds distributed next
				if(currentSeedContainer == housesPerPlayer) {
					currentSeedContainer = 0;
					playerSewingSeeds = (playerSewingSeeds == playerOneHold) ? playerTwoHold:playerOneHold;
				}else{
					currentSeedContainer++;
				}
				
				seeds--;
			}
			
			//perform steal
			if(captureOccurs){
				int capturedSeeds = playerTwoHold.removeSeedsFromHouse(housesPerPlayer-currentSeedContainer+1);
				int seedAtLastIndex = playerOneHold.removeSeedsFromHouse(currentSeedContainer);
				
				playerOneHold.addToPlayerStore(capturedSeeds+seedAtLastIndex);
			}
			
		} else {
			int seeds = playerTwoHold.removeSeedsFromHouse(houseSelection);
			if(seeds == 0){
				KalahOutput.pleaseTryAgain(io);
				return currentPlayer;
			}
			
			//Determines if last seed lands in player store
			if((houseSelection + seeds - 1) == housesPerPlayer) {
				currentPlayer = Player.TWO;
			}else{
				currentPlayer = Player.ONE;
			}

			
			
			int currentSeedContainer = houseSelection;
			PlayerHold playerSewingSeeds = playerTwoHold;
			

			while(seeds>0){
				if(playerSewingSeeds == playerOneHold && currentSeedContainer==housesPerPlayer){
					currentSeedContainer = 0;
					playerSewingSeeds = playerTwoHold;
				}
				
				
				playerSewingSeeds.incrementSeedsInHouse(currentSeedContainer+1);
				
				if(seeds == 1
						&& currentSeedContainer != housesPerPlayer
						&& playerSewingSeeds == playerTwoHold
						&& playerSewingSeeds.getNumOfSeedsInHouse(currentSeedContainer+1) == 1
						&& playerOneHold.getNumOfSeedsInHouse(housesPerPlayer - currentSeedContainer) >0
						) {
					captureOccurs = true;
				}
			
				//Change KalahTraversal State, set player sewing seeds, change current seedContainer
				
				
				
				KalahTraversalState traversalState = new KalahTraversalState((playerSewingSeeds == playerOneHold) ? Player.ONE : Player.TWO,currentSeedContainer);
				//KalahTraverser traverser = new  Traverser() traversal = traverser.getNextState(traversal)
				KalahTraversable boardTraverser = new AClockwiseTraversal();
				traversalState = boardTraverser.getNextTraversalState(traversalState, housesPerPlayer);
				
				playerSewingSeeds = (traversalState.getCurrentPlayerHoldSeedsDistributed() == Player.ONE) ? playerOneHold:playerTwoHold;
				currentSeedContainer = traversalState.getCurrentSeedContainerBeingSewnForPlayer();
				

				
				seeds--;
			}
			
			
			
		
			if(captureOccurs){
				int capturedSeeds = playerOneHold.removeSeedsFromHouse(housesPerPlayer-currentSeedContainer+1);
				int seedAtLastIndex = playerTwoHold.removeSeedsFromHouse(currentSeedContainer);
				playerTwoHold.addToPlayerStore(capturedSeeds+seedAtLastIndex);
			}
			
			
		}
		
		return currentPlayer;
	}
	
	
	
	
	private boolean captureOccurs(PlayerHold currentPlayer, int houseSelection, int seedsPriorToDistribution, int housesPerPlayer){
		//determine if last seed placed on this side
		boolean lastSeedPlacedInThisPlayersHolding = true;
		
		if(houseSelection + seedsPriorToDistribution <= housesPerPlayer ) {
			lastSeedPlacedInThisPlayersHolding = true;
		}
		
		if(seedsPriorToDistribution + houseSelection > 1  )
		
		if(!lastSeedPlacedInThisPlayersHolding) return lastSeedPlacedInThisPlayersHolding;
		
		
		
		
		
		return false;
		
	}
	
	
	
	


	
}
