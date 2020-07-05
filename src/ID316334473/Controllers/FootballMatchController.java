package ID316334473.Controllers;

import ID316334473.UIHandler;
import ID316334473.Models.FootballMatchModel;
import ID316334473.Views.FootballMatchView;
import ID316334473.Views.MatchBracketView;
import ID316334473.Views.View;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class FootballMatchController extends WindowController {
	// Constants

	// Fields

	// Properties (Getters and Setters)
	public FootballMatchView getFootballMatchView() {
		return (FootballMatchView) getView();
	}

	// Constructors
	public FootballMatchController(View view) {
		super(view);

		FootballMatchView footballMatchView = getFootballMatchView();
		TextField player0HalfScoreTextField = footballMatchView.getPlayer0HalfScoreTextField(),
				player1HalfScoreTextField = footballMatchView.getPlayer1HalftScoreTextField();
		ImageView player0TurnImageView = footballMatchView.getPlayer0TurnImageView(),
				player1TurnImageView = footballMatchView.getPlayer1TurnImageView();
		MatchBracketView parentView = footballMatchView.getParentView();
		FootballMatchModel footballMatch = (FootballMatchModel) parentView.getMatch();
		ChangeListener<String> player0ScoreTextFieldsChangeListener = new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldText, String newText) {
				if (!newText.matches("\\d*"))
					player0HalfScoreTextField.setText(newText.replaceAll("[^\\d]", ""));
				if (newText.length() > 2) // Score in a match must be smaller than 100
					player0HalfScoreTextField.setText(oldText);
			}
		};
		ChangeListener<String> player1ScoreTextFieldsChangeListener = new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldText, String newText) {
				if (!newText.matches("\\d*"))
					player1HalfScoreTextField.setText(newText.replaceAll("[^\\d]", ""));
				if (newText.length() > 2) // Score in a match must be smaller than 100
					player1HalfScoreTextField.setText(oldText);
			}
		};
		EventHandler<KeyEvent> player0SetScoreTextFieldEventHandler = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if ((!footballMatch.isOver()) && (event.getCode() == KeyCode.TAB)
						&& (!player0HalfScoreTextField.getText().isBlank())) {
					UIHandler.playAudio("Kick.mp3");
					footballMatch.addScore(Integer.parseInt(player0HalfScoreTextField.getText()));

					player0HalfScoreTextField.setFocusTraversable(false);
					player1HalfScoreTextField.setFocusTraversable(true);
					player1HalfScoreTextField.requestFocus();

					player0TurnImageView.setVisible(false);
					player1TurnImageView.setVisible(true);

					event.consume();
				}
			}
		};
		EventHandler<KeyEvent> player1SetScoreTextFieldEventHandler = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if ((!footballMatch.isOver()) && (event.getCode() == KeyCode.TAB)
						&& (!player1HalfScoreTextField.getText().isBlank())) {
					UIHandler.playAudio("Kick.mp3");
					footballMatch.addScore(Integer.parseInt(player1HalfScoreTextField.getText()));

					player0HalfScoreTextField.setFocusTraversable(true);
					player1HalfScoreTextField.setFocusTraversable(false);
					player0HalfScoreTextField.requestFocus();

					player0TurnImageView.setVisible(true);
					player1TurnImageView.setVisible(false);

					footballMatchView.getPlayer0ScoreTextField()
							.setText("" + footballMatch.getPlayer0().getNumericMatchScore());
					footballMatchView.getPlayer1ScoreTextField()
							.setText("" + footballMatch.getPlayer1().getNumericMatchScore());

					player0HalfScoreTextField.clear();
					player1HalfScoreTextField.clear();
					if (footballMatch.isOver()) {
						player0HalfScoreTextField.setVisible(false);
						player1HalfScoreTextField.setVisible(false);
						player0TurnImageView.setVisible(false);
						player1TurnImageView.setVisible(false);

						parentView.updateScene();
						footballMatchView.close();
					}

					event.consume();
				}
			}
		};

		player0HalfScoreTextField.textProperty().addListener(player0ScoreTextFieldsChangeListener);
		player1HalfScoreTextField.textProperty().addListener(player1ScoreTextFieldsChangeListener);
		player0HalfScoreTextField.addEventFilter(KeyEvent.KEY_PRESSED, player0SetScoreTextFieldEventHandler);
		player1HalfScoreTextField.addEventFilter(KeyEvent.KEY_PRESSED, player1SetScoreTextFieldEventHandler);
		footballMatchView.getStage()
				.setOnCloseRequest(e -> footballMatchView.getParentView().getPlayButton().setDisable(false));
	}

	// Methods
}
