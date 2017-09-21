package location;

import java.util.ArrayList;
import java.util.LinkedList;

import character.Enemy;
import character.NPC;
import character.Player;
import item.Item;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.TextBox;
import main.Usefull;

/**
 * Diese Klasse modelliert Räume in der Welt von Zuul.
 * 
 * Ein "Raum" repräsentiert einen Ort in der virtuellen Landschaft des Spiels.
 * Ein Raum ist mit anderen Räumen über Ausgänge verbunden. Mögliche
 * Ausgänge liegen im Norden, Osten, Süden und Westen. Für jede Richtung
 * hält ein Raum eine Referenz auf den benachbarten Raum.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */
public class Room {
	private ArrayList<Door> ausgaenge = new ArrayList<Door>();
	private String beschreibung;
	private Image bg;
	protected GraphicsContext gc;
	private LinkedList<Item> gegenstaende = new LinkedList<Item>();
	private LinkedList<Enemy> gegner = new LinkedList<Enemy>();
	private Worldmap land;
	private ArrayList<Landscape> landschaft = new ArrayList<Landscape>();
	private LinkedList<NPC> npc = new LinkedList<NPC>();
	private TextBox textbox = TextBox.newTextBox();

	/**
	 * Erzeuge einen Raum. Ein Raum hat anfangs keine
	 * Ausgänge.
	 * 
	 * @param beschreibung
	 *            enth�lt eine Beschreibung
	 * @param land
	 *            Die Klasse die die Story und Objekte verwaltet
	 * @param gc
	 *            Graphicscontext mit dem der Raumhintergrund dargestellt wird
	 */
	public Room(String beschreibung, Worldmap land, Image bg, GraphicsContext gc) {
		this.beschreibung = beschreibung;
		this.land = land;
		this.bg = bg;
		this.gc = gc;
	}

	/**
	 * Gegenstand zum Raum hinzuf�gen
	 * @param gegenstand
	 * Der Gegenstand der hinzugef�gt werden soll
	 */
	public void addItem(Item gegenstand) {
		gegenstaende.add(gegenstand);
	}

	/**
	 * Gegner aus dem Raum entfernen
	 * @param gegner
	 * der zu erntfernende Gegner
	 */
	public void enterneGegner(Enemy gegner) {
		this.gegner.remove(gegner);
	}

	/**
	 * NPC aus dem Raum entfernen
	 * @param gegner
	 * der zu erntfernende NPC
	 */
	public void enterneNPC(NPC npc) {
		this.npc.remove(npc);
	}

	public String gegenstaendeToString() {
		StringBuilder sb = new StringBuilder("");
		for (Item gegenstand : gegenstaende) {
			sb.append(gegenstand.getName());
			sb.append(" ");
		}
		return sb.toString();
	}

	private String gegnerToString() {
		StringBuilder sb = new StringBuilder("");
		for (Enemy gn : gegner) {
			sb.append(gn.getName());
			sb.append(" ");
		}
		return sb.toString();
	}

	/*
	 * public Room getExit(String richtung) { return ausgaenge.get(richtung); }
	 */

	public ArrayList<Door> getAusgaenge() {
		return ausgaenge;
	}

	public Item getClosestItem(Point2D pos, int maxDist) {
		Item closest = null;
		double minDist = Double.MAX_VALUE;
		for (Item item : gegenstaende) {
			if (Usefull.intersects(pos.getX(), pos.getY(), maxDist, maxDist, item.getX(), item.getY(), item.getWidth(),
					item.getHeight())) {
				double dist = pos.distance(new Point2D(item.getX(), item.getY()));
				if (dist < minDist) {
					closest = item;
					minDist = dist;
				}
			}
		}
		return closest;
	}

	public Landscape getClosestLandscape(Point2D pos, int maxDist) {
		Landscape closest = null;
		double minDist = Double.MAX_VALUE;
		for (Landscape landscape : landschaft) {
			if (Usefull.intersects(pos.getX(), pos.getY(), maxDist, maxDist, landscape.getX(), landscape.getY(),
					landscape.getW(), landscape.getH())) {
				double dist = pos.distance(new Point2D(landscape.getX(), landscape.getY()));
				if (dist < minDist) {
					closest = landscape;
					minDist = dist;
				}
			}
		}
		return closest;
	}

	public Item getGegenstand(String name) {
		for (Item gs : gegenstaende) {
			if (gs.getName().equalsIgnoreCase(name)) {
				return gs;
			}
		}
		return null;
	}

	public LinkedList<Enemy> getGegnerList() {
		return gegner;
	}

	public Worldmap getLand() {
		return land;
	}

	public Landscape getLandschaft(String name) {
		for (Landscape ls : landschaft) {
			if (ls.getName().equalsIgnoreCase(name)) {
				return ls;
			}
		}
		return null;
	}

	public String getLongDesciption() {
		StringBuilder sb = new StringBuilder("");
		sb.append(gibBeschreibung());
		sb.append(System.getProperty("line.separator"));
		sb.append("Hier ist: ");
		sb.append(landschaftToString());
		sb.append(System.getProperty("line.separator"));
		sb.append("Gegenstände: ");
		sb.append(gegenstaendeToString());
		sb.append(System.getProperty("line.separator"));
		sb.append("Gegner: ");
		sb.append(gegnerToString());
		sb.append(System.getProperty("line.separator"));
		sb.append("NPC's: ");
		sb.append(npcsToString());
		return sb.toString();
	}

	public NPC getNPC(String name) {
		for (NPC np : npc) {
			if (np.getName().equalsIgnoreCase(name)) {
				return np;
			}
		}
		return null;
	}

	/**
	 * @return Die Beschreibung dieses Raums.
	 */
	public String gibBeschreibung() {
		return beschreibung;
	}

	public void landschaftBauen(Landscape landscape) {
		landschaft.add(landscape);
		landscape.setRaum(this);
	}

	public void landschaftEntfernen(String name) {
		for (Landscape ls : landschaft) {
			if (ls.getName().equalsIgnoreCase(name)) {
				landschaft.remove(ls);
				break;
			}
		}
	}

	public String landschaftToString() {
		StringBuilder sb = new StringBuilder("");
		for (Landscape landscape : landschaft) {
			sb.append(landscape.getName());
			sb.append(" ");
		}
		return sb.toString();
	}

	private String npcsToString() {
		StringBuilder sb = new StringBuilder("");
		for (NPC np : npc) {
			sb.append(np.getName());
			sb.append(" ");
		}
		return sb.toString();
	}

	public void onEnterRoomEvent(Player spieler) {
		for (int i = landschaft.size() - 1; i >= 0; i--) {
			Landscape ls = landschaft.get(i);
			ls.onEnterRoom(spieler);
		}
	}

	public Item removeItem(String name) {
		for (Item gs : gegenstaende) {
			if (gs.getName().equalsIgnoreCase(name)) {
				gegenstaende.remove(gs);
				return gs;
			}
		}
		return null;
	}

	/**
	 * Definiere die Ausgänge dieses Raums. Jede Richtung führt entweder in einen
	 * anderen Raum oder ist 'null' (kein Ausgang).
	 */
	public void setzeAusgang(Image image, GraphicsContext gc, Point2D pos, Point2D nextPos, Room nextRoom) {
		ausgaenge.add(new Door(image, gc, pos, nextPos, nextRoom));
	}

	public void setzeGegner(Enemy gegner) {
		this.gegner.add(gegner);
	}

	public void setzeNPC(NPC npc) {
		this.npc.add(npc);
	}

	public void show() {
		gc.drawImage(bg, 0, 0);

		for (Item gs : gegenstaende) {
			gs.show();
		}

		for (Landscape ls : landschaft) {
			ls.show();
		}

		for (NPC np : npc) {
			np.show();
		}

		for (Enemy g : gegner) {
			g.show();
		}

		for (Door door : ausgaenge) {
			door.show();
		}
	}

	public void update(Player player) {
		for (int i = gegner.size() - 1; i >= 0; i--) {
			Enemy g = gegner.get(i);
			g.move(player);
			if (!g.update()) {
				g.dropItems();
				gegner.remove(g);
			}
		}

		Point2D pos = player.getPosition();
		pos = new Point2D(pos.getX() - player.getWidth() / 2, pos.getY() - player.getHeight() / 2);

		for (Door door : ausgaenge) {
			if (Usefull.intersects(door.getX(), door.getY(), door.getWidth(), door.getHeight(), pos.getX(), pos.getY(),
					player.getWidth(), player.getHeight())) {
				door.changeRoom(player);
				textbox.addText(player.getRoom().getLongDesciption());
				break;
			}
		}
	}
}
