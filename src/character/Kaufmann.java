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
			System.out.println("Dies sind meine Gegenstände: ");
			for (Gegenstand gs : gegenstaende) {
				System.out.println("Name: " + gs.getName());
				System.out.println("Preis: " + gs.getPreis());
				System.out.println();
			}
			
			befehl = pr.liefereBefehl();
			if (befehl.gibBefehlswort().equalsIgnoreCase("take")) {
				Gegenstand verkauf = getGegenstand(befehl.gibZweitesWort());
				if (verkauf != null && spieler.getGeld() >= verkauf.getPreis()) {
					spieler.gegenstandAufnehmen(gegenstandAblegen(verkauf.getName()));
					geld += verkauf.getPreis();
					spieler.setGeld(spieler.getGeld() - verkauf.getPreis());
					System.out.println("Handel durchgeführt");
				} else {
					System.out.println("Der Gegenstand existiert nicht oder ist zu teuer für sie");
				}
			} else if (befehl.gibBefehlswort().equalsIgnoreCase("drop")) {
				Gegenstand kauf = spieler.getGegenstand(befehl.gibZweitesWort());
				if (kauf != null && geld >= kauf.getPreis()) {
					gegenstandAufnehmen(spieler.gegenstandAblegen(kauf.getName()));
					geld -= kauf.getPreis();
					spieler.setGeld(spieler.getGeld() + kauf.getPreis());
					System.out.println("Handel durchgeführt");
				} else {
					System.out.println("Der Gegenstand existiert nicht oder ist zu teuer für den Händler");
				}
			} else if (befehl.gibBefehlswort().equalsIgnoreCase("quit")) {
				System.out.println("Handel beendet");
				break;
			} else {
				System.out.println("Diesen Befehl gibt es hier nicht!");
			}
		} while (true);
	}
}