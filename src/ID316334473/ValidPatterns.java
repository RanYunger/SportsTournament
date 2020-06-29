package ID316334473;

public enum ValidPatterns {

	// The anonymous Country's citizen IDs consist of 9 digits
	PLAYER_ID("^[0-9]{9}$"),
	// Citizen's name must have at least a first and a last name, and all words must be capitalized
	PLAYER_FULL_NAME("^[A-Z][a-z]+(?: [A-Z][a-z]+){1,}$");

	private String pattern;

	ValidPatterns(String pattern) {
		this.pattern = pattern;
	}

	public String getPattern() {
		return pattern;
	}
}
