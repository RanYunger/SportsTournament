package ID316334473.Models;

public abstract class MatchModel {
	// Constants
	public static final int NO_SCORE = -1;
	public static final int TIE = 0;
	public static final int MAX_PLAYERS = 8;

	// Fields
	protected boolean turn; // False = player 0, True = player 1
	protected boolean isOver;
	protected PlayerModel player0, player1, winner, loser;

	// Properties (Getters and Setters)
	public boolean getTurn() {
		return turn;
	}

	private void setTurn(boolean turn) {
		this.turn = turn;
	}

	protected void toggleTurn() {
		setTurn(!turn);
	}

	public boolean isOver() {
		return isOver;
	}

	protected void setIsOver(boolean hasEnded) {
		this.isOver = hasEnded;
	}

	public PlayerModel[] getPlayers() {
		return new PlayerModel[] { player0, player1 };
	}

	public PlayerModel getPlayer0() {
		return player0;
	}

	private void setPlayer0(PlayerModel player0) {
		this.player0 = player0;
	}

	public PlayerModel getPlayer1() {
		return player1;
	}

	private void setPlayer1(PlayerModel player1) {
		this.player1 = player1;
	}

	public PlayerModel getWinner() {
		return winner;
	}

	public void setWinner(PlayerModel winner) {
		this.winner = winner;

		if (winner != null) {
			setLoser(winner.equals(player0) ? player1 : player0);
			setIsOver(true);
		}
	}

	public PlayerModel getLoser() {
		return loser;
	}

	private void setLoser(PlayerModel loser) {
		this.loser = loser;
	}

	public PlayerModel getCurrentPlayer() {
		return turn ? player1 : player0;
	}

	public PlayerModel getOtherPlayer() {
		return getCurrentPlayer().equals(player0) ? player1 : player0;
	}

	// Constructors
	public MatchModel(PlayerModel player0, PlayerModel player1) {
		setTurn(false); // Player 0 begins
		setIsOver(false);
		setPlayer0(player0);
		setPlayer1(player1);
		setWinner(null);
		setLoser(null);
	}

	// Methods
	public abstract PlayerModel addScore(int score);

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MatchModel other = (MatchModel) obj;
		if (player0 == null) {
			if (other.player0 != null)
				return false;
		} else if (!player0.equals(other.player0))
			return false;
		if (player1 == null) {
			if (other.player1 != null)
				return false;
		} else if (!player1.equals(other.player1))
			return false;
		return true;
	}
}