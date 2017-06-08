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
		
		
		int numOfLoops = 0;
		
		int oppositeHouse =housesPerPlayer-houseSelection +1;
		
		int y = houseSelection + (oppositeHouse*2);
		int z =0;
		
		
		while (z < seedsPriorToDistribution)
			z+=y;
		
		z-=y;
		int x=0;
		
		if(z>=y)
			x= z - (houseSelection + (oppositeHouse*2)) + (housesPerPlayer*2+1);
		
		
//		System.out.println(houseSelection);
//		System.out.println(oppositeHouse);
//		System.out.println(x);
		
		if (x>housesPerPlayer*2 && x%(housesPerPlayer*2+1)==0){
			numOfLoops = x/(housesPerPlayer*2+1);
		}
		seeds += numOfLoops;
		
		while(seeds > 0) {
			stateAfterDistribution = traverser.getNextTraversalState(stateAfterDistribution, housesPerPlayer);
			PlayerHold currentSeedHoldSewn = (stateAfterDistribution.getCurrentPlayerHoldSeedsDistributed() == Player.ONE) ? pOneHold : pTwoHold;
			PlayerHold otherPlayer = (stateAfterDistribution.getCurrentPlayerHoldSeedsDistributed() == Player.ONE) ? pTwoHold : pOneHold;
			
			
			
			if(seeds == 1) {
				
				
				if(stateAfterDistribution.getCurrentPlayerHoldSeedsDistributed() != currentPlayer) {
					
					return false;
				}
				

				int houseOne = stateAfterDistribution.getCurrentSeedContainerBeingSewnForPlayer();
				int otherHouse = housesPerPlayer - stateAfterDistribution.getCurrentSeedContainerBeingSewnForPlayer()+1;
				if(houseOne == 0 || houseOne == housesPerPlayer+1 ){
					
					return false;
				}else {
					return (currentSeedHoldSewn.getNumOfSeedsInHouse(houseOne) ==1 
							&& otherPlayer.getNumOfSeedsInHouse(otherHouse) >0 );
				}
			}

			
			seeds--;
		}
		
		
		
		return captureOccurs;

		
	}
	
	
	
	


	
}