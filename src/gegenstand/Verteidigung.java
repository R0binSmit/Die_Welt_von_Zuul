package gegenstand;

public abstract class Verteidigung extends Gegenstand {
	protected double block;
	public Verteidigung(String name, String beschreibung, int gewicht, int preis, double block) {
		super(name, beschreibung, gewicht, preis, false);
		this.block = block;
	}
}