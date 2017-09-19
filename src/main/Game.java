package main;

import java.util.HashMap;
import java.util.LinkedList;

import befehlsVerarbeitung.Befehl;
import befehlsVerarbeitung.Parser;
import character.NPC;
import character.Player;
import item.Item;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
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

public class Game {
	private HashMap<KeyCode, Runnable> actions = new HashMap<KeyCode, Runnable>();
	private Player spieler;
	private Landkarte land;
	private KampfSystem kampfSystem;
	private Textverwaltung tv;
	private GraphicsContext gc;

	/**
	 * Erzeuge ein Spiel und initialisiere die interne Raumkarte.
	 */
	public Game(GraphicsContext gc) {
		land = new Landkarte(gc);
		land.raeumeAnlegen();
		parser = new Parser();
		spieler = new Player("Dave", 100, land.getStartpoint(), 20, 20, Usefull.linkToImage("/Bilder/Dave.png"), gc,
				null);
		spieler.setGeld(300);
		this.gc = gc;
		tv = new Textverwaltung(gc);

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

		actions.put(KeyCode.E, () -> {
			spieler.interagieren();
		});

		actions.put(KeyCode.H, () -> {

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

		gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

		spieler.getAktuellerRaum().show();

		Point2D pos = spieler.getPos();
		pos = new Point2D(pos.getX() - spieler.getW() / 2, pos.getY() - spieler.getH() / 2);
		HashMap<String, Raum> ausgeange = spieler.getAktuellerRaum().getAusgaenge();
		if (ausgeange.get("north") != null) {
			gc.fillRect(300, 0, 200, 50);
			if (Usefull.intersects(300, 0, 200, 50, pos.getX(), pos.getY(), spieler.getW(), spieler.getH())) {
				spieler.setPos(new Point2D(spieler.getPos().getX(), 700));
				wechsleRaum(new Befehl(null, "north"));
			}
		}

		if (ausgeange.get("east") != null) {
			gc.fillRect(750, 300, 50, 200);
			if (Usefull.intersects(750, 300, 50, 200, pos.getX(), pos.getY(), spieler.getW(), spieler.getH())) {
				spieler.setPos(new Point2D(100, spieler.getPos().getY()));
				wechsleRaum(new Befehl(null, "east"));
			}
		}

		if (ausgeange.get("south") != null) {
			gc.fillRect(300, 750, 200, 50);
			if (Usefull.intersects(300, 750, 200, 50, pos.getX(), pos.getY(), spieler.getW(), spieler.getH())) {
				spieler.setPos(new Point2D(spieler.getPos().getX(), 100));
				wechsleRaum(new Befehl(null, "south"));
			}
		}

		if (ausgeange.get("west") != null) {
			gc.fillRect(0, 300, 50, 200);
			if (Usefull.intersects(0, 300, 50, 200, pos.getX(), pos.getY(), spieler.getW(), spieler.getH())) {
				spieler.setPos(new Point2D(700, spieler.getPos().getY()));
				wechsleRaum(new Befehl(null, "west"));
			}
		}

		spieler.show();
		tv.refresh();
	}

	/**
	 * Einen Begrüßungstext für den Spieler ausgeben.
	 */
	private void willkommenstextAusgeben() {
		tv.addText("Willkommen zu Zuul!");
		tv.addText("Tippen sie 'help', wenn Sie Hilfe brauchen.");
		tv.addText();
		tv.addText("Seltsame Ereignisse haben ihre Schatten vorausgeworfen." + System.getProperty("line.separator")
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
	public void verarbeiteBefehl(KeyCode key) {
		if (key == KeyCode.H) {
			hilfstextAusgeben();
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
		Item gs = spieler.eat(name);
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
		Item gs = spieler.getAktuellerRaum().getGegenstand(name);
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
		Item gs = spieler.gegenstandAblegen(name);
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
		tv.addText("Sie haben sich verlaufen. Sie sind allein.");
		tv.addText("Sie irren auf dem Unigelände herum.");
	}

	/**
	 * Versuche, den Raum zu wechseln. Wenn es einen Ausgang gibt, wechsele in den
	 * neuen Raum, ansonsten gib eine Fehlermeldung aus.
	 */
	private void wechsleRaum(Befehl befehl) {
		String richtung = befehl.gibZweitesWort();
		Raum naechsterRaum = spieler.getAktuellerRaum().getAusgang(richtung);
		spieler.setAktuellerRaum(naechsterRaum);
		tv.addText(spieler.getAktuellerRaum().getLongDesciption());
		LinkedList<character.Character> spielerGroup = new LinkedList<character.Character>();
		spielerGroup.add(spieler);
		// kampfSystem = new KampfSystem(spielerGroup, naechsterRaum.getGegnerList());
		naechsterRaum.onEnterRoomEvent(spieler);
		// if (kampfSystem.checkKampfStart(naechsterRaum)) {
		// kampfSystem.startKampf();
		// }
	}
}