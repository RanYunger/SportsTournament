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
	private ObservableList<ParticipantModel> participants;
	private TreeMap<ParticipantModel, Double> results;

	// Properties (Getters and Setters)
	public GameType getGameType() {
		return gameType;
	}

	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}

	public ObservableList<ParticipantModel> getParticipants() {
		return participants;
	}

	public void setParticipants(ObservableList<ParticipantModel> participants) {
		this.participants = participants;
	}

	public TreeMap<ParticipantModel, Double> getResults() {
		return results;
	}

	public void setResults(TreeMap<ParticipantModel, Double> results) {
		this.results = results;
	}

	// Constructors
	public TournamentModel(GameType gameType, ObservableList<ParticipantModel> participants) {
		setGameType(gameType);
		setParticipants(participants);
		setResults(new TreeMap<ParticipantModel, Double>());
	}

	// Methods
	public boolean addParticipant(ParticipantModel participant) {
		return participants.size() < MAX_PARTICIPANTS ? participants.add(participant) : false;
	}
}
