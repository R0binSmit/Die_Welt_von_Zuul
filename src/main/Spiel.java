package main;

import befehlsVerarbeitung.Befehl;
import befehlsVerarbeitung.Parser;
import character.Spieler;
import gegenstand.Gegenstand;
import ort.Landkarte;
import ort.Landscape;
import ort.Raum;

/**
 * Dies ist die Hauptklasse der Anwendung "Die Welt von Zuul". "Die Welt von
 * Zuul" ist ein sehr einfaches, textbasiertes Adventure-Game. Ein Spieler kann
 * sich in einer Umgebung bewegen, mehr nicht. Das Spiel sollte auf jeden Fall
 * ausgebaut werden, damit es interessanter wird!
 * 
 * Zum Spielen muss eine Instanz dieser Klasse erzeugt werden und an ihr die
 * Methode "spielen" aufgerufen werden.
 * 
 * Diese Instanz erzeugt und initialisiert alle anderen Objekte der Anwendung:
 * Sie legt alle Räume und einen Parser an und startet das Spiel. Sie wertet
 * auch die Befehle aus, die der Parser liefert, und sorgt für ihre Ausführung.
 * 
 * @author Michael Kölling und David J. Barnes
 * @version 2008.03.30
 */

public class Spiel {
	private Parser parser;
	private Spieler spieler;
	private Landkarte land;

	/**
	 * Erzeuge ein Spiel und initialisiere die interne Raumkarte.
	 */
	public Spiel() {
		land = new Landkarte();
		land.raeumeAnlegen();
		parser = new Parser();
		spieler = new Spieler(100, land.getStartpoint(), null); // das Spiel startet draussen
	}

	/**
	 * Die Hauptmethode zum Spielen. Läuft bis zum Ende des Spiels in einer
	 * Schleife.
	 */
	public void spielen() {
		willkommenstextAusgeben();

		// Die Hauptschleife. Hier lesen wir wiederholt Befehle ein
		// und führen sie aus, bis das Spiel beendet wird.

		boolean beendet = false;
		while (!beendet) {
			Befehl befehl = parser.liefereBefehl();
			beendet = verarbeiteBefehl(befehl);
		}
		System.out.println("Danke für dieses Spiel. Auf Wiedersehen.");
	}

	/**
	 * Einen Begrüßungstext für den Spieler ausgeben.
	 */
	private void willkommenstextAusgeben() {
		System.out.println("Willkommen zu Zuul!");
		System.out.println("Tippen sie 'help', wenn Sie Hilfe brauchen.");
		System.out.println();
		System.out.println(spieler.getAktuellerRaum().getLongDesciption());
	}

	/**
	 * Verarbeite einen gegebenen Befehl (führe ihn aus).
	 * 
	 * @param befehl
	 *            Der zu verarbeitende Befehl.
	 * @return 'true', wenn der Befehl das Spiel beendet, 'false' sonst.
	 */
	private boolean verarbeiteBefehl(Befehl befehl) {
		boolean moechteBeenden = false;

		if (befehl.istUnbekannt()) {
			System.out.println("Ich weiß nicht, was Sie meinen...");
			return false;
		}

		String befehlswort = befehl.gibBefehlswort();
		if (befehlswort.equalsIgnoreCase("help")) {
			hilfstextAusgeben();
		} else if (befehlswort.equalsIgnoreCase("go")) {
			wechsleRaum(befehl);
		} else if (befehlswort.equalsIgnoreCase("quit")) {
			moechteBeenden = beenden(befehl);
		} else if (befehlswort.equalsIgnoreCase("look")) {
			lookAround(befehl.gibZweitesWort());
		} else if (befehlswort.equalsIgnoreCase("take")) {
			nimmGegenstand(befehl.gibZweitesWort());
		} else if (befehlswort.equalsIgnoreCase("drop")) {
			legeGegenstandAb(befehl.gibZweitesWort());
		} else if (befehlswort.equalsIgnoreCase("inventory")) {
			System.out.println(spieler.showStatus());
		} else if (befehlswort.equalsIgnoreCase("eat")) {
			eat(befehl.gibZweitesWort());
		} else if (befehlswort.equalsIgnoreCase("heal")) {
			spieler.kleineHeilung(spieler);
		} else if (befehlswort.equalsIgnoreCase("hurt")) {
			spieler.leichtVerletzen(spieler);
		} else if (befehlswort.equalsIgnoreCase("stab")) {
			spieler.schwerVerletzen(spieler);
		} else if (befehlswort.equalsIgnoreCase("suicide")) {
			spieler.toeten(spieler);
		} else if (befehlswort.equalsIgnoreCase("use")) {
			nutzeLandschaft(befehl.gibZweitesWort());
		} else if (befehlswort.equalsIgnoreCase("smallRevival")) {
			spieler.kleineWiederbelebung(spieler);
		} else if (befehlswort.equalsIgnoreCase("largeRevial")) {
			spieler.grosseWiederbelebung(spieler);
		}
		return moechteBeenden;
	}

	public void eat(String name) {
		Gegenstand gs = spieler.eat(name);
		if (gs != null) {
			System.out.println(gs.getName() + " gegessen");
		} else {
			System.out.println("Gegenstand " + name + " existiert nicht oder ist nicht essbar!");
		}
	}

	public void nutzeLandschaft(String name) {
		Landscape ls = spieler.getAktuellerRaum().getLandschaft(name);
		if (ls != null) {
			ls.onUse(spieler);
		}
	}

	public void nimmGegenstand(String name) {
		Gegenstand gs = spieler.getAktuellerRaum().getGegenstand(name);
		if (gs != null) {
			if (spieler.gegenstandAufnehmen(gs)) {
				System.out.println(gs.getName() + " Aufgehoben");
			} else {
				System.out.println("Der Gegenstand " + gs.getName() + " ist zu schwer");
			}
		}
	}

	public void legeGegenstandAb(String name) {
		Gegenstand gs = spieler.gegenstandAblegen(name);
		if (gs != null) {
			System.out.println(gs.getName() + " abgelegt");
		} else {
			System.out.println("Den Gegenstand " + name + " gibt es in deinem Inventar nicht");
		}
	}

	// Implementierung der Benutzerbefehle:

	/**
	 * Gib Hilfsinformationen aus. Hier geben wir eine etwas alberne und unklare
	 * Beschreibung aus, sowie eine Liste der Befehlswörter.
	 */
	private void hilfstextAusgeben() {
		System.out.println("Sie haben sich verlaufen. Sie sind allein.");
		System.out.println("Sie irren auf dem Unigelände herum.");
		System.out.println();
		System.out.println("Ihnen stehen folgende Befehle zur Verfügung:");
		System.out.println("	" + parser.getBefehle());
	}

	private void lookAround(String item) {
		if (item != null) {
			Gegenstand gs = spieler.getGegenstand(item);
			gs = gs != null ? gs : spieler.getAktuellerRaum().getGegenstand(item);
			if (gs != null) {
				System.out.println(gs.getBeschreibung());
			} else {
				System.out.println("Diesen Gegenstand gibt es nicht in diesem Raum oder deinem Inventar");
			}
		} else {
			System.out.println(spieler.getAktuellerRaum().getLongDesciption());
		}
	}

	/**
	 * Versuche, den Raum zu wechseln. Wenn es einen Ausgang gibt, wechsele in den
	 * neuen Raum, ansonsten gib eine Fehlermeldung aus.
	 */
	private void wechsleRaum(Befehl befehl) {
		if (!befehl.hatZweitesWort()) {
			// Gibt es kein zweites Wort, wissen wir nicht, wohin...
			System.out.println("Wohin möchten Sie gehen?");
			return;
		}

		String richtung = befehl.gibZweitesWort();

		// Wir versuchen den Raum zu verlassen.
		Raum naechsterRaum = spieler.getAktuellerRaum().getAusgang(richtung);

		if (naechsterRaum == null) {
			System.out.println("Dort ist keine Tür!");
		} else {
			spieler.setAktuellerRaum(naechsterRaum);
			System.out.println(spieler.getAktuellerRaum().getLongDesciption());
			naechsterRaum.onEnterRoomEvent(spieler);
		}
	}

	/**
	 * "quit" wurde eingegeben. Überprüfe den Rest des Befehls, ob das Spiel
	 * wirklich beendet werden soll.
	 * 
	 * @return 'true', wenn der Befehl das Spiel beendet, 'false' sonst.
	 */
	private boolean beenden(Befehl befehl) {
		if (befehl.hatZweitesWort()) {
			System.out.println("Was soll beendet werden?");
			return false;
		} else {
			return true; // Das Spiel soll beendet werden.
		}
	}
}
