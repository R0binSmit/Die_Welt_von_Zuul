package gegenstand;

public class Helm extends Gegenstand {
	private double block;
	public Helm(String name, String beschreibung, int gewicht, double block) {
		super(name, beschreibung, gewicht);
		this.block = block;
	}
}