package ID316334473.Controllers;

import ID316334473.UIHandler;
import ID316334473.Models.TennisMatchModel;
import ID316334473.Views.MatchBracketView;
import ID316334473.Views.TennisMatchView;
import ID316334473.Views.View;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class TennisMatchController extends WindowController {
	// Constants

	// Fields

	// Properties (Getters and Setters)
	public TennisMatchView getTennisMatchView() {
		return (TennisMatchView) getView();
	}

	// Constructors
	public TennisMatchController(View view) {
		super(view);

		TennisMatchView tennisMatchView = getTennisMatchView();
		TextField player0SetScoreTextField = tennisMatchView.getPlayer0SetScoreTextField(),
				player1SetScoreTextField = tennisMatchView.getPlayer1SetScoreTextField();
		ImageView player0TurnImageView = tennisMatchView.getPlayer0TurnImageView(),
				player1TurnImageView = tennisMatchView.getPlayer1TurnImageView();
		MatchBracketView parentView = tennisMatchView.getParentView();
		TennisMatchModel tennisMatch = (TennisMatchModel) parentView.getMatch();
		ChangeListener<String> player0ScoreTextFieldsChangeListener = new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldText, String newText) {
				if (!newText.matches("\\d*")) {
					player0SetScoreTextField.setText(newText.replaceAll("[^\\d]", ""));
				}
				if (newText.length() > 2) // Score in a tennis match must be smaller than 100
					player0SetScoreTextField.setText(oldText);
			}
		};
		ChangeListener<String> player1ScoreTextFieldsChangeListener = new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldText, String newText) {
				if (!newText.matches("\\d*")) {
					player1SetScoreTextField.setText(newText.replaceAll("[^\\d]", ""));
				}
				if (newText.length() > 2) // Score in a tennis match must be smaller than 100
					player1SetScoreTextField.setText(oldText);
			}
		};
		EventHandler<KeyEvent> player0SetScoreTextFieldEventHandler = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if ((!tennisMatch.isOver()) && (event.getCode() == KeyCode.TAB)
						&& (!player0SetScoreTextField.getText().isBlank())) {
					UIHandler.playAudio("Hit.mp3");
					tennisMatch.addScore(Integer.parseInt(player0SetScoreTextField.getText()));

					player1SetScoreTextField.requestFocus();
					player0TurnImageView.setVisible(false);
					player1TurnImageView.setVisible(true);

					event.consume();
				}
			}
		};
		EventHandler<KeyEvent> player1SetScoreTextFieldEventHandler = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if ((!tennisMatch.isOver()) && (event.getCode() == KeyCode.TAB)
						&& (!player1SetScoreTextField.getText().isBlank())) {
					UIHandler.playAudio("Hit.mp3");
					tennisMatch.addScore(Integer.parseInt(player1SetScoreTextField.getText()));

					player0SetScoreTextField.requestFocus();
					player0TurnImageView.setVisible(true);
					player1TurnImageView.setVisible(false);

					tennisMatchView.getPlayer0ScoreTextField().setText("" + tennisMatch.getPlayer0().getNumericScore());
					tennisMatchView.getPlayer1ScoreTextField().setText("" + tennisMatch.getPlayer1().getNumericScore());

					player0SetScoreTextField.clear();
					player1SetScoreTextField.clear();
					if (tennisMatch.isOver()) {
						player0SetScoreTextField.setVisible(false);
						player1SetScoreTextField.setVisible(false);
						player0TurnImageView.setVisible(false);
						player1TurnImageView.setVisible(false);

						parentView.declareMatchResults();
						tennisMatchView.close();
					}

					event.consume();
				}
			}
		};

		player0SetScoreTextField.textProperty().addListener(player0ScoreTextFieldsChangeListener);
		player1SetScoreTextField.textProperty().addListener(player1ScoreTextFieldsChangeListener);
		player0SetScoreTextField.addEventFilter(KeyEvent.KEY_PRESSED, player0SetScoreTextFieldEventHandler);
		player1SetScoreTextField.addEventFilter(KeyEvent.KEY_PRESSED, player1SetScoreTextFieldEventHandler);
		tennisMatchView.getStage()
				.setOnCloseRequest(e -> tennisMatchView.getParentView().getPlayButton().setDisable(false));
	}

	// Methods
}
