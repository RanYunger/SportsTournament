package ID316334473.Models;

import ID316334473.UIHandler;
import ID316334473.Models.TournamentModel.GameType;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public abstract class PlayerModel implements Comparable<PlayerModel> {
	// Constants
	public static final int NO_SCORE = 0, ID_LENGTH = 9;

	// Fields
	private SimpleIntegerProperty ID;
	private SimpleStringProperty name;
	private GameType game;
	private SimpleIntegerProperty matchScore, tournamentScore;

	// Properties (Getters and Setters)
	public SimpleIntegerProperty getObservableID() {
		return ID;
	}

	public int getNumericID() {
		return ID.get();
	}

	private void setID(int ID) {
		if (String.valueOf(ID).length() != ID_LENGTH)
			UIHandler.showError("Player's ID must contain exactly 9 digits.");
		this.ID = new SimpleIntegerProperty(ID);
	}

	public SimpleStringProperty getObservableName() {
		return name;
	}

	public String getTextualName() {
		return name.get();
	}

	private void setName(String name) {
		if (name.isBlank())
			UIHandler.showError("CitizenModel's name must contain at least 1 letter.");
		this.name = new SimpleStringProperty(name);
	}

	public GameType getGame() {
		return game;
	}

	public void setGame(GameType game) {
		this.game = game;
	}

	public SimpleIntegerProperty getObservableMatchScore() {
		return matchScore;
	}

	public int getNumericMatchScore() {
		return matchScore.get();
	}

	public void setMatchscore(int matchScore) {
		if (matchScore < NO_SCORE)
			UIHandler.showError("Player's score must be a non-negative number.");
		this.matchScore = new SimpleIntegerProperty(matchScore);
	}

	public SimpleIntegerProperty getObservableTournamentScore() {
		return tournamentScore;
	}

	public int getNumericTournamentScore() {
		return tournamentScore.get();
	}

	public void setTournamentScore(int tournamentScore) {
		if (tournamentScore < NO_SCORE)
			UIHandler.showError("Player's score must be a non-negative number.");
		this.tournamentScore = new SimpleIntegerProperty(tournamentScore);
	}

	// Constructors
	public PlayerModel(int ID, String name, GameType game) {
		setID(ID);
		setName(name);
		setGame(game);
		setMatchscore(NO_SCORE);
		setTournamentScore(NO_SCORE);
	}

	// Methods
	public void accumulateScore(int addedScore) {
		setTournamentScore(getNumericTournamentScore() + addedScore);
	}

	@Override
	public int compareTo(PlayerModel other) {
		return Integer.compare(getNumericID(), other.getNumericID());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlayerModel other = (PlayerModel) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		if (game != other.game)
			return false;
		if (matchScore == null) {
			if (other.matchScore != null)
				return false;
		} else if (!matchScore.equals(other.matchScore))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (tournamentScore == null) {
			if (other.tournamentScore != null)
				return false;
		} else if (!tournamentScore.equals(other.tournamentScore))
			return false;
		return true;
	}
}