package ort;

import character.Spieler;

public abstract class Landscape {
	protected String name;
	protected String beschreibung;
	protected Raum raum;

	public Landscape(String name, String beschreibung) {
		this.name = name;
		this.beschreibung = beschreibung;
	}

	public abstract void onEnterRoom(Spieler spieler);

	public abstract void onUse(Spieler spieler);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public Raum getRaum() {
		return raum;
	}

	public void setRaum(Raum raum) {
		this.raum = raum;
	}

}
