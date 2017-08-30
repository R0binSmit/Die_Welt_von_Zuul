package gegenstand;

public class Brustplatte extends Gegenstand {
	private double block;
	public Brustplatte(String name, String beschreibung, int gewicht, double block) {
		super(name, beschreibung, gewicht);
		this.block = block;
	}
}