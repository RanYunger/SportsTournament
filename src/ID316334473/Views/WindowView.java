package ID316334473.Views;

import ID316334473.UIHandler;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

public abstract class WindowView extends View {
	// Constants

	// Fields
	protected ImageView audioImageView, homeImageView;

	// Properties (Getters and Setters)
	public ImageView getAudioImageView() {
		return audioImageView;
	}

	protected void setAudioImageView(ImageView audioImageView) {
		this.audioImageView = audioImageView;
	}

	public ImageView getHomeImageView() {
		return homeImageView;
	}

	protected void setHomeImageView(ImageView homeImageView) {
		this.homeImageView = homeImageView;
	}

	// Constructors

	// Methods
	@Override
	protected abstract void buildScene();

	protected void addEffects() {
		Scene scene = stage.getScene();
		ObservableList<Node> rootNodes = scene.getRoot().getChildrenUnmodifiable();
		ImageView imageViewNode;
		Node currentNode;

		for (int i = 0; i < rootNodes.size(); i++) {
			currentNode = rootNodes.get(i);
			if (currentNode instanceof ImageView) {
				imageViewNode = (ImageView) currentNode;
				if (imageViewNode.getImage().getUrl().contains("Audio")) {
					setAudioImageView(imageViewNode);
					UIHandler.addAudioToImageView(audioImageView, "Sparkle.mp3");
				} else if (imageViewNode.getImage().getUrl().contains("Home")) {
					setHomeImageView(imageViewNode);
					UIHandler.addAudioToImageView(imageViewNode, "Sparkle.mp3");
				}
			}
		}
	}
}
