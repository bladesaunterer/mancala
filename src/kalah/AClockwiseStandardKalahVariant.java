package kalah;

import com.qualitascorpus.testsupport.IO;

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
				
				//checking if capture occurs, triggered by fact that last seed is being sewn
				if(seeds == 1
						&& currentSeedContainer != housesPerPlayer
						&& playerSewingSeeds == playerOneHold
						&& playerSewingSeeds.getNumOfSeedsInHouse(currentSeedContainer+1) == 1
						&& playerTwoHold.getNumOfSeedsInHouse(housesPerPlayer - currentSeedContainer) >0) {
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
			
				
				if(currentSeedContainer == housesPerPlayer) {
					currentSeedContainer = 0;
					playerSewingSeeds= (playerSewingSeeds == playerOneHold) ? playerTwoHold:playerOneHold;
					
				}else{
					currentSeedContainer++;
				}
				
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


	
}
