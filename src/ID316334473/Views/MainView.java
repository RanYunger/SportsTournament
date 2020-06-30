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

public class MainView extends WindowView {
	// Constants

	// Fields
	private HBox optionsHBox;
	private VBox playersVBox, championshipsVBox, trophiesVBox;
	private ImageView playersImageView, championshipsImageView, trophiesImageView;
	private Label playersLabel, championshipsLabel, trophiesLabel;

	// Properties (Getters and Setters)
	public ImageView getPlayersImageView() {
		return playersImageView;
	}

	public ImageView getChampionshipsImageView() {
		return championshipsImageView;
	}

	public ImageView getTrophiesImageView() {
		return trophiesImageView;
	}

	// Constructors
	public MainView() {
		super();

		buildScene();
		addEffects();
	}

	// Methods
	@Override
	protected void buildScene() {
		double sceneWidth = 1500, sceneHeight = 700, backgroundFontSize = 50, viewFontSize = 30;

		optionsHBox = new HBox();
		playersVBox = new VBox();
		championshipsVBox = new VBox();
		trophiesVBox = new VBox();
		playersImageView = UIHandler.buildImage("Players.png", 350, 300);
		championshipsImageView = UIHandler.buildImage("Championships.png", 350, 300);
		trophiesImageView = UIHandler.buildImage("Trophies.png", 350, 300);
		playersLabel = new Label("Players");
		championshipsLabel = new Label("Championships");
		trophiesLabel = new Label("Trophies");

		optionsHBox.setAlignment(Pos.CENTER);
		playersVBox.setAlignment(Pos.CENTER_LEFT);
		championshipsVBox.setAlignment(Pos.CENTER);
		trophiesVBox.setAlignment(Pos.CENTER_RIGHT);
		playersLabel.setFont(new Font(viewFontSize));
		playersLabel.setTextFill(Color.WHITE);
		championshipsLabel.setFont(new Font(viewFontSize));
		championshipsLabel.setTextFill(Color.WHITE);
		trophiesLabel.setFont(new Font(viewFontSize));
		trophiesLabel.setTextFill(Color.WHITE);

		playersVBox.getChildren().addAll(playersImageView, playersLabel);
		VBox.setMargin(playersImageView, new Insets(0, 0, 10, 0));
		VBox.setMargin(playersLabel, new Insets(10, 0, 0, 125));

		championshipsVBox.getChildren().addAll(championshipsImageView, championshipsLabel);
		VBox.setMargin(championshipsImageView, new Insets(0, 0, 10, 0));
		VBox.setMargin(championshipsLabel, new Insets(10, 0, 0, 0));

		trophiesVBox.getChildren().addAll(trophiesImageView, trophiesLabel);
		VBox.setMargin(trophiesImageView, new Insets(0, 0, 10, 0));
		VBox.setMargin(trophiesLabel, new Insets(10, 125, 0, 0));

		optionsHBox.getChildren().addAll(playersVBox, championshipsVBox, trophiesVBox);
		HBox.setMargin(playersVBox, new Insets(0, 50, 0, 0));
		HBox.setMargin(championshipsVBox, new Insets(0, 50, 0, 50));
		HBox.setMargin(trophiesVBox, new Insets(0, 0, 0, 50));

		stage.setScene(new Scene(
				UIHandler.buildBackground("Arena.jpg", optionsHBox, sceneWidth, sceneHeight, backgroundFontSize),
				sceneWidth, sceneHeight));
		UIHandler.setGeneralFeatures(stage);

		stage.show();
	}

	@Override
	protected void addEffects() {
		super.addEffects();

		UIHandler.addCursorEffectsToNode(playersImageView);
		UIHandler.addCursorEffectsToNode(championshipsImageView);
		UIHandler.addCursorEffectsToNode(trophiesImageView);
	}
}
