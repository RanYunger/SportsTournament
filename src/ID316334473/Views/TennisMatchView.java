package ID316334473.Views;

import ID316334473.UIHandler;
import ID316334473.Models.TennisMatchModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

public class TennisMatchView extends MatchView {
	// Constants

	// Fields
	private GridPane gridPane;
	private VBox playerScoresTextFieldsVBox;
	private TextField[] playerRoundScoresTextFields;
	private TennisMatchModel tennisMatch;

	// Properties (Getters and Setters)
	public TennisMatchModel getTennisMatch() {
		return tennisMatch;
	}

	public void setTennisMatch(TennisMatchModel tennisMatch) {
		this.tennisMatch = tennisMatch;
	}

	public void setPlayerRoundScoresTextFields(TextField[] playerRoundScoresTextFields) {
		this.playerRoundScoresTextFields = playerRoundScoresTextFields;
	}

	public TextField getPlayer0RoundScoreTextField() {
		return playerRoundScoresTextFields[0];
	}

	public TextField getPlayer1RoundScoreTextField() {
		return playerRoundScoresTextFields[1];
	}

	// Constructors
	public TennisMatchView(MatchBracketView parentView, TennisMatchModel tennisMatch) {
		super(parentView);

		setTennisMatch(tennisMatch);
		setPlayerRoundScoresTextFields(new TextField[2]);

		buildScene();
		addEffects();
	}

	// Methods
	@Override
	protected void buildScene() {
		double sceneWidth = 700, sceneHeight = 500, backgroundFontSize = 30;

		gridPane = new GridPane();
		playerScoresTextFieldsVBox = new VBox();

		gridPane.getRowConstraints().add(new RowConstraints());
		gridPane.getRowConstraints().get(0).setPercentHeight(30);
		gridPane.getRowConstraints().add(new RowConstraints());
		gridPane.getRowConstraints().get(1).setPercentHeight(70);

		gridPane.setAlignment(Pos.CENTER);
		playerScoresTextFieldsVBox.setAlignment(Pos.CENTER);

		for (int i = 0; i < playerRoundScoresTextFields.length; i++) {
			playerRoundScoresTextFields[i] = new TextField();
			playerRoundScoresTextFields[i].setAlignment(Pos.CENTER);
			playerRoundScoresTextFields[i].setMaxWidth(50);
		}

		playerScoresTextFieldsVBox.getChildren().addAll(playerRoundScoresTextFields[0], playerRoundScoresTextFields[1]);
		VBox.setMargin(playerRoundScoresTextFields[0], new Insets(0, 0, 70, 0));
		VBox.setMargin(playerRoundScoresTextFields[1], new Insets(70, 0, 70, 0));

		gridPane.add(matchHBox, 0, 0);
		gridPane.add(playerScoresTextFieldsVBox, 0, 1);

		GridPane.setMargin(matchHBox, new Insets(70, 0, 0, 0));

		stage.setScene(new Scene(
				UIHandler.buildBackground("TennisArena.jpg", gridPane, sceneWidth, sceneHeight, backgroundFontSize),
				sceneWidth, sceneHeight));
		UIHandler.setGeneralFeatures(stage);

		stage.show();
	}

	@Override
	protected Node asNode() {
		// TODO Auto-generated method stub
		return null;
	}
}
