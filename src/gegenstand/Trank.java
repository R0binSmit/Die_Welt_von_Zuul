package gegenstand;

public class Trank extends Gegenstand {
	public Trank(String name, String beschreibung, int gewicht, int preis) {
		super(name, beschreibung, gewicht, preis, true);
	}
}