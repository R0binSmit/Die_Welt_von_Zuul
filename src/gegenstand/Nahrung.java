package gegenstand;

public class Nahrung extends Gegenstand {
	public Nahrung(String name, String beschreibung, int gewicht, int preis) {
		super(name, beschreibung, gewicht, preis, true);
	}
	
	public Nahrung(String name, String beschreibung, int gewicht, int preis, boolean essbar) {
		super(name, beschreibung, gewicht, preis, essbar);
	}
}