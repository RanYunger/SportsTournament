package ID316334473.Views;

import ID316334473.UIHandler;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MainView extends View {
	// Constants

	// Fields
	private HBox optionsHBox;
	private VBox playersVBox, matchesVBox, trophiesVBox;
	private ImageView audioImageView, playersImageView, matchesImageView, trophiesImageView;
	private Label playersLabel, matchesLabel, trophiesLabel;

	// Properties (Getters and Setters)
	public ImageView getAudioImageView() {
		return audioImageView;
	}

	public void setAudioImageView(ImageView audioImageView) {
		this.audioImageView = audioImageView;
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
		matchesVBox = new VBox();
		trophiesVBox = new VBox();
		playersImageView = UIHandler.buildImage("Players.png", 300, 300);
		matchesImageView = UIHandler.buildImage("Matches.png", 300, 300);
		trophiesImageView = UIHandler.buildImage("Trophies.png", 300, 300);
		playersLabel = new Label("Players");
		matchesLabel = new Label("Matches");
		trophiesLabel = new Label("Trophies");

		optionsHBox.setAlignment(Pos.CENTER);
		playersVBox.setAlignment(Pos.CENTER_LEFT);
		matchesVBox.setAlignment(Pos.CENTER);
		trophiesVBox.setAlignment(Pos.CENTER_RIGHT);
		playersLabel.setFont(new Font(viewFontSize));
		playersLabel.setTextFill(Color.WHITE);
		matchesLabel.setFont(new Font(viewFontSize));
		matchesLabel.setTextFill(Color.WHITE);
		trophiesLabel.setFont(new Font(viewFontSize));
		trophiesLabel.setTextFill(Color.WHITE);

		playersVBox.getChildren().addAll(playersImageView, playersLabel);
		VBox.setMargin(playersImageView, new Insets(0, 0, 10, 0));
		VBox.setMargin(playersLabel, new Insets(10, 0, 0, 100));

		matchesVBox.getChildren().addAll(matchesImageView, matchesLabel);
		VBox.setMargin(matchesImageView, new Insets(0, 0, 10, 0));
		VBox.setMargin(matchesLabel, new Insets(10, 0, 0, 0));

		trophiesVBox.getChildren().addAll(trophiesImageView, trophiesLabel);
		VBox.setMargin(trophiesImageView, new Insets(0, 0, 10, 0));
		VBox.setMargin(trophiesLabel, new Insets(10, 100, 0, 0));

		optionsHBox.getChildren().addAll(playersVBox, matchesVBox, trophiesVBox);
		HBox.setMargin(playersVBox, new Insets(0, 50, 0, 0));
		HBox.setMargin(matchesVBox, new Insets(0, 50, 0, 50));
		HBox.setMargin(trophiesVBox, new Insets(0, 0, 0, 50));

		stage.setScene(new Scene(UIHandler.buildBackground(optionsHBox, sceneWidth, sceneHeight, backgroundFontSize),
				sceneWidth, sceneHeight));
		UIHandler.setGeneralFeatures(stage);

		stage.show();
	}

	@Override
	protected void addEffects() {
		Scene scene = stage.getScene();
		ObservableList<Node> rootNodes = scene.getRoot().getChildrenUnmodifiable();
		Node currentNode;

		for (int i = 0; i < rootNodes.size(); i++) {
			currentNode = rootNodes.get(i);
			if ((currentNode instanceof ImageView) && ((ImageView) currentNode).getImage().getWidth() == 30) {
				setAudioImageView((ImageView) currentNode);
				UIHandler.addAudioToImageView(audioImageView, "Sparkle.mp3");
			}
		}
		
		UIHandler.addAudioToImageView(playersImageView, "Marching.mp3");
		UIHandler.addAudioToImageView(matchesImageView, "Whistle.mp3");
		UIHandler.addAudioToImageView(trophiesImageView, "Sparkle.mp3");
	}
}
