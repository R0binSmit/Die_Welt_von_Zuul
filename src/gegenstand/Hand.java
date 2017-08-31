package gegenstand;

public abstract class Hand extends Gegenstand {
	public Hand(String name, String beschreibung, int gewicht, int preis) {
		super(name, beschreibung, gewicht, preis, false);
	}
}