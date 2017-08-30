package character;

import java.util.LinkedList;

import gegenstand.Gegenstand;
import ort.Raum;

public class Kaufmann extends NPC {
	public Kaufmann(int maxTraglast, Raum raum, LinkedList<Gegenstand> gegenstaende) {
		super(maxTraglast, raum, gegenstaende);
	}
}