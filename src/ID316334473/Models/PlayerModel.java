package ID316334473.Models;

import ID316334473.UIHandler;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PlayerModel {
	// Constants

	// Fields
	private SimpleIntegerProperty ID;
	private SimpleStringProperty name;

	// Properties (Getters and Setters)
	public SimpleIntegerProperty getObservableID() {
		return ID;
	}

	public int getNumericID() {
		return ID.get();
	}

	private void setID(int ID) {
		if (String.valueOf(ID).length() != 9)
			UIHandler.showError("Participant's ID must contain exactly 9 digits.");
		this.ID = new SimpleIntegerProperty(ID);
	}

	public SimpleStringProperty getObservableName() {
		return name;
	}

	public String getTextualName() {
		return name.get();
	}

	private void setName(String name) {
		if (name.isBlank())
			UIHandler.showError("CitizenModel's name must contain at least 1 letter.");
		this.name = new SimpleStringProperty(name);
	}

	// Constructors
	public PlayerModel(int ID, String name) {
		setID(ID);
		setName(name);
	}

	// Methods
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlayerModel other = (PlayerModel) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}