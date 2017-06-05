package kalah.util;

import com.qualitascorpus.testsupport.IO;

import kalah.Player;

public class HumanPlayer implements KalahPlayerInput {
	public static final String QUIT_STRING = "q";
	private static final String USER_PROMPT = "Player P%d's turn - Specify house number or '" + QUIT_STRING + "' to quit: ";

	@Override
	public String getPlayerInput(Player player, IO io){
		return io.readFromKeyboard(String.format(USER_PROMPT, player.numericValue()));
	}

}
