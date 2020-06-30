package ID316334473.Views;

import ID316334473.Models.PlayerModel;
import javafx.collections.ObservableList;

public class TournamentBracketView extends View {
	// Constants

	// Fields
	private ObservableList<PlayerModel> tournamentPlayers;

	// Properties (Getters and Setters)
	public ObservableList<PlayerModel> getTournamentPlayers() {
		return tournamentPlayers;
	}

	public void setTournamentPlayers(ObservableList<PlayerModel> tournamentPlayers) {
		this.tournamentPlayers = tournamentPlayers;
	}

	// Constructors
	public TournamentBracketView(ObservableList<PlayerModel> tournamentPlayers) {
		setTournamentPlayers(tournamentPlayers);
		
		buildScene();
	}

	// Methods
	@Override
	protected void buildScene() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void addEffects() {
		// TODO Auto-generated method stub
		
	}
}
