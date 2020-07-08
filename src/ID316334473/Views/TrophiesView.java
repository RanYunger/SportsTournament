package ID316334473.Views;

import ID316334473.SearchHandler;
import ID316334473.UIHandler;
import ID316334473.Models.PlayerModel;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class TrophiesView extends WindowView {
	// Constants

	// Fields
	private HBox optionsHBox, tennisFinalTrioNamesHBox, basketballFinalTrioNamesHBox, footballFinalTrioNamesHBox;
	private VBox tennisFinalTrioVBox, basketballFinalTrioVBox, footballFinalTrioVBox;
	private ImageView[] gameImageViews, trophiesImageViews;
	private Label[] tennisFinalTrioNameLabels, basketballFinalTrioNameLabels, footballFinalTrioNameLabels;

	// Properties (Getters and Setters)
	public void setTennisFinalTrioNameLabels(Label[] tennisFinalTrioNameLabels) {
		this.tennisFinalTrioNameLabels = tennisFinalTrioNameLabels;
	}

	public void setBasketballFinalTrioNameLabels(Label[] basketballFinalTrioNameLabels) {
		this.basketballFinalTrioNameLabels = basketballFinalTrioNameLabels;
	}

	public void setFootballFinalTrioNameLabels(Label[] footballFinalTrioNameLabels) {
		this.footballFinalTrioNameLabels = footballFinalTrioNameLabels;
	}

	public Label getTennis1stPlayer() {
		return tennisFinalTrioNameLabels[1];
	}

	public Label getTennis2ndPlayer() {
		return tennisFinalTrioNameLabels[0];
	}

	public Label getTennis3rdPlayer() {
		return tennisFinalTrioNameLabels[2];
	}

	public Label getBasketball1stPlayer() {
		return basketballFinalTrioNameLabels[1];
	}

	public Label getBasketball2ndPlayer() {
		return basketballFinalTrioNameLabels[0];
	}

	public Label getBasketball3rdPlayer() {
		return basketballFinalTrioNameLabels[2];
	}

	public Label getFootball1stPlayer() {
		return footballFinalTrioNameLabels[1];
	}

	public Label getFootball2ndPlayer() {
		return footballFinalTrioNameLabels[0];
	}

	public Label getFootball3rdPlayer() {
		return footballFinalTrioNameLabels[2];
	}

	// Constructors
	public TrophiesView() {
		super();

		setTennisFinalTrioNameLabels(new Label[3]);
		setBasketballFinalTrioNameLabels(new Label[3]);
		setFootballFinalTrioNameLabels(new Label[3]);

		buildScene();
		addEffects();
	}

	// Methods
	@Override
	protected void buildScene() {
		double sceneWidth = 1500, sceneHeight = 700, backgroundFontSize = 50, winnerFont = 30, viewFont = 20;
		String[] games = { "Tennis", "Basketball", "Football" };
		ObservableList<PlayerModel> tennisFinalTrio = SearchHandler.getTennisFinalTrio(),
				basketballFinalTrio = SearchHandler.getBasketballFinalTrio(),
				footballFinalTrio = SearchHandler.getFootballFinalTrio();

		optionsHBox = new HBox();
		tennisFinalTrioNamesHBox = new HBox();
		basketballFinalTrioNamesHBox = new HBox();
		footballFinalTrioNamesHBox = new HBox();
		tennisFinalTrioVBox = new VBox();
		basketballFinalTrioVBox = new VBox();
		footballFinalTrioVBox = new VBox();
		gameImageViews = new ImageView[3];
		trophiesImageViews = new ImageView[3];

		optionsHBox.setAlignment(Pos.CENTER);
		tennisFinalTrioNamesHBox.setAlignment(Pos.CENTER);
		basketballFinalTrioNamesHBox.setAlignment(Pos.CENTER);
		footballFinalTrioNamesHBox.setAlignment(Pos.CENTER);
		tennisFinalTrioVBox.setAlignment(Pos.CENTER);
		basketballFinalTrioVBox.setAlignment(Pos.CENTER);
		footballFinalTrioVBox.setAlignment(Pos.CENTER);

		for (int i = 0; i < basketballFinalTrioNameLabels.length; i++) {
			gameImageViews[i] = UIHandler.buildImage(games[i] + ".png", 100, 100);
			trophiesImageViews[i] = UIHandler.buildImage("Trophies.png", 400, 300);

			tennisFinalTrioNameLabels[i] = new Label(tennisFinalTrio.get(i).getTextualName().replace(" ", "\n"));
			basketballFinalTrioNameLabels[i] = new Label(
					basketballFinalTrio.get(i).getTextualName().replace(" ", "\n"));
			footballFinalTrioNameLabels[i] = new Label(footballFinalTrio.get(i).getTextualName().replace(" ", "\n"));

			tennisFinalTrioNameLabels[i].setFont(i == 1 ? new Font(winnerFont) : new Font(viewFont));
			tennisFinalTrioNameLabels[i].setTextFill(i == 1 ? Color.GOLD : Color.WHITE);
			tennisFinalTrioNameLabels[i].setTextAlignment(TextAlignment.CENTER);
			basketballFinalTrioNameLabels[i].setFont(i == 1 ? new Font(winnerFont) : new Font(viewFont));
			basketballFinalTrioNameLabels[i].setTextFill(i == 1 ? Color.GOLD : Color.WHITE);
			basketballFinalTrioNameLabels[i].setTextAlignment(TextAlignment.CENTER);
			footballFinalTrioNameLabels[i].setFont(i == 1 ? new Font(winnerFont) : new Font(viewFont));
			footballFinalTrioNameLabels[i].setTextFill(i == 1 ? Color.GOLD : Color.WHITE);
			footballFinalTrioNameLabels[i].setTextAlignment(TextAlignment.CENTER);
		}

		tennisFinalTrioNamesHBox.getChildren().addAll(tennisFinalTrioNameLabels);
		HBox.setMargin(tennisFinalTrioNameLabels[0], new Insets(0, 10, 0, 0));
		HBox.setMargin(tennisFinalTrioNameLabels[1], new Insets(0, 50, 0, 50));
		HBox.setMargin(tennisFinalTrioNameLabels[2], new Insets(0, 0, 0, 10));
		basketballFinalTrioNamesHBox.getChildren().addAll(basketballFinalTrioNameLabels);
		HBox.setMargin(basketballFinalTrioNameLabels[0], new Insets(0, 10, 0, 0));
		HBox.setMargin(basketballFinalTrioNameLabels[1], new Insets(0, 50, 0, 50));
		HBox.setMargin(basketballFinalTrioNameLabels[2], new Insets(0, 0, 0, 10));
		footballFinalTrioNamesHBox.getChildren().addAll(footballFinalTrioNameLabels);
		HBox.setMargin(footballFinalTrioNameLabels[0], new Insets(0, 10, 0, 0));
		HBox.setMargin(footballFinalTrioNameLabels[1], new Insets(0, 50, 0, 50));
		HBox.setMargin(footballFinalTrioNameLabels[2], new Insets(0, 0, 0, 10));

		tennisFinalTrioVBox.getChildren().addAll(gameImageViews[0], trophiesImageViews[0], tennisFinalTrioNamesHBox);
		VBox.setMargin(gameImageViews[0], new Insets(0, 0, 10, 0));
		basketballFinalTrioVBox.getChildren().addAll(gameImageViews[1], trophiesImageViews[1],
				basketballFinalTrioNamesHBox);
		VBox.setMargin(gameImageViews[1], new Insets(0, 0, 10, 0));
		footballFinalTrioVBox.getChildren().addAll(gameImageViews[2], trophiesImageViews[2],
				footballFinalTrioNamesHBox);
		VBox.setMargin(gameImageViews[2], new Insets(0, 0, 10, 0));

		optionsHBox.getChildren().addAll(tennisFinalTrioVBox, basketballFinalTrioVBox, footballFinalTrioVBox);
		HBox.setMargin(tennisFinalTrioVBox, new Insets(0, 10, 0, 0));
		HBox.setMargin(basketballFinalTrioVBox, new Insets(0, 70, 0, 70));
		HBox.setMargin(footballFinalTrioVBox, new Insets(0, 0, 0, 10));

		stage.setScene(new Scene(
				UIHandler.buildBackground("Arena.jpg", optionsHBox, sceneWidth, sceneHeight, backgroundFontSize),
				sceneWidth, sceneHeight));
		UIHandler.setGeneralFeatures(stage);

		stage.show();
	}

	@Override
	protected void addEffects() {
		super.addEffects();
	}

	@Override
	protected Node asNode() {
		// TODO: COMPLETE
		return null;
	}
}
