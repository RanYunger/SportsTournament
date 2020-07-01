package ID316334473.Views;

import ID316334473.UIHandler;
import ID316334473.Models.PlayerModel;
import ID316334473.Models.TournamentModel.GameType;
import ID316334473.Views.BracketViews.TournamentBracketView;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;

public class TournamentView extends WindowView {
	// Constants

	// Fields
	private TournamentBracketView tournamentBracketView;
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

		tournamentBracketView = new TournamentBracketView(players);

		stage.setScene(new Scene(UIHandler.buildBackground(gameName + "Arena.jpg", tournamentBracketView.asNode(),
				sceneWidth, sceneHeight, backgroundFontSize), sceneWidth, sceneHeight));
		UIHandler.setGeneralFeatures(stage);

		stage.show();
	}

	@Override
	protected void addEffects() {
		super.addEffects();
	}

	@Override
	protected Node asNode() {
		return (Node) tournamentBracketView.asNode();
	}
}
