package ID316334473.Models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ParticipantModel {
	// Constants

	// Fields
	private SimpleIntegerProperty ID;
	private SimpleStringProperty name;
	private SimpleDoubleProperty score;

	// Properties (Getters and Setters)
	public SimpleIntegerProperty getObservableID() {
		return ID;
	}

	public int getNumericID() {
		return ID.get();
	}

	private void setID(int ID) {
		this.ID = new SimpleIntegerProperty(ID);
	}

	public SimpleStringProperty getObservableName() {
		return name;
	}

	public String getTextualName() {
		return name.get();
	}

	private void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}

	public SimpleDoubleProperty getObservableScore() {
		return score;
	}

	public double getNumericScore() {
		return score.get();
	}

	private void setScore(double score) {
		this.score = new SimpleDoubleProperty(score);
	}

	// Constructors
	public ParticipantModel(int ID, String name) {
		setID(ID);
		setName(name);
		setScore(0);
	}

	// Methods
}
