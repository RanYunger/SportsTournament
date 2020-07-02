package ID316334473.Controllers;

import ID316334473.UIHandler;
import ID316334473.Models.BasketballMatchModel;
import ID316334473.Models.FootballMatchModel;
import ID316334473.Models.MatchModel;
import ID316334473.Models.TennisMatchModel;
import ID316334473.Models.TournamentModel.GameType;
import ID316334473.Views.BasketballMatchView;
import ID316334473.Views.FootballMatchView;
import ID316334473.Views.MatchBracketView;
import ID316334473.Views.TennisMatchView;
import ID316334473.Views.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MatchBracketController extends Controller {
	// Constants

	// Fields

	// Properties (Getters and Setters)
	public MatchBracketView getMatchBracketView() {
		return (MatchBracketView) getView();
	}

	// Constructors
	public MatchBracketController(View view) {
		super(view);

		MatchBracketView matchBracketView = getMatchBracketView();
		EventHandler<ActionEvent> playMatchButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				MatchModel match = matchBracketView.getMatch();
				GameType game = match.getPlayer0().getGame();

				switch (game) {
				case Tennis:
					matchBracketView.getPlayButton().setDisable(true);

					TennisMatchView tennisMatchView = new TennisMatchView(matchBracketView, (TennisMatchModel) match);
					TennisMatchController tennisMatchController = new TennisMatchController(tennisMatchView);

					UIHandler.playAudio("RoundOne.mp3");
					tennisMatchController.addEventHandlersToGeneralButtons();
					break;
				case Basketball:
					matchBracketView.getPlayButton().setDisable(true);

					BasketballMatchView basketballMatchView = new BasketballMatchView(matchBracketView,
							(BasketballMatchModel) match);
					BasketballMatchController basketballMatchController = new BasketballMatchController(
							basketballMatchView);

					UIHandler.playAudio("RoundOne.mp3");
					basketballMatchController.addEventHandlersToGeneralButtons();
					break;
				case Football:
					matchBracketView.getPlayButton().setDisable(true);

					FootballMatchView footballMatchView = new FootballMatchView(matchBracketView,
							(FootballMatchModel) match);
					FootballMatchController footballMatchController = new FootballMatchController(footballMatchView);

					UIHandler.playAudio("RoundOne.mp3");
					footballMatchController.addEventHandlersToGeneralButtons();
					break;
				}
			}
		};

		matchBracketView.getPlayButton().setOnAction(playMatchButtonEventHandler);
	}

	// Methods
}
