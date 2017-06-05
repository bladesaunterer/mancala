package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;

import kalah.util.HumanPlayer;

/**
 * This class is the starting point for the Modifiability Assignment.
 */
public class Kalah {
	private static final int SEEDS_PER_HOUSE = 4;
	
	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}
	
	public void play(IO io) {
		new GameInstance(io, new HumanPlayer(Player.ONE, io), new HumanPlayer(Player.TWO, io), SEEDS_PER_HOUSE).play();
	}
}
