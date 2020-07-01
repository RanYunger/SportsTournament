package ID316334473.Views.BracketViews;

import java.util.ArrayList;

import ID316334473.Models.BaketballMatchModel;
import ID316334473.Models.FootballMatchModel;
import ID316334473.Models.MatchModel;
import ID316334473.Models.PlayerModel;
import ID316334473.Models.TennisMatchModel;
import ID316334473.Views.View;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TournamentBracketView extends View {
	// Constants

	// Fields
	private HBox hBox;
	private VBox quarterFinalsVBox, semiFinalsVBox, finalsVBox;
	private MatchBracketView[] quarterFinalsBracketViews, semiFinalsBracketViews;
	private MatchBracketView finalsBracketView;
	private ObservableList<PlayerModel> tournamentPlayers;

	// Properties (Getters and Setters)
	public ObservableList<PlayerModel> getTournamentPlayers() {
		return tournamentPlayers;
	}

	public void setTournamentPlayers(ObservableList<PlayerModel> tournamentPlayers) {
		this.tournamentPlayers = tournamentPlayers;
	}

	public MatchBracketView[] getQuarterFinalsBracketViews() {
		return quarterFinalsBracketViews;
	}

	private void setQuarterFinalsBracketViews(MatchBracketView[] quarterFinals) {
		this.quarterFinalsBracketViews = quarterFinals;
	}

	public MatchBracketView[] getSemiFinalsBracketViews() {
		return semiFinalsBracketViews;
	}

	private void setSemiFinalsBracketViews(MatchBracketView[] semiFinals) {
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
		setQuarterFinalsBracketViews(new MatchBracketView[tournamentPlayers.size() / 2]);
		setSemiFinalsBracketViews(new MatchBracketView[quarterFinalsBracketViews.length / 2]);
		setFinalsBracketView(new MatchBracketView(null));

		initQuarterFinals();
		initSemiFinals();

		buildScene();
	}

	// Methods
	@Override
	protected void buildScene() {
		hBox = new HBox();
		quarterFinalsVBox = new VBox();
		semiFinalsVBox = new VBox();
		finalsVBox = new VBox();

		hBox.setAlignment(Pos.CENTER);
		quarterFinalsVBox.setAlignment(Pos.CENTER);
		semiFinalsVBox.setAlignment(Pos.CENTER);
		finalsVBox.setAlignment(Pos.CENTER);

		for (int i = 0; i < quarterFinalsBracketViews.length; i++) {
			quarterFinalsVBox.getChildren().add(quarterFinalsBracketViews[i].asNode());
			VBox.setMargin(quarterFinalsBracketViews[i].asNode(), new Insets(40));
		}
		for (int i = 0; i < semiFinalsBracketViews.length; i++) {
			semiFinalsVBox.getChildren().add(semiFinalsBracketViews[i].asNode());
			VBox.setMargin(semiFinalsBracketViews[i].asNode(), new Insets(120));
		}
		finalsVBox.getChildren().add(finalsBracketView.asNode());
		VBox.setMargin(finalsBracketView.asNode(), new Insets(40));

		hBox.getChildren().addAll(quarterFinalsVBox, semiFinalsVBox, finalsVBox);
	}

	private void initQuarterFinals() {
		ArrayList<MatchBracketView> matches = new ArrayList<MatchBracketView>();
		PlayerModel currentPlayer0, currentPlayer1;
		MatchModel match = null;

		for (int i = 0; i < tournamentPlayers.size(); i = i + 2) {
			currentPlayer0 = tournamentPlayers.get(i);
			currentPlayer1 = tournamentPlayers.get(i + 1);
			switch (currentPlayer0.getGame()) {
			case Tennis:
				match = new TennisMatchModel(currentPlayer0, currentPlayer1);
				break;
			case Basketball:
				match = new BaketballMatchModel(currentPlayer0, currentPlayer1);
				break;
			case Football:
				match = new FootballMatchModel(currentPlayer0, currentPlayer1);
				break;
			}
			matches.add(new MatchBracketView(match));
		}

		for (int i = 0; i < matches.size(); i++)
			quarterFinalsBracketViews[i] = matches.get(i);
	}

	private void initSemiFinals() {
		for (int i = 0; i < semiFinalsBracketViews.length; i++)
			semiFinalsBracketViews[i] = new MatchBracketView(null);
	}

	@Override
	protected void addEffects() {
		// TODO Auto-generated method stub

	}

	@Override
	public Node asNode() {
		return (Node) hBox;
	}
}
