package ID316334473.Models;

import ID316334473.Models.TournamentModel.GameType;

public class TennisPlayerModel extends PlayerModel {
	// Constants

	// Fields

	// Properties (Getters and Setters)

	// Constructors
	public TennisPlayerModel(int ID, String name, Gender gender) {
		super(ID, name, gender, GameType.Tennis);
	}

	// Methods
}
