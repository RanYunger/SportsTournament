package ID316334473.Views;

import ID316334473.UIHandler;
import ID316334473.Models.BasketballMatchModel;
import ID316334473.Models.MatchModel;
import ID316334473.Models.TennisMatchModel;
import ID316334473.Models.TournamentModel.GameType;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MatchBracketView extends View {
	// Constants

	// Fields
	private HBox hBox;
	private VBox playerStatusesVBox, playerNamesVBox, playerScoresVBox;
	private ImageView[] playerStatusesImageViews;
	private TextField[] playerNamesTextFields, playerScoresTextFields;
	private Button playButton;
	private Bounds bounds;
	private MatchModel match;

	// Properties (Getters and Setters)
	public MatchModel getMatch() {
		return match;
	}

	private void setMatch(MatchModel match) {
		this.match = match;
	}

	public ImageView getWinnerImageView() {
		return match.getWinner().equals(match.getPlayer0()) ? playerStatusesImageViews[0] : playerStatusesImageViews[1];
	}

	public ImageView getLoserImageView() {
		return getWinnerImageView().equals(playerStatusesImageViews[0]) ? playerStatusesImageViews[1]
				: playerStatusesImageViews[0];
	}

	public TextField getWinnerScoreTextField() {
		return match.getWinner().equals(match.getPlayer0()) ? playerScoresTextFields[0] : playerScoresTextFields[1];
	}

	public TextField getLoserScoreTextField() {
		return getWinnerScoreTextField().equals(playerScoresTextFields[0]) ? playerScoresTextFields[1]
				: playerScoresTextFields[0];
	}

	public Button getPlayButton() {
		return playButton;
	}

	public Bounds getBounds() {
		return bounds;
	}

	public void setBounds(Bounds bounds) {
		this.bounds = bounds;
	}

	// Constructors
	public MatchBracketView(MatchModel match) {
		super();

		setMatch(match);

		buildScene();
		addEffects();
	}

	// Methods
	@Override
	protected void buildScene() {
		int length = 2;
		double fontSize = 15;

		hBox = new HBox();
		playerStatusesVBox = new VBox();
		playerNamesVBox = new VBox();
		playerScoresVBox = new VBox();
		playerStatusesImageViews = new ImageView[length];
		playerNamesTextFields = new TextField[length];
		playerScoresTextFields = new TextField[length];
		playButton = new Button("Play!");

		for (int i = 0; i < length; i++) {
			playerStatusesImageViews[i] = UIHandler.buildImage(match == null ? "Question.png" : getGame(match) + ".png",
					30, 30);
			playerNamesTextFields[i] = new TextField(match == null ? "-" : match.getPlayers()[i].getTextualName());
			playerScoresTextFields[i] = new TextField("-");

			playerNamesTextFields[i].setEditable(false);
			playerNamesTextFields[i].setAlignment(Pos.CENTER);
			playerNamesTextFields[i].setFont(new Font(fontSize));
			playerNamesTextFields[i].setMinWidth(150);

			playerScoresTextFields[i].setEditable(false);
			playerScoresTextFields[i].setAlignment(Pos.CENTER);
			playerScoresTextFields[i].setFont(new Font(fontSize));
			playerScoresTextFields[i].setMaxWidth(50);
		}
		playButton.setMinHeight(61);
		playButton.setVisible(match != null); // visible only if the match could begin

		playerStatusesVBox.getChildren().addAll(playerStatusesImageViews[0], playerStatusesImageViews[1]);
		playerNamesVBox.getChildren().addAll(playerNamesTextFields[0], playerNamesTextFields[1]);
		playerScoresVBox.getChildren().addAll(playerScoresTextFields[0], playerScoresTextFields[1]);

		hBox.getChildren().addAll(playerStatusesVBox, playerNamesVBox, playerScoresVBox, playButton);

//		Bounds temp = hBox.localToScene(hBox.getBoundsInLocal());

		setBounds(hBox.localToScene(hBox.getBoundsInLocal()));
	}

	@Override
	protected void addEffects() {
		UIHandler.addCursorEffectsToNode(playButton);
	}

	@Override
	public Node asNode() {
		return (Node) hBox;
	}

	private String getGame(MatchModel match) {
		if (match instanceof TennisMatchModel)
			return GameType.Tennis.name();
		if (match instanceof BasketballMatchModel)
			return GameType.Basketball.name();

		return GameType.Football.name();
	}
}
