package character;

import java.util.LinkedList;

import befehlsVerarbeitung.Befehl;
import befehlsVerarbeitung.Parser;
import gegenstand.Gegenstand;
import ort.Raum;

public class Kaufmann extends NPC {
	public Kaufmann(String name, int maxTraglast, Raum raum, LinkedList<Gegenstand> gegenstaende) {
		super(name, maxTraglast, raum, gegenstaende);
	}

	public void interagieren(Spieler spieler) {
		System.out.println(text);
		Parser pr = new Parser();
		Befehl befehl;
		do {
			System.out.println(getInventory());
			befehl = pr.liefereBefehl();
			if (befehl.gibBefehlswort().equalsIgnoreCase("take")) {
				if (trade(this, spieler, befehl.gibZweitesWort())) {
					System.out.println("Handel durchgeführt");
				} else {
					System.out.println("Der Gegenstand existiert nicht oder ist zu teuer für sie");
				}
			} else if (befehl.gibBefehlswort().equalsIgnoreCase("drop")) {
				if (trade(spieler, this, befehl.gibZweitesWort())) {
					System.out.println("Handel durchgeführt");
				} else {
					System.out.println("Der Gegenstand existiert nicht oder ist zu teuer für sie");
				}
			} else if (befehl.gibBefehlswort().equalsIgnoreCase("quit")) {
				System.out.println("Handel beendet");
				break;
			} else {
				System.out.println("Diesen Befehl gibt es hier nicht!");
			}
		} while (true);
	}
	
	public boolean trade(Character verkauf, Character kauf, String gegenstand) {
		Gegenstand ware = verkauf.getGegenstand(gegenstand);
		if (ware != null && kauf.getGeld() >= ware.getPreis()) {
			kauf.gegenstandAufnehmen(verkauf.gegenstandAblegen(ware.getName()));
			verkauf.setGeld(verkauf.getGeld() + ware.getPreis());
			kauf.setGeld(kauf.getGeld() - ware.getPreis());
			return true;
		}
		return false;
	}
}