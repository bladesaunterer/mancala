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

		if(currentPlayer == Player.ONE){
			int seeds = playerOneHold.removeSeedsFromHouse(houseSelection);
			if(seeds == 0){
				KalahOutput.pleaseTryAgain(io);
				return currentPlayer;
			}
			boolean captureOccurs = false;
			
			//Determines if last seed lands in player store
			if((houseSelection + seeds - 1) == housesPerPlayer) {
				currentPlayer = Player.ONE;
			}else{
				currentPlayer = Player.TWO;
			}
			
		
			
			int index = houseSelection;
			PlayerHold playerSewingSeeds = playerOneHold;
			

			while(seeds>0){
				if(playerSewingSeeds == playerTwoHold && index==housesPerPlayer){
					index = 0;
					playerSewingSeeds = playerOneHold;
				}

				playerSewingSeeds.incrementSeedsAtIndex(index);
				
				
				if(seeds == 1
						&& index != housesPerPlayer
						&& playerSewingSeeds == playerOneHold
						&& playerSewingSeeds.getNumOfSeedsAtIndex(index) == 1
						&& playerTwoHold.getNumOfSeedsAtIndex(housesPerPlayer - index - 1) >0) {
					captureOccurs = true;
				}
				
				if(index == housesPerPlayer) {
					index = 0;
					playerSewingSeeds = (playerSewingSeeds == playerOneHold) ? playerTwoHold:playerOneHold;
				}else{
					index++;
				}
				
				seeds--;
			}
			
			if(captureOccurs){
				int capturedSeeds = playerTwoHold.removeSeedsFromHouse(housesPerPlayer-index+1);
				int seedAtLastIndex = playerOneHold.removeSeedsFromHouse(index);
				
				playerOneHold.addToPlayerStore(capturedSeeds+seedAtLastIndex);
			}
			
		} else {
			int seeds = playerTwoHold.removeSeedsFromHouse(houseSelection);
			if(seeds == 0){
				KalahOutput.pleaseTryAgain(io);
				return currentPlayer;
			}
			boolean captureOccurs = false;
			
			//Determines if last seed lands in player store
			if((houseSelection + seeds - 1) == housesPerPlayer) {
				currentPlayer = Player.TWO;
			}else{
				currentPlayer = Player.ONE;
			}

			
			
			int index = houseSelection;
			PlayerHold playerSewingSeeds = playerTwoHold;
			

			while(seeds>0){
				if(playerSewingSeeds == playerOneHold && index==housesPerPlayer){
					index = 0;
					playerSewingSeeds = playerTwoHold;
				}
				
				
				playerSewingSeeds.incrementSeedsAtIndex(index);
				
				if(seeds == 1
						&& index != housesPerPlayer
						&& playerSewingSeeds == playerTwoHold
						&& playerSewingSeeds.getNumOfSeedsAtIndex(index) == 1
						&& playerOneHold.getNumOfSeedsAtIndex(housesPerPlayer - index - 1) >0
						) {
					captureOccurs = true;
				}
			
				
				if(index == housesPerPlayer) {
					index = 0;
					playerSewingSeeds= (playerSewingSeeds == playerOneHold) ? playerTwoHold:playerOneHold;
					
				}else{
					index++;
				}
				
				seeds--;
			}
			
			if(captureOccurs){
				int capturedSeeds = playerOneHold.removeSeedsFromHouse(housesPerPlayer-index+1);
				int seedAtLastIndex = playerTwoHold.removeSeedsFromHouse(index);
				playerTwoHold.addToPlayerStore(capturedSeeds+seedAtLastIndex);
			}
			
			
		}
		
		return currentPlayer;
	}


	
}
