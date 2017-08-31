package gegenstand;

public abstract class Gegenstand {
	private String name, beschreibung;
	private int gewicht, preis;
	private boolean essbar = false;
	
	public Gegenstand(String name, String beschreibung, int gewicht, int preis, boolean essbar) {
		this.name = name;
		this.beschreibung = beschreibung;
		this.gewicht = gewicht;
		this.essbar = essbar;
		this.preis = preis;
	}

	public String toString() {
		return "Name: " + name + System.getProperty("line.separator") + "Beschreibung: " + beschreibung
				+ System.getProperty("line.separator") + "Gewicht: " + gewicht;
	}

	public String getName() {
		return name;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public int getGewicht() {
		return gewicht;
	}

	public boolean isEssbar() {
		return essbar;
	}

	public void setEssbar(boolean essbar) {
		this.essbar = essbar;
	}

	public int getPreis() {
		return preis;
	}

	public void setPreis(int preis) {
		this.preis = preis;
	}
}