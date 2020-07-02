package ID316334473.Views;

public abstract class MatchView extends WindowView {
	// Constants

	// Fields
	private MatchBracketView parentView;

	// Properties (Getters and Setters)
	public MatchBracketView getParentView() {
		return parentView;
	}

	public void setParentView(MatchBracketView parentView) {
		this.parentView = parentView;
	}

	// Constructors
	public MatchView(MatchBracketView parentView) {
		super();

		setParentView(parentView);
	}

	// Methods
}
