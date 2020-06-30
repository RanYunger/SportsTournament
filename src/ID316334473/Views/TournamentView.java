package ID316334473.Views;

import ID316334473.UIHandler;
import ID316334473.Models.PlayerModel;
import ID316334473.Models.TournamentModel.GameType;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class TournamentView extends WindowView {
	// Constants

	// Fields
	private GameType game;
	private ObservableList<PlayerModel> players;

	// Properties (Getters and Setters)
	public GameType getGame() {
		return game;
	}

	public void setGame(GameType game) {
		this.game = game;
	}

	public ObservableList<PlayerModel> getPlayers() {
		return players;
	}

	public void setPlayers(ObservableList<PlayerModel> players) {
		this.players = players;
	}

	// Constructors
	public TournamentView(GameType game, ObservableList<PlayerModel> players) {
		super();

		setGame(game);
		setPlayers(players);

		buildScene();
		addEffects();
	}

	// Methods
	@Override
	protected void buildScene() {
		String gameName = game.name();
		double sceneWidth = 1500, sceneHeight = 700, backgroundFontSize = 50;

		stage.setScene(new Scene(UIHandler.buildBackground(gameName + "Arena.jpg",
				/* UIHandler.buildTournamentBracket(), */ new GridPane(), sceneWidth, sceneHeight, backgroundFontSize),
				sceneWidth, sceneHeight));
		UIHandler.setGeneralFeatures(stage);

		stage.show();
	}

	@Override
	protected void addEffects() {
		super.addEffects();
	}
}
