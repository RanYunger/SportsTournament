package ID316334473.Views;

import ID316334473.Models.BasketballMatchModel;
import javafx.scene.Node;

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
		// TODO: COMPLETE
	}

	@Override
	protected Node asNode() {
		// TODO Auto-generated method stub
		return null;
	}
}
