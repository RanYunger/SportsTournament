package ID316334473.Controllers;

import ID316334473.Views.View;

public abstract class Controller {
	// Constants

	// Fields
	protected View view;

	// Properties (Getters and Setters)
	public View getView() {
		return view;
	}

	protected void setView(View view) {
		this.view = view;
	}

	// Constructors
	public Controller(View view) {
		setView(view);
	}

	// Methods
}
