package ID316334473.Views;

import ID316334473.UIHandler;
import ID316334473.Models.BasketballMatchModel;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class BasketballMatchView extends MatchView {
	// Constants

	// Fields
	private BasketballMatchModel basketballMatch;

	// Properties (Getters and Setters)
	public BasketballMatchModel getBasketballMatch() {
		return basketballMatch;
	}

	public void setBasketballMatch(BasketballMatchModel basketballMatch) {
		this.basketballMatch = basketballMatch;
	}

	// Constructors
	public BasketballMatchView(MatchBracketView parentView, BasketballMatchModel basketballMatch) {
		super(parentView);

		setBasketballMatch(basketballMatch);

		buildScene();
		addEffects();
	}

	// Methods
	@Override
	protected void buildScene() {
		double sceneWidth = 700, sceneHeight = 500, backgroundFontSize = 30;

		stage.setScene(new Scene(UIHandler.buildBackground("BasketballArena.jpg", new GridPane(), sceneWidth,
				sceneHeight, backgroundFontSize), sceneWidth, sceneHeight));
		UIHandler.setGeneralFeatures(stage);

		stage.show();
	}

	@Override
	protected Node asNode() {
		// TODO Auto-generated method stub
		return null;
	}
}
