package kalah;

import com.qualitascorpus.testsupport.IO;


public abstract class KalahVariantGameLogic {
	protected PlayerHold playerOneHold, playerTwoHold;
	protected int housesPerPlayer;
	protected IO io;
	
	
	public KalahVariantGameLogic(int housesPerPlayer, IO io){
		this.housesPerPlayer = housesPerPlayer;
		this.io = io;
	}
	
	
	public abstract Player playerTurn(PlayerHold playerOneHold, PlayerHold playerTwoHold, int houseSelection, Player currentPlayer); 
	
}
