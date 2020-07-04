package ID316334473.Views;

import ID316334473.UIHandler;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public abstract class MatchView extends WindowView {
	// Constants

	// Fields
	protected GridPane gridPane;
	protected HBox matchHBox;
	private MatchBracketView parentView;

	// Properties (Getters and Setters)
	public MatchBracketView getParentView() {
		return parentView;
	}

	public void setParentView(MatchBracketView parentView) {
		this.parentView = parentView;
	}

	private void setMatchHBox(HBox matchHBox) {
		this.matchHBox = matchHBox;
	}

	public ImageView getPlayer0TurnImageView() {
		return (ImageView) matchHBox.getChildren().get(0);
	}

	public TextField getPlayer0NameTextField() {
		return (TextField) matchHBox.getChildren().get(1);
	}

	public TextField getPlayer0ScoreTextField() {
		return (TextField) matchHBox.getChildren().get(2);
	}

	public TextField getPlayer1ScoreTextField() {
		return (TextField) matchHBox.getChildren().get(4);
	}

	public TextField getPlayer1NameTextField() {
		return (TextField) matchHBox.getChildren().get(5);
	}

	public ImageView getPlayer1TurnImageView() {
		return (ImageView) matchHBox.getChildren().get(6);
	}

	// Constructors
	public MatchView(MatchBracketView parentView) {
		super();

		setParentView(parentView);
		setMatchHBox(UIHandler.buildMatchHBox(parentView.getMatch()));
	}

	// Methods
	@Override
	protected abstract void buildScene();

	@Override
	protected void addEffects() {
		super.addEffects();

		homeImageView.setVisible(false); // User won't be able to jump back to MainView from here

		UIHandler.playAudio("Round1.mp3");
	}
}
