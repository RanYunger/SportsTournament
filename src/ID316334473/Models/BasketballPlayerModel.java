package ID316334473.Models;

import ID316334473.Models.TournamentModel.GameType;

public class BasketballPlayerModel extends PlayerModel {
	// Constants

	// Fields

	// Properties (Getters and Setters)

	// Constructors
	public BasketballPlayerModel(int ID, String name, Gender gender) {
		super(ID, name, gender, GameType.Basketball);
	}

	// Methods
}
