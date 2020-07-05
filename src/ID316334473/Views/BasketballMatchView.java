package ID316334473.Views;

import ID316334473.UIHandler;
import ID316334473.Models.BasketballMatchModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

public class BasketballMatchView extends MatchView {
	// Constants

	// Fields
	private VBox playerQuarterScoreTextFieldsVBox;
	private TextField[] playerQuarterScoresTextFields;
	private BasketballMatchModel basketballMatch;

	// Properties (Getters and Setters)
	public BasketballMatchModel getBasketballMatch() {
		return basketballMatch;
	}

	public void setBasketballMatch(BasketballMatchModel basketballMatch) {
		this.basketballMatch = basketballMatch;
	}

	public void setPlayerQuarterScoresTextFields(TextField[] playerQuarterScoresTextFields) {
		this.playerQuarterScoresTextFields = playerQuarterScoresTextFields;
	}

	public TextField getPlayer0QuarterScoreTextField() {
		return playerQuarterScoresTextFields[0];
	}

	public TextField getPlayer1QuartertScoreTextField() {
		return playerQuarterScoresTextFields[1];
	}

	// Constructors
	public BasketballMatchView(MatchBracketView parentView, BasketballMatchModel basketballMatch) {
		super(parentView);

		setBasketballMatch(basketballMatch);
		setPlayerQuarterScoresTextFields(new TextField[2]);

		buildScene();
		addEffects();
	}

	// Methods
	@Override
	protected void buildScene() {
		double sceneWidth = 700, sceneHeight = 500, backgroundFontSize = 30;

		gridPane = new GridPane();
		playerQuarterScoreTextFieldsVBox = new VBox();

		gridPane.getRowConstraints().add(new RowConstraints());
		gridPane.getRowConstraints().get(0).setPercentHeight(30);
		gridPane.getRowConstraints().add(new RowConstraints());
		gridPane.getRowConstraints().get(1).setPercentHeight(70);

		gridPane.setAlignment(Pos.CENTER);
		playerQuarterScoreTextFieldsVBox.setAlignment(Pos.CENTER);

		for (int i = 0; i < playerQuarterScoresTextFields.length; i++) {
			playerQuarterScoresTextFields[i] = new TextField();
			playerQuarterScoresTextFields[i].setAlignment(Pos.CENTER);
			playerQuarterScoresTextFields[i].setMaxWidth(50);
		}
		playerQuarterScoresTextFields[1].setFocusTraversable(false); // Player 0 opens the match

		playerQuarterScoreTextFieldsVBox.getChildren().addAll(playerQuarterScoresTextFields[0],
				playerQuarterScoresTextFields[1]);
		VBox.setMargin(playerQuarterScoresTextFields[0], new Insets(0, 0, 70, 0));
		VBox.setMargin(playerQuarterScoresTextFields[1], new Insets(70, 0, 70, 0));

		gridPane.add(matchHBox, 0, 0);
		gridPane.add(playerQuarterScoreTextFieldsVBox, 0, 1);

		GridPane.setMargin(matchHBox, new Insets(70, 0, 0, 0));

		stage.setScene(new Scene(
				UIHandler.buildBackground("BasketballArena.jpg", gridPane, sceneWidth, sceneHeight, backgroundFontSize),
				sceneWidth, sceneHeight));
		UIHandler.setGeneralFeatures(stage);

		stage.show();
	}

	@Override
	protected Node asNode() {
		return (Node) gridPane;
	}
}
