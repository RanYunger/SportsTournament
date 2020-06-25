package ID316334473.Controllers;

import ID316334473.Views.AddPlayerView;
import ID316334473.Views.View;

public class AddPlayerController extends Controller {
	// Constants

	// Fields

	// Properties (Getters and Setters)
	public AddPlayerView getAddPlayerView() {
		return (AddPlayerView) getView();
	}

	// Constructors
	public AddPlayerController(View view) {
		super(view);
		
//		AddPlayerView addPlayerView = getAddPlayerView();
//		EventHandler<ActionEvent> addPlayerButtonEventHandler = new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				// TODO: Complete
//			}
//		};		
	}

	// Methods
}
