package ID316334473.Controllers;

import ID316334473.UIHandler;
import ID316334473.Views.MainView;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MainController {
	// Constants

	// Fields
	private MainView mainView;

	// Properties (Getters and Setters)
	public MainView getMainView() {
		return mainView;
	}

	private void setMainView(MainView mainView) {
		this.mainView = mainView;
	}
	
	// Constructors
	public MainController(MainView mainView) {
		setMainView(mainView);

		EventHandler<MouseEvent> audioImageViewEventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				ImageView audioImageView = mainView.getAudioImageView(), newImageView;

				UIHandler.toggleAudio();
				newImageView = UIHandler.buildImage(UIHandler.isAudioOn() ? "AudioOn.png" : "AudioOff.png", 30, 30);
				audioImageView.setImage(newImageView.getImage());
			}
		};

		mainView.getAudioImageView().setOnMouseClicked(audioImageViewEventHandler);
	}

	// Methods
}
