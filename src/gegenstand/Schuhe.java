package gegenstand;

public class Schuhe extends Gegenstand {
	private double block;
	public Schuhe(String name, String beschreibung, int gewicht, double block) {
		super(name, beschreibung, gewicht, false);
		this.block = block;
	}
}