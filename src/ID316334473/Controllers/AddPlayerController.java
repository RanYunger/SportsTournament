package ID316334473.Controllers;

import ID316334473.SearchHandler;
import ID316334473.UIHandler;
import ID316334473.ValidPatterns;
import ID316334473.Models.BasketballPlayerModel;
import ID316334473.Models.FootballPlayerModel;
import ID316334473.Models.PlayerModel;
import ID316334473.Models.PlayerModel.Gender;
import ID316334473.Models.TennisPlayerModel;
import ID316334473.Models.TournamentModel.GameType;
import ID316334473.Views.AddPlayerView;
import ID316334473.Views.PlayersView;
import ID316334473.Views.View;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class AddPlayerController extends WindowController {
	// Constants

	// Fields

	// Properties (Getters and Setters)
	public AddPlayerView getAddPlayerView() {
		return (AddPlayerView) getView();
	}

	// Constructors
	public AddPlayerController(View view, PlayersView playersView) {
		super(view);

		AddPlayerView addPlayerView = getAddPlayerView();
		RadioButton maleRadioButton = addPlayerView.getMaleRadioButton(),
				femaleRadioButton = addPlayerView.getFemaleRadioButton();

		ChangeListener<Boolean> maleRadioButtonChangeListener = new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				femaleRadioButton.setSelected(!newValue);
			}
		};
		ChangeListener<Boolean> femaleRadioButtonChangeListener = new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				maleRadioButton.setSelected(!newValue);
			}
		};
		EventHandler<ActionEvent> sumbitButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				TextField playerIDTextField = addPlayerView.getPlayerIDTextField(),
						playerNameTextField = addPlayerView.getPlayerNameTextField();

				PlayerModel player = null;
				GameType game = addPlayerView.getGame();
				String IDText = playerIDTextField.getText(), playerName = playerNameTextField.getText();
				Gender playerGender = maleRadioButton.isSelected() ? Gender.Male : Gender.Female;
				int playerID;

				// Validations
				if (!IDText.matches(ValidPatterns.PLAYER_ID.getPattern())) {
					UIHandler.showError("Invalid ID!", playerIDTextField.getTooltip().getText());

					return;
				}
				playerID = Integer.parseInt(IDText);
				if (SearchHandler.getPlayerByID(playerID, addPlayerView.getGame()) != null) {
					UIHandler.showError("This player already exists. Try a different ID.");

					return;
				}
				if (!playerName.matches(ValidPatterns.PLAYER_FULL_NAME.getPattern())) {
					UIHandler.showError("Invalid name!", playerNameTextField.getTooltip().getText());

					return;
				}

				switch (game) {
				case Tennis:
					player = new TennisPlayerModel(playerID, playerName, playerGender);
					break;
				case Basketball:
					player = new BasketballPlayerModel(playerID, playerName, playerGender);
					break;
				case Football:
					player = new FootballPlayerModel(playerID, playerName, playerGender);
					break;
				}

				playersView.addPlayer(player);
				addPlayerView.close();
				UIHandler.showSuccess(String.format("A new %s player was added successfully!", game.name()), true);

				playersView.disableAllButtons(false);
			}
		};

		addPlayerView.getMaleRadioButton().selectedProperty().addListener(maleRadioButtonChangeListener);
		addPlayerView.getFemaleRadioButton().selectedProperty().addListener(femaleRadioButtonChangeListener);
		addPlayerView.getSubmitButton().setOnAction(sumbitButtonEventHandler);
		addPlayerView.getStage().setOnCloseRequest(e -> playersView.disableAllButtons(false));
	}

	// Methods
}