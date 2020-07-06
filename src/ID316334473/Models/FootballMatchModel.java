package ID316334473.Models;

import java.util.Arrays;

import ID316334473.UIHandler;
import ID316334473.Models.PlayerModel.Gender;

public class FootballMatchModel extends MatchModel {
	// Constants
	public static final int GOAL = 1;
	public static final int MAX_HALVES = 2; // Zero-based
	public static final int MAX_PENALTY_KICKS = 9;
	public static final int MIN_WARNING_BARRIER = 6;

	// Fields
	private int[] player0Halves, player1Halves, player0PenaltyKicks, player1PenaltyKicks;
	private int currentHalf, currentPenaltyKick;

	// Properties (Getters and Setters)
	public int getCurrentHalf() {
		return currentHalf;
	}

	public void setCurrentHalf(int currentHalf) {
		this.currentHalf = currentHalf;
	}

	public int getCurrentPenaltyKick() {
		return currentPenaltyKick;
	}

	public void setCurrentPenaltyKick(int currentPenaltyKick) {
		this.currentPenaltyKick = currentPenaltyKick;
	}

	public int[] getPlayer0Halves() {
		return player0Halves;
	}

	public void setPlayer0Halves(int[] player0Halves) {
		this.player0Halves = player0Halves;
		Arrays.setAll(this.player0Halves, p -> NO_SCORE);
	}

	public int[] getPlayer1Halves() {
		return player1Halves;
	}

	public void setPlayer1Halves(int[] player1Halves) {
		this.player1Halves = player1Halves;
		Arrays.setAll(this.player1Halves, p -> NO_SCORE);
	}

	public int[] getPlayer0PenaltyKicks() {
		return player0PenaltyKicks;
	}

	public void setPlayer0PenaltyKicks(int[] player0PenaltyKicks) {
		this.player0PenaltyKicks = player0PenaltyKicks;
		Arrays.setAll(this.player0PenaltyKicks, p -> NO_SCORE);

	}

	public int[] getPlayer1PenaltyKicks() {
		return player1PenaltyKicks;
	}

	public void setPlayer1PenaltyKicks(int[] player1PenaltyKicks) {
		this.player1PenaltyKicks = player1PenaltyKicks;
		Arrays.setAll(this.player1PenaltyKicks, p -> NO_SCORE);

	}

	public int[] getCurrentPlayerHalves() {
		return getCurrentPlayer().equals(player0) ? player0Halves : player1Halves;
	}

	public int[] getOtherPlayerHalves() {
		return getCurrentPlayer().equals(player0) ? player1Halves : player0Halves;
	}

	public int[] getCurrentPlayerPenaltyKicks() {
		return getCurrentPlayer().equals(player0) ? player0PenaltyKicks : player1PenaltyKicks;
	}

	public int[] getOtherPlayerPenaltyKicks() {
		return getCurrentPlayer().equals(player0) ? player1PenaltyKicks : player0PenaltyKicks;
	}

	// Constructors
	public FootballMatchModel(PlayerModel player0, PlayerModel player1) {
		super(player0, player1);

		setCurrentHalf(TIE);
		setCurrentPenaltyKick(TIE);
		setPlayer0Halves(new int[MAX_HALVES + 1]);
		setPlayer1Halves(new int[MAX_HALVES + 1]);
		setPlayer0PenaltyKicks(new int[MAX_PENALTY_KICKS + 1]);
		setPlayer1PenaltyKicks(new int[MAX_PENALTY_KICKS + 1]);
	}

	// Methods
	@Override
	public PlayerModel addScore(int score) {
		PlayerModel currentPlayer = getCurrentPlayer(), otherPlayer = getOtherPlayer();
		int[] currentPlayerHalves = getCurrentPlayerHalves(), otherPlayerHalves = getOtherPlayerHalves();
		int currentPlayerMatchScore = NO_SCORE, otherPlayerMatchScore = NO_SCORE;

		// Validations
		if (currentHalf > MAX_HALVES)
			return addPenaltyKick(score > TIE);

		currentPlayerHalves[currentHalf] = score;
		currentPlayer.accumulateScore(score);

		if ((getOtherPlayerHalves()[currentHalf] == NO_SCORE) && (currentHalf > TIE))
			UIHandler.playAudio(otherPlayer.getGender() == Gender.Male ? "FinishHim.mp3" : "FinishHer.mp3");
		if (otherPlayerHalves[currentHalf] != NO_SCORE) { // Both players have played the same amount of halves
			currentPlayer.setMatchscore(currentPlayer.getNumericMatchScore() + score);
			otherPlayer.setMatchscore(otherPlayer.getNumericMatchScore() + otherPlayerHalves[currentHalf]);

			if (currentHalf > TIE) { // After at least 2 halves
				currentPlayerMatchScore = currentPlayer.getNumericMatchScore();
				otherPlayerMatchScore = otherPlayer.getNumericMatchScore();
				// Conditions to end the match
				if (Math.abs(currentPlayerMatchScore - otherPlayerMatchScore) != TIE) {
					setWinner(Integer.compare(currentPlayerMatchScore, otherPlayerMatchScore) > TIE ? currentPlayer
							: otherPlayer);

					UIHandler.showSuccess(winner.getTextualName() + " wins!", false);
					UIHandler.playAudio(loser.getNumericMatchScore() == TIE ? "FlawlessVictory.mp3" : "Whistle.mp3");

					return winner;
				}
			}

			currentHalf++;
			if (currentHalf <= MAX_HALVES)
				UIHandler.playAudio(
						currentHalf == MAX_HALVES ? "FinalRound.mp3" : String.format("Round%d.mp3", currentHalf + 1));
			else // penalty kicks
			{
				UIHandler.showWarning(
						"We've entered the penalty kicks rounds. Each non-negative score will be counted as a single goal!",
						false);
				UIHandler.playAudio("Round1.mp3");
			}
		}

		toggleTurn();

		return null; // The match goes on
	}

	private PlayerModel addPenaltyKick(boolean isGoal) {
		PlayerModel currentPlayer = getCurrentPlayer(), otherPlayer = getOtherPlayer();
		int[] currentPlayerPenaltyKicks = getCurrentPlayerPenaltyKicks(),
				otherPlayerPenaltyKicks = getOtherPlayerPenaltyKicks();
		int currentPlayerMatchScore = NO_SCORE, otherPlayerMatchScore = NO_SCORE;

		// Validations
		if (currentPenaltyKick > MAX_PENALTY_KICKS)
			return null;

		currentPlayerPenaltyKicks[currentPenaltyKick] = isGoal ? GOAL : TIE;
		currentPlayer.accumulateScore(currentPlayerPenaltyKicks[currentPenaltyKick]);
		if (otherPlayerPenaltyKicks[currentPenaltyKick] != NO_SCORE) { // Both players have played the same amount of
																		// penalty kicks
			currentPlayer.setMatchscore(
					currentPlayer.getNumericMatchScore() + currentPlayerPenaltyKicks[currentPenaltyKick]);
			otherPlayer.setMatchscore(otherPlayer.getNumericMatchScore() + otherPlayerPenaltyKicks[currentPenaltyKick]);

			currentPlayerMatchScore = currentPlayer.getNumericMatchScore();
			otherPlayerMatchScore = otherPlayer.getNumericMatchScore();

			// Conditions to end the match
			if ((currentPenaltyKick == MAX_PENALTY_KICKS)
					|| (Math.abs(currentPlayerMatchScore - otherPlayerMatchScore) != TIE)) {
				setWinner(Integer.compare(currentPlayerMatchScore, otherPlayerMatchScore) > TIE ? currentPlayer
						: otherPlayer);

				UIHandler.showSuccess(winner.getTextualName() + " wins!", false);
				if (Math.abs(currentPlayerMatchScore - otherPlayerMatchScore) == TIE)
					UIHandler.playAudio("Impressive.mp3");
				else
					UIHandler.playAudio(loser.getNumericMatchScore() == TIE ? "FlawlessVictory.mp3" : "Whistle.mp3");

				return winner;
			}

			// Urges the user to end match soon
			currentPenaltyKick++;
			if ((currentPenaltyKick > MIN_WARNING_BARRIER) && (currentPenaltyKick < MAX_PENALTY_KICKS))
				UIHandler.showWarning(
						String.format("The match will end in %d rounds!", (MAX_PENALTY_KICKS + 1) - currentPenaltyKick),
						false);
			if (currentPenaltyKick <= MAX_PENALTY_KICKS)
				UIHandler.playAudio(currentPenaltyKick == MAX_PENALTY_KICKS ? "FinalRound.mp3"
						: String.format("Round%d.mp3", currentPenaltyKick + 1));
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
		FootballMatchModel other = (FootballMatchModel) obj;
		if (!Arrays.equals(player0Halves, other.player0Halves))
			return false;
		if (player0PenaltyKicks == null) {
			if (other.player0PenaltyKicks != null)
				return false;
		} else if (!player0PenaltyKicks.equals(other.player0PenaltyKicks))
			return false;
		if (!Arrays.equals(player1Halves, other.player1Halves))
			return false;
		if (player1PenaltyKicks == null) {
			if (other.player1PenaltyKicks != null)
				return false;
		} else if (!player1PenaltyKicks.equals(other.player1PenaltyKicks))
			return false;
		return true;
	}
}