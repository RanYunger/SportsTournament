package ID316334473.Views;

import ID316334473.UIHandler;
import ID316334473.Models.FootballMatchModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

public class FootballMatchView extends MatchView {
	// Constants

	// Fields
	private VBox playerHalfScoreTextFieldsVBox;
	private TextField[] playerHalfScoresTextFields;
	private FootballMatchModel footballMatch;

	// Properties (Getters and Setters)
	public FootballMatchModel getFootballMatch() {
		return footballMatch;
	}

	public void setFootballMatch(FootballMatchModel footballMatch) {
		this.footballMatch = footballMatch;
	}

	public void setPlayerHalfScoresTextFields(TextField[] playerHalfScoresTextFields) {
		this.playerHalfScoresTextFields = playerHalfScoresTextFields;
	}

	public TextField getPlayer0HalfScoreTextField() {
		return playerHalfScoresTextFields[0];
	}

	public TextField getPlayer1HalftScoreTextField() {
		return playerHalfScoresTextFields[1];
	}

	// Constructors
	public FootballMatchView(MatchBracketView parentView, FootballMatchModel footballMatch) {
		super(parentView);

		setFootballMatch(footballMatch);
		setPlayerHalfScoresTextFields(new TextField[2]);

		buildScene();
		addEffects();
	}

	// Methods
	@Override
	protected void buildScene() {
		double sceneWidth = 700, sceneHeight = 500, backgroundFontSize = 30;

		gridPane = new GridPane();
		playerHalfScoreTextFieldsVBox = new VBox();

		gridPane.getRowConstraints().add(new RowConstraints());
		gridPane.getRowConstraints().get(0).setPercentHeight(30);
		gridPane.getRowConstraints().add(new RowConstraints());
		gridPane.getRowConstraints().get(1).setPercentHeight(70);

		gridPane.setAlignment(Pos.CENTER);
		playerHalfScoreTextFieldsVBox.setAlignment(Pos.CENTER);

		for (int i = 0; i < playerHalfScoresTextFields.length; i++) {
			playerHalfScoresTextFields[i] = new TextField();
			playerHalfScoresTextFields[i].setAlignment(Pos.CENTER);
			playerHalfScoresTextFields[i].setMaxWidth(50);
		}
		playerHalfScoresTextFields[1].setFocusTraversable(false); // Player 0 opens the match

		playerHalfScoreTextFieldsVBox.getChildren().addAll(playerHalfScoresTextFields[0],
				playerHalfScoresTextFields[1]);
		VBox.setMargin(playerHalfScoresTextFields[0], new Insets(0, 0, 70, 0));
		VBox.setMargin(playerHalfScoresTextFields[1], new Insets(70, 0, 70, 0));

		gridPane.add(matchHBox, 0, 0);
		gridPane.add(playerHalfScoreTextFieldsVBox, 0, 1);

		GridPane.setMargin(matchHBox, new Insets(70, 0, 0, 0));

		stage.setScene(new Scene(
				UIHandler.buildBackground("FootballArena.jpg", gridPane, sceneWidth, sceneHeight, backgroundFontSize),
				sceneWidth, sceneHeight));
		UIHandler.setGeneralFeatures(stage);

		stage.show();
	}

	@Override
	protected Node asNode() {
		return (Node) gridPane;
	}
}
