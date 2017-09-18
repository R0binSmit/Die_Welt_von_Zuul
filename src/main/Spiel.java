package main;

import java.util.HashMap;
import java.util.LinkedList;

import befehlsVerarbeitung.Befehl;
import befehlsVerarbeitung.Parser;
import character.NPC;
import character.Spieler;
import gegenstand.Gegenstand;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
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
	private HashMap<String, Spieler> party = new HashMap<String, Spieler>();
	private HashMap<KeyCode, Runnable> actions = new HashMap<KeyCode, Runnable>();
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
		party.put("Dave", new Spieler("Dave", 100, land.getStartpoint(), 20, 20,
				new Image(ZuulUI.class.getResourceAsStream("/Bilder/Dave.png")), null));
		// party.put("Egon", new Spieler("Egon", 100, land.getStartpoint(), 40, 40, new
		// Image("../Bilder/Dave.png"), null));
		spieler = party.get("Dave");
		spieler.setGeld(300);

		setActions();
		willkommenstextAusgeben();
	}

	private void setActions() {
		actions.put(KeyCode.W, () -> {
			spieler.move(KeyCode.W);
		});

		actions.put(KeyCode.A, () -> {
			spieler.move(KeyCode.A);
		});

		actions.put(KeyCode.S, () -> {
			spieler.move(KeyCode.S);
		});

		actions.put(KeyCode.D, () -> {
			spieler.move(KeyCode.D);
		});
	}

	/**
	 * Die Hauptmethode zum Spielen. Läuft bis zum Ende des Spiels in einer
	 * Schleife.
	 */
	public void update(HashMap<KeyCode, Boolean> keys) {
		for (KeyCode key : keys.keySet()) {
			try {
				actions.get(key).run();
			} catch (Exception ex) {
			}
		}

		ZuulUI.gc.clearRect(0, 0, ZuulUI.gc.getCanvas().getWidth(), ZuulUI.gc.getCanvas().getHeight());
		spieler.getAktuellerRaum().show();
		spieler.show();
	}

	/**
	 * Einen Begrüßungstext für den Spieler ausgeben.
	 */
	private void willkommenstextAusgeben() {
		System.out.println("Willkommen zu Zuul!");
		System.out.println("Tippen sie 'help', wenn Sie Hilfe brauchen.");
		System.out.println();
		System.out.println("Seltsame Ereignisse haben ihre Schatten vorausgeworfen."
				+ System.getProperty("line.separator")
				+ "Über Nacht viel der Goldpreis auf 3 US-Dollar pro Feinunze und die Menscheit strebte nach einen neuen Wertanlage:"
				+ System.getProperty("line.separator") + "Lutetium!" + System.getProperty("line.separator")
				+ "Niemand weiß, was dann geschah. Vielleicht gruben wir zu tief, vielleicht ließen wir uns auf falsche Götzen ein,"
				+ System.getProperty("line.separator") + "fest steht, dass das Ende der Welt über uns kam."
				+ System.getProperty("line.separator")
				+ "Nur hatten die Propheten keine Ahnung, wie seltsam das Ende werden würde."
				+ System.getProperty("line.separator")
				+ "Jetzt stehst du alleine vor der Universität. In der Ferne schnurrt eine Katze."
				+ System.getProperty("line.separator") + "");
		System.out.println();
		// System.out.println(spieler.getAktuellerRaum().getLongDesciption());
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
			if (spieler.getZustand().isMovable())
				wechsleRaum(befehl);
			else
				System.out.println("Sie können sich nicht bewegen!");
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
		} else if (befehlswort.equalsIgnoreCase("changePlayer")) {
			changePlayer(befehl.gibZweitesWort());
		}
		return moechteBeenden;
	}

	public void changePlayer(String name) {
		Spieler zw = party.get(name);
		if (zw != null) {
			spieler = zw;
			System.out.println("du spielst nun als " + name + ".");
		} else {
			System.out.println("Diesen Spieler gibt es nicht!");
		}
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
			LinkedList<character.Character> spielerGroup = new LinkedList<character.Character>();
			spielerGroup.add(spieler);
			kampfSystem = new KampfSystem(spielerGroup, naechsterRaum.getGegnerList());
			naechsterRaum.onEnterRoomEvent(spieler);
			if (kampfSystem.checkKampfStart(naechsterRaum)) {
				kampfSystem.startKampf();
			}
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