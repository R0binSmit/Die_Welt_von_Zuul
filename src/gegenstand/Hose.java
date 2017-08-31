package gegenstand;

public class Hose extends Gegenstand {
	private double block;
	public Hose(String name, String beschreibung, int gewicht, int preis, double block) {
		super(name, beschreibung, gewicht, preis, false);
		this.block = block;
	}
}