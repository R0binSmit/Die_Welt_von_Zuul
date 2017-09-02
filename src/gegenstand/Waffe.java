package gegenstand;

public class Waffe extends Gegenstand implements Hand {
	private int schaden;
	public Waffe(String name, String beschreibung, int gewicht, int preis, int schaden) {
		super(name, beschreibung, gewicht, preis, false);
		this.schaden = schaden;
	}
}