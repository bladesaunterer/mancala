package kalah;

public enum Player {
	ONE(1),TWO(2);
	
	private int value;

	private Player(int value) {
		this.value = value;
	}
	
	public int numericValue() {
		return value;
	}
}
