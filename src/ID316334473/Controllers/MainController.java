package ID316334473.Controllers;

import ID316334473.UIHandler;
import ID316334473.Views.ChampionshipsView;
import ID316334473.Views.MainView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MainController extends Controller {
	// Constants

	// Fields

	// Properties (Getters and Setters)
	public MainView getMainView() {
		return (MainView) super.getView();
	}

	// Constructors
	public MainController(MainView mainView) {
		super(mainView);

		EventHandler<MouseEvent> playersImageViewEventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				mainView.close();
				UIHandler.playAudio("Marching.mp3");
//
//				PlayersView playersView = new PlayersView();
//				PlayersController playersController = new PlayersController(playersView);
//
//				playersController.addEventHandlersToGeneralButtons();
			}
		};
		EventHandler<MouseEvent> championshipsImageViewEventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				mainView.close();
				UIHandler.playAudio("Whistle.mp3");

				ChampionshipsView championshipsView = new ChampionshipsView();
				ChampionshipsController championshipsController = new ChampionshipsController(championshipsView);

				championshipsController.addEventHandlersToGeneralButtons();
			}
		};
		EventHandler<MouseEvent> trophiesImageViewEventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				mainView.close();
				UIHandler.playAudio("Sparkle.mp3");
//				
//				TrophiesView trophiesView = new TrophiesView();
//				TrophiesController trophiesController = new TrophiesController(trophiesView);
//				
//				trophiesController.addEventHandlersToGeneralButtons();
			}
		};

		getMainView().getPlayersImageView().setOnMouseClicked(playersImageViewEventHandler);
		getMainView().getChampionshipsImageView().setOnMouseClicked(championshipsImageViewEventHandler);
		getMainView().getTrophiesImageView().setOnMouseClicked(trophiesImageViewEventHandler);

	}

	// Methods
}
