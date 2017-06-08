package kalah.util;

import com.qualitascorpus.testsupport.IO;

import kalah.board.Player;

public class HumanPlayer implements KalahPlayerInput {
	private static final String QUIT_STRING = "q";
	private static final String USER_PROMPT = "Player P%d's turn - Specify house number or '" + QUIT_STRING + "' to quit: ";
	private Player player;
	private IO io;
	
	public HumanPlayer(Player player, IO io){
		this.player = player;
		this.io = io;
	}

	@Override
	public String getPlayerInput(){
		return io.readFromKeyboard(String.format(USER_PROMPT, player.numericValue()));
	}
	
}
