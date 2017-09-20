package main;

import java.util.HashMap;
import java.util.LinkedList;

import character.NPC;
import character.Player;
import item.Item;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import location.Worldmap;
import location.Landscape;
import location.Room;

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
	private Player player;
	private Worldmap land;
	private TextBox textbox;
	private GraphicsContext gc;

	/**
	 * Erzeuge ein Spiel und initialisiere die interne Raumkarte.
	 */
	public Game(GraphicsContext gc) {
		land = new Worldmap(gc);
		land.raeumeAnlegen();
		player = new Player("Dave", "Ich liebe dich", land.getStartpoint(), 20, 20, Usefull.linkToImage("/Bilder/Dave.png"), gc, null);
		this.gc = gc;
		textbox = new TextBox(gc);

		setActions();
		outputWelcomeText();
	}

	private void setActions() {
		actions.put(KeyCode.W, () -> {
			player.move(KeyCode.W);
		});

		actions.put(KeyCode.A, () -> {
			player.move(KeyCode.A);
		});

		actions.put(KeyCode.S, () -> {
			player.move(KeyCode.S);
		});

		actions.put(KeyCode.D, () -> {
			player.move(KeyCode.D);
		});

		actions.put(KeyCode.E, () -> {
			player.interagieren();
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

		player.getRoom().show();
		player.getRoom().update(player);

		Point2D pos = player.getPosition();
		pos = new Point2D(pos.getX() - player.getWidth() / 2, pos.getY() - player.getHeight() / 2);
		HashMap<String, Room> exits = player.getRoom().getAusgaenge();
		if (exits.get("north") != null) {
			gc.fillRect(300, 0, 200, 50);
			if (Usefull.intersects(300, 0, 200, 50, pos.getX(), pos.getY(), player.getWidth(), player.getHeight())) {
				player.setPosition(new Point2D(player.getPosition().getX(), 700));
				changeRoom("north");
			}
		}

		if (exits.get("east") != null) {
			gc.fillRect(750, 300, 50, 200);
			if (Usefull.intersects(750, 300, 50, 200, pos.getX(), pos.getY(), player.getWidth(), player.getHeight())) {
				player.setPosition(new Point2D(100, player.getPosition().getY()));
				changeRoom("east");
			}
		}

		if (exits.get("south") != null) {
			gc.fillRect(300, 750, 200, 50);
			if (Usefull.intersects(300, 750, 200, 50, pos.getX(), pos.getY(), player.getWidth(), player.getHeight())) {
				player.setPosition(new Point2D(player.getPosition().getX(), 100));
				changeRoom("south");
			}
		}

		if (exits.get("west") != null) {
			gc.fillRect(0, 300, 50, 200);
			if (Usefull.intersects(0, 300, 50, 200, pos.getX(), pos.getY(), player.getWidth(), player.getHeight())) {
				player.setPosition(new Point2D(700, player.getPosition().getY()));
				changeRoom("west");
			}
		}

		player.show();
		textbox.refresh();
	}

	/**
	 * Einen Begrüßungstext für den Spieler ausgeben.
	 */
	private void outputWelcomeText() {
		textbox.addText("Willkommen zu Zuul!");
		textbox.addText("Tippen sie 'help', wenn Sie Hilfe brauchen.");
		textbox.addText();
		textbox.addText("Seltsame Ereignisse haben ihre Schatten vorausgeworfen." + System.getProperty("line.separator")
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
	public void processCommand(KeyCode key) {
		if (key == KeyCode.H) {
			printHelp();
		}
	}

	public void talk(String name) {
		NPC npc = player.getRoom().getNPC(name);
		if (npc != null) {
			npc.interact(player);
		} else {
			System.out.println("Diesen NPC gibt es nicht!");
		}
	}

	public void nutzeLandschaft(String name) {
		Landscape ls = player.getRoom().getLandschaft(name);
		if (ls != null) {
			ls.onUse(player);
		}
	}

	public void nimmGegenstand(String name) {
		Item item = player.getRoom().getGegenstand(name);
		if (item != null) {
			player.pickUpItem(item);
			player.getRoom().removeItem(name);
			System.out.println(item.getName() + " Aufgehoben");
		}
	}

	public void legeGegenstandAb(String name) {
		Item item = player.dropItem(name);
		if (item != null) {
			player.getRoom().addItem(item);
			System.out.println(item.getName() + " abgelegt");
		} else {
			System.out.println("Den Gegenstand " + name + " gibt es in deinem Inventar nicht");
		}
	}

	// Implementierung der Benutzerbefehle:

	/**
	 * Gib Hilfsinformationen aus. Hier geben wir eine etwas alberne und unklare
	 * Beschreibung aus, sowie eine Liste der Befehlswörter.
	 */
	private void printHelp() {
		textbox.addText("Sie haben sich verlaufen. Sie sind allein.");
		textbox.addText("Sie irren auf dem Unigelände herum.");
	}

	/**
	 * Versuche, den Raum zu wechseln. Wenn es einen Ausgang gibt, wechsele in den
	 * neuen Raum, ansonsten gib eine Fehlermeldung aus.
	 */
	private void changeRoom(String direction) {
		Room nextRoom = player.getRoom().getExit(direction);
		player.setRoom(nextRoom);
		textbox.addText(player.getRoom().getLongDesciption());
		LinkedList<character.Character> spielerGroup = new LinkedList<character.Character>();
		spielerGroup.add(player);
		nextRoom.onEnterRoomEvent(player);
	}
}