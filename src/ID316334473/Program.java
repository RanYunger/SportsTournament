package ID316334473;

import java.util.ArrayList;

import ID316334473.Controllers.MainController;
import ID316334473.Models.PlayerModel;
import ID316334473.Views.MainView;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.stage.Stage;

public class Program extends Application {
	// Constants

	// Fields

	// Properties (Getters and Setters)

	// Constructors

	// Methods
	@Override
	public void start(Stage primaryStage) throws Exception {
		MainView mainView = new MainView();
		MainController mainController = new MainController(mainView);

		SearchHandler.setTennisFinalTrio(FXCollections.observableList(new ArrayList<PlayerModel>()));
		SearchHandler.setBasketballFinalTrio(FXCollections.observableList(new ArrayList<PlayerModel>()));
		SearchHandler.setFootballFinalTrio(FXCollections.observableList(new ArrayList<PlayerModel>()));

		UIHandler.playAudio("WeWillRockYou.mp3");
		mainController.addEventHandlersToGeneralButtons();
		UIHandler.setMainView(mainView);
		UIHandler.setMainController(mainController);
	}
}
