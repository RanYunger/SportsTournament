package ID316334473.Controllers;

import ID316334473.Views.MatchBracketView;
import ID316334473.Views.TournamentBracketView;
import ID316334473.Views.View;

public class TournamentBracketController extends Controller {
	// Constants

	// Fields
	private MatchBracketController[] quarterFinalsBracketControllers, semiFinalsBracketControllers;
	private MatchBracketController finalsBracketController;

	// Properties (Getters and Setters)
	public TournamentBracketView getTournamentBracketView() {
		return (TournamentBracketView) getView();
	}

	public MatchBracketController[] getQuarterFinalsBracketControllers() {
		return quarterFinalsBracketControllers;
	}

	public void setQuarterFinalsBracketControllers(MatchBracketController[] quarterFinalsBracketControllers) {
		this.quarterFinalsBracketControllers = quarterFinalsBracketControllers;
	}

	public MatchBracketController[] getSemiFinalsBracketControllers() {
		return semiFinalsBracketControllers;
	}

	public void setSemiFinalsBracketControllers(MatchBracketController[] semiFinalsBracketControllers) {
		this.semiFinalsBracketControllers = semiFinalsBracketControllers;
	}

	public MatchBracketController getFinalsBracketController() {
		return finalsBracketController;
	}

	public void setFinalsBracketController(MatchBracketController finalsBracketController) {
		this.finalsBracketController = finalsBracketController;
	}

	// Constructors
	public TournamentBracketController(View view, MatchBracketView[] quarterFinalsBracketViews,
			MatchBracketView[] semiFinalsBracketViews, MatchBracketView finalsBracketView) {
		super(view);

		setQuarterFinalsBracketControllers(new MatchBracketController[quarterFinalsBracketViews.length]);
		setSemiFinalsBracketControllers(new MatchBracketController[semiFinalsBracketViews.length]);
		setFinalsBracketController(new MatchBracketController(finalsBracketView));

		initQuarterAndSemiFinalsControllers(quarterFinalsBracketViews, semiFinalsBracketViews);
	}

	// Methods
	private void initQuarterAndSemiFinalsControllers(MatchBracketView[] quarterFinalsBracketViews,
			MatchBracketView[] semiFinalsBracketViews) {
		for (int i = 0; i < quarterFinalsBracketViews.length; i++)
			quarterFinalsBracketControllers[i] = new MatchBracketController(quarterFinalsBracketViews[i]);
		for (int i = 0; i < semiFinalsBracketViews.length; i++)
			semiFinalsBracketControllers[i] = new MatchBracketController(semiFinalsBracketViews[i]);
	}
}
