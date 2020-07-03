package ID316334473.Views;

import ID316334473.Models.FootballMatchModel;
import javafx.scene.Node;

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
		// TODO: COMPLETE

	}

	@Override
	protected Node asNode() {
		// TODO Auto-generated method stub
		return null;
	}
}
