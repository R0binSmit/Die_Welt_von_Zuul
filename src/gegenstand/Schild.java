package gegenstand;

public class Schild extends Hand {
	private double block;
	public Schild(String name, String beschreibung, int gewicht, double block) {
		super(name, beschreibung, gewicht);
		this.block = block;
	}
}