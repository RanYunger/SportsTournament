package ID316334473;

import java.io.File;

import ID316334473.Controllers.MainController;
import ID316334473.Views.MainView;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

// Helper class which contains all UI methods, plus extra helpful methods.
public class UIHandler {
	// Constants

	// Fields
	private static MainController mainController;
	private static MainView mainView;

	private static boolean isAudioOn = true;
	private static MediaPlayer mediaPlayer;
	private static Media media;

	// Properties
	public static MainController getMainController() {
		return mainController;
	}

	public static void setMainController(MainController mainController) {
		UIHandler.mainController = mainController;
	}

	public static MainView getMainView() {
		return mainView;
	}

	public static void setMainView(MainView mainView) {
		UIHandler.mainView = mainView;
	}

	public static boolean isAudioOn() {
		return isAudioOn;
	}

	public static void toggleAudio() {
		mediaPlayer.setMute(isAudioOn);
		isAudioOn = !isAudioOn;
	}

	// Methods
	public static void addCursorEffectsToNode(Node node) {
		node.setOnMouseEntered(entered -> node.getScene().setCursor(Cursor.HAND));
		node.setOnMouseExited(entered -> node.getScene().setCursor(Cursor.DEFAULT));
	}

	public static void addAudioToImageView(ImageView imageView, String audioFileName) {
		imageView.setOnMouseClicked(click -> playAudio(audioFileName));
		addCursorEffectsToNode(imageView);
	}

	public static void playAudio(String audioFileName) {
		String path = String.format("%s\\bin\\%s", System.getProperty("user.dir"), audioFileName);

		// Validations
		if (mediaPlayer != null)
			mediaPlayer.stop();

		media = new Media(new File(path).toURI().toString());
		mediaPlayer = new MediaPlayer(media);

		if (isAudioOn)
			mediaPlayer.play();
	}

	private static void showAlert(AlertType alertType, String title, String header, String message, String audio) {
		Alert alert = new Alert(alertType);
		alert.initOwner(mainView.getStage());
		alert.setX(alert.getOwner().getX() + alert.getOwner().getWidth() - alert.getWidth());
		alert.setY(alert.getOwner().getY() + alert.getOwner().getHeight() - alert.getHeight());

		alert.setTitle(title);
		alert.setHeaderText(header);

		if (message != null && !message.isBlank()) {
			TextArea textArea = new TextArea(message);
			textArea.setEditable(false);
			alert.getDialogPane().setExpandableContent(new ScrollPane(textArea));
		}

		playAudio(audio);

		alert.showAndWait();
	}

	public static void showSuccess(String message) {
		showAlert(AlertType.INFORMATION, "Success", message, "", "Yayyy.mp3");
	}

	public static void showWarning(String message) {
		showAlert(AlertType.WARNING, "Warning", message, "", "UhOh.mp3");
	}

	public static void showError(String message) {
		showAlert(AlertType.ERROR, "Error", message, "", "Awww.mp3");
	}

	public static void showError(String header, String message) {
		showAlert(AlertType.ERROR, "Error", header, message, "Awww.mp3");
	}

	public static void showError(String header, StackTraceElement[] stackTrace) {
		StringBuilder fullMessage = new StringBuilder();

		for (StackTraceElement stackTraceElement : stackTrace) {
			fullMessage.append(stackTraceElement.toString() + "\n");
		}

		showError(header, fullMessage.toString());
	}

	public static void setGeneralFeatures(Stage stage) {
		setIcon(stage);
		stage.setTitle("Sports Tournament");
		stage.setResizable(false);
	}

	public static void setIcon(Stage stage) {
		stage.getIcons().add(UIHandler.buildImage("Elections.jpg", 0, 0).getImage());
	}

	public static StackPane buildBackground(Node node, double width, double height, double fontSize, boolean hasTabs) {
		ImageView backgroundImage = buildImage("IsraelFlag.PNG", width, height),
				audioImageView = buildImage("AudioOn.png", 30, 30);
		Label topLabel = new Label("מדינה אנונימית במזרח התיכון");
		Label bottomLabel = new Label("מערכת ניהול בחירות בתקופת קורונה");
		Label fileAComplaintLabel = new Label("מצאת תקלה?");
		Label audioLabel = new Label("Audio:");
		Button fileAComplaintButton = new Button("התלונן עלינו!");
		StackPane stackPane = new StackPane();

		topLabel.setFont(new Font(fontSize));
		topLabel.setTextFill(Color.WHITE);
		bottomLabel.setFont(new Font(fontSize));
		bottomLabel.setTextFill(Color.WHITE);
		fileAComplaintLabel.setFont(new Font(15));
		fileAComplaintLabel.setTextFill(Color.WHITE);
		audioLabel.setFont(new Font(15));
		audioLabel.setTextFill(Color.WHITE);
		fileAComplaintButton.setStyle("-fx-background-radius: 5em; -fx-background-color: Red;");
		fileAComplaintButton.toFront();

		stackPane.getChildren().addAll(backgroundImage, topLabel, bottomLabel, node);
		if (node instanceof TabPane) { // Visible only in Tabs
			stackPane.getChildren().addAll(fileAComplaintLabel, fileAComplaintButton, audioLabel, audioImageView);
			StackPane.setMargin(fileAComplaintLabel, new Insets(height, 10, height * 1.8, width * 0.92));
			StackPane.setMargin(fileAComplaintButton, new Insets(height, 10, height * 1.8, width * 0.8));
			StackPane.setMargin(audioLabel, new Insets(height, width * 0.95, height * 1.8, 10));
			StackPane.setMargin(audioImageView, new Insets(height, width * 0.88, height * 1.8, 10));
		}
		StackPane.setMargin(topLabel, new Insets(hasTabs ? height : height * 0.92, 0, height * 1.8, 0));
		StackPane.setMargin(bottomLabel, new Insets(hasTabs ? height * 0.95 : height * 0.92, 0, height * 0.08, 0));

		return stackPane;
	}

	public static ImageView buildImage(String imageName, double height, double width) {
		Image image = new Image(imageName, height, width, false, false);

		return new ImageView(image);
	}
}