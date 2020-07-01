package ID316334473.Controllers;

import ID316334473.SearchHandler;
import ID316334473.UIHandler;
import ID316334473.Models.PlayerModel;
import ID316334473.Models.TournamentModel;
import ID316334473.Models.TournamentModel.GameType;
import ID316334473.Views.TournamentView;
import ID316334473.Views.TournamentsView;
import ID316334473.Views.View;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class TournamentsController extends WindowController {
	// Constants

	// Fields

	// Properties (Getters and Setters)
	public TournamentsView getTournamentsView() {
		return (TournamentsView) getView();
	}

	// Constructors
	public TournamentsController(View view) {
		super(view);

		TournamentsView tournamentsView = getTournamentsView();
		EventHandler<MouseEvent> addTennisPlayerButtonEventHandler = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				ObservableList<PlayerModel> tennisPlayers = SearchHandler.getTennisPlayers();

				// Validations
				if ((tennisPlayers == null) || (tennisPlayers.size() < TournamentModel.MAX_PARTICIPANTS))
					UIHandler.showWarning("Make sure to have exactly 8 tennis players!");
				else {
					TournamentView tournamentView = new TournamentView(GameType.Tennis, tennisPlayers);
					TournamentController tournamentController = new TournamentController(tournamentView);

					UIHandler.playAudio("Hit.mp3");
					tournamentController.addEventHandlersToGeneralButtons();
					tournamentsView.close();
				}
			}
		};
		EventHandler<MouseEvent> addBasketballPlayerButtonEventHandler = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				ObservableList<PlayerModel> basketballPlayers = SearchHandler.getBaketballPlayers();

				// Validations
				if ((basketballPlayers == null) || (basketballPlayers.size() < TournamentModel.MAX_PARTICIPANTS))
					UIHandler.showWarning("Make sure to have exactly 8 basketball players!");
				else {
					TournamentView tournamentView = new TournamentView(GameType.Basketball, basketballPlayers);
					TournamentController tournamentController = new TournamentController(tournamentView);

					UIHandler.playAudio("Bounce.mp3");
					tournamentController.addEventHandlersToGeneralButtons();
					tournamentsView.close();
				}
			}
		};
		EventHandler<MouseEvent> addFootballPlayerButtonEventHandler = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				ObservableList<PlayerModel> footballPlayers = SearchHandler.getFootballPlayers();

				// Validations
				if ((footballPlayers == null) || (footballPlayers.size() < TournamentModel.MAX_PARTICIPANTS))
					UIHandler.showWarning("Make sure to have exactly 8 football players!");
				else {
					TournamentView tournamentView = new TournamentView(GameType.Football,
							SearchHandler.getFootballPlayers());
					TournamentController tournamentController = new TournamentController(tournamentView);

					UIHandler.playAudio("Kick.mp3");
					tournamentController.addEventHandlersToGeneralButtons();
					tournamentsView.close();
				}
			}
		};

		tournamentsView.getTennisImageView().setOnMouseClicked(addTennisPlayerButtonEventHandler);
		tournamentsView.getBasketballImageView().setOnMouseClicked(addBasketballPlayerButtonEventHandler);
		tournamentsView.getFootballImageView().setOnMouseClicked(addFootballPlayerButtonEventHandler);
	}

	// Methods
}
