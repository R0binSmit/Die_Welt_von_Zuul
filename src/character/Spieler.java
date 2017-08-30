package character;

import java.util.LinkedList;

import gegenstand.Gegenstand;
import ort.Raum;

public class Spieler extends Character {
	boolean hauptSpieler = false;

	public Spieler(int maxTraglast, Raum raum, LinkedList<Gegenstand> gegenstaende) {
		super(maxTraglast, raum, gegenstaende);
	}

	public boolean isHauptSpieler() {
		return hauptSpieler;
	}

	public void setHauptSpieler(boolean hauptSpieler) {
		this.hauptSpieler = hauptSpieler;
	}

	@Override
	public void interagieren() {
		
	}
}