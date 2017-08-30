package character;

import java.util.LinkedList;

import gegenstand.Gegenstand;
import ort.Raum;

public class Kaufmann extends NPC {
	public Kaufmann(String name, int maxTraglast, Raum raum, LinkedList<Gegenstand> gegenstaende) {
		super(name, maxTraglast, raum, gegenstaende);
	}
}