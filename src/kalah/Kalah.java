package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;

import kalah.board.AClockwiseStandardKalahVariant;
import kalah.board.Board;
import kalah.board.KalahVariantGameLogic;
import kalah.board.Player;
import kalah.util.HumanPlayer;

/**
 * This class is the starting point for the Modifiability Assignment.
 */
public class Kalah {
	private static final int SEEDS_PER_HOUSE = 4;
	private static final int HOUSES_PER_PLAYER = 6;
	
	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}
	
	public void play(IO io) {
		KalahVariantGameLogic gameLogic = new AClockwiseStandardKalahVariant(HOUSES_PER_PLAYER, io);
		Board board = new Board(HOUSES_PER_PLAYER,SEEDS_PER_HOUSE, gameLogic);
		new GameInstance(io, new HumanPlayer(Player.ONE, io), new HumanPlayer(Player.TWO, io), board).play();
	}
}
