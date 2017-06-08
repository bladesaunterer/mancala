package kalah.board;

public class KalahTraversalState {
	private Player currentPlayerHold;
	private int currentSeedStore;

	public KalahTraversalState(Player currentPlayerHold, int currentSeedStore) {
		this.currentPlayerHold = currentPlayerHold;
		this.currentSeedStore = currentSeedStore;
	}

	public Player getCurrentPlayerHoldSeedsDistributed() {
		return currentPlayerHold;
	}

	public int getCurrentSeedContainerBeingSownForPlayer() {
		return currentSeedStore;
	}

}
