package kalah;

import java.util.List;

import com.qualitascorpus.testsupport.IO;

import kalah.components.SeedContainer;
import kalah.util.KalahOutput;

public class Board implements KalahBoardInfoRetriever {
	private PlayerHold playerOne;
	private PlayerHold playerTwo;
	private int housesPerPlayer;
	private KalahVariantGameLogic gameLogic;
	
	public Board(int housesPerPlayer, int seedsPerHouse, KalahVariantGameLogic gameLogic) {
		this.playerOne = new PlayerHold(housesPerPlayer, seedsPerHouse);
		this.playerTwo = new PlayerHold(housesPerPlayer, seedsPerHouse);	
		this.housesPerPlayer = housesPerPlayer;
		this.gameLogic = gameLogic;
	}
	
	public int getFinalPlayerScore(Player player) {
		if(player == Player.ONE){
			return playerOne.getFinalPlayerScore();
		} else {
			return playerTwo.getFinalPlayerScore();
		}
	}

	@Override
	public List<SeedContainer> getPlayerSeedContainers(Player player) {
		if(player == Player.ONE){
			return playerOne.getSeedContainers();
		} else {
			return playerTwo.getSeedContainers();
		}
	}

	/*
	 * Changes state of board and returns player whose turn is next
	 */
	public Player playerTurn(int houseSelection, Player currentPlayer) {
		
		return gameLogic.playerTurn(playerOne, playerTwo, houseSelection, currentPlayer); 
//		
		
//		if(currentPlayer == Player.ONE){
//			int seeds = playerOne.removeSeedsFromHouse(houseSelection);
//			if(seeds == 0){
//				KalahOutput.pleaseTryAgain(io);
//				return currentPlayer;
//			}
//			boolean captureOccurs = false;
//			
//			//Determines if last seed lands in player store
//			if((houseSelection + seeds - 1) == housesPerPlayer) {
//				currentPlayer = Player.ONE;
//			}else{
//				currentPlayer = Player.TWO;
//			}
//			
//		
//			
//			int index = houseSelection;
//			PlayerHold playerSewingSeeds = playerOne;
//			
//
//			while(seeds>0){
//				if(playerSewingSeeds == playerTwo && index==housesPerPlayer){
//					index = 0;
//					playerSewingSeeds = playerOne;
//				}
//
//				playerSewingSeeds.incrementSeedsAtIndex(index);
//				
//				
//				if(seeds == 1
//						&& index != housesPerPlayer
//						&& playerSewingSeeds == playerOne
//						&& playerSewingSeeds.getNumOfSeedsAtIndex(index) == 1
//						&& playerTwo.getNumOfSeedsAtIndex(housesPerPlayer - index - 1) >0) {
//					captureOccurs = true;
//				}
//				
//				if(index == housesPerPlayer) {
//					index = 0;
//					playerSewingSeeds = (playerSewingSeeds == playerOne) ? playerTwo:playerOne;
//				}else{
//					index++;
//				}
//				
//				seeds--;
//			}
//			
//			if(captureOccurs){
//				int capturedSeeds = playerTwo.removeSeedsFromHouse(housesPerPlayer-index+1);
//				int seedAtLastIndex = playerOne.removeSeedsFromHouse(index);
//				
//				playerOne.addToPlayerStore(capturedSeeds+seedAtLastIndex);
//			}
//			
//		} else {
//			int seeds = playerTwo.removeSeedsFromHouse(houseSelection);
//			if(seeds == 0){
//				KalahOutput.pleaseTryAgain(io);
//				return currentPlayer;
//			}
//			boolean captureOccurs = false;
//			
//			//Determines if last seed lands in player store
//			if((houseSelection + seeds - 1) == housesPerPlayer) {
//				currentPlayer = Player.TWO;
//			}else{
//				currentPlayer = Player.ONE;
//			}
//
//			
//			
//			int index = houseSelection;
//			PlayerHold playerSewingSeeds = playerTwo;
//			
//
//			while(seeds>0){
//				if(playerSewingSeeds == playerOne && index==housesPerPlayer){
//					index = 0;
//					playerSewingSeeds = playerTwo;
//				}
//				
//				
//				playerSewingSeeds.incrementSeedsAtIndex(index);
//				
//				if(seeds == 1
//						&& index != housesPerPlayer
//						&& playerSewingSeeds == playerTwo
//						&& playerSewingSeeds.getNumOfSeedsAtIndex(index) == 1
//						&& playerOne.getNumOfSeedsAtIndex(housesPerPlayer - index - 1) >0
//						) {
//					captureOccurs = true;
//				}
//			
//				
//				if(index == housesPerPlayer) {
//					index = 0;
//					playerSewingSeeds= (playerSewingSeeds == playerOne) ? playerTwo:playerOne;
//					
//				}else{
//					index++;
//				}
//				
//				seeds--;
//			}
//			
//			if(captureOccurs){
//				int capturedSeeds = playerOne.removeSeedsFromHouse(housesPerPlayer-index+1);
//				int seedAtLastIndex = playerTwo.removeSeedsFromHouse(index);
//				playerTwo.addToPlayerStore(capturedSeeds+seedAtLastIndex);
//			}
//			
//			
//		}
//		
//		return currentPlayer;
	}
	


	public boolean gameOver(Player currentPlayer) {
		return getNumberOfSeedsInHouses(currentPlayer) == 0;
	}
	
	
	private int getNumberOfSeedsInHouses(Player player) {
		if(player == Player.ONE)
			return playerOne.getNumberOfSeedsInHouses();
		else 
			return playerTwo.getNumberOfSeedsInHouses();
	}
	
	public int getNumberOfHouses(){
		return this.housesPerPlayer;
	}
	
	
	
	
}
