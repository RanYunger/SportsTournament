package ID316334473.Views;

import ID316334473.UIHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ChampionshipsView extends View {
	// Constants

	// Fields
	private HBox optionsHBox;
	private ImageView tennisImageView, basketballImageView, footballImageView;

	// Properties (Getters and Setters)	

	// Constructors
	public ChampionshipsView() {
		super();
		
		buildScene();
		addEffects();
	}

	// Methods
	@Override
	protected void buildScene() {
		double sceneWidth = 1500, sceneHeight = 700, backgroundFontSize = 50;

		optionsHBox = new HBox();
		tennisImageView = UIHandler.buildImage("Tennis.png", 300, 300);
		basketballImageView = UIHandler.buildImage("Basketball.png", 300, 300);
		footballImageView = UIHandler.buildImage("Football.png", 280, 280);

		optionsHBox.setAlignment(Pos.CENTER);

		optionsHBox.getChildren().addAll(tennisImageView, basketballImageView, footballImageView);
		HBox.setMargin(tennisImageView, new Insets(0, 50, 0, 0));
		HBox.setMargin(basketballImageView, new Insets(0, 50, 0, 50));
		HBox.setMargin(footballImageView, new Insets(0, 0, 0, 60));

		stage.setScene(new Scene(UIHandler.buildBackground("Arena.jpg", optionsHBox, sceneWidth, sceneHeight, backgroundFontSize),
				sceneWidth, sceneHeight));
		UIHandler.setGeneralFeatures(stage);

		stage.show();
	}

	@Override
	protected void addEffects() {
		super.addEffects();
		
		UIHandler.addAudioToImageView(tennisImageView, "Hit.mp3");
		UIHandler.addAudioToImageView(basketballImageView, "Bounce.mp3");
		UIHandler.addAudioToImageView(footballImageView, "Kick.mp3");
	}
}
