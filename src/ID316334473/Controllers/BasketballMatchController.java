package ID316334473.Controllers;

import ID316334473.UIHandler;
import ID316334473.Models.BasketballMatchModel;
import ID316334473.Views.BasketballMatchView;
import ID316334473.Views.MatchBracketView;
import ID316334473.Views.View;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class BasketballMatchController extends WindowController {
	// Constants

	// Fields

	// Properties (Getters and Setters)
	public BasketballMatchView getBasketballMatchView() {
		return (BasketballMatchView) getView();
	}

	// Constructors
	public BasketballMatchController(View view) {
		super(view);

		BasketballMatchView basketballMatchView = getBasketballMatchView();
		TextField player0QuarterScoreTextField = basketballMatchView.getPlayer0QuarterScoreTextField(),
				player1QuarterScoreTextField = basketballMatchView.getPlayer1QuartertScoreTextField();
		ImageView player0TurnImageView = basketballMatchView.getPlayer0TurnImageView(),
				player1TurnImageView = basketballMatchView.getPlayer1TurnImageView();
		MatchBracketView parentView = basketballMatchView.getParentView();
		BasketballMatchModel basketballMatch = (BasketballMatchModel) parentView.getMatch();
		ChangeListener<String> player0ScoreTextFieldsChangeListener = new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldText, String newText) {
				if (!newText.matches("\\d*")) {
					player0QuarterScoreTextField.setText(newText.replaceAll("[^\\d]", ""));
				}
				if (newText.length() > 2) // Score in a match must be smaller than 100
					player0QuarterScoreTextField.setText(oldText);
			}
		};
		ChangeListener<String> player1ScoreTextFieldsChangeListener = new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldText, String newText) {
				if (!newText.matches("\\d*")) {
					player1QuarterScoreTextField.setText(newText.replaceAll("[^\\d]", ""));
				}
				if (newText.length() > 2) // Score in a match must be smaller than 100
					player1QuarterScoreTextField.setText(oldText);
			}
		};
		EventHandler<KeyEvent> player0SetScoreTextFieldEventHandler = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if ((!basketballMatch.isOver()) && (event.getCode() == KeyCode.TAB)
						&& (!player0QuarterScoreTextField.getText().isBlank())) {
					UIHandler.playAudio("Net.mp3");
					basketballMatch.addScore(Integer.parseInt(player0QuarterScoreTextField.getText()));

					player0QuarterScoreTextField.setFocusTraversable(false);
					player1QuarterScoreTextField.setFocusTraversable(true);
					player0QuarterScoreTextField.setDisable(true);
					player1QuarterScoreTextField.setDisable(false);
					player1QuarterScoreTextField.requestFocus();

					player0TurnImageView.setVisible(false);
					player1TurnImageView.setVisible(true);

					event.consume();
				}
			}
		};
		EventHandler<KeyEvent> player1SetScoreTextFieldEventHandler = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if ((!basketballMatch.isOver()) && (event.getCode() == KeyCode.TAB)
						&& (!player1QuarterScoreTextField.getText().isBlank())) {
					UIHandler.playAudio("Net.mp3");
					basketballMatch.addScore(Integer.parseInt(player1QuarterScoreTextField.getText()));

					player0QuarterScoreTextField.setFocusTraversable(true);
					player1QuarterScoreTextField.setFocusTraversable(false);
					player0QuarterScoreTextField.setDisable(false);
					player1QuarterScoreTextField.setDisable(true);
					player0QuarterScoreTextField.requestFocus();

					player0TurnImageView.setVisible(true);
					player1TurnImageView.setVisible(false);

					basketballMatchView.getPlayer0ScoreTextField()
							.setText("" + basketballMatch.getPlayer0().getNumericMatchScore());
					basketballMatchView.getPlayer1ScoreTextField()
							.setText("" + basketballMatch.getPlayer1().getNumericMatchScore());

					player0QuarterScoreTextField.clear();
					player1QuarterScoreTextField.clear();
					if (basketballMatch.isOver()) {
						player0QuarterScoreTextField.setVisible(false);
						player1QuarterScoreTextField.setVisible(false);
						player0TurnImageView.setVisible(false);
						player1TurnImageView.setVisible(false);

						parentView.updateScene();
						basketballMatchView.close();
					}

					event.consume();
				}
			}
		};

		player0QuarterScoreTextField.textProperty().addListener(player0ScoreTextFieldsChangeListener);
		player1QuarterScoreTextField.textProperty().addListener(player1ScoreTextFieldsChangeListener);
		player0QuarterScoreTextField.addEventFilter(KeyEvent.KEY_PRESSED, player0SetScoreTextFieldEventHandler);
		player1QuarterScoreTextField.addEventFilter(KeyEvent.KEY_PRESSED, player1SetScoreTextFieldEventHandler);
		basketballMatchView.getStage()
				.setOnCloseRequest(e -> basketballMatchView.getParentView().getPlayButton().setDisable(false));
	}

	// Methods
}
