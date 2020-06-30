package ID316334473.Controllers;

import ID316334473.UIHandler;
import ID316334473.Models.PlayerModel;
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
	public TournamentsView getChampionshipsView() {
		return (TournamentsView) getView();
	}

	// Constructors
	public TournamentsController(View view) {
		super(view);

		TournamentsView championshipsView = getChampionshipsView();
		EventHandler<MouseEvent> addTennisPlayerButtonEventHandler = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				ObservableList<PlayerModel> players = null;

				TournamentView championshipView = new TournamentView(GameType.Tennis, players);
				TournamentController championshipController = new TournamentController(championshipView);
				
				UIHandler.playAudio("Hit.mp3");
				championshipController.addEventHandlersToGeneralButtons();
				championshipsView.close();
			}
		};
		EventHandler<MouseEvent> addBasketballPlayerButtonEventHandler = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				ObservableList<PlayerModel> players = null;

				TournamentView championshipView = new TournamentView(GameType.Basketball, players);
				TournamentController championshipController = new TournamentController(championshipView);

				UIHandler.playAudio("Bounce.mp3");
				championshipController.addEventHandlersToGeneralButtons();
				championshipsView.close();
			}
		};
		EventHandler<MouseEvent> addFootballPlayerButtonEventHandler = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				ObservableList<PlayerModel> players = null;

				TournamentView championshipView = new TournamentView(GameType.Football, players);
				TournamentController championshipController = new TournamentController(championshipView);
			
				UIHandler.playAudio("Kick.mp3");
				championshipController.addEventHandlersToGeneralButtons();
				championshipsView.close();
			}
		};

		championshipsView.getTennisImageView().setOnMouseClicked(addTennisPlayerButtonEventHandler);
		championshipsView.getBasketballImageView().setOnMouseClicked(addBasketballPlayerButtonEventHandler);
		championshipsView.getFootballImageView().setOnMouseClicked(addFootballPlayerButtonEventHandler);
	}

	// Methods
}
