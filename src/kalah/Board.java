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
