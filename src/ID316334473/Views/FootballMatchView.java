package ID316334473.Views;

import ID316334473.UIHandler;
import ID316334473.Models.FootballMatchModel;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class FootballMatchView extends MatchView {
	// Constants

	// Fields
	private FootballMatchModel footballMatch;

	// Properties (Getters and Setters)
	public FootballMatchModel getFootballMatch() {
		return footballMatch;
	}

	public void setFootballMatch(FootballMatchModel footballMatch) {
		this.footballMatch = footballMatch;
	}

	// Constructors
	public FootballMatchView(MatchBracketView parentView, FootballMatchModel footballMatch) {
		super(parentView);

		setFootballMatch(footballMatch);

		buildScene();
		addEffects();
	}

	// Methods
	@Override
	protected void buildScene() {
		double sceneWidth = 700, sceneHeight = 500, backgroundFontSize = 30;

		stage.setScene(new Scene(UIHandler.buildBackground("FootballArena.jpg", new GridPane(), sceneWidth, sceneHeight,
				backgroundFontSize), sceneWidth, sceneHeight));
		UIHandler.setGeneralFeatures(stage);

		stage.show();
	}

	@Override
	protected Node asNode() {
		// TODO Auto-generated method stub
		return null;
	}
}
