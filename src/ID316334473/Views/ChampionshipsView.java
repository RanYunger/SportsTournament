package ID316334473.Views;

import ID316334473.UIHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ChampionshipsView extends View {
	// Constants

	// Fields
	private HBox optionsHBox;
	private VBox tennisVBox, basketballVBox, footballVBox;
	private ImageView tennisImageView, basketballImageView, footballImageView;
	private Label tennisLabel, basketballLabel, footballLabel;

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
		double sceneWidth = 1500, sceneHeight = 700, backgroundFontSize = 50, viewFontSize = 30;

		optionsHBox = new HBox();
		tennisVBox = new VBox();
		basketballVBox = new VBox();
		footballVBox = new VBox();
		tennisImageView = UIHandler.buildImage("Tennis.png", 300, 300);
		basketballImageView = UIHandler.buildImage("Basketball.png", 300, 300);
		footballImageView = UIHandler.buildImage("Football.png", 280, 280);
		tennisLabel = new Label("Tennis");
		basketballLabel = new Label("Basketball");
		footballLabel = new Label("Football");

		optionsHBox.setAlignment(Pos.CENTER);
		tennisVBox.setAlignment(Pos.CENTER_LEFT);
		basketballVBox.setAlignment(Pos.CENTER);
		footballVBox.setAlignment(Pos.CENTER_RIGHT);
		tennisLabel.setFont(new Font(viewFontSize));
		tennisLabel.setTextFill(Color.WHITE);
		basketballLabel.setFont(new Font(viewFontSize));
		basketballLabel.setTextFill(Color.WHITE);
		footballLabel.setFont(new Font(viewFontSize));
		footballLabel.setTextFill(Color.WHITE);

		tennisVBox.getChildren().addAll(tennisImageView, tennisLabel);
		VBox.setMargin(tennisImageView, new Insets(0, 0, 10, 0));
		VBox.setMargin(tennisLabel, new Insets(10, 0, 0, 100));

		basketballVBox.getChildren().addAll(basketballImageView, basketballLabel);
		VBox.setMargin(basketballImageView, new Insets(0, 0, 10, 0));
		VBox.setMargin(basketballLabel, new Insets(10, 0, 0, 0));

		footballVBox.getChildren().addAll(footballImageView, footballLabel);
		VBox.setMargin(footballImageView, new Insets(0, 0, 10, 0));
		VBox.setMargin(footballLabel, new Insets(10, 100, 0, 0));

		optionsHBox.getChildren().addAll(tennisVBox, basketballVBox, footballVBox);
		HBox.setMargin(tennisVBox, new Insets(0, 50, 0, 0));
		HBox.setMargin(basketballVBox, new Insets(0, 50, 0, 50));
		HBox.setMargin(footballVBox, new Insets(0, 0, 0, 50));

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
