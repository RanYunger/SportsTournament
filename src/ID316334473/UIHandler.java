package ID316334473;

import java.io.File;
import java.util.Optional;

import ID316334473.Controllers.MainController;
import ID316334473.Models.MatchModel;
import ID316334473.Models.PlayerModel;
import ID316334473.Views.MainView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
		if (mediaPlayer != null) {
			mediaPlayer.setMute(isAudioOn);
			isAudioOn = !isAudioOn;
		}
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

	@SuppressWarnings("static-access")
	public static void playAudio(String audioFileName) {
		String path = String.format("%s\\bin\\%s", System.getProperty("user.dir"), audioFileName);

		// Validations
		if (mediaPlayer != null)
			mediaPlayer.stop();

		media = new Media(new File(path).toURI().toString());
		mediaPlayer = new MediaPlayer(media);

		mediaPlayer.setCycleCount(audioFileName.equals("WeWillRockYou.mp3") ? mediaPlayer.INDEFINITE : 0);

		if (isAudioOn)
			mediaPlayer.play();
	}

	private static Optional<ButtonType> showAlert(AlertType alertType, String title, String header, String message,
			String audioFileName, boolean showAndWait) {
		Alert alert = new Alert(alertType);
		alert.initOwner(mainView.getStage());
		alert.setX(alert.getOwner().getX() + alert.getOwner().getWidth() - alert.getWidth());
		alert.setY(alert.getOwner().getY() + alert.getOwner().getHeight() - alert.getHeight());

		alert.setTitle(title);
		alert.setHeaderText(header);

		if ((message != null) && (!message.isBlank())) {
			TextArea textArea = new TextArea(message);
			textArea.setEditable(false);
			alert.getDialogPane().setExpandableContent(new ScrollPane(textArea));
		}

		if (!audioFileName.isBlank())
			playAudio(audioFileName);

		if (showAndWait)
			return alert.showAndWait();
		else
			alert.show();

		return null;
	}

	public static void showSuccess(String message, boolean hasAudio) {
		showAlert(AlertType.INFORMATION, "Success", message, "", hasAudio ? "Yayyy.mp3" : "", hasAudio);
	}

	public static void showWarning(String message, boolean hasAudio) {
		showAlert(AlertType.WARNING, "Warning", message, "", hasAudio ? "UhOh.mp3" : "", true);
	}

	public static void showError(String message) {
		showAlert(AlertType.ERROR, "Error", message, "", "Awww.mp3", true);
	}

	public static void showError(String header, String message) {
		showAlert(AlertType.ERROR, "Error", header, message, "Awww.mp3", true);
	}

	@SuppressWarnings("unchecked")
	public static TableView<PlayerModel> buildPlayersTableView() {
		TableView<PlayerModel> tableView = new TableView<PlayerModel>();
		TableColumn<PlayerModel, Number> playerIDTableColumn, playerScoreTableColumn;
		TableColumn<PlayerModel, String> playerNameTableColumn;

		playerIDTableColumn = new TableColumn<PlayerModel, Number>("ID");
		playerIDTableColumn.setCellValueFactory(cell -> cell.getValue().getObservableID());
		playerIDTableColumn.setMinWidth(160);

		playerNameTableColumn = new TableColumn<PlayerModel, String>("Name");
		playerNameTableColumn.setCellValueFactory(cell -> cell.getValue().getObservableName());
		playerNameTableColumn.setMinWidth(178);

		playerScoreTableColumn = new TableColumn<PlayerModel, Number>("Score");
		playerScoreTableColumn.setCellValueFactory(cell -> cell.getValue().getObservableTournamentScore());
		playerScoreTableColumn.setMinWidth(160);

		tableView.getColumns().addAll(playerIDTableColumn, playerNameTableColumn, playerScoreTableColumn);
		for (TableColumn<?, ?> tableColumn : tableView.getColumns()) {
			tableColumn.setStyle("-fx-alignment: CENTER;");
			tableColumn.setEditable(false);
			tableColumn.setReorderable(false);
			tableColumn.setSortable(false);
			tableColumn.setResizable(false);
		}

		return tableView;
	}

	public static HBox buildMatchHBox(MatchModel matchModel) {
		HBox matchHBox = new HBox();
		TextField[] playerNamesTextFields = new TextField[2], playerScoresTextFields = new TextField[2];
		ImageView playerTurnsImageViews[] = new ImageView[2];
		ImageView vsImageView;
		Tooltip instructionsTooltip = new Tooltip("Press TAB to pass turn");
		PlayerModel player0 = matchModel.getPlayer0(), player1 = matchModel.getPlayer1();

		playerNamesTextFields[0] = new TextField(player0.getTextualName());
		playerNamesTextFields[1] = new TextField(player1.getTextualName());
		playerScoresTextFields[0] = new TextField("" + player0.getNumericMatchScore());
		playerScoresTextFields[1] = new TextField("" + player1.getNumericMatchScore());
		vsImageView = UIHandler.buildImage("Vs.png", 30, 34);

		matchHBox.setAlignment(Pos.CENTER);
		for (int i = 0; i < playerNamesTextFields.length; i++) {
			playerNamesTextFields[i].setEditable(false);
			playerNamesTextFields[i].setAlignment(Pos.CENTER);
			playerNamesTextFields[i].setMinWidth(150);
			Tooltip.install(playerNamesTextFields[i], instructionsTooltip);

			playerScoresTextFields[i].setEditable(false);
			playerScoresTextFields[i].setAlignment(Pos.CENTER);
			playerScoresTextFields[i].setMaxWidth(50);
			Tooltip.install(playerScoresTextFields[i], instructionsTooltip);

			playerTurnsImageViews[i] = buildImage(player0.getGame() + ".png", 30, 30);
			playerTurnsImageViews[i].setVisible(i == 0);
			Tooltip.install(playerTurnsImageViews[i], instructionsTooltip);
		}

		Tooltip.install(vsImageView, instructionsTooltip);

		matchHBox.getChildren().addAll(playerTurnsImageViews[0], playerNamesTextFields[0], playerScoresTextFields[0],
				vsImageView, playerScoresTextFields[1], playerNamesTextFields[1], playerTurnsImageViews[1]);
		HBox.setMargin(vsImageView, new Insets(0, 10, 0, 10));
		for (Node child : matchHBox.getChildren()) {
			child.setFocusTraversable(false);
		}

		return matchHBox;
	}

	public static void setGeneralFeatures(Stage stage) {
		setIcon(stage);
		stage.setTitle("Sports Tournament");
		stage.setResizable(false);
	}

	public static void setIcon(Stage stage) {
		stage.getIcons().add(UIHandler.buildImage("Tournaments.png", 0, 0).getImage());
	}

	public static StackPane buildBackground(String backgroundImageName, Node node, double width, double height,
			double fontSize) {
		ImageView backgroundImage = buildImage(backgroundImageName, width, height),
				audioImageView = buildImage(isAudioOn ? "AudioOn.png" : "AudioOff.png", 30, 30),
				homeImageView = buildImage("Home.png", 30, 30);
		VBox topVBox = new VBox();
		Label creatorLabel = new Label("From one of the creators of \"Corona Elections\":"),
				topLabel = new Label("Sports Tournaments™"),
				bottomLabel = new Label("The only app to manage sports-related stuff");
		StackPane stackPane = new StackPane();

		topVBox.setAlignment(Pos.CENTER);
		creatorLabel.setFont(new Font(15));
		creatorLabel.setTextFill(Color.WHITE);
		topLabel.setFont(new Font(fontSize));
		topLabel.setTextFill(Color.WHITE);
		bottomLabel.setFont(new Font(fontSize));
		bottomLabel.setTextFill(Color.WHITE);

		topVBox.getChildren().addAll(creatorLabel, topLabel);
		VBox.setMargin(creatorLabel, new Insets(10, 0, 0, 0));
		VBox.setMargin(topLabel, new Insets(0, 0, -10, 0));

		stackPane.getChildren().addAll(backgroundImage, topVBox, bottomLabel, node);
		stackPane.getChildren().addAll(audioImageView, homeImageView);
		StackPane.setMargin(audioImageView, new Insets(height, width * 0.95, height * 1.9, 10));
		StackPane.setMargin(homeImageView, new Insets(height, 10, height * 1.9, width * 0.95));
		StackPane.setMargin(topVBox, new Insets(height * 0.92, 0, height * 1.8, 0));
		StackPane.setMargin(bottomLabel, new Insets(height * 0.92, 0, height * 0.08, 0));

		return stackPane;
	}

	public static ImageView buildImage(String imageName, double height, double width) {
		Image image = new Image(imageName, height, width, false, false);

		return new ImageView(image);
	}
}