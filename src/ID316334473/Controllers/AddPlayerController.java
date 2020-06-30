package ID316334473.Controllers;

import ID316334473.UIHandler;
import ID316334473.ValidPatterns;
import ID316334473.Models.BasketballPlayerModel;
import ID316334473.Models.FootballPlayerModel;
import ID316334473.Models.PlayerModel;
import ID316334473.Models.TennisPlayerModel;
import ID316334473.Models.TournamentModel.GameType;
import ID316334473.Views.AddPlayerView;
import ID316334473.Views.PlayersView;
import ID316334473.Views.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
		EventHandler<ActionEvent> sumbitButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				TextField playerIDTextField = addPlayerView.getPlayerIDTextField(),
						playerNameTextField = addPlayerView.getPlayerNameTextField();

				PlayerModel player = null;
				GameType game = addPlayerView.getGame();
				String IDText = playerIDTextField.getText(), playerName = playerNameTextField.getText();
				int playerID;

				// Validations
				if (!IDText.matches(ValidPatterns.PLAYER_ID.getPattern())) {
					UIHandler.showError("Invalid ID!", playerIDTextField.getTooltip().getText());
					return;
				}
				playerID = Integer.parseInt(IDText);
				if (!playerName.matches(ValidPatterns.PLAYER_FULL_NAME.getPattern())) {
					UIHandler.showError("Invalid name!", playerNameTextField.getTooltip().getText());
					return;
				}
				
				switch(game) {
				case Tennis: 
					player = new TennisPlayerModel(playerID, playerName);
					break;
				case Basketball:
					player = new BasketballPlayerModel(playerID, playerName);
					break;
				case Football: 
					player = new FootballPlayerModel(playerID, playerName);
					break;
				}
				
				playersView.addPlayer(player);
				addPlayerView.close();
				UIHandler.showSuccess(String.format("A new %s player was added successfully!", game.name()));
				
				playersView.disableAllButtons(false);
			}
		};

		addPlayerView.getSubmitButton().setOnAction(sumbitButtonEventHandler);
		addPlayerView.getStage().setOnCloseRequest(e -> playersView.disableAllButtons(false));
	}

	// Methods
}