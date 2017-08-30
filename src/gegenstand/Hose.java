package gegenstand;

public class Hose extends Gegenstand {
	private double block;
	public Hose(String name, String beschreibung, int gewicht, double block) {
		super(name, beschreibung, gewicht);
		this.block = block;
	}
}