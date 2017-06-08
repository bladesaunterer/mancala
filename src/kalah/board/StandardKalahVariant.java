package kalah.board;

import com.qualitascorpus.testsupport.IO;

import kalah.components.PlayerHold;
import kalah.util.KalahOutput;

public class StandardKalahVariant extends KalahVariantGameLogic {

	public StandardKalahVariant(int housesPerPlayer, IO io) {
		super(housesPerPlayer, io);
	}

	@Override
	public Player playerTurn(PlayerHold playerOneHold, PlayerHold playerTwoHold, int houseSelection,
			Player currentPlayer, KalahTraversable boardTraverser) {
		boolean captureOccurs = false;
		int seedsToDistribute = 0;

		if(currentPlayer == Player.ONE){
			int seeds = playerOneHold.removeSeedsFromHouse(houseSelection);
			seedsToDistribute = seeds;
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
//				if(seeds == 1
//						&& currentSeedContainer != housesPerPlayer
//						&& playerSewingSeeds == playerOneHold
//						&& playerSewingSeeds.getNumOfSeedsInHouse(currentSeedContainer+1) == 1
//						&& playerTwoHold.getNumOfSeedsInHouse(housesPerPlayer - currentSeedContainer) > 0) {
//					captureOccurs = true;
//				}
				
				//determine where seeds distributed next
				KalahTraversalState traversalState = new KalahTraversalState((playerSewingSeeds == playerOneHold) ? Player.ONE : Player.TWO,currentSeedContainer);

				traversalState = boardTraverser.getNextTraversalState(traversalState, housesPerPlayer);
				
				playerSewingSeeds = (traversalState.getCurrentPlayerHoldSeedsDistributed() == Player.ONE) ? playerOneHold:playerTwoHold;
				currentSeedContainer = traversalState.getCurrentSeedContainerBeingSewnForPlayer();
				
				
				seeds--;
			}
			
			//perform steal
			Player lastToSewSeed = (currentPlayer == Player.ONE) ? Player.TWO : Player.ONE;
			
			if(captureOccurs(lastToSewSeed, playerOneHold,playerTwoHold,houseSelection,seedsToDistribute, housesPerPlayer)){
				int capturedSeeds = playerTwoHold.removeSeedsFromHouse(housesPerPlayer-currentSeedContainer+1);
				int seedAtLastIndex = playerOneHold.removeSeedsFromHouse(currentSeedContainer);
				
				playerOneHold.addToPlayerStore(capturedSeeds+seedAtLastIndex);
			}
			
		} else {
			int seeds = playerTwoHold.removeSeedsFromHouse(houseSelection);
			seedsToDistribute = seeds;
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
				
//				if(seeds == 1
//						&& currentSeedContainer != housesPerPlayer
//						&& playerSewingSeeds == playerTwoHold
//						&& playerSewingSeeds.getNumOfSeedsInHouse(currentSeedContainer+1) == 1
//						&& playerOneHold.getNumOfSeedsInHouse(housesPerPlayer - currentSeedContainer) >0
//						) {
//					captureOccurs = true;
//				}
				

			
				//Change KalahTraversal State, set player sewing seeds, change current seedContainer
				
				
				
				KalahTraversalState traversalState = new KalahTraversalState((playerSewingSeeds == playerOneHold) ? Player.ONE : Player.TWO,currentSeedContainer);
				traversalState = boardTraverser.getNextTraversalState(traversalState, housesPerPlayer);
				
				playerSewingSeeds = (traversalState.getCurrentPlayerHoldSeedsDistributed() == Player.ONE) ? playerOneHold:playerTwoHold;
				currentSeedContainer = traversalState.getCurrentSeedContainerBeingSewnForPlayer();
				

				
				seeds--;
			}
			
			
			Player lastToSewSeed = (currentPlayer == Player.ONE) ? Player.TWO : Player.ONE;
		
			if(captureOccurs(lastToSewSeed, playerOneHold,playerTwoHold,houseSelection,seedsToDistribute, housesPerPlayer)){
				int capturedSeeds = playerOneHold.removeSeedsFromHouse(housesPerPlayer-currentSeedContainer+1);
				int seedAtLastIndex = playerTwoHold.removeSeedsFromHouse(currentSeedContainer);
				playerTwoHold.addToPlayerStore(capturedSeeds+seedAtLastIndex);
			}
			
			
		}
		
		return currentPlayer;
	}
	
	
	
	
	private boolean captureOccurs(Player currentPlayer, PlayerHold pOneHold, PlayerHold pTwoHold, int houseSelection, int seedsPriorToDistribution, int housesPerPlayer){
		
		KalahTraversable traverser = new AClockwiseTraversal();
		KalahTraversalState stateAfterDistribution = new KalahTraversalState(currentPlayer, houseSelection);
		int seeds = seedsPriorToDistribution;
		boolean captureOccurs = false;
		
		System.out.println(seeds);
		
		int numOfLoops = seeds/(housesPerPlayer*2+1);
		seeds += numOfLoops;
		
		while(seeds > 0) {
			stateAfterDistribution = traverser.getNextTraversalState(stateAfterDistribution, housesPerPlayer);
			PlayerHold currentSeedHoldSewn = (stateAfterDistribution.getCurrentPlayerHoldSeedsDistributed() == Player.ONE) ? pOneHold : pTwoHold;
			PlayerHold otherPlayer = (stateAfterDistribution.getCurrentPlayerHoldSeedsDistributed() == Player.ONE) ? pTwoHold : pOneHold;
			
			
			
			if(seeds == 1) {
//				
				
//				
//				System.out.println(stateAfterDistribution.getCurrentPlayerHoldSeedsDistributed());
//				System.out.println(stateAfterDistribution.getCurrentSeedContainerBeingSewnForPlayer());
				
				
				if(stateAfterDistribution.getCurrentPlayerHoldSeedsDistributed() != currentPlayer) {
					
					return false;
				}
				
//				int houseLastSewn = stateAfterDistribution.getCurrentSeedContainerBeingSewnForPlayer();
//				if(houseLastSewn < housesPerPlayer 
//						&& currentSeedHoldSewn.getNumOfSeedsInHouse(houseLastSewn+1) == 0
//						&& otherPlayer.getNumOfSeedsInHouse(housesPerPlayer - stateAfterDistribution.getCurrentSeedContainerBeingSewnForPlayer()+1) > 0)
//					return true;
				
				int houseOne = stateAfterDistribution.getCurrentSeedContainerBeingSewnForPlayer();
				int otherHouse = housesPerPlayer - stateAfterDistribution.getCurrentSeedContainerBeingSewnForPlayer()+1;
				if(houseOne == 0 || houseOne == housesPerPlayer+1 ){
					
					return false;
				}else {
//					System.out.println("reached");
					System.out.println(currentSeedHoldSewn.getNumOfSeedsInHouse(houseOne));
					System.out.println(otherPlayer.getNumOfSeedsInHouse(otherHouse));
					return (currentSeedHoldSewn.getNumOfSeedsInHouse(houseOne) ==1 
							&& otherPlayer.getNumOfSeedsInHouse(otherHouse) >0 );
				}
			}

			
			seeds--;
		}
		
		
		
		return captureOccurs;
		
		
		
		
		
		
//		Player playerToLastDistributeSeed = stateAfterDistribution.getCurrentPlayerHoldSeedsDistributed();
//		int seedContainerDistributed = stateAfterDistribution.getCurrentSeedContainerBeingSewnForPlayer();
//		System.out.println(stateAfterDistribution.getCurrentPlayerHoldSeedsDistributed());
//		System.out.println(seedContainerDistributed);
//		System.out.println(housesPerPlayer - seedContainerDistributed +1);
//		
//		if(playerToLastDistributeSeed != currentPlayer)
//			return false;
//		
//		if(seedContainerDistributed <= housesPerPlayer){
//			if(currentPlayer == Player.ONE){
//				captureOccurs  = pOneHold.getNumOfSeedsInHouse(seedContainerDistributed) == 1 && pTwoHold.getNumOfSeedsInHouse(housesPerPlayer - seedContainerDistributed +1) >0;
//				System.out.println(captureOccurs);
//				return captureOccurs;
//			}else {
//				captureOccurs = pTwoHold.getNumOfSeedsInHouse(seedContainerDistributed) == 1 && pOneHold.getNumOfSeedsInHouse(housesPerPlayer - seedContainerDistributed +1) >0;
//				System.out.println(captureOccurs);
//				return captureOccurs;
//			}
//		}
//		
//		
//		return false;
		
		

		
		
//		if(playerToLastDistributeSeed == Player.ONE 
//				&& pOneHold.getNumOfSeedsInHouse(seedContainerDistributed) == 1
//				&& seedContainerDistributed != housesPerPlayer){
//			captureOccurs = pTwoHold.getNumOfSeedsInHouse(housesPerPlayer - seedContainerDistributed) > 0;
//		}else if (seedContainerDistributed != housesPerPlayer
//				&& pTwoHold.getNumOfSeedsInHouse(seedContainerDistributed) == 1) {
//			captureOccurs = pOneHold.getNumOfSeedsInHouse(housesPerPlayer - seedContainerDistributed) > 0;
//			
//		}
		
		
		
		
		
		
		
		
		
		
//		return captureOccurs;
		
	}
	
	
	
	


	
}