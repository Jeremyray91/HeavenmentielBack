package heavenmentiel.enums;

public enum TypeEvent {
	CONCERT("Concert"), CINEMA("Cinema"), SPECTACLE("Spectacle"), SPORT("Sport"), JEUX("Jeux");
	
	private String s;
	
	private TypeEvent(String s) {
		this.s = s;
	}
	
	public String toString() {
		return this.s;
	}
}
