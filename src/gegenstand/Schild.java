package gegenstand;

public class Schild extends Hand {
	private double block;
	public Schild(String name, String beschreibung, int gewicht, int preis, double block) {
		super(name, beschreibung, gewicht, preis);
		this.block = block;
	}
}