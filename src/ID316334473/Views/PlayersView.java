package ID316334473.Views;

import ID316334473.UIHandler;
import ID316334473.Models.BasketballPlayerModel;
import ID316334473.Models.FootballPlayerModel;
import ID316334473.Models.PlayerModel;
import ID316334473.Models.TennisPlayerModel;
import ID316334473.Models.TournamentModel.GameType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class PlayersView extends WindowView {
	// Constants

	// Fields
	private GridPane gridPane;
	private VBox tennisVBox, basketballVBox, footballVBox;
	private ImageView tennisImageView, basketballImageView, footballImageView;
	private Button addTennisPlayerButton, addBasketballPlayerButton, addFootballPlayerButton;
	private ObservableList<PlayerModel> tennisPlayers, basketballPlayers, footballPlayers;
	private TableView<PlayerModel> tennisPlayersTableView, basketballPlayersTableView, footballPlayersTableView;

	// Properties (Getters and Setters)
	public ObservableList<PlayerModel> getTennisPlayers() {
		return tennisPlayers;
	}

	public ObservableList<PlayerModel> getBasketballPlayers() {
		return basketballPlayers;
	}

	public ObservableList<PlayerModel> getFootballPlayers() {
		return footballPlayers;
	}
	
	public Button getAddTennisPlayerButton() {
		return addTennisPlayerButton;
	}

	public Button getAddBasketballPlayerButton() {
		return addBasketballPlayerButton;
	}

	public Button getAddFootballPlayerButton() {
		return addFootballPlayerButton;
	}

	public TableView<PlayerModel> getTennisPlayersTableView() {
		return tennisPlayersTableView;
	}

	public TableView<PlayerModel> getBasketballPlayersTableView() {
		return basketballPlayersTableView;
	}

	public TableView<PlayerModel> getFootballPlayersTableView() {
		return footballPlayersTableView;
	}

	// Constructors
	public PlayersView() {
		super();

		buildScene();
		addEffects();

		initPlayers();
	}

	// Methods
	@Override
	protected void buildScene() {
		double sceneWidth = 1500, sceneHeight = 700, backgroundFontSize = 50, viewFontSize = 15;

		gridPane = new GridPane();
		tennisVBox = new VBox();
		basketballVBox = new VBox();
		footballVBox = new VBox();
		tennisImageView = UIHandler.buildImage("Tennis.png", 80, 80);
		basketballImageView = UIHandler.buildImage("Basketball.png", 80, 80);
		footballImageView = UIHandler.buildImage("Football.png", 75, 75);
		addTennisPlayerButton = new Button("Add Player");
		addBasketballPlayerButton = new Button("Add Player");
		addFootballPlayerButton = new Button("Add Player");
		tennisPlayersTableView = UIHandler.buildPlayersTableView();
		basketballPlayersTableView = UIHandler.buildPlayersTableView();
		footballPlayersTableView = UIHandler.buildPlayersTableView();

		gridPane.getRowConstraints().add(new RowConstraints());
		gridPane.getRowConstraints().get(0).setPercentHeight(100);

		gridPane.getColumnConstraints().add(new ColumnConstraints());
		gridPane.getColumnConstraints().get(0).setPercentWidth(400);
		gridPane.getColumnConstraints().add(new ColumnConstraints());
		gridPane.getColumnConstraints().get(1).setPercentWidth(400);
		gridPane.getColumnConstraints().add(new ColumnConstraints());
		gridPane.getColumnConstraints().get(2).setPercentWidth(400);

		addTennisPlayerButton.setMinWidth(100);
		addTennisPlayerButton.setFont(new Font(viewFontSize));
		addBasketballPlayerButton.setMinWidth(100);
		addBasketballPlayerButton.setFont(new Font(viewFontSize));
		addFootballPlayerButton.setMinWidth(100);
		addFootballPlayerButton.setFont(new Font(viewFontSize));

		tennisVBox.setAlignment(Pos.CENTER);
		basketballVBox.setAlignment(Pos.CENTER);
		footballVBox.setAlignment(Pos.CENTER);

		tennisVBox.getChildren().addAll(tennisImageView, addTennisPlayerButton, tennisPlayersTableView);
		VBox.setMargin(tennisImageView, new Insets(90, 0, 0, 0));
		VBox.setMargin(addTennisPlayerButton, new Insets(10, 0, 10, 0));
		VBox.setMargin(tennisPlayersTableView, new Insets(10, 0, 90, 0));

		basketballVBox.getChildren().addAll(basketballImageView, addBasketballPlayerButton, basketballPlayersTableView);
		VBox.setMargin(basketballImageView, new Insets(90, 0, 0, 10));
		VBox.setMargin(addBasketballPlayerButton, new Insets(10, 0, 10, 10));
		VBox.setMargin(basketballPlayersTableView, new Insets(10, 0, 90, 0));

		footballVBox.getChildren().addAll(footballImageView, addFootballPlayerButton, footballPlayersTableView);
		VBox.setMargin(footballImageView, new Insets(90, 0, 0, 0));
		VBox.setMargin(addFootballPlayerButton, new Insets(10, 0, 10, 0));
		VBox.setMargin(footballPlayersTableView, new Insets(15, 0, 90, 0));

		gridPane.add(tennisVBox, 0, 0);
		gridPane.add(basketballVBox, 1, 0);
		gridPane.add(footballVBox, 2, 0);

		stage.setScene(
				new Scene(UIHandler.buildBackground("Arena.jpg", gridPane, sceneWidth, sceneHeight, backgroundFontSize),
						sceneWidth, sceneHeight));
		UIHandler.setGeneralFeatures(stage);

		stage.show();
	}

	@Override
	protected void addEffects() {
		super.addEffects();

		UIHandler.addCursorEffectsToNode(addTennisPlayerButton);
		UIHandler.addCursorEffectsToNode(addBasketballPlayerButton);
		UIHandler.addCursorEffectsToNode(addFootballPlayerButton);
	}

	public void initPlayers() {
		tennisPlayers = FXCollections.observableArrayList();
		tennisPlayers.add(new TennisPlayerModel(123456789, "Maria Sharapova"));
		tennisPlayers.add(new TennisPlayerModel(123456788, "Serena Williams"));
		tennisPlayers.add(new TennisPlayerModel(123456787, "Shahar Pe'er"));

		basketballPlayers = FXCollections.observableArrayList();
		basketballPlayers.add(new BasketballPlayerModel(123456786, "Michael Jordan"));
		basketballPlayers.add(new BasketballPlayerModel(123456785, "Shaquille O'Neal"));
		basketballPlayers.add(new BasketballPlayerModel(123456784, "Dennis Rodman"));

		footballPlayers = FXCollections.observableArrayList();
		footballPlayers.add(new FootballPlayerModel(123456783, "Christiano Ronaldo"));
		footballPlayers.add(new FootballPlayerModel(123456782, "Lionel Messi"));
		footballPlayers.add(new FootballPlayerModel(123456781, "Ronaldinio"));

		tennisPlayersTableView.setItems(tennisPlayers);
		basketballPlayersTableView.setItems(basketballPlayers);
		footballPlayersTableView.setItems(footballPlayers);
	}

	public void addPlayer(PlayerModel player) {
		GameType game = player.getGame();

		switch (game) {
		case Tennis:
			tennisPlayers.add(player);
			break;
		case Basketball:
			basketballPlayers.add(player);
			break;
		case Football:
			footballPlayers.add(player);
			break;
		}
	}
	
	public void disableAllButtons(boolean disabled) {
		addTennisPlayerButton.setDisable(disabled);
		addBasketballPlayerButton.setDisable(disabled);
		addFootballPlayerButton.setDisable(disabled);
	}
}