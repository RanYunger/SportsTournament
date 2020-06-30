package ID316334473;

import ID316334473.Controllers.MainController;
import ID316334473.Views.MainView;
import javafx.application.Application;
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
		
		mainController.addEventHandlersToGeneralButtons();
		UIHandler.setMainView(mainView);
		UIHandler.setMainController(mainController);
	}
}
