package ID316334473.Views;

import ID316334473.UIHandler;
import ID316334473.Models.TennisMatchModel;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class TennisMatchView extends MatchView {
	// Constants

	// Fields
	private TennisMatchModel tennisMatch;

	// Properties (Getters and Setters)
	public TennisMatchModel getTennisMatch() {
		return tennisMatch;
	}

	public void setTennisMatch(TennisMatchModel tennisMatch) {
		this.tennisMatch = tennisMatch;
	}

	// Constructors
	public TennisMatchView(MatchBracketView parentView, TennisMatchModel tennisMatch) {
		super(parentView);

		setTennisMatch(tennisMatch);

		buildScene();
		addEffects();
	}

	// Methods
	@Override
	protected void buildScene() {
		double sceneWidth = 700, sceneHeight = 500, backgroundFontSize = 30;

		stage.setScene(new Scene(UIHandler.buildBackground("TennisArena.jpg", new GridPane(), sceneWidth, sceneHeight,
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
