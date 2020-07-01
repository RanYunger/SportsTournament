package ID316334473.Models;

import java.util.Arrays;

import javafx.beans.property.SimpleIntegerProperty;

public class TennisMatchModel extends MatchModel {
	// Constants
	public static final int MIN_SETS = 3, MAX_SETS = 5;
	public static final int MIN_WINS = MIN_SETS;

	// Fields
	private SimpleIntegerProperty currentSet;
	private int[] player0Sets, player1Sets;
	private int player0Wins, player1Wins;

	// Properties (Getters and Setters)
	public SimpleIntegerProperty getObservableCurrentSet() {
		return currentSet;
	}

	public int getNumericCurrentSet() {
		return currentSet.get();
	}

	public void setCurrentSet(int currentSet) {
		this.currentSet = new SimpleIntegerProperty(currentSet);
	}

	public int[] getPlayer0Sets() {
		return player0Sets;
	}

	public void setPlayer0Sets(int[] player0Sets) {
		this.player0Sets = player0Sets;
		Arrays.setAll(this.player0Sets, p -> NO_SCORE);
	}

	public int[] getPlayer1Sets() {
		return player1Sets;
	}

	public void setPlayer1Sets(int[] player1Sets) {
		this.player1Sets = player1Sets;
		Arrays.setAll(this.player1Sets, p -> NO_SCORE);
	}

	public int getPlayer0Wins() {
		return player0Wins;
	}

	public void setPlayer0Wins(int player0Wins) {
		this.player0Wins = player0Wins;
	}

	public int getPlayer1Wins() {
		return player1Wins;
	}

	public void setPlayer1Wins(int player1Wins) {
		this.player1Wins = player1Wins;
	}

	public int[] getCurrentPlayerSets() {
		return getCurrentPlayer().equals(player0) ? player0Sets : player1Sets;
	}

	public int[] getOtherPlayerSets() {
		return getCurrentPlayer().equals(player0) ? player1Sets : player0Sets;
	}

	public int getCurrentPlayerWins() {
		return getCurrentPlayer().equals(player0) ? player0Wins : player1Wins;
	}

	public int getOtherPlayerWins() {
		return getCurrentPlayer().equals(player0) ? player1Wins : player0Wins;
	}

	// Constructors
	public TennisMatchModel(PlayerModel player0, PlayerModel player1) {
		super(player0, player1);

		setCurrentSet(TIE);
		setPlayer0Sets(new int[MAX_SETS]);
		setPlayer1Sets(new int[MAX_SETS]);
		setPlayer0Wins(TIE);
		setPlayer1Wins(TIE);
	}

	// Methods
	@Override
	public PlayerModel addScore(int score) {
		PlayerModel currentPlayer = getCurrentPlayer(), otherPlayer = getOtherPlayer();
		int[] currentPlayerSets = getCurrentPlayerSets(), otherPlayerSets = getOtherPlayerSets();
		int currentPlayerWins = getCurrentPlayerWins(), otherPlayerWins = getOtherPlayerWins(),
				currentSet = getNumericCurrentSet();

		// Checking whether the match could end
		currentPlayerSets[currentSet] = score;
		if (otherPlayerSets[currentSet] != NO_SCORE) { // Both players have played the same
														// amount of sets
			currentPlayerWins = currentPlayerSets[currentSet] > otherPlayerSets[currentSet] ? ++currentPlayerWins
					: currentPlayerWins;
			otherPlayerWins = currentPlayerSets[currentSet] < otherPlayerSets[currentSet] ? ++otherPlayerWins
					: otherPlayerWins;
			if (((currentSet == MIN_SETS) || (currentSet == MAX_SETS))
					&& (Math.abs(currentPlayerWins - otherPlayerWins) == MIN_WINS)) {
				setWinner(Integer.compare(currentPlayerWins, otherPlayerWins) > TIE ? currentPlayer : otherPlayer);

				return winner;
			}

			currentSet++;
		}

		toggleTurn();

		return null; // The match goes on
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TennisMatchModel other = (TennisMatchModel) obj;
		if (!Arrays.equals(player0Sets, other.player0Sets))
			return false;
		if (!Arrays.equals(player1Sets, other.player1Sets))
			return false;
		return true;
	}
}