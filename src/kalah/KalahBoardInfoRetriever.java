package kalah;

import java.util.List;

import kalah.components.SeedContainer;

public interface KalahBoardInfoRetriever {
	public List<SeedContainer> getSeedContainers(Player player);
	public int getNumberOfHouses();
}
