package gegenstand;

public class Brustplatte extends Gegenstand {
	private double block;
	public Brustplatte(String name, String beschreibung, int gewicht, int preis, double block) {
		super(name, beschreibung, gewicht, preis, false);
		this.block = block;
	}
}