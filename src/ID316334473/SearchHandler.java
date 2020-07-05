package ID316334473;

import java.util.Collections;
import java.util.List;

import ID316334473.Models.BasketballPlayerModel;
import ID316334473.Models.FootballPlayerModel;
import ID316334473.Models.PlayerModel;
import ID316334473.Models.TennisPlayerModel;
import ID316334473.Models.TournamentModel.GameType;
import javafx.collections.ObservableList;

// This class contains methods which aren't necessarily related to UI
public class SearchHandler {
	// Constants

	// Fields
	private static ObservableList<PlayerModel> tennisPlayers, basketballPlayers, footballPlayers;
	private static ObservableList<PlayerModel> tennisFinalTrio, basketballFinalTrio, footballFinalTrio;

	// Properties (Getters and Setters)
	public static ObservableList<PlayerModel> getTennisPlayers() {
		return tennisPlayers;
	}

	public static void setTennisPlayers(ObservableList<PlayerModel> tennisPlayers) {
		tennisPlayers.sort((p1, p2) -> Integer.compare(p1.getNumericTournamentScore(), p2.getNumericTournamentScore()));

		// Reversing the sorted collection (Sort will be: biggest -> smallest)
		Collections.reverse(tennisPlayers);

		SearchHandler.tennisPlayers = tennisPlayers;
	}

	public static ObservableList<PlayerModel> getBaketballPlayers() {
		return basketballPlayers;
	}

	public static void setBaketballPlayers(ObservableList<PlayerModel> baketballPlayers) {
		baketballPlayers
				.sort((p1, p2) -> Integer.compare(p1.getNumericTournamentScore(), p2.getNumericTournamentScore()));

		// Reversing the sorted collection (Sort will be: biggest -> smallest)
		Collections.reverse(baketballPlayers);

		SearchHandler.basketballPlayers = baketballPlayers;
	}

	public static ObservableList<PlayerModel> getFootballPlayers() {
		return footballPlayers;
	}

	public static void setFootballPlayers(ObservableList<PlayerModel> footballPlayers) {
		footballPlayers
				.sort((p1, p2) -> Integer.compare(p1.getNumericTournamentScore(), p2.getNumericTournamentScore()));

		// Reversing the sorted collection (Sort will be: biggest -> smallest)
		Collections.reverse(footballPlayers);

		SearchHandler.footballPlayers = footballPlayers;
	}

	public static ObservableList<PlayerModel> getTennisFinalTrio() {
		return tennisFinalTrio;
	}

	public static void setTennisFinalTrio(ObservableList<PlayerModel> tennisFinalTrio) {
		SearchHandler.tennisFinalTrio = tennisFinalTrio;
	}

	public static ObservableList<PlayerModel> getBasketballFinalTrio() {
		return basketballFinalTrio;
	}

	public static void setBasketballFinalTrio(ObservableList<PlayerModel> basketballFinalTrio) {
		SearchHandler.basketballFinalTrio = basketballFinalTrio;
	}

	public static ObservableList<PlayerModel> getFootballFinalTrio() {
		return footballFinalTrio;
	}

	public static void setFootballFinalTrio(ObservableList<PlayerModel> footballFinalTrio) {
		SearchHandler.footballFinalTrio = footballFinalTrio;
	}

	// Methods
	private static <T, U> T binarySearch(List<T> collection, U key) {
		return binarySearch(collection, key, 0, collection.size() - 1);
	}

	private static <T, U> T binarySearch(List<T> array, U key, int start, int end) {
		if (start <= end) {
			int mid = (start + end) / 2;

			T element = array.get(mid);
			if (array.get(0) instanceof TennisPlayerModel) {
				int ballotID = ((TennisPlayerModel) array.get(mid)).getNumericID();
				if (ballotID == (int) key)
					return element;

				if (ballotID > (int) key)
					return binarySearch(array, key, start, mid - 1);

				return binarySearch(array, key, mid + 1, end);
			}

			if (array.get(0) instanceof BasketballPlayerModel) {
				int citizenID = ((BasketballPlayerModel) array.get(mid)).getNumericID();
				if (citizenID == (int) key)
					return element;

				if (citizenID > (int) key)
					return binarySearch(array, key, start, mid - 1);

				return binarySearch(array, key, mid + 1, end);
			}

			if (array.get(0) instanceof FootballPlayerModel) {
				String partyName = ((FootballPlayerModel) array.get(mid)).getTextualName();
				if (partyName.equals((String) key))
					return element;

				if (partyName.compareTo((String) key) > 0)
					return binarySearch(array, key, start, mid - 1);

				return binarySearch(array, key, mid + 1, end);
			}
		}

		return null;
	}

	public static PlayerModel getPlayerByID(int playerID, GameType game) {
		List<? extends PlayerModel> playersList = null;

		switch (game) {
		case Tennis:
			playersList = tennisPlayers;
			break;
		case Basketball:
			playersList = basketballPlayers;
			break;
		case Football:
			playersList = footballPlayers;
			break;
		}

		Collections.sort(playersList);
		return binarySearch(playersList, playerID);
	}
}
