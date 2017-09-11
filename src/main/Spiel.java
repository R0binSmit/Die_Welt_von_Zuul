package main;

import java.util.LinkedList;

import befehlsVerarbeitung.Befehl;
import befehlsVerarbeitung.Parser;
import character.NPC;
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
 * Sie legt alle R�ume und einen Parser an und startet das Spiel. Sie wertet
 * auch die Befehle aus, die der Parser liefert, und sorgt f�r ihre Ausf�hrung.
 * 
 * @author Michael K�lling und David J. Barnes
 * @version 2008.03.30
 */

public class Spiel {
	private Parser parser;
	private Spieler spieler;
	private Landkarte land;
	private KampfSystem kampfSystem;

	/**
	 * Erzeuge ein Spiel und initialisiere die interne Raumkarte.
	 */
	public Spiel() {
		land = new Landkarte();
		land.raeumeAnlegen();
		parser = new Parser();
		spieler = new Spieler("Egon", 100, land.getStartpoint(), null); // das Spiel startet draussen
		spieler.setGeld(300);
	}

	/**
	 * Die Hauptmethode zum Spielen. L�uft bis zum Ende des Spiels in einer
	 * Schleife.
	 */
	public void spielen() {
		willkommenstextAusgeben();

		// Die Hauptschleife. Hier lesen wir wiederholt Befehle ein
		// und f�hren sie aus, bis das Spiel beendet wird.

		boolean beendet = false;
		while (!beendet) {
			Befehl befehl = parser.liefereBefehl();
			beendet = verarbeiteBefehl(befehl);
		}
		System.out.println("Danke f�r dieses Spiel. Auf Wiedersehen.");
	}

	/**
	 * Einen Begr��ungstext f�r den Spieler ausgeben.
	 */
	private void willkommenstextAusgeben() {
		System.out.println("Willkommen zu Zuul!");
		System.out.println("Tippen sie 'help', wenn Sie Hilfe brauchen.");
		System.out.println();
		System.out.println("Seltsame Ereignisse haben ihre Schatten vorausgeworfen." + System.getProperty("line.separator") +
				"�ber Nacht viel der Goldpreis auf 3 US-Dollar pro Feinunze und die Menscheit strebte nach einen neuen Wertanlage:" + System.getProperty("line.separator") +
				"Lutetium!" + System.getProperty("line.separator") +
				"Niemand wei�, was dann geschah. Vielleicht gruben wir zu tief, vielleicht lie�en wir uns auf falsche G�tzen ein," + System.getProperty("line.separator") +
				"fest steht, dass das Ende der Welt �ber uns kam." + System.getProperty("line.separator") +
				"Nur hatten die Propheten keine Ahnung, wie seltsam das Ende werden w�rde." + System.getProperty("line.separator") +
				"Jetzt stehst du alleine vor der Universit�t. In der Ferne schnurrt eine Katze." + System.getProperty("line.separator") +
				"");
		System.out.println();
		System.out.println(spieler.getAktuellerRaum().getLongDesciption());
	}

	/**
	 * Verarbeite einen gegebenen Befehl (f�hre ihn aus).
	 * 
	 * @param befehl
	 *            Der zu verarbeitende Befehl.
	 * @return 'true', wenn der Befehl das Spiel beendet, 'false' sonst.
	 */
	private boolean verarbeiteBefehl(Befehl befehl) {
		boolean moechteBeenden = false;

		if (befehl.istUnbekannt()) {
			System.out.println("Ich wei� nicht, was Sie meinen...");
			return false;
		}

		String befehlswort = befehl.gibBefehlswort();
		if (befehlswort.equalsIgnoreCase("help")) {
			hilfstextAusgeben();
		} else if (befehlswort.equalsIgnoreCase("go")) {
			if(spieler.getZustand().isMovable())
			wechsleRaum(befehl);
			else
				System.out.println("Sie k�nnen sich nicht bewegen!");
		} else if (befehlswort.equalsIgnoreCase("quit")) {
			moechteBeenden = beenden(befehl);
		} else if (befehlswort.equalsIgnoreCase("look")) {
			lookAround(befehl.gibZweitesWort());
		} else if (befehlswort.equalsIgnoreCase("take")) {
			nimmGegenstand(befehl.gibZweitesWort());
		} else if (befehlswort.equalsIgnoreCase("drop")) {
			legeGegenstandAb(befehl.gibZweitesWort());
		} else if (befehlswort.equalsIgnoreCase("inventory")) {
			System.out.println(spieler.getInventory());
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
		} else if (befehlswort.equalsIgnoreCase("talk")) {
			talk(befehl.gibZweitesWort());
		} else if (befehlswort.equalsIgnoreCase("status")) {
			System.out.println(spieler.getStatus());
		}
		return moechteBeenden;
	}
	
	public void talk(String name) {
		NPC npc = spieler.getAktuellerRaum().getNPC(name);
		if (npc != null) {
			npc.interagieren(spieler);
		} else {
			System.out.println("Diesen NPC gibt es nicht!");
		}
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
				spieler.getAktuellerRaum().gegenstandAufheben(name);
				System.out.println(gs.getName() + " Aufgehoben");
			} else {
				System.out.println("Der Gegenstand " + gs.getName() + " ist zu schwer");
			}
		}
	}

	public void legeGegenstandAb(String name) {
		Gegenstand gs = spieler.gegenstandAblegen(name);
		if (gs != null) {
			spieler.getAktuellerRaum().gegenstandAblegen(gs);
			System.out.println(gs.getName() + " abgelegt");
		} else {
			System.out.println("Den Gegenstand " + name + " gibt es in deinem Inventar nicht");
		}
	}

	// Implementierung der Benutzerbefehle:

	/**
	 * Gib Hilfsinformationen aus. Hier geben wir eine etwas alberne und unklare
	 * Beschreibung aus, sowie eine Liste der Befehlsw�rter.
	 */
	private void hilfstextAusgeben() {
		System.out.println("Sie haben sich verlaufen. Sie sind allein.");
		System.out.println("Sie irren auf dem Unigel�nde herum.");
		System.out.println();
		System.out.println("Ihnen stehen folgende Befehle zur Verf�gung:");
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
			System.out.println("Wohin m�chten Sie gehen?");
			return;
		}

		String richtung = befehl.gibZweitesWort();

		// Wir versuchen den Raum zu verlassen.
		Raum naechsterRaum = spieler.getAktuellerRaum().getAusgang(richtung);

		if (naechsterRaum == null) {
			System.out.println("Dort ist keine T�r!");
		} else {
			spieler.setAktuellerRaum(naechsterRaum);
			System.out.println(spieler.getAktuellerRaum().getLongDesciption());
			LinkedList<character.Character> spielerGroup = new LinkedList<character.Character>();
			spielerGroup.add(spieler);
			kampfSystem = new KampfSystem(spielerGroup, naechsterRaum.getGegnerList());
			naechsterRaum.onEnterRoomEvent(spieler);
			if(kampfSystem.checkKampfStart(naechsterRaum)) {
				kampfSystem.startKampf();
			}
		}
	}

	/**
	 * "quit" wurde eingegeben. �berpr�fe den Rest des Befehls, ob das Spiel
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