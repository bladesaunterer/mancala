package kalah.board;

import java.util.List;

import kalah.components.PlayerHold;
import kalah.components.SeedContainer;

public class Board implements KalahBoardInfoRetriever {
	private PlayerHold playerOne;
	private PlayerHold playerTwo;
	private int housesPerPlayer;
	private KalahVariantGameLogic gameLogic;
	private Player seedLastPlaced = Player.ONE;
	
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
		if(player == Player.ONE){
			seedLastPlaced = Player.ONE;
			return playerOne.getNumberOfSeedsInHouses();
		} else { 
			seedLastPlaced = Player.TWO;
			return playerTwo.getNumberOfSeedsInHouses();
		}
	}
	
	public int getNumberOfHouses(){
		return this.housesPerPlayer;
	}
	
	public Player seedLastPlaced(){
		return seedLastPlaced;
	}

}
