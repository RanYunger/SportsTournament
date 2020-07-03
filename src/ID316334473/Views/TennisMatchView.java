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
	private VBox playerSetScoreTextFieldsVBox;
	private TextField[] playerSetScoresTextFields;
	private TennisMatchModel tennisMatch;

	// Properties (Getters and Setters)
	public TennisMatchModel getTennisMatch() {
		return tennisMatch;
	}

	public void setTennisMatch(TennisMatchModel tennisMatch) {
		this.tennisMatch = tennisMatch;
	}

	public void setPlayerSetScoresTextFields(TextField[] playerRoundScoresTextFields) {
		this.playerSetScoresTextFields = playerRoundScoresTextFields;
	}

	public TextField getPlayer0SetScoreTextField() {
		return playerSetScoresTextFields[0];
	}

	public TextField getPlayer1SetScoreTextField() {
		return playerSetScoresTextFields[1];
	}

	// Constructors
	public TennisMatchView(MatchBracketView parentView, TennisMatchModel tennisMatch) {
		super(parentView);

		setTennisMatch(tennisMatch);
		setPlayerSetScoresTextFields(new TextField[2]);

		buildScene();
		addEffects();
	}

	// Methods
	@Override
	protected void buildScene() {
		double sceneWidth = 700, sceneHeight = 500, backgroundFontSize = 30;

		gridPane = new GridPane();
		playerSetScoreTextFieldsVBox = new VBox();

		gridPane.getRowConstraints().add(new RowConstraints());
		gridPane.getRowConstraints().get(0).setPercentHeight(30);
		gridPane.getRowConstraints().add(new RowConstraints());
		gridPane.getRowConstraints().get(1).setPercentHeight(70);

		gridPane.setAlignment(Pos.CENTER);
		playerSetScoreTextFieldsVBox.setAlignment(Pos.CENTER);

		for (int i = 0; i < playerSetScoresTextFields.length; i++) {
			playerSetScoresTextFields[i] = new TextField();
			playerSetScoresTextFields[i].setAlignment(Pos.CENTER);
			playerSetScoresTextFields[i].setMaxWidth(50);
		}

		playerSetScoreTextFieldsVBox.getChildren().addAll(playerSetScoresTextFields[0], playerSetScoresTextFields[1]);
		VBox.setMargin(playerSetScoresTextFields[0], new Insets(0, 0, 70, 0));
		VBox.setMargin(playerSetScoresTextFields[1], new Insets(70, 0, 70, 0));

		gridPane.add(matchHBox, 0, 0);
		gridPane.add(playerSetScoreTextFieldsVBox, 0, 1);

		GridPane.setMargin(matchHBox, new Insets(70, 0, 0, 0));

		stage.setScene(new Scene(
				UIHandler.buildBackground("TennisArena.jpg", gridPane, sceneWidth, sceneHeight, backgroundFontSize),
				sceneWidth, sceneHeight));
		UIHandler.setGeneralFeatures(stage);

		stage.show();
	}

	@Override
	protected Node asNode() {
		return (Node) gridPane;
	}
}
