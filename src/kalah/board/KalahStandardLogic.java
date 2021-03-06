package kalah.board;

import com.qualitascorpus.testsupport.IO;

import kalah.board.interfaces.KalahTraversable;
import kalah.board.interfaces.KalahVariantGameLogic;
import kalah.components.PlayerHold;
import kalah.util.KalahOutput;

public class KalahStandardLogic implements KalahVariantGameLogic {
	int housesPerPlayer;
	IO io;

	public KalahStandardLogic(int housesPerPlayer, IO io) {
		this.io = io;
		this.housesPerPlayer = housesPerPlayer;
	}

	@Override
	public Player playerTurn(PlayerHold playerOneHold, PlayerHold playerTwoHold, int houseSelection,
			Player currentPlayer, KalahTraversable boardTraverser) {
		PlayerHold currentPlayerHold = (currentPlayer == Player.ONE)? playerOneHold: playerTwoHold;
		
		int seeds = currentPlayerHold.removeSeedsFromHouse(houseSelection);
		if(seeds == 0){
			KalahOutput.pleaseTryAgain(io);
			return currentPlayer;
		}
		
		KalahTraversalState traversalState = new KalahTraversalState(currentPlayer, houseSelection); 
		
		PlayerHold playerHoldBeingSown = currentPlayerHold;
		
		while(seeds >0) {
			traversalState = boardTraverser.getNextTraversalState(traversalState, housesPerPlayer, currentPlayer);
			playerHoldBeingSown = traversalState.getCurrentPlayerHoldSeedsDistributed()==Player.ONE ? playerOneHold : playerTwoHold;
		  
			playerHoldBeingSown.incrementSeedsInHouse(traversalState.getCurrentSeedContainerBeingSownForPlayer());
			seeds--;
		}
		
	
		if(captureOccurs(currentPlayer, traversalState, playerOneHold, playerTwoHold)){
			PlayerHold thisPlayerHold = traversalState.getCurrentPlayerHoldSeedsDistributed()==Player.ONE ? playerOneHold : playerTwoHold;
			PlayerHold otherPlayerHold = traversalState.getCurrentPlayerHoldSeedsDistributed()==Player.ONE ? playerTwoHold : playerOneHold;
			int lastSeedContainerSown = traversalState.getCurrentSeedContainerBeingSownForPlayer();
			
			int capturedSeeds = otherPlayerHold.removeSeedsFromHouse(housesPerPlayer-lastSeedContainerSown+1);
			int seedAtLastIndex = thisPlayerHold.removeSeedsFromHouse(lastSeedContainerSown);
			
			thisPlayerHold.addToPlayerStore(capturedSeeds+seedAtLastIndex);
		}
		
		if(traversalState.getCurrentSeedContainerBeingSownForPlayer() == housesPerPlayer+1)
			return currentPlayer;
		else
			return (currentPlayer==Player.ONE) ? Player.TWO : Player.ONE;
	}
	
	private boolean captureOccurs(Player currentPlayer,KalahTraversalState traversalState, PlayerHold playerOneHold, PlayerHold playerTwoHold){
		if (currentPlayer != traversalState.getCurrentPlayerHoldSeedsDistributed())
			return false;
		
		int lastSeedContainerIncremented = traversalState.getCurrentSeedContainerBeingSownForPlayer();
		int oppositeContainer = housesPerPlayer-lastSeedContainerIncremented+1;
		
		if(lastSeedContainerIncremented > housesPerPlayer || lastSeedContainerIncremented < 1)
			return false;
		
		PlayerHold thisPlayerHold = traversalState.getCurrentPlayerHoldSeedsDistributed()==Player.ONE ? playerOneHold : playerTwoHold;
		PlayerHold otherPlayerHold = traversalState.getCurrentPlayerHoldSeedsDistributed()==Player.ONE ? playerTwoHold : playerOneHold;
	
		
		if(thisPlayerHold.getNumOfSeedsInHouse(lastSeedContainerIncremented) == 1 
				&& otherPlayerHold.getNumOfSeedsInHouse(oppositeContainer)>0){
			return true;
		}
		
		return false;
	}





}
