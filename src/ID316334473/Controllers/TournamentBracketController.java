package ID316334473.Controllers;

import java.util.ArrayList;

import ID316334473.Views.MatchBracketView;
import ID316334473.Views.TournamentBracketView;
import ID316334473.Views.View;

public class TournamentBracketController extends Controller {
	// Constants

	// Fields
	private ArrayList<MatchBracketController> quarterFinalsBracketControllers, semiFinalsBracketControllers;
	private MatchBracketController finalsBracketController;

	// Properties (Getters and Setters)
	public TournamentBracketView getTournamentBracketView() {
		return (TournamentBracketView) getView();
	}

	public ArrayList<MatchBracketController> getQuarterFinalsBracketControllers() {
		return quarterFinalsBracketControllers;
	}

	public void setQuarterFinalsBracketControllers(ArrayList<MatchBracketController> quarterFinalsBracketControllers) {
		this.quarterFinalsBracketControllers = quarterFinalsBracketControllers;
	}

	public ArrayList<MatchBracketController> getSemiFinalsBracketControllers() {
		return semiFinalsBracketControllers;
	}

	public void setSemiFinalsBracketControllers(ArrayList<MatchBracketController> semiFinalsBracketControllers) {
		this.semiFinalsBracketControllers = semiFinalsBracketControllers;
	}

	public MatchBracketController getFinalsBracketController() {
		return finalsBracketController;
	}

	public void setFinalsBracketController(MatchBracketController finalsBracketController) {
		this.finalsBracketController = finalsBracketController;
	}

	// Constructors
	public TournamentBracketController(View view, ArrayList<MatchBracketView> quarterFinalsBracketViews,
			ArrayList<MatchBracketView> semiFinalsBracketViews, MatchBracketView finalsBracketView) {
		super(view);

		setQuarterFinalsBracketControllers(new ArrayList<MatchBracketController>(quarterFinalsBracketViews.size()));
		setSemiFinalsBracketControllers(new ArrayList<MatchBracketController>(semiFinalsBracketViews.size()));
		setFinalsBracketController(new MatchBracketController(finalsBracketView));

		initQuarterAndSemiFinalsControllers(quarterFinalsBracketViews, semiFinalsBracketViews);
	}

	// Methods
	private void initQuarterAndSemiFinalsControllers(ArrayList<MatchBracketView> quarterFinalsBracketViews,
			ArrayList<MatchBracketView> semiFinalsBracketViews) {
		for (int i = 0; i < quarterFinalsBracketViews.size(); i++)
			quarterFinalsBracketControllers.add(new MatchBracketController(quarterFinalsBracketViews.get(i)));
		for (int i = 0; i < semiFinalsBracketViews.size(); i++)
			semiFinalsBracketControllers.add(new MatchBracketController(semiFinalsBracketViews.get(i)));
	}
}
