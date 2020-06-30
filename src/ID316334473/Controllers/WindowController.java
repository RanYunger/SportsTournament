package ID316334473.Controllers;

import ID316334473.UIHandler;
import ID316334473.Views.MainView;
import ID316334473.Views.View;
import ID316334473.Views.WindowView;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public abstract class WindowController extends Controller {
	// Constants

	// Fields

	// Properties (Getters and Setters)
	public WindowView getWindowView() {
		return (WindowView) getView();
	}

	// Constructors
	public WindowController(View view) {
		super(view);
	}

	// Methods
	public void addEventHandlersToGeneralButtons() {
		WindowView view = getWindowView();

		EventHandler<MouseEvent> audioImageViewEventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				ImageView audioImageView = view.getAudioImageView(), newImageView;

				UIHandler.toggleAudio();
				newImageView = UIHandler.buildImage(UIHandler.isAudioOn() ? "AudioOn.png" : "AudioOff.png", 30, 30);
				audioImageView.setImage(newImageView.getImage());
			}
		};
		EventHandler<MouseEvent> homeImageViewEventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// Validations
				if (view instanceof MainView)
					return;

				MainView mainView = new MainView();
				MainController mainController = new MainController(mainView);

				view.close();
				UIHandler.setMainView(mainView);
				UIHandler.setMainController(mainController);
			}
		};

		view.getAudioImageView().setOnMouseClicked(audioImageViewEventHandler);
		view.getHomeImageView().setOnMouseClicked(homeImageViewEventHandler);
	}
}
