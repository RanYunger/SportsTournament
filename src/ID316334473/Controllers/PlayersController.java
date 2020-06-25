package ID316334473.Controllers;

import ID316334473.UIHandler;
import ID316334473.Models.MatchModel;
import ID316334473.Models.PlayerModel;
import ID316334473.Models.TournamentModel.GameType;
import ID316334473.Views.AddPlayerView;
import ID316334473.Views.PlayersView;
import ID316334473.Views.View;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class PlayersController extends Controller {
	// Constants

	// Fields

	// Properties (Getters and Setters)
	public PlayersView getPlayersView() {
		return (PlayersView) getView();
	}

	// Constructors
	public PlayersController(View view) {
		super(view);

		PlayersView playersView = getPlayersView();
		EventHandler<ActionEvent> addTennisPlayerButtonEventHandler = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ObservableList<PlayerModel> tennisPlayers = playersView.getTennisPlayers();

				if (tennisPlayers.size() == MatchModel.MAX_PLAYERS)
					UIHandler.showError("There are already 8 tennis players. Better luck next time.");

				AddPlayerController addPlayerController = new AddPlayerController(new AddPlayerView(GameType.Tennis));

				addPlayerController.addEventHandlersToGeneralButtons();
			}
		};
		EventHandler<ActionEvent> addBasketballPlayerButtonEventHandler = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ObservableList<PlayerModel> BasketballPlayers = playersView.getBasketballPlayers();

				if (BasketballPlayers.size() == MatchModel.MAX_PLAYERS)
					UIHandler.showError("There are already 8 basketball players. Better luck next time.");

				AddPlayerController addPlayerController = new AddPlayerController(new AddPlayerView(GameType.Basketball));

				addPlayerController.addEventHandlersToGeneralButtons();
			}
		};
		EventHandler<ActionEvent> addFootballPlayerButtonEventHandler = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ObservableList<PlayerModel> footballPlayers = playersView.getFootballPlayers();

				if (footballPlayers.size() == MatchModel.MAX_PLAYERS)
					UIHandler.showError("There are already 8 football players. Better luck next time.");

				AddPlayerController addPlayerController = new AddPlayerController(new AddPlayerView(GameType.Football));

				addPlayerController.addEventHandlersToGeneralButtons();
			}
		};
		
		playersView.getAddTennisPlayerButton().setOnAction(addTennisPlayerButtonEventHandler);
		playersView.getAddBasketballPlayerButton().setOnAction(addBasketballPlayerButtonEventHandler);
		playersView.getAddFootballPlayerButton().setOnAction(addFootballPlayerButtonEventHandler);
	}

	// Methods
}