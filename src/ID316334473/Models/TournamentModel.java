package ID316334473.Models;

import java.util.TreeMap;

import javafx.collections.ObservableList;

public class TournamentModel {
	// Constants
	public static final int MAX_PARTICIPANTS = 8;

	public enum GameType {
		Tennis, Basketball, Football
	}

	// Fields
	private GameType gameType;
	private ObservableList<PlayerModel> participants;
	private TreeMap<PlayerModel, Double> results;

	// Properties (Getters and Setters)
	public GameType getGameType() {
		return gameType;
	}

	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}

	public ObservableList<PlayerModel> getParticipants() {
		return participants;
	}

	public void setParticipants(ObservableList<PlayerModel> participants) {
		this.participants = participants;
	}

	public TreeMap<PlayerModel, Double> getResults() {
		return results;
	}

	public void setResults(TreeMap<PlayerModel, Double> results) {
		this.results = results;
	}

	// Constructors
	public TournamentModel(GameType gameType, ObservableList<PlayerModel> participants) {
		setGameType(gameType);
		setParticipants(participants);
		setResults(new TreeMap<PlayerModel, Double>());
	}

	// Methods
	public boolean addParticipant(PlayerModel participant) {
		return participants.size() < MAX_PARTICIPANTS ? participants.add(participant) : false;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TournamentModel other = (TournamentModel) obj;
		if (gameType != other.gameType)
			return false;
		if (participants == null) {
			if (other.participants != null)
				return false;
		} else if (!participants.equals(other.participants))
			return false;
		if (results == null) {
			if (other.results != null)
				return false;
		} else if (!results.equals(other.results))
			return false;
		return true;
	}
}