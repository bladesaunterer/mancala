package kalah.components;

public class SeedContainer {
	protected int numOfSeeds = 0;

	public SeedContainer(int numOfSeeds) {
		this.numOfSeeds = numOfSeeds;
	}

	public void increment() {
		numOfSeeds++;
	}

	public int getNumOfSeeds() {
		return numOfSeeds;
	}

}