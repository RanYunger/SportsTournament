package ID316334473.Controllers;

import ID316334473.UIHandler;
import ID316334473.Models.PlayerModel;
import ID316334473.Models.TournamentModel.GameType;
import ID316334473.Views.ChampionshipView;
import ID316334473.Views.ChampionshipsView;
import ID316334473.Views.View;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ChampionshipsController extends Controller {
	// Constants

	// Fields

	// Properties (Getters and Setters)
	public ChampionshipsView getChampionshipsView() {
		return (ChampionshipsView) getView();
	}

	// Constructors
	public ChampionshipsController(View view) {
		super(view);

		ChampionshipsView championshipsView = getChampionshipsView();
		EventHandler<MouseEvent> addTennisPlayerButtonEventHandler = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				ObservableList<PlayerModel> players = null;

				ChampionshipView championshipView = new ChampionshipView(GameType.Tennis, players);
				ChampionshipController championshipController = new ChampionshipController(championshipView);
				
				UIHandler.playAudio("Hit.mp3");
				championshipController.addEventHandlersToGeneralButtons();
				championshipsView.close();
			}
		};
		EventHandler<MouseEvent> addBasketballPlayerButtonEventHandler = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				ObservableList<PlayerModel> players = null;

				ChampionshipView championshipView = new ChampionshipView(GameType.Basketball, players);
				ChampionshipController championshipController = new ChampionshipController(championshipView);

				UIHandler.playAudio("Bounce.mp3");
				championshipController.addEventHandlersToGeneralButtons();
				championshipsView.close();
			}
		};
		EventHandler<MouseEvent> addFootballPlayerButtonEventHandler = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				ObservableList<PlayerModel> players = null;

				ChampionshipView championshipView = new ChampionshipView(GameType.Football, players);
				ChampionshipController championshipController = new ChampionshipController(championshipView);
			
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
