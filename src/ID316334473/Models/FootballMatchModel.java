package ID316334473.Models;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.beans.property.SimpleIntegerProperty;

public class FootballMatchModel extends MatchModel {
	// Constants
	public static final int GOAL = 1;
	public static final int MAX_HALVES = 3;

	// Fields
	private SimpleIntegerProperty currentHalf, currentPenaltyKick;
	private int[] player0Halves, player1Halves;
	private ArrayList<Boolean> player0PenaltyKicks, player1PenaltyKicks;

	// Properties (Getters and Setters)
	public SimpleIntegerProperty getObservableCurrentHalf() {
		return currentHalf;
	}

	public int getNumericCurrentHalf() {
		return currentHalf.get();
	}

	public void setCurrentHalf(int currentHalf) {
		this.currentHalf = new SimpleIntegerProperty(currentHalf);
	}

	public SimpleIntegerProperty getObservableCurrentPenaltyKick() {
		return currentPenaltyKick;
	}

	public int getNumericCurrentPenaltyKick() {
		return currentPenaltyKick.get();
	}

	public void setCurrentPenaltyKick(int currentPenaltyKick) {
		this.currentPenaltyKick = new SimpleIntegerProperty(currentPenaltyKick);
	}

	public int[] getPlayer0Halves() {
		return player0Halves;
	}

	public void setPlayer0Halves(int[] Player0Halves) {
		this.player0Halves = Player0Halves;
		Arrays.setAll(this.player0Halves, p -> NO_SCORE);
	}

	public int[] getPlayer1Halves() {
		return player1Halves;
	}

	public void setPlayer1Halves(int[] Player1Halves) {
		this.player1Halves = Player1Halves;
		Arrays.setAll(this.player1Halves, p -> NO_SCORE);
	}

	public ArrayList<Boolean> getPlayer0PenaltyKicks() {
		return player0PenaltyKicks;
	}

	public void setPlayer0PenaltyKicks(ArrayList<Boolean> Player0PenaltyKicks) {
		this.player0PenaltyKicks = Player0PenaltyKicks;
	}

	public ArrayList<Boolean> getPlayer1PenaltyKicks() {
		return player1PenaltyKicks;
	}

	public void setPlayer1PenaltyKicks(ArrayList<Boolean> Player1PenaltyKicks) {
		this.player1PenaltyKicks = Player1PenaltyKicks;
	}

	public int[] getCurrentPlayerHalves() {
		return getCurrentPlayer().equals(player0) ? player0Halves : player1Halves;
	}

	public int[] getOtherPlayerHalves() {
		return getCurrentPlayer().equals(player0) ? player1Halves : player0Halves;
	}

	public ArrayList<Boolean> getCurrentPlayerPenaltyKicks() {
		return getCurrentPlayer().equals(player0) ? player0PenaltyKicks : player1PenaltyKicks;
	}

	public ArrayList<Boolean> getOtherPlayerPenaltyKicks() {
		return getCurrentPlayer().equals(player0) ? player1PenaltyKicks : player0PenaltyKicks;
	}

	public int getCurrentPlayerTotalHalvesScore() {
		int sum = TIE;
		int[] currentPlayerHalves = getCurrentPlayerHalves();

		for (int i = TIE; i < currentPlayerHalves.length; i++)
			sum += currentPlayerHalves[i] != NO_SCORE ? currentPlayerHalves[i] : TIE;

		return sum;
	}

	public int getCurrentPlayerTotalPenaltyKicksScore() {
		int sum = TIE;
		ArrayList<Boolean> currentPlayerPenaltyKicks = getCurrentPlayerPenaltyKicks();

		for (int i = TIE; i < currentPlayerPenaltyKicks.size(); i++)
			sum += currentPlayerPenaltyKicks.get(i) ? GOAL : TIE;

		return sum;
	}

	public int getCurrentPlayerTotalScore() {
		return getCurrentPlayerTotalHalvesScore() + getCurrentPlayerTotalPenaltyKicksScore();
	}

	public int getOtherPlayerTotalHalvesScore() {
		int sum = TIE;
		int[] otherPlayerHalves = getOtherPlayerHalves();

		for (int i = TIE; i < otherPlayerHalves.length; i++)
			sum += otherPlayerHalves[i] != NO_SCORE ? otherPlayerHalves[i] : TIE;

		return sum;
	}

	public int getOtherPlayerTotalPenaltyKicksScore() {
		int sum = TIE;
		ArrayList<Boolean> otherPlayerPenaltyKicks = getOtherPlayerPenaltyKicks();

		for (int i = TIE; i < otherPlayerPenaltyKicks.size(); i++)
			sum += otherPlayerPenaltyKicks.get(i) ? GOAL : TIE;

		return sum;
	}

	public int getOtherPlayerTotalScore() {
		return getOtherPlayerTotalHalvesScore() + getOtherPlayerTotalPenaltyKicksScore();
	}

	// Constructors
	public FootballMatchModel(PlayerModel player0, PlayerModel player1) {
		super(player0, player1);

		setCurrentHalf(TIE);
		setCurrentPenaltyKick(TIE);
		setPlayer0Halves(new int[MAX_HALVES]);
		setPlayer1Halves(new int[MAX_HALVES]);
		setPlayer0PenaltyKicks(new ArrayList<Boolean>());
		setPlayer1PenaltyKicks(new ArrayList<Boolean>());
	}

	// Methods
	@Override
	public PlayerModel addScore(int score) {
		PlayerModel currentPlayer = getCurrentPlayer(), otherPlayer = getOtherPlayer();
		int[] currentPlayerHalves = getCurrentPlayerHalves(), otherPlayerHalves = getOtherPlayerHalves();
		int currentPlayerTotalHalvesScore = NO_SCORE, otherPlayerTotalHalvesScore = NO_SCORE,
				currentHalf = getNumericCurrentHalf();

		// Checking whether the match could end
		if (currentHalf < MAX_HALVES) {
			currentPlayerHalves[currentHalf] = score;
			if (otherPlayerHalves[currentHalf] != NO_SCORE) { // Both players have played the same amount of halves
				currentPlayerTotalHalvesScore = getCurrentPlayerTotalHalvesScore();
				otherPlayerTotalHalvesScore = getOtherPlayerTotalHalvesScore();
				if (Math.abs(currentPlayerTotalHalvesScore - otherPlayerTotalHalvesScore) != TIE) {
					setWinner(Integer.compare(currentPlayerTotalHalvesScore, otherPlayerTotalHalvesScore) > TIE
							? currentPlayer
							: otherPlayer);

					return winner;
				}
			}

			currentHalf++;
		}
		
		toggleTurn();

		return null; // The match goes on
	}

	public PlayerModel addPenaltyKick(boolean isGoal) {
		PlayerModel currentPlayer = getCurrentPlayer(), otherPlayer = getOtherPlayer();
		ArrayList<Boolean> currentPlayerPenaltyKicks = getCurrentPlayerPenaltyKicks(),
				otherPlayerPenaltyKicks = getOtherPlayerPenaltyKicks();
		int currentPlayerTotalPenaltyKicksScore = NO_SCORE, otherPlayerTotalPenaltyKicksScore = NO_SCORE,
				currentPenaltyKick = getNumericCurrentPenaltyKick();

		// Checking whether the match could end
		currentPlayerPenaltyKicks.add(isGoal);
		if (Math.abs(otherPlayerPenaltyKicks.size() - currentPenaltyKick) == TIE) { // Both players have played the same
																					// amount of kicks
			currentPlayerTotalPenaltyKicksScore = getCurrentPlayerTotalPenaltyKicksScore();
			otherPlayerTotalPenaltyKicksScore = getOtherPlayerTotalPenaltyKicksScore();
			if (Math.abs(currentPlayerTotalPenaltyKicksScore - otherPlayerTotalPenaltyKicksScore) != TIE)
				return Integer.compare(currentPlayerTotalPenaltyKicksScore, otherPlayerTotalPenaltyKicksScore) > TIE
						? currentPlayer
						: otherPlayer;

			currentPenaltyKick++;
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