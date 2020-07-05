package ID316334473.Views;

import java.util.ArrayList;

import ID316334473.UIHandler;
import ID316334473.Models.BasketballMatchModel;
import ID316334473.Models.FootballMatchModel;
import ID316334473.Models.MatchModel;
import ID316334473.Models.PlayerModel;
import ID316334473.Models.TennisMatchModel;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TournamentBracketView extends View {
	// Constants

	// Fields
	private HBox hBox;
	private VBox quarterFinalsVBox, quarterFinalsArrowsVBox, semiFinalsVBox, finalsVBox, finalsArrowVBox;
	private ImageView quarterFinalsTopArrowImageView, quarterFinalsBottomArrowImageView, finalsArrowImageView;
	private ArrayList<MatchBracketView> quarterFinalsBracketViews, semiFinalsBracketViews;
	private MatchBracketView finalsBracketView;
	private ObservableList<PlayerModel> tournamentPlayers;

	// Properties (Getters and Setters)
	public ObservableList<PlayerModel> getTournamentPlayers() {
		return tournamentPlayers;
	}

	public void setTournamentPlayers(ObservableList<PlayerModel> tournamentPlayers) {
		this.tournamentPlayers = tournamentPlayers;
	}

	public ArrayList<MatchBracketView> getQuarterFinalsBracketViews() {
		return quarterFinalsBracketViews;
	}

	private void setQuarterFinalsBracketViews(ArrayList<MatchBracketView> quarterFinals) {
		this.quarterFinalsBracketViews = quarterFinals;
	}

	public ArrayList<MatchBracketView> getSemiFinalsBracketViews() {
		return semiFinalsBracketViews;
	}

	private void setSemiFinalsBracketViews(ArrayList<MatchBracketView> semiFinals) {
		this.semiFinalsBracketViews = semiFinals;
	}

	public MatchBracketView getFinalsBracketView() {
		return finalsBracketView;
	}

	private void setFinalsBracketView(MatchBracketView finals) {
		this.finalsBracketView = finals;
	}

	// Constructors
	public TournamentBracketView(ObservableList<PlayerModel> tournamentPlayers) {
		setTournamentPlayers(tournamentPlayers);
		setQuarterFinalsBracketViews(new ArrayList<MatchBracketView>(tournamentPlayers.size() / 2));
		setSemiFinalsBracketViews(new ArrayList<MatchBracketView>(tournamentPlayers.size() / 4));
		setFinalsBracketView(new MatchBracketView(null, null, this));

		initSemiFinals();
		initQuarterFinals();

		buildScene();
		addEffects();
	}

	// Methods
	@Override
	protected void buildScene() {
		hBox = new HBox();
		quarterFinalsVBox = new VBox();
		quarterFinalsArrowsVBox = new VBox();
		semiFinalsVBox = new VBox();
		finalsVBox = new VBox();
		finalsArrowVBox = new VBox();
		quarterFinalsTopArrowImageView = UIHandler.buildImage("Arrow.png", 180, 215);
		quarterFinalsBottomArrowImageView = UIHandler.buildImage("Arrow.png", 180, 215);
		finalsArrowImageView = UIHandler.buildImage("Arrow.png", 180, 380);

		hBox.setAlignment(Pos.CENTER);
		quarterFinalsVBox.setAlignment(Pos.CENTER);
		quarterFinalsArrowsVBox.setAlignment(Pos.CENTER);
		semiFinalsVBox.setAlignment(Pos.CENTER);
		finalsVBox.setAlignment(Pos.CENTER);
		finalsArrowVBox.setAlignment(Pos.CENTER);

		for (int i = 0; i < quarterFinalsBracketViews.size(); i++) {
			quarterFinalsVBox.getChildren().add(quarterFinalsBracketViews.get(i).asNode());
			VBox.setMargin(quarterFinalsBracketViews.get(i).asNode(), new Insets(30, 0, 30, 0));
		}
		for (int i = 0; i < semiFinalsBracketViews.size(); i++) {
			semiFinalsVBox.getChildren().add(semiFinalsBracketViews.get(i).asNode());
			VBox.setMargin(semiFinalsBracketViews.get(i).asNode(), new Insets(110, 0, 110, 0));
		}
		finalsVBox.getChildren().add(finalsBracketView.asNode());
		VBox.setMargin(finalsBracketView.asNode(), new Insets(40, 0, 40, 0));

		quarterFinalsArrowsVBox.getChildren().addAll(quarterFinalsTopArrowImageView, quarterFinalsBottomArrowImageView);
		VBox.setMargin(quarterFinalsTopArrowImageView, new Insets(0, 40, 35, 40));
		VBox.setMargin(quarterFinalsBottomArrowImageView, new Insets(35, 40, 0, 40));

		finalsArrowVBox.getChildren().add(finalsArrowImageView);
		VBox.setMargin(finalsArrowImageView, new Insets(0, 40, 0, 40));

		hBox.getChildren().addAll(quarterFinalsVBox, quarterFinalsArrowsVBox, semiFinalsVBox, finalsArrowVBox,
				finalsVBox);
	}

	private void initQuarterFinals() {
		ArrayList<MatchBracketView> matches = new ArrayList<MatchBracketView>();
		PlayerModel currentPlayer0, currentPlayer1;
		MatchModel match = null;

		for (int i = 0; i < tournamentPlayers.size(); i += 2) {
			currentPlayer0 = tournamentPlayers.get(i);
			currentPlayer1 = tournamentPlayers.get(i + 1);
			switch (currentPlayer0.getGame()) {
			case Tennis:
				match = new TennisMatchModel(currentPlayer0, currentPlayer1);
				break;
			case Basketball:
				match = new BasketballMatchModel(currentPlayer0, currentPlayer1);
				break;
			case Football:
				match = new FootballMatchModel(currentPlayer0, currentPlayer1);
				break;
			}

			matches.add(new MatchBracketView(match,
					i < 4 ? semiFinalsBracketViews.get(0) : semiFinalsBracketViews.get(1), this));
		}

		for (int i = 0; i < matches.size(); i += 2) {
			matches.get(i).setTwinMatchBracketView(matches.get(i + 1));
			matches.get(i + 1).setTwinMatchBracketView(matches.get(i));
		}

		quarterFinalsBracketViews.addAll(matches);
	}

	private void initSemiFinals() {
		for (int i = 0; i < 2; i++)
			semiFinalsBracketViews.add(new MatchBracketView(null, finalsBracketView, this));

		semiFinalsBracketViews.get(0).setTwinMatchBracketView(semiFinalsBracketViews.get(1));
		semiFinalsBracketViews.get(1).setTwinMatchBracketView(semiFinalsBracketViews.get(0));
	}

	@Override
	protected void addEffects() {
		// TODO: COMPLETE
	}

	@Override
	public Node asNode() {
		return (Node) hBox;
	}
}
