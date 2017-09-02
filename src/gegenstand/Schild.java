package gegenstand;

public class Schild extends Verteidigung implements Hand{
	public Schild(String name, String beschreibung, int gewicht, int preis, double block) {
		super(name, beschreibung, gewicht, preis, block);
	}
}