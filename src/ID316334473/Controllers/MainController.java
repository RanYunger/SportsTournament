package ID316334473.Controllers;

import ID316334473.UIHandler;
import ID316334473.Views.ChampionshipsView;
import ID316334473.Views.MainView;
import ID316334473.Views.PlayersView;
import ID316334473.Views.TrophiesView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MainController extends Controller {
	// Constants

	// Fields

	// Properties (Getters and Setters)
	public MainView getMainView() {
		return (MainView) getView();
	}

	// Constructors
	public MainController(MainView view) {
		super(view);

		MainView mainView = getMainView();
		EventHandler<MouseEvent> playersImageViewEventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				UIHandler.playAudio("Marching.mp3");

				PlayersView playersView = new PlayersView();
				PlayersController playersController = new PlayersController(playersView);

				playersController.addEventHandlersToGeneralButtons();
			}
		};
		EventHandler<MouseEvent> championshipsImageViewEventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				UIHandler.playAudio("Whistle.mp3");

				ChampionshipsView championshipsView = new ChampionshipsView();
				ChampionshipsController championshipsController = new ChampionshipsController(championshipsView);

				championshipsController.addEventHandlersToGeneralButtons();
			}
		};
		EventHandler<MouseEvent> trophiesImageViewEventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				UIHandler.playAudio("Cheers.mp3");
				
				TrophiesView trophiesView = new TrophiesView();
				TrophiesController trophiesController = new TrophiesController(trophiesView);
				
				trophiesController.addEventHandlersToGeneralButtons();
			}
		};

		mainView.getPlayersImageView().setOnMouseClicked(playersImageViewEventHandler);
		mainView.getChampionshipsImageView().setOnMouseClicked(championshipsImageViewEventHandler);
		mainView.getTrophiesImageView().setOnMouseClicked(trophiesImageViewEventHandler);
	}

	// Methods
}
