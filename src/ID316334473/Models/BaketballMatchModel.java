package ID316334473.Models;

import java.util.Arrays;

import javafx.beans.property.SimpleIntegerProperty;

public class BaketballMatchModel extends MatchModel {
	// Constants
	public static final int MAX_QUARTERS = 4;

	// Fields
	private SimpleIntegerProperty currentQuarter;
	private int[] player0QuarterScores, player1QuarterScores;

	// Properties (Getters and Setters)
	public SimpleIntegerProperty getObservableCurrentQuarter() {
		return currentQuarter;
	}

	public int getNumericCurrentQuarter() {
		return currentQuarter.get();
	}

	public void setCurrentQuarter(int currentQuarter) {
		this.currentQuarter = new SimpleIntegerProperty(currentQuarter);
	}

	public int[] getPlayer0QuarterScores() {
		return player0QuarterScores;
	}

	public void setPlayer0QuarterScores(int[] player0QuarterScores) {
		this.player0QuarterScores = player0QuarterScores;
		Arrays.setAll(this.player0QuarterScores, p -> NO_SCORE);
	}

	public int[] getPlayer1QuarterScores() {
		return player1QuarterScores;
	}

	public void setPlayer1QuarterScores(int[] player1QuarterScores) {
		this.player1QuarterScores = player1QuarterScores;
		Arrays.setAll(this.player1QuarterScores, p -> NO_SCORE);
	}

	public int[] getCurrentPlayerQuarterScores() {
		return getCurrentPlayer().equals(player0) ? player0QuarterScores : player1QuarterScores;
	}

	public int[] getOtherPlayerQuarterScores() {
		return getCurrentPlayer().equals(player0) ? player1QuarterScores : player0QuarterScores;
	}

	public int getCurrentPlayerTotalScore() {
		int sum = TIE;
		int[] currentPlayerQuarterScores = getCurrentPlayerQuarterScores();

		for (int i = TIE; i < currentPlayerQuarterScores.length; i++)
			sum += currentPlayerQuarterScores[i] != NO_SCORE ? currentPlayerQuarterScores[i] : TIE;

		return sum;
	}

	public int getOtherPlayerTotalScore() {
		int sum = TIE;
		int[] otherPlayerQuarterScores = getOtherPlayerQuarterScores();

		for (int i = TIE; i < otherPlayerQuarterScores.length; i++)
			sum += otherPlayerQuarterScores[i] != NO_SCORE ? otherPlayerQuarterScores[i] : TIE;

		return sum;
	}

	// Constructors
	public BaketballMatchModel(PlayerModel player0, PlayerModel player1) {
		super(player0, player1);

		setCurrentQuarter(TIE);
		setPlayer0QuarterScores(new int[MAX_QUARTERS]);
		setPlayer1QuarterScores(new int[MAX_QUARTERS]);
	}

	// Methods
	@Override
	public PlayerModel addScore(int score) {
		PlayerModel currentPlayer = getCurrentPlayer(), otherPlayer = getOtherPlayer();
		int[] currentPlayerQuarterScores = getCurrentPlayerQuarterScores(),
				otherPlayerQuarterScores = getOtherPlayerQuarterScores();
		int currentPlayerTotalScore = NO_SCORE, otherPlayerTotalScore = NO_SCORE,
				currentQuarter = getNumericCurrentQuarter();

		// Checking whether the match could end
		currentPlayerQuarterScores[currentQuarter] = score;
		if (otherPlayerQuarterScores[currentQuarter] != NO_SCORE) { // Both players have played the same
																	// amount of quarters
			if (currentQuarter == MAX_QUARTERS) {
				currentPlayerTotalScore = getCurrentPlayerTotalScore();
				otherPlayerTotalScore = getOtherPlayerTotalScore();
				if (Math.abs(currentPlayerTotalScore - otherPlayerTotalScore) != TIE)
					return Integer.compare(currentPlayerTotalScore, otherPlayerTotalScore) > TIE ? currentPlayer
							: otherPlayer;
			}

			currentQuarter++;
		}

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
		BaketballMatchModel other = (BaketballMatchModel) obj;
		if (!Arrays.equals(player0QuarterScores, other.player0QuarterScores))
			return false;
		if (!Arrays.equals(player1QuarterScores, other.player1QuarterScores))
			return false;
		return true;
	}
}