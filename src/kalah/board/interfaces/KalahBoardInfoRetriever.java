package kalah.board.interfaces;

import java.util.List;

import kalah.board.Player;
import kalah.components.SeedContainer;

public interface KalahBoardInfoRetriever {
	public List<SeedContainer> getPlayerSeedContainers(Player player);

	public int getNumberOfHouses();

	public int getFinalPlayerScore(Player player);
}
