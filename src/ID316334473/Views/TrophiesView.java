package ID316334473.Views;

import ID316334473.UIHandler;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class TrophiesView extends View {
	// Constants

	// Fields

	// Properties (Getters and Setters)

	// Constructors
	public TrophiesView() {
		super();
		
		buildScene();
		addEffects();
	}

	// Methods
	@Override
	protected void buildScene() {
		double sceneWidth = 1500, sceneHeight = 700, backgroundFontSize = 50;
		
		stage.setScene(new Scene(
				UIHandler.buildBackground("Arena.jpg", new VBox(), sceneWidth, sceneHeight, backgroundFontSize),
				sceneWidth, sceneHeight));
		UIHandler.setGeneralFeatures(stage);

		stage.show();
	}

	@Override
	protected void addEffects() {
		super.addEffects();
	}
}
