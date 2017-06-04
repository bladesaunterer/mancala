package kalah.components;

public class Store extends SeedContainer {
	public Store(int numOfSeeds) {
		super(numOfSeeds);
	}
	
	public void addToStore(int seeds) {
		this.numOfSeeds = this.numOfSeeds + seeds;
	}

}
