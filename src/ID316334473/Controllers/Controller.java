package ID316334473.Controllers;

import ID316334473.UIHandler;
import ID316334473.Views.MainView;
import ID316334473.Views.View;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public abstract class Controller {
	// Constants

	// Fields
	private View view;

	// Properties (Getters and Setters)
	public View getView() {
		return view;
	}

	protected void setView(View view) {
		this.view = view;
	}

	// Constructors
	public Controller(View view) {
		setView(view);
	}

	// Methods
	public void addEventHandlersToGeneralButtons() {
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
				UIHandler.getMainView().getStage().requestFocus();
			}
		};

		view.getAudioImageView().setOnMouseClicked(audioImageViewEventHandler);
		view.getHomeImageView().setOnMouseClicked(homeImageViewEventHandler);
	}
}
