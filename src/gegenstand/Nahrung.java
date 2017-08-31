package gegenstand;

public class Nahrung extends Gegenstand {
	public Nahrung(String name, String beschreibung, int gewicht) {
		super(name, beschreibung, gewicht, true);
	}
	
	public Nahrung(String name, String beschreibung, int gewicht, boolean essbar) {
		super(name, beschreibung, gewicht, essbar);
	}
}