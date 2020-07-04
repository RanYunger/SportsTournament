package ID316334473.Views;

import ID316334473.UIHandler;
import ID316334473.Models.TournamentModel.GameType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class AddPlayerView extends WindowView {
	// Constants

	// Fields
	private GameType game;
	private GridPane gridPane;
	private VBox vBox;
	private HBox playerIDHBox, playerNameHBox;
	private Label headerLabel, playerIDLabel, playerNameLabel;
	private TextField playerIDTextField, playerNameTextField;
	private Button submitButton;
	private ImageView playerImageView;

	// Properties (Getters and Setters)
	public GameType getGame() {
		return game;
	}

	public void setGame(GameType game) {
		this.game = game;
	}

	public TextField getPlayerIDTextField() {
		return playerIDTextField;
	}

	public TextField getPlayerNameTextField() {
		return playerNameTextField;
	}

	public Button getSubmitButton() {
		return submitButton;
	}

	// Constructors
	public AddPlayerView(GameType game) {
		super();

		setGame(game);

		buildScene();
		addEffects();
	}

	// Methods
	@Override
	protected void buildScene() {
		String gameName = game.name();
		double sceneWidth = 700, sceneHeight = 500, backgroundFontSize = 30, viewFontSize = 20;

		gridPane = new GridPane();
		vBox = new VBox();
		playerIDHBox = new HBox();
		playerNameHBox = new HBox();
		headerLabel = new Label(String.format("New %s Player", gameName));
		playerIDLabel = new Label("ID:");
		playerNameLabel = new Label("Name:");
		playerIDTextField = new TextField();
		playerNameTextField = new TextField();
		submitButton = new Button("Submit");
		playerImageView = UIHandler.buildImage(gameName + "Player.png", sceneHeight * 0.7, sceneWidth / 2);

		gridPane.getRowConstraints().add(new RowConstraints());
		gridPane.getRowConstraints().get(0).setPercentHeight(100);

		gridPane.getColumnConstraints().add(new ColumnConstraints());
		gridPane.getColumnConstraints().get(0).setPercentWidth(50);
		gridPane.getColumnConstraints().add(new ColumnConstraints());
		gridPane.getColumnConstraints().get(1).setPercentWidth(50);

		vBox.setAlignment(Pos.CENTER);
		headerLabel.setFont(new Font(backgroundFontSize));
		headerLabel.setTextFill(Color.WHITE);
		playerIDLabel.setFont(new Font(viewFontSize));
		playerIDLabel.setTextFill(Color.WHITE);
		playerNameLabel.setFont(new Font(viewFontSize));
		playerNameLabel.setTextFill(Color.WHITE);
		playerIDTextField.setMinWidth(200);
		playerIDTextField.setTooltip(new Tooltip("Format: 9 digits"));
		playerNameTextField.setMinWidth(200);
		playerNameTextField.setTooltip(new Tooltip("Format: firstname surename (capitalized) (i.e. John Doe)"));
		submitButton.setFont(new Font(viewFontSize));

		playerIDHBox.getChildren().addAll(playerIDLabel, playerIDTextField);
		HBox.setMargin(playerIDLabel, new Insets(0, 10, 0, 10));
		HBox.setMargin(playerIDTextField, new Insets(0, 10, 0, 43));

		playerNameHBox.getChildren().addAll(playerNameLabel, playerNameTextField);
		HBox.setMargin(playerNameLabel, new Insets(10, 10, 0, 10));
		HBox.setMargin(playerNameTextField, new Insets(10, 10, 0, 10));

		vBox.getChildren().addAll(headerLabel, playerIDHBox, playerNameHBox, submitButton);
		VBox.setMargin(headerLabel, new Insets(0, 0, 10, 0));
		VBox.setMargin(playerIDHBox, new Insets(10, 0, 0, 0));
		VBox.setMargin(playerNameHBox, new Insets(10, 0, 0, 0));
		VBox.setMargin(submitButton, new Insets(10, 0, 0, 0));

		gridPane.add(vBox, 0, 0);
		gridPane.add(playerImageView, 1, 0);

		stage.setScene(new Scene(UIHandler.buildBackground(gameName + "Arena.jpg", gridPane, sceneWidth, sceneHeight,
				backgroundFontSize), sceneWidth, sceneHeight));
		UIHandler.setGeneralFeatures(stage);

		stage.show();
	}

	@Override
	protected void addEffects() {
		super.addEffects();

		homeImageView.setVisible(false); // User won't be able to jump back to MainView from here
	}

	@Override
	protected Node asNode() {
		return (Node) gridPane;
	}
}
