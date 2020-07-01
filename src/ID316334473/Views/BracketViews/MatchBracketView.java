package ID316334473.Views.BracketViews;

import ID316334473.UIHandler;
import ID316334473.Models.BaketballMatchModel;
import ID316334473.Models.MatchModel;
import ID316334473.Models.TennisMatchModel;
import ID316334473.Models.TournamentModel.GameType;
import ID316334473.Views.View;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MatchBracketView extends View {
	// Constants

	// Fields
	private VBox vBox;
	private HBox player0HBox, player1HBox;
	private ImageView[] playerStatusesImageViews;
	private TextField[] playerNamesTextFields, playerScoresTextFields;
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

	// Constructors
	public MatchBracketView(MatchModel match) {
		super();

		setMatch(match);

		buildScene();
	}

	// Methods
	@Override
	protected void buildScene() {
		int length = 2;
		double fontSize = 15;

		vBox = new VBox();
		player0HBox = new HBox();
		player1HBox = new HBox();
		playerStatusesImageViews = new ImageView[length];
		playerNamesTextFields = new TextField[length];
		playerScoresTextFields = new TextField[length];
		
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

		player0HBox.getChildren().addAll(playerStatusesImageViews[0], playerNamesTextFields[0],
				playerScoresTextFields[0]);
		player1HBox.getChildren().addAll(playerStatusesImageViews[1], playerNamesTextFields[1],
				playerScoresTextFields[1]);
		vBox.getChildren().addAll(player0HBox, player1HBox);
	}

	@Override
	protected void addEffects() {
		// TODO Auto-generated method stub
	}

	@Override
	public Node asNode() {
		return (Node) vBox;
	}

	private String getGame(MatchModel match) {
		if (match instanceof TennisMatchModel)
			return GameType.Tennis.name();
		if (match instanceof BaketballMatchModel)
			return GameType.Basketball.name();

		return GameType.Football.name();
	}
}
