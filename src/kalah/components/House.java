package kalah.components;

public class House extends SeedContainer {

	public House(int numOfSeeds) {
		super(numOfSeeds);
	}

	public int removeSeeds() {
		int currentSeeds = numOfSeeds;
		numOfSeeds = 0;
		return currentSeeds;
	}
}
