package ID316334473.Models;

import java.util.Arrays;

import ID316334473.UIHandler;
import ID316334473.Models.PlayerModel.Gender;

public class BasketballMatchModel extends MatchModel {
	// Constants
	public static final int MAX_QUARTERS = 3; // Zero-based

	// Fields
	private int[] player0QuarterScores, player1QuarterScores;
	private int currentQuarter;

	// Properties (Getters and Setters)
	public int getCurrentQuarter() {
		return currentQuarter;
	}

	public void setCurrentQuarter(int currentQuarter) {
		this.currentQuarter = currentQuarter;
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

	// Constructors
	public BasketballMatchModel(PlayerModel player0, PlayerModel player1) {
		super(player0, player1);

		setCurrentQuarter(TIE);
		setPlayer0QuarterScores(new int[MAX_QUARTERS + 1]);
		setPlayer1QuarterScores(new int[MAX_QUARTERS + 1]);
	}

	// Methods
	@Override
	public PlayerModel addScore(int score) {
		PlayerModel currentPlayer = getCurrentPlayer(), otherPlayer = getOtherPlayer();
		int[] currentPlayerQuarterScores = getCurrentPlayerQuarterScores(),
				otherPlayerQuarterScores = getOtherPlayerQuarterScores();
		int currentPlayerMatchScore = NO_SCORE, otherPlayerMatchScore = NO_SCORE;

		// Validations
		if (currentQuarter > MAX_QUARTERS)
			return null;

		currentPlayerQuarterScores[currentQuarter] = score;
		currentPlayer.accumulateScore(score);

		if ((getOtherPlayerQuarterScores()[currentQuarter] == NO_SCORE) && (currentQuarter == MAX_QUARTERS))
			UIHandler.playAudio(otherPlayer.getGender() == Gender.Male ? "FinishHim.mp3" : "FinishHer.mp3");
		if (otherPlayerQuarterScores[currentQuarter] != NO_SCORE) { // Both players have played the current quarter
			currentPlayer.setMatchscore(currentPlayer.getNumericMatchScore() + score);
			otherPlayer.setMatchscore(otherPlayer.getNumericMatchScore() + otherPlayerQuarterScores[currentQuarter]);

			// Conditions to end the match
			if (currentQuarter == MAX_QUARTERS) {
				currentPlayerMatchScore = currentPlayer.getNumericMatchScore();
				otherPlayerMatchScore = otherPlayer.getNumericMatchScore();

				setWinner(Integer.compare(currentPlayerMatchScore, otherPlayerMatchScore) > TIE ? currentPlayer
						: otherPlayer);

				UIHandler.showSuccess(winner.getTextualName() + " wins!", false);
				UIHandler.playAudio(loser.getNumericMatchScore() == TIE ? "FlawlessVictory.mp3" : "Whistle.mp3");

				return winner;
			}

			currentQuarter++;
			UIHandler.playAudio(currentQuarter == MAX_QUARTERS ? "FinalRound.mp3"
					: String.format("Round%d.mp3", currentQuarter + 1));
		}

		toggleTurn();

		return null; // The match goes on
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BasketballMatchModel other = (BasketballMatchModel) obj;
		if (currentQuarter != other.currentQuarter)
			return false;
		if (!Arrays.equals(player0QuarterScores, other.player0QuarterScores))
			return false;
		if (!Arrays.equals(player1QuarterScores, other.player1QuarterScores))
			return false;
		return true;
	}
}