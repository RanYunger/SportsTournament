package ID316334473.Views;

import java.util.ArrayList;

import ID316334473.SearchHandler;
import ID316334473.UIHandler;
import ID316334473.Models.BasketballMatchModel;
import ID316334473.Models.FootballMatchModel;
import ID316334473.Models.MatchModel;
import ID316334473.Models.PlayerModel;
import ID316334473.Models.TennisMatchModel;
import ID316334473.Models.TournamentModel.GameType;
import javafx.collections.FXCollections;
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
	private ArrayList<ImageView> playerStatusesImageViews;
	private ArrayList<TextField> playerNamesTextFields, playerScoresTextFields;
	private Button playButton;
	private MatchBracketView twinMatchBracketView, nextMatchBracketView;
	private TournamentBracketView parentView;
	private MatchModel match;

	// Properties (Getters and Setters)
	public MatchModel getMatch() {
		return match;
	}

	public void setMatch(MatchModel match) {
		this.match = match;
	}

	public MatchBracketView getTwinMatchBracketView() {
		return twinMatchBracketView;
	}

	public void setTwinMatchBracketView(MatchBracketView twinMatchBracketView) {
		this.twinMatchBracketView = twinMatchBracketView;
	}

	public MatchBracketView getNextMatchBracketView() {
		return nextMatchBracketView;
	}

	private void setNextMatchBracketView(MatchBracketView nextMatchBracketView) {
		this.nextMatchBracketView = nextMatchBracketView;
	}

	public TournamentBracketView getParentView() {
		return parentView;
	}

	private void setParentView(TournamentBracketView parentView) {
		this.parentView = parentView;
	}

	public ImageView getWinnerImageView() {
		if (!match.isOver())
			return null;

		return match.getWinner().equals(match.getPlayer0()) ? playerStatusesImageViews.get(0)
				: playerStatusesImageViews.get(1);
	}

	public ImageView getLoserImageView() {
		if (!match.isOver())
			return null;

		return getWinnerImageView().equals(playerStatusesImageViews.get(0)) ? playerStatusesImageViews.get(1)
				: playerStatusesImageViews.get(0);
	}

	public TextField getWinnerScoreTextField() {
		if (!match.isOver())
			return null;

		return match.getWinner().equals(match.getPlayer0()) ? playerScoresTextFields.get(0)
				: playerScoresTextFields.get(1);
	}

	public TextField getLoserScoreTextField() {
		if (!match.isOver())
			return null;

		return getWinnerScoreTextField().equals(playerScoresTextFields.get(0)) ? playerScoresTextFields.get(1)
				: playerScoresTextFields.get(0);
	}

	public Button getPlayButton() {
		return playButton;
	}

	// Constructors
	public MatchBracketView(MatchModel match, MatchBracketView nextMatchBracketView, TournamentBracketView parentView) {
		super();

		setMatch(match);
		setNextMatchBracketView(nextMatchBracketView);
		setParentView(parentView);

		buildScene();
		addEffects();
	}

	// Methods
	@Override
	protected void buildScene() {
		int length = 2;
		double fontSize = 15, height = 40;

		hBox = new HBox();
		playerStatusesVBox = new VBox();
		playerNamesVBox = new VBox();
		playerScoresVBox = new VBox();
		playerStatusesImageViews = new ArrayList<ImageView>(length);
		playerNamesTextFields = new ArrayList<TextField>(length);
		playerScoresTextFields = new ArrayList<TextField>(length);
		playButton = new Button("Play!");

		for (int i = 0; i < length; i++) {
			playerStatusesImageViews.add(UIHandler.buildImage("Question.png", height, height));
			playerNamesTextFields.add(new TextField("-"));
			playerScoresTextFields.add(new TextField("-"));

			playerNamesTextFields.get(i).setEditable(false);
			playerNamesTextFields.get(i).setAlignment(Pos.CENTER);
			playerNamesTextFields.get(i).setFont(new Font(fontSize));
			playerNamesTextFields.get(i).setMinHeight(height);
			playerNamesTextFields.get(i).setMinWidth(150);

			playerScoresTextFields.get(i).setEditable(false);
			playerScoresTextFields.get(i).setAlignment(Pos.CENTER);
			playerScoresTextFields.get(i).setFont(new Font(fontSize));
			playerScoresTextFields.get(i).setMinHeight(height);
			playerScoresTextFields.get(i).setMaxWidth(50);
		}
		playButton.setMinHeight(78);
		playButton.setDisable(match == null);

		playerStatusesVBox.getChildren().addAll(playerStatusesImageViews.get(0), playerStatusesImageViews.get(1));
		playerNamesVBox.getChildren().addAll(playerNamesTextFields.get(0), playerNamesTextFields.get(1));
		playerScoresVBox.getChildren().addAll(playerScoresTextFields.get(0), playerScoresTextFields.get(1));

		hBox.getChildren().addAll(playerStatusesVBox, playerNamesVBox, playerScoresVBox, playButton);

		if (match != null)
			updateScene();
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

	private void tryAdvance() {
		MatchModel twinMatch;
		PlayerModel winner0, winner1;

		if (nextMatchBracketView == null) { // final match
			if (match.isOver()) {
				winner0 = match.getWinner();
				switch (winner0.getGame()) { // Sorts the players by their final tournament score
				case Tennis: {
					SearchHandler.setTennisPlayers(SearchHandler.getTennisPlayers());
					SearchHandler.setTennisFinalTrio(
							FXCollections.observableList(SearchHandler.getTennisPlayers().subList(0, 3)));
				}
					break;
				case Basketball: {
					SearchHandler.setBaketballPlayers(SearchHandler.getBaketballPlayers());
					SearchHandler.setBasketballFinalTrio(
							FXCollections.observableList(SearchHandler.getBaketballPlayers().subList(0, 3)));
				}
					break;
				case Football: {
					SearchHandler.setFootballPlayers(SearchHandler.getFootballPlayers());
					SearchHandler.setFootballFinalTrio(
							FXCollections.observableList(SearchHandler.getFootballPlayers().subList(0, 3)));
				}
					break;
				}

				UIHandler.showSuccess(String.format("%s is the tournament's winner!", winner0.getTextualName()), false);
				UIHandler.playAudio("Tada.mp3");
			}
		} else if (twinMatchBracketView != null) {
			twinMatch = twinMatchBracketView.getMatch();
			if ((match.isOver()) && (twinMatch.isOver())) {
				winner0 = match.getWinner();
				winner1 = twinMatch.getWinner();

				switch (winner0.getGame()) {
				case Tennis:
					nextMatchBracketView.setMatch(new TennisMatchModel(winner0, winner1));
					break;
				case Basketball:
					nextMatchBracketView.setMatch(new BasketballMatchModel(winner0, winner1));
					break;
				case Football:
					nextMatchBracketView.setMatch(new FootballMatchModel(winner0, winner1));
					break;
				}
				nextMatchBracketView.updateScene();
			}
		}
	}

	public void updateScene() {
		double imageHeight = 40;
		ImageView loserImageView;
		PlayerModel winner, loser;

		if (match.isOver()) {
			winner = match.getWinner();
			loser = match.getLoser();

			getWinnerImageView().setImage(UIHandler.buildImage("Winner.png", imageHeight, imageHeight).getImage());
			getWinnerScoreTextField().setText("" + winner.getNumericMatchScore());

			loserImageView = getLoserImageView();
			loserImageView.setImage(UIHandler.buildImage("Loser.png", imageHeight, imageHeight).getImage());
			UIHandler.addAudioToImageView(loserImageView, "AllStar.mp3");
			getLoserScoreTextField().setText("" + loser.getNumericMatchScore());
			playButton.setDisable(true);

			winner.setMatchscore(PlayerModel.NO_SCORE);
			loser.setMatchscore(PlayerModel.NO_SCORE);

			tryAdvance();
		} else

		{
			playerNamesTextFields.get(0).setText(match.getPlayer0().getTextualName());
			playerNamesTextFields.get(1).setText(match.getPlayer1().getTextualName());
			for (ImageView playerStatusImageView : playerStatusesImageViews)
				playerStatusImageView
						.setImage(UIHandler.buildImage(getGame(match) + ".png", imageHeight, imageHeight).getImage());
			playButton.setDisable(false);
		}
	}
}
