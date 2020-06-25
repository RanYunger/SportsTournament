package ID316334473.Views;

import ID316334473.UIHandler;
import ID316334473.Models.TournamentModel.GameType;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class AddPlayerView extends View {
	// Constants

	// Fields
	private GameType game;

	// Properties (Getters and Setters)
	public GameType getGame() {
		return game;
	}

	public void setGame(GameType game) {
		this.game = game;
	}

	// Constructors
	public AddPlayerView(GameType game) {
		super();
		
		setGame(game);
		
		buildScene();
		addEffects();
	}

	// Methods
	@Override
	protected void buildScene() {
		double sceneWidth = 1500, sceneHeight = 700, backgroundFontSize = 50, viewFontSize = 15;
		
		stage.setScene(
				new Scene(UIHandler.buildBackground(game.name() + "Arena.jpg", new GridPane(), sceneWidth, sceneHeight, backgroundFontSize),
						sceneWidth, sceneHeight));
		UIHandler.setGeneralFeatures(stage);

		stage.show();
	}

	@Override
	protected void addEffects() {
		super.addEffects();
	}
}
