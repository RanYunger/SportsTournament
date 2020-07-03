package ID316334473.Models;

import java.util.Arrays;

import ID316334473.UIHandler;

public class TennisMatchModel extends MatchModel {
	// Constants
	public static final int WIN = 1;
	public static final int MIN_SETS = 2, MAX_SETS = 4; // Zero-based
	public static final int MIN_WINS = 3;

	// Fields
	private int[] player0Sets, player1Sets;
	private int currentSet;

	// Properties (Getters and Setters)
	public int getCurrentSet() {
		return currentSet;
	}

	public void setCurrentSet(int currentSet) {
		this.currentSet = currentSet;
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

	public int[] getCurrentPlayerSets() {
		return getCurrentPlayer().equals(player0) ? player0Sets : player1Sets;
	}

	public int[] getOtherPlayerSets() {
		return getCurrentPlayerSets().equals(player0Sets) ? player1Sets : player0Sets;
	}

	public int getCurrentPlayerWins() {
		return getCurrentPlayer().equals(player0) ? player0.getNumericScore() : player1.getNumericScore();
	}

	public int getOtherPlayerWins() {
		return getCurrentPlayer().equals(player0) ? player1.getNumericScore() : player0.getNumericScore();
	}

	// Constructors
	public TennisMatchModel(PlayerModel player0, PlayerModel player1) {
		super(player0, player1);

		setCurrentSet(TIE);
		setPlayer0Sets(new int[MAX_SETS + 1]);
		setPlayer1Sets(new int[MAX_SETS + 1]);
	}

	// Methods
	@Override
	public PlayerModel addScore(int score) {
		PlayerModel currentPlayer = getCurrentPlayer(), otherPlayer = getOtherPlayer();
		int[] currentPlayerSets = getCurrentPlayerSets(), otherPlayerSets = getOtherPlayerSets();
		int currentPlayerWins = getCurrentPlayerWins(), otherPlayerWins = getOtherPlayerWins();

		// Checking whether the match could end
		currentPlayerSets[currentSet] = score;
		if (otherPlayerSets[currentSet] != NO_SCORE) { // Both players have played the current set
			currentPlayerWins += currentPlayerSets[currentSet] > otherPlayerSets[currentSet] ? WIN : TIE;
			otherPlayerWins += currentPlayerSets[currentSet] < otherPlayerSets[currentSet] ? WIN : TIE;

			currentPlayer.setscore(currentPlayerWins);
			otherPlayer.setscore(otherPlayerWins);

			// Conditions to end the match
			if ((currentSet == MIN_SETS) && (Math.abs(currentPlayerWins - otherPlayerWins) == MIN_WINS)) {
				setWinner(Integer.compare(currentPlayerWins, otherPlayerWins) > TIE ? currentPlayer : otherPlayer);

				UIHandler.showSuccess(winner.getTextualName() + " wins!", false);
				UIHandler.playAudio("FlawlessVictory.mp3");

				return winner;
			} else if ((currentSet == MAX_SETS) && (currentPlayerWins != otherPlayerWins)) {
				setWinner(Integer.compare(currentPlayerWins, otherPlayerWins) > TIE ? currentPlayer : otherPlayer);

				UIHandler.showSuccess(winner.getTextualName() + " wins!", false);

				return winner;
			}

			++currentSet;
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