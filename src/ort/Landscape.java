package ort;

import java.util.HashMap;

import character.Spieler;

public abstract class Landscape {
	protected String name;
	protected String beschreibung;
	protected Raum raum;
	protected HashMap<LandscapeResponse, String> landscapeResponse;

	public Landscape(String name, String beschreibung, HashMap<LandscapeResponse, String> landscapeResponse) {
		this.name = name;
		this.beschreibung = beschreibung;
		this.landscapeResponse = landscapeResponse == null ? new HashMap<LandscapeResponse, String>()
				: landscapeResponse;
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
	
	public String getResponse(LandscapeResponse lr) {
		return landscapeResponse.get(lr);
	}
}
