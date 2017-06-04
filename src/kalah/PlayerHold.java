package kalah;

import java.util.ArrayList;
import java.util.List;

import kalah.components.House;
import kalah.components.SeedContainer;
import kalah.components.Store;

public class PlayerHold {
	private List<SeedContainer> seedContainers = new ArrayList<SeedContainer>();
	private static final int INITIAL_SCORE = 0;
	
	public PlayerHold(int housesPerPlayer, int seedsPerHouse) {
		for(int i=0; i<housesPerPlayer; i++){
			seedContainers.add(new House(seedsPerHouse));
		}
		seedContainers.add(new Store(INITIAL_SCORE));
		
	}
	
	
	public int getFinalPlayerScore() {
		int total = 0;
		for(SeedContainer s: seedContainers){
			total+=s.getNumOfSeeds();
		}
		
		return total;
	}


	public List<SeedContainer> getSeedContainers() {
		return seedContainers;
	}
	
	
	public int getNumberOfSeedsInHouses(){
		int total = 0;
		
		for(SeedContainer s: seedContainers){
			total += (s instanceof House) ? s.getNumOfSeeds() : 0;
		}
		return total;
	}
	
	public int removeSeedsFromHouse(int houseSelection){
		int numberOfSeeds = ((House)seedContainers.get(houseSelection-1)).removeSeeds();
		return numberOfSeeds;
	}
	
	public int getNumOfSeedsAtIndex(int index) {
		return seedContainers.get(index).getNumOfSeeds();
	}
	
	public void incrementSeedsAtIndex(int index) {
		seedContainers.get(index).increment();
	}
	
	public void addToPlayerStore(int seeds) {
		((Store)seedContainers.get(seedContainers.size()-1)).addToStore(seeds);
	}

}
