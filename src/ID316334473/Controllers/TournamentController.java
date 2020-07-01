package ID316334473.Controllers;

import ID316334473.Views.TournamentView;
import ID316334473.Views.View;

public class TournamentController extends WindowController {
	// Constants

	// Fields

	// Properties (Getters and Setters)
	public TournamentView getTournamentView() {
		return (TournamentView) getView();
	}

	// Constructors
	public TournamentController(View view) {
		super(view);
	}

	// Methods
}
