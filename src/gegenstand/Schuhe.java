package gegenstand;

public class Schuhe extends Gegenstand {
	private double block;
	public Schuhe(String name, String beschreibung, int gewicht, int preis, double block) {
		super(name, beschreibung, gewicht, preis, false);
		this.block = block;
	}
}