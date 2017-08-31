package gegenstand;

public class Helm extends Gegenstand {
	private double block;
	public Helm(String name, String beschreibung, int gewicht, int preis, double block) {
		super(name, beschreibung, gewicht, preis, false);
		this.block = block;
	}
}