package ID316334473.Controllers;

import ID316334473.Views.TennisMatchView;
import ID316334473.Views.View;

public class TennisMatchController extends WindowController {
	// Constants

	// Fields

	// Properties (Getters and Setters)
	public TennisMatchView getTennisMatchView() {
		return (TennisMatchView) getView();
	}

	// Constructors
	public TennisMatchController(View view) {
		super(view);
		// TODO Auto-generated constructor stub
		TennisMatchView tennisMatchView = getTennisMatchView();

		tennisMatchView.getStage()
				.setOnCloseRequest(e -> tennisMatchView.getParentView().getPlayButton().setDisable(false));
	}

	// Methods
}
