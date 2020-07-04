package ID316334473.Views;

import java.util.Collections;

import ID316334473.SearchHandler;
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
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class PlayersView extends WindowView {
	// Constants

	// Fields
	private HBox hBox;
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

		hBox = new HBox();
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

		hBox.getChildren().addAll(tennisVBox, basketballVBox, footballVBox);

		stage.setScene(
				new Scene(UIHandler.buildBackground("Arena.jpg", hBox, sceneWidth, sceneHeight, backgroundFontSize),
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

	@Override
	protected Node asNode() {
		return (Node) hBox;
	}

	public void initPlayers() {
		tennisPlayers = SearchHandler.getTennisPlayers();
		basketballPlayers = SearchHandler.getBaketballPlayers();
		footballPlayers = SearchHandler.getFootballPlayers();

		if (tennisPlayers == null) {
			tennisPlayers = FXCollections.observableArrayList();
			tennisPlayers.add(new TennisPlayerModel(123456771, "Shahar Pe'er"));
			tennisPlayers.add(new TennisPlayerModel(123456772, "Serena Williams"));
			tennisPlayers.add(new TennisPlayerModel(123456773, "Maria Sharapova"));
			tennisPlayers.add(new TennisPlayerModel(123456774, "Roger Federer"));
			tennisPlayers.add(new TennisPlayerModel(123456775, "Jimmy Connors"));
			tennisPlayers.add(new TennisPlayerModel(123456776, "Rafael Nadal"));
			tennisPlayers.add(new TennisPlayerModel(123456777, "Boris Becker"));
			tennisPlayers.add(new TennisPlayerModel(123456778, "John Doe")); // For debugging purposes, will be deleted

			SearchHandler.setTennisPlayers(tennisPlayers);
		}

		if (basketballPlayers == null) {
			basketballPlayers = FXCollections.observableArrayList();
			basketballPlayers.add(new BasketballPlayerModel(123456781, "Lebron James"));
			basketballPlayers.add(new BasketballPlayerModel(123456782, "Shaquille O'Neal"));
			basketballPlayers.add(new BasketballPlayerModel(123456783, "Michael Jordan"));
			basketballPlayers.add(new BasketballPlayerModel(123456784, "Kareem Abdul-Jabbar"));
			basketballPlayers.add(new BasketballPlayerModel(123456785, "Stephen curry"));
			basketballPlayers.add(new BasketballPlayerModel(123456786, "Kevin Durant"));
			basketballPlayers.add(new BasketballPlayerModel(123456787, "Magic Johnson"));

			SearchHandler.setBaketballPlayers(basketballPlayers);
		}

		if (footballPlayers == null) {
			footballPlayers = FXCollections.observableArrayList();
			footballPlayers.add(new FootballPlayerModel(123456791, "Ronaldinio"));
			footballPlayers.add(new FootballPlayerModel(123456792, "Lionel Messi"));
			footballPlayers.add(new FootballPlayerModel(123456793, "Christiano Ronaldo"));
			footballPlayers.add(new FootballPlayerModel(123456794, "Neymar"));
			footballPlayers.add(new FootballPlayerModel(123456795, "Diego Maradona"));
			footballPlayers.add(new FootballPlayerModel(123456796, "Zinedine Zidane"));
			footballPlayers.add(new FootballPlayerModel(123456797, "Robert Lawandowski"));

			SearchHandler.setFootballPlayers(footballPlayers);
		}

		tennisPlayersTableView.setItems(tennisPlayers);
		basketballPlayersTableView.setItems(basketballPlayers);
		footballPlayersTableView.setItems(footballPlayers);

		sortAllPlayers();
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

	private void sortAllPlayers() {
		tennisPlayers.sort((p1, p2) -> Integer.compare(p1.getNumericTournamentScore(), p2.getNumericTournamentScore()));
		basketballPlayers
				.sort((p1, p2) -> Integer.compare(p1.getNumericTournamentScore(), p2.getNumericTournamentScore()));
		footballPlayers
				.sort((p1, p2) -> Integer.compare(p1.getNumericTournamentScore(), p2.getNumericTournamentScore()));

		// Reversing the sorted collections (Sort will be: biggest -> smallest)
		Collections.reverse(tennisPlayers);
		Collections.reverse(basketballPlayers);
		Collections.reverse(footballPlayers);
	}
}