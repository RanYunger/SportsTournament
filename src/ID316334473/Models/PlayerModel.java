package ID316334473.Models;

import ID316334473.UIHandler;
import ID316334473.Models.TournamentModel.GameType;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public abstract class PlayerModel {
	// Constants

	// Fields
	private SimpleIntegerProperty ID;
	private SimpleStringProperty name;
	private GameType game;
	private SimpleIntegerProperty score;

	// Properties (Getters and Setters)
	public SimpleIntegerProperty getObservableID() {
		return ID;
	}

	public int getNumericID() {
		return ID.get();
	}

	private void setID(int ID) {
		if (String.valueOf(ID).length() != 9)
			UIHandler.showError("Participant's ID must contain exactly 9 digits.");
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

	public SimpleIntegerProperty getObservableScore() {
		return score;
	}

	public int getNumericScore() {
		return score.get();
	}

	private void setscore(int score) {
		if (score < 0)
			UIHandler.showError("Participant's ID must contain exactly 9 digits.");
		this.score = new SimpleIntegerProperty(score);
	}

	// Constructors
	public PlayerModel(int ID, String name, GameType game) {
		setID(ID);
		setName(name);
		setGame(game);
		setscore(0);
	}

	// Methods

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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (score == null) {
			if (other.score != null)
				return false;
		} else if (!score.equals(other.score))
			return false;
		return true;
	}
}